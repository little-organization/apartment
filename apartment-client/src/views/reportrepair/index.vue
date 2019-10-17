<template>
  <div class="app-container">
    <div class="filter-container">
      <el-date-picker v-model="createtimeLimit" type="datetimerange" :picker-options="pickerOptions" range-separator="至" start-placeholder="【创建】开始日期" end-placeholder="【创建】结束日期" @change="resolveCreateLimit()" @keyup.enter.native="handleFilter" />
      <el-date-picker v-model="updatetimeLimit" type="datetimerange" :picker-options="pickerOptions" range-separator="至" start-placeholder="【更新】开始日期" end-placeholder="【更新】结束日期" @change="resolveUpdateLimit()" @keyup.enter.native="handleFilter" />
      <el-input v-model.trim="listQuery.username" placeholder="租户姓名" style="width: 150px;" class="filter-item" @keyup.enter.native="handleFilter" />
      <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">
        搜索
      </el-button>
      <!-- <el-button v-waves :loading="downloadLoading" class="filter-item" type="primary" icon="el-icon-download" @click="handleDownload">
        导出
      </el-button> -->
    </div>

    <el-table
      :key="tableKey"
      v-loading="listLoading"
      :data="list"
      border
      fit
      stripe
      highlight-current-row
      style="width: 100%;"
      @filter-change="filterHanderChange"
    >
      <el-table-column label="ID" type="index" align="center" width="60" />
      <el-table-column label="报修人" prop="username" align="center">
        <template slot-scope="scope">
          <el-tooltip class="item" effect="light" content="点击查看租户信息" placement="top">
            <el-button type="text" size="mini" close-transition @click="watchUserData(scope.row, 1)">{{ scope.row.username }}</el-button>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column label="报修描述内容" prop="content" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.content }}</span>
        </template>
      </el-table-column>
      <el-table-column label="报修公寓地址" prop="apartmentAddress" align="center">
        <template slot-scope="scope">
          <el-tooltip class="item" effect="light" content="点击查看公寓信息" placement="top">
            <el-button type="text" size="mini" close-transition @click="watchApartmentData(scope.row)"><span>{{ scope.row.apartmentAddress }}</span></el-button>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column label="报修文件" prop="hasFile" column-key="hasFile" align="center" :filter-multiple="false" :filter-method="filterHasFile" :filters="hasFileList" filter-placement="bottom-end">
        <template slot-scope="scope">
          <el-tooltip class="item" effect="light" :content="scope.row.hasfile === 0 ? '' : '点击查看报修文件' " placement="top">
            <el-button :disabled="scope.row.hasfile !== 1" :type="scope.row.hasfile === 0 ? 'info' : 'primary'" size="mini" close-transition @click="watchFileData(scope.row)">{{ scope.row.hasfile===1 ? '有文件' : '无文件' }}</el-button>
          </el-tooltip>
        </template>
      </el-table-column>
      <!-- <el-table-column label="维修状态" prop="status" column-key="status" align="center" :filter-method="filterStatus" :filters="statusList" filter-placement="bottom-end">
        <template slot-scope="scope">
          <el-tag>{{ scope.row.status }}</el-tag>
        </template>
      </el-table-column> -->
      <el-table-column label="维修状态" prop="status" column-key="status" width="110px" align="center" :filter-method="filterStatus" :filters="statusList" filter-placement="bottom-left">
        <template slot-scope="scope">
          <el-tooltip v-if="checkPermission(['超级管理员'])" class="item" effect="light" content="点击更改修理状态" placement="right">
            <el-button type="success" size="mini" close-transition @click.native="changeRepairStatus(scope.row)">
              <span>{{ scope.row.status }}</span>
            </el-button>
          </el-tooltip>
          <el-tag v-else>{{ scope.row.status }}</el-tag>
        </template>
      </el-table-column>
      <!-- <el-table-column label="领取任务" prop="repairId" column-key="repairId" align="center" :filter-multiple="false" :filter-method="filterRepairId" :filters="repairIdList" filter-placement="bottom-end">
        <template slot-scope="scope">
          <el-tooltip class="item" effect="light" :content="scope.row.repairId === 0 ? '点击领取任务(仅售后人员)' : '任务已领取,点击查看售后人员信息' " placement="top">
            <el-button :type="scope.row.repairId === 0 ? 'info' : 'success'" size="mini" @click="scope.row.repairId === 0 ? changeRepairIdInRepairs(scope.row) : watchUserData(scope.row, 2)">{{ scope.row.repairId === 0 ? '未领取' : '已领取' }}</el-button>
          </el-tooltip>
        </template>
      </el-table-column> -->
      <el-table-column label="创建时间" prop="createtime" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.createtime | parseTime }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新时间" prop="conductTime" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.conductTime | parseTime }}</span>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页插件 -->
    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />
    <!-- 修改报修状态 -->
    <el-dialog title="更改维修状态" :visible.sync="dialogStatusVisible">
      <el-form ref="userForm" :model="temp" label-position="center" label-width="37%">
        <el-form-item label="请选择状态:" prop="status">
          <el-select v-model="temp.status" placeholder="请选择状态">
            <el-option v-for="item in statusList" :key="item.text" :label="item.value" :value="item.value">
              <span style="float: left">{{ item.value }}</span>
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogStatusVisible = false">
          取消
        </el-button>
        <el-button :disabled="confirmUpdate" type="primary" @click="updateRepairStatusData()">
          确认修改
        </el-button>
      </div>
    </el-dialog>
    <!-- 租户信息查看 -->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogUserVisible">
      <el-table v-if="userinfo!=null" v-loading="userLoading" :data="[userinfo]" border fit highlight-current-row>
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-form label-position="left" inline class="demo-table-expand">
              <el-form-item label="租户ID">
                <span>{{ props.row.id }}</span>
              </el-form-item>
              <el-form-item label="租户姓名">
                <span>{{ props.row.name }}</span>
              </el-form-item>
              <el-form-item label="联系电话">
                <span>{{ props.row.phone }}</span>
              </el-form-item>
              <el-form-item label="租户性别">
                <span>{{ props.row.sex===0?'女':'男' }}</span>
              </el-form-item>
              <el-form-item label="证件类型">
                <span>{{ props.row.idType }}</span>
              </el-form-item>
              <el-form-item label="证件号码">
                <span>{{ props.row.idNumber }}</span>
              </el-form-item>
              <el-form-item label="租住状态">
                <span>{{ props.row.isLive===0?'未入住':'已入住' }}</span>
              </el-form-item>
            </el-form>
          </template>
        </el-table-column>
        <el-table-column prop="id" label="租户ID" />
        <el-table-column prop="name" label="租户姓名" />
        <el-table-column prop="phone" label="租户手机号" />
      </el-table>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogUserVisible = false">
          关闭
        </el-button>
      </div>
    </el-dialog>
    <!-- 查看公寓信息 -->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogApartmentVisible">
      <el-table v-if="apartmentinfo!=null" v-loading="apartmentLoading" :data="[apartmentinfo]" border fit highlight-current-row>
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-form label-position="left" inline class="demo-table-expand">
              <el-form-item label="户型">
                <span>{{ props.row.houseType }}</span>
              </el-form-item>
              <el-form-item label="面积">
                <span>{{ props.row.roomArea }}m&sup2;</span>
              </el-form-item>
              <el-form-item label="朝向">
                <el-tag type="success" close-transition>{{ props.row.face }}</el-tag>
              </el-form-item>
              <el-form-item label="标价(￥)">
                <span>{{ props.row.price }}元</span>
              </el-form-item>
              <el-form-item label="支付模式">
                <el-tag type="success" close-transition>{{ props.row.pattern }}</el-tag>
              </el-form-item>
              <el-form-item label="地址">
                <span>{{ props.row.address }}</span>
              </el-form-item>
            </el-form>
          </template>
        </el-table-column>
        <el-table-column prop="houseType" label="户型" />
        <el-table-column prop="price" label="标价(￥)" />
        <el-table-column prop="pattern" label="支付模式" />
      </el-table>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogApartmentVisible = false">
          关闭
        </el-button>
      </div>
    </el-dialog>
    <!-- 查看文件 -->
    <el-dialog :title="textMap[dialogStatus]" width="40%" :visible.sync="dialogFileVisible">
      <viewer ref="viewer" :images="images" class="viewer" @inited="inited">
        <img v-for="src in images" :key="src" :src="src" class="avatar">
      </viewer>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFileVisible = false">
          关闭
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
// image
import { fetchList, statusList, updateRepair, getImages } from '@/api/repairs'
import { apartmentById } from '@/api/apartment'
import { userInfoById } from '@/api/apartmentuser'
import { repairInfoById } from '@/api/apartmentrepair'
import waves from '@/directive/waves' // waves directive
import { mapGetters } from 'vuex'
import { parseTime } from '@/utils'
import Pagination from '@/components/Pagination' // secondary package based on el-pagination
import permission from '@/directive/permission/index.js' // 权限判断指令
import checkPermission from '@/utils/permission' // 权限判断函数

