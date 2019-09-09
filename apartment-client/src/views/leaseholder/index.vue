<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input v-model.trim="listQuery.name" type="text" :min="1" placeholder="姓名" style="width: 200px;" class="filter-item" @keyup.enter.native="handleFilter">
        <template slot="prepend">姓名</template>
      </el-input>
      <el-input v-model.number="listQuery.phone" :min="1" placeholder="手机号" style="width: 250px;" class="filter-item" @keyup.enter.native="handleFilter">
        <template slot="prepend">电话号码</template>
      </el-input>
      <el-input v-model.trim="listQuery.idNumber" :min="1" placeholder="证件号码" style="width: 300px;" class="filter-item" @keyup.enter.native="handleFilter">
        <template slot="prepend">证件号码</template>
      </el-input>
      <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">
        搜索
      </el-button>
      <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit" @click="handleCreate">
        添加
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
      <el-table-column label="ID" type="index" align="center" />
      <el-table-column label="姓名" prop="name" column-key="name" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column label="性别" prop="sex" column-key="sex" align="center" :filter-multiple="false" :filter-method="filterSex" :filters="sexList" filter-placement="bottom-end">
        <template slot-scope="scope">
          <el-tag :type="scope.row.sex === 1 ? 'success' : ''" close-transition>{{ scope.row.sex ===1 ? '男' : '女' }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="电话号码" prop="phone" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.phone }}</span>
        </template>
      </el-table-column>
      <el-table-column label="证件类型" prop="idType" column-key="idType" align="center" :filter-method="filterIdType" :filters="idTypeList" filter-placement="bottom-end">
        <template slot-scope="scope">
          <el-tag type="success" close-transition>{{ scope.row.idType }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="证件号码" prop="idNumber" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.idNumber }}</span>
        </template>
      </el-table-column>
      <el-table-column label="状态" prop="isLive" column-key="isLive" align="center" :filter-multiple="false" :filter-method="filterIsLive" :filters="isLiveList" filter-placement="bottom-end">
        <template slot-scope="scope">
          <el-tooltip class="item" effect="light" :content="scope.row.isLive === 1 ? '点击查看入住信息' : '未入住' " placement="right">
            <el-button :disabled="scope.row.isLive === 0" :type="scope.row.isLive === 1 ? 'success' : 'info'" size="mini" close-transition @click="handleApartmentData(scope.row)">{{ scope.row.isLive===1 ? '已入住' : '未入住' }}</el-button>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="147px" class-name="small-padding">
        <template slot-scope="{row}">
          <el-button type="primary" size="mini" @click="handleUpdate(row)">
            编辑
          </el-button>
          <el-button v-if="row.status!='deleted'" size="mini" type="danger" @click="deleteDate(row)">
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页插件 -->
    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />
    <!-- 添加与修改 -->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :rules="rules" :model="temp" label-position="left" label-width="110px" style="width: 400px; margin-left:50px;">
        <el-form-item label="租户姓名" prop="name">
          <el-input v-model.trim="temp.name" placeholder="请输入租户姓名" />
        </el-form-item>
        <el-form-item label="租户性别" prop="sex">
          <el-select v-model="temp.sex" placeholder="请选择" default-first-option>
            <el-option v-for="item in sexList" :key="item.text" :label="item.text" :value="item.value" />
          </el-select>
        </el-form-item>
        <el-form-item label="租户手机号码" prop="phone">
          <el-input v-model="temp.phone" placeholder="请输入手机号码" />
        </el-form-item>
        <el-form-item label="租户证件类型" prop="idType">
          <el-select v-model="temp.idType" placeholder="请选择或输入" filterable allow-create default-first-option clearable>
            <el-option v-for="item in idTypeList" :key="item.text" :label="item.text" :value="item.value" />
          </el-select>
        </el-form-item>
        <el-form-item label="租户证件号码" prop="idNumber">
          <el-input v-model.trim="temp.idNumber" :minlength="4" placeholder="请输入证件号码" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">
          取消
        </el-button>
        <el-button type="primary" @click="dialogStatus==='create'?createData():updateData()">
          {{ dialogStatus==='create'? '确认添加' : '确认修改' }}
        </el-button>
      </div>
    </el-dialog>
    <!-- 查看公寓信息 -->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogApartmentVisible">
      <el-table v-if="apartmentList!=null" :data="apartmentList" border fit highlight-current-row>
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
  </div>
</template>

<script>
import { apartmentListByUserid } from '@/api/apartment'
import { getUserList, idTypeList, deleteUserById, updateUserById, createUser } from '@/api/apartmentuser'
import waves from '@/directive/waves' // waves directive
import { parseTime } from '@/utils'
import Pagination from '@/components/Pagination' // secondary package based on el-pagination
import { validatePhoneTwo, validateValueIsEmity } from '@/utils/validate'