export default {
  name: 'ComplexTable',
  components: { Pagination },
  directives: { waves, permission },
  data() {
    return {
      hasFileList: [
        { text: '有文件', value: 1 },
        { text: '无文件', value: 0 }
      ],
      repairIdList: [
        { text: '已领取', value: 1 },
        { text: '未领取', value: 0 }
      ],
      statusList: [
        { text: '未修理', value: '未修理' },
        { text: '进行中', value: '进行中' },
        { text: '已完成', value: '已完成' }
      ],
      createtimeLimit: null,
      updatetimeLimit: null,
      userListLoading: false,
      confirmUpdate: false,
      images: [],
      userinfo: {
        id: null,
        name: null,
        sex: null,
        phone: '',
        idNumber: '',
        idType: '',
        isLive: null
      },
      apartmentinfo: {
        id: null,
        userid: null,
        roomArea: '',
        houseType: '',
        face: '',
        price: '',
        pattern: '',
        address: '',
        status: 0
      },
      tableKey: 0,
      list: null,
      total: 1,
      listLoading: false,
      listQuery: {
        page: 1,
        limit: 10,
        username: null,
        createTimeBefore: null,
        createTimeAfter: null,
        conductTimeBefore: null,
        conductTimeAfter: null,
        hasFileSet: null,
        statusSet: null,
        repairId: -1
      },
      temp: {
        id: null,
        userid: null,
        username: '',
        apartmentid: '',
        apartmentAddress: '',
        status: '',
        content: '',
        createtime: '',
        conductTime: '',
        hasFile: null,
        repairId: null
      },
      dialogStatus: '',
      textMap: {
        userWatch: '查看租户信息',
        aftersaleWatch: '查看售后人员信息',
        apartmentWatch: '查看公寓租户信息',
        fileWatch: '查看报修文件'
      },
      // 查看租户
      userLoading: false,
      dialogUserVisible: false,
      // 查看公寓
      apartmentLoading: false,
      dialogApartmentVisible: false,
      dialogStatusVisible: false,
      // 查看文件
      dialogFileVisible: false,
      downloadLoading: false,
      pickerOptions: {
        shortcuts: [{
          text: '最近一天',
          onClick(picker) {
            const end = new Date()
            const start = new Date()
            start.setTime(start.getTime() - 3600 * 1000 * 24)
            picker.$emit('pick', [start, end])
          }
        }, {
          text: '最近一周',
          onClick(picker) {
            const end = new Date()
            const start = new Date()
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7)
            picker.$emit('pick', [start, end])
          }
        }, {
          text: '最近一个月',
          onClick(picker) {
            const end = new Date()
            const start = new Date()
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30)
            picker.$emit('pick', [start, end])
          }
        }, {
          text: '最近三个月',
          onClick(picker) {
            const end = new Date()
            const start = new Date()
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90)
            picker.$emit('pick', [start, end])
          }
        }]
      }
    }
  },
  computed: {
    ...mapGetters([
      'userInfo'
    ])
  },
  created() {
    this.getList()
    this.getStatusList()
  },
  methods: {
    // 权限验证函数
    checkPermission,
    // 文件查看器
    inited(viewer) {
      this.$viewer = viewer
    },
    // 获取报修的数据
    getList() {
      this.listLoading = true
      fetchList(this.listQuery).then(response => {
        this.list = response.data.records
        this.total = response.data.total
        this.listLoading = false
      })
    },
    // 获取修理状态所有类型
    getStatusList() {
      statusList().then(response => {
        this.statusList = this.union(response.data, this.statusList)
      })
    },
    union(listA, listB) {
      var n = 0
      for (let i = 0; i < listA.length; i++) {
        for (let j = 0; j < listB.length; j++) {
          if (listA[i].value === listB[j].value) {
            n = 1
          }
        }
        if (n !== 1) {
          listB.push(listA[i])
        }
      }
      return listB
    },
    // 过滤参数的改变
    handleFilter() {
      this.listQuery.page = 1
      this.getList()
    },
    // 拆分创建时间
    resolveCreateLimit() {
      if (this.createtimeLimit === null) {
        this.listQuery.createTimeBefore = null
        this.listQuery.createTimeAfter = null
      } else {
        this.listQuery.createTimeBefore = parseTime(this.createtimeLimit[0])
        this.listQuery.createTimeAfter = parseTime(this.createtimeLimit[1])
      }
    },
    // 拆分更新时间
    resolveUpdateLimit() {
      if (this.updatetimeLimit === null) {
        this.listQuery.conductTimeBefore = null
        this.listQuery.conductTimeAfter = null
      } else {
        this.listQuery.conductTimeBefore = parseTime(this.updatetimeLimit[0])
        this.listQuery.conductTimeAfter = parseTime(this.updatetimeLimit[1])
      }
    },
    // 售后人员领取任务
    changeRepairIdInRepairs(row) {
      if (row.repairId !== 0) {
        this.$notify({
          title: '失败',
          message: '此任务已被领取，无法重复领取',
          type: 'error',
          duration: 2000
        })
        return
      } else if (!this.checkPermission(['售后人员'])) {
        this.$notify({
          title: '失败',
          message: '此任务仅可被售后人员领取',
          type: 'error',
          duration: 2000
        })
        return
      }
      if (this.confirmUpdate) {
        return
      }
      this.confirmUpdate = true
      this.temp = Object.assign({}, row) // copy obj
      this.temp.repairId = this.userInfo.repairId
      updateRepair(this.temp).then((response) => {
        const result = response.data
        if (result) {
          for (const v of this.list) {
            if (v.id === this.temp.id) {
              const index = this.list.indexOf(v)
              this.temp.conductTime = new Date().getTime()
              this.list.splice(index, 1, this.temp)
              break
            }
          }
          this.dialogStatusVisible = false
          this.$notify({
            title: '成功',
            message: '领取任务成功',
            type: 'success',
            duration: 2000
          })
        } else {
          this.$notify({
            title: '失败',
            message: '领取任务失败，此任务或已被领取',
            type: 'error',
            duration: 2000
          })
        }
      })
      this.confirmUpdate = false
    },
    watchUserData(row, type) {
      console.log(type === 2 && this.checkPermission(['系统管理员', '超级管理员']))
      if (type === 2 && this.checkPermission(['系统管理员', '超级管理员'])) {
        this.userLoading = true
        this.dialogStatus = 'userWatch'
        this.dialogUserVisible = true
        repairInfoById(row.repairId).then(response => {
          this.userinfo = response.data
          this.userLoading = false
        })
      } else if (type === 1) {
        this.userLoading = true
        this.dialogStatus = 'aftersaleWatch'
        this.dialogUserVisible = true
        userInfoById(row.userid).then(response => {
          this.userinfo = response.data
          this.userLoading = false
        })
      } else {
        this.$notify({
          title: '提示',
          message: '此功能不允许您访问',
          type: 'info',
          duration: 2000
        })
      }
    },
    // 修改维修状态的先决判断
    changeRepairStatus(row) {
      this.temp = Object.assign({}, row) // copy obj
      this.dialogStatusVisible = true
    },
    // 执行更改维修状态方法
    updateRepairStatusData() {
      if (this.confirmUpdate) {
        return
      }
      this.confirmUpdate = true
      updateRepair(this.temp).then((response) => {
        const result = response.data
        if (result) {
          for (const v of this.list) {
            if (v.id === this.temp.id) {
              const index = this.list.indexOf(v)
              this.temp.conductTime = new Date().getTime()
              this.list.splice(index, 1, this.temp)
              break
            }
          }
          this.dialogStatusVisible = false
          this.$notify({
            title: '成功',
            message: '更新成功',
            type: 'success',
            duration: 2000
          })
        } else {
          this.$notify({
            title: '失败',
            message: '更新失败',
            type: 'error',
            duration: 2000
          })
        }
      })
      this.confirmUpdate = false
    },
    watchApartmentData(row) {
      this.apartmentLoading = true
      this.dialogStatus = 'apartmentWatch'
      this.dialogApartmentVisible = true
      apartmentById(row.apartmentid).then(response => {
        this.apartmentinfo = response.data
        this.apartmentLoading = false
      })
    },
    watchFileData(row) {
      if (row.id === this.temp.id && this.images.length > 0) {
        this.dialogStatus = 'fileWatch'
        this.dialogFileVisible = true
        return
      }
      this.images = []
      this.dialogStatus = 'fileWatch'
      this.temp = Object.assign({}, row) // copy obj
      getImages(this.temp.id).then(response => {
        const files = response.data
        for (const v of files) {
          this.images.unshift(v.resource)
          // image(v.resource).then(response => {
          //   if (response.data) {
          //     this.images.push('data:image/' + v.fileType + ';base64,' + response.message)
          //   }
          // })
        }
      })
      this.dialogFileVisible = true
    },
    // handleDownload() {
    //   this.downloadLoading = true
    //   import('@/vendor/Export2Excel').then(excel => {
    //     const tHeader = ['timestamp', 'title', 'type', 'importance', 'status']
    //     const filterVal = ['timestamp', 'title', 'type', 'importance', 'status']
    //     const data = this.formatJson(filterVal, this.list)
    //     excel.export_json_to_excel({
    //       header: tHeader,
    //       data,
    //       filename: 'table-list'
    //     })
    //     this.downloadLoading = false
    //   })
    // },
    // 未使用
    formatJson(filterVal, jsonData) {
      return jsonData.map(v => filterVal.map(j => {
        if (j === 'timestamp') {
          return parseTime(v[j])
        } else {
          return v[j]
        }
      }))
    },
    // 是否有文件过滤
    filterHasFile(value, row) {
      return row.hasFile === value
    },
    // 报修状态过滤
    filterStatus(value, row) {
      return row.status === value
    },
    // 任务领取状态过滤
    filterRepairId(value, row) {
      if (value === 0) {
        return row.repairId === value
      } else {
        return row.repairId !== 0
      }
    },
    // 过滤条件改变时触发
    filterHanderChange(filters) {
      if (filters.hasFile !== undefined) {
        if (filters.hasFile.length >= 1) {
          this.listQuery.hasFileSet = []
          for (var i in filters.hasFile) {
            this.listQuery.hasFileSet.push(filters.hasFile[i])
          }
        } else {
          this.listQuery.hasFileSet = null
        }
      } else if (filters.status !== undefined) {
        if (filters.status.length >= 1) {
          this.listQuery.statusSet = []
          for (var j in filters.status) {
            this.listQuery.statusSet.push(filters.status[j])
          }
        } else {
          this.listQuery.statusSet = null
        }
      } else if (filters.repairId !== undefined) {
        if (filters.repairId.length >= 1) {
          this.listQuery.repairId = filters.repairId[0]
        } else {
          this.listQuery.repairId = -1
        }
      }
      console.log(this.listQuery.repairId)
      this.getList()
    }
  }
}
</script>
<style scoped>
  .filter-container {
    padding-bottom: 20px;
  }
</style>
<style>
  .demo-table-expand {
    font-size: 0;
  }
  .demo-table-expand label {
    width: 90px;
    color: #99a9bf;
  }
  .demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 50%;
  }
  .avatar {
    width: 168px;
    height: 168px;
    margin: 0px 4px 4px 4px;
    border-radius: 6px;
  }
</style>