export default {
  name: 'ComplexTable',
  components: { Pagination },
  directives: { waves },
  data() {
    return {
      isLiveList: [
        { text: '已入住', value: 1 },
        { text: '未入住', value: 0 }
      ],
      sexList: [
        { text: '男', value: 1 },
        { text: '女', value: 2 }
      ],
      idTypeList: null,
      apartmentList: null,
      tableKey: 0,
      list: null,
      total: 1,
      listLoading: false,
      listQuery: {
        page: 1,
        limit: 10,
        id: null,
        name: null,
        sexSet: null,
        phone: null,
        idNumber: null,
        idTypeSet: null,
        isLiveSet: null
      },
      temp: {
        id: null,
        name: null,
        sex: null,
        phone: null,
        idNumber: null,
        idType: null,
        isLive: null
      },
      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '修改租户信息',
        create: '添加租户信息',
        apartment: '查看租户公寓信息'
      },
      dialogApartmentVisible: false,
      rules: {
        name: [{ required: true, message: '租户姓名必填', trigger: 'blur' }],
        sex: [{ required: true, message: '租户性别必填', trigger: 'change' }],
        phone: [
          { required: true, trigger: 'blur', validator: validateValueIsEmity },
          { trigger: 'blur', validator: validatePhoneTwo }
        ],
        idType: [{ required: true, message: '租户证件必填', trigger: 'change' }],
        idNumber: [{ required: true, message: '租户证件必填', trigger: 'blur' }]
      },
      downloadLoading: false
    }
  },
  created() {
    this.getList()
    this.getIdTypeList()
  },
  methods: {
    // 获取租户的数据
    getList() {
      this.listLoading = true
      getUserList(this.listQuery).then(response => {
        this.list = response.data.records
        this.total = response.data.total
        this.listLoading = false
      })
    },
    // 获取证件类型所有类型
    getIdTypeList() {
      idTypeList().then(response => {
        this.idTypeList = response.data
      })
    },
    // 查看租户的公寓信息
    handleApartmentData(row) {
      this.dialogStatus = 'apartment'
      this.dialogApartmentVisible = true
      this.temp = Object.assign({}, row) // copy obj
      apartmentListByUserid(this.temp.id).then((response) => {
        this.apartmentList = response.data
      })
    },
    // 过滤参数改变
    handleFilter() {
      this.listQuery.page = 1
      this.getList()
    },
    resetTemp() {
      this.temp = {
        id: null,
        name: null,
        sex: null,
        phone: null,
        idNumber: null,
        idType: null,
        isLive: null
      }
    },
    // 添加租户信息
    handleCreate() {
      this.resetTemp()
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    // 添加租户信息
    createData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          createUser(this.temp).then((response) => {
            const result = response.data
            if (result) {
              this.total++
              this.list.unshift(this.temp)
              this.dialogFormVisible = false
              this.$notify({
                title: '成功',
                message: '添加成功',
                type: 'success',
                duration: 2000
              })
            } else {
              this.$notify({
                title: '失败',
                message: '添加失败',
                type: 'error',
                duration: 2000
              })
            }
          })
        }
      })
    },
    // 修改租户信息
    handleUpdate(row) {
      this.temp = Object.assign({}, row) // copy obj
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    // 修改租户信息
    updateData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          updateUserById(this.temp).then((response) => {
            const result = response.data
            if (result) {
              for (const v of this.list) {
                if (v.id === this.temp.id) {
                  const index = this.list.indexOf(v)
                  this.list.splice(index, 1, this.temp)
                  break
                }
              }
              this.dialogFormVisible = false
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
        }
      })
    },
    // 删除租户信息
    deleteDate(row) {
      this.$confirm('此操作将永久删除该租户信息, 并且会对当前租户所租的公寓信息进行修改, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteUserById(row).then((response) => {
          const result = response.data
          if (result) {
            for (const v of this.list) {
              if (v.id === row.id) {
                const index = this.list.indexOf(row)
                this.list.splice(index, 1)
                break
              }
            }
            this.$message({
              type: 'success',
              message: '删除成功!'
            })
          } else {
            this.$message({
              type: 'info',
              message: '删除失败!'
            })
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除或删除失败!'
        })
      })
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
    // 入住状态过滤
    filterIsLive(value, row) {
      return row.isLive === value
    },
    // 证件类型过滤
    filterIdType(value, row) {
      return row.idType === value
    },
    // 性别过滤
    filterSex(value, row) {
      return row.sex === value
    },
    // 过滤条件改变时触发
    filterHanderChange(filters) {
      if (filters.isLive !== undefined) {
        if (filters.isLive.length >= 1) {
          this.listQuery.isLiveSet = []
          for (var i in filters.isLive) {
            this.listQuery.isLiveSet.push(filters.isLive[i])
          }
        } else {
          this.listQuery.isLiveSet = null
        }
      } else if (filters.idType !== undefined) {
        if (filters.idType.length >= 1) {
          this.listQuery.idTypeSet = []
          for (var j in filters.idType) {
            this.listQuery.idTypeSet.push(filters.idType[j])
          }
        } else {
          this.listQuery.idTypeSet = null
        }
      } else if (filters.sex !== undefined) {
        if (filters.sex.length >= 1) {
          this.listQuery.sexSet = []
          for (var m in filters.sex) {
            this.listQuery.sexSet.push(filters.sex[m])
          }
        } else {
          this.listQuery.sexSet = null
        }
      }
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
</style>
