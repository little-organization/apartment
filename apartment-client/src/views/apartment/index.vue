<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input v-model.number="listQuery.roomAreaMin" type="number" :min="1" placeholder="最小面积" style="width: 200px;" class="filter-item" @keyup.enter.native="handleFilter">
        <template slot="prepend">公寓面积</template>
      </el-input>
      -
      <el-input v-model.number="listQuery.roomAreaMax" type="number" :min="1" placeholder="最大面积" style="width: 100px;" class="filter-item" @keyup.enter.native="handleFilter" />
      <el-input v-model.number="listQuery.priceMin" type="number" :min="1" placeholder="最小标价" style="width: 170px;" class="filter-item" @keyup.enter.native="handleFilter">
        <template slot="prepend">标价</template>
      </el-input>
      -
      <el-input v-model.number="listQuery.priceMax" type="number" :min="1" placeholder="最大标价" style="width: 100px;" class="filter-item" @keyup.enter.native="handleFilter" />
      <el-input v-model.trim="listQuery.username" placeholder="租户姓名" style="width: 100px;" class="filter-item" @keyup.enter.native="handleFilter" />
      <el-input v-model.trim="listQuery.address" placeholder="公寓地址" style="width: 200px;" class="filter-item" @keyup.enter.native="handleFilter" />
      <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">
        搜索
      </el-button>
      <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit" @click="handleCreate">
        添加
      </el-button>
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
      <el-table-column label="ID" type="index" align="center" width="70" />
      <el-table-column label="户型" prop="houseType" column-key="houseType" width="110px" align="center" :filter-method="filterHouseType" :filters="houseTypeList" filter-placement="bottom-end">
        <template slot-scope="scope">
          <span>{{ scope.row.houseType }}</span>
        </template>
      </el-table-column>
      <el-table-column label="面积" prop="roomArea" width="100px" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.roomArea }}m&sup2;</span>
        </template>
      </el-table-column>
      <el-table-column label="朝向" prop="face" column-key="face" width="100px" align="center" :filter-method="filterFace" :filters="faceList" filter-placement="bottom-end">
        <template slot-scope="scope">
          <el-tag type="success" close-transition>{{ scope.row.face }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="标价(￥)" prop="price" width="100px" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.price }}元</span>
        </template>
      </el-table-column>
      <el-table-column label="支付模式" prop="pattern" column-key="pattern" width="110px" align="center" :filter-method="filterPattern" :filters="patternList" filter-placement="bottom-end">
        <template slot-scope="scope">
          <el-tag type="success" close-transition>{{ scope.row.pattern }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="地址" prop="address" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.address }}</span>
        </template>
      </el-table-column>
      <el-table-column label="状态" prop="status" column-key="status" width="110px" align="center" :filter-multiple="false" :filter-method="filterStatus" :filters="status" filter-placement="bottom-end">
        <template slot-scope="scope">
          <el-tooltip class="item" effect="light" :content="scope.row.status === 1 ? '点击查看或修改租户信息' : '点击添加租户' " placement="right">
            <el-button :type="scope.row.status === 1 ? 'success' : 'info'" size="mini" @click="handleUserData(scope.row)">{{ scope.row.status===0 ? '未出租' : '已出租' }}</el-button>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="147px" class-name="small-padding fixed-width">
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
      <el-form ref="dataForm" :rules="rules" :model="temp" label-position="left" label-width="80px" style="width: 400px; margin-left:50px;">
        <el-form-item label="公寓户型" prop="houseType">
          <el-select v-model="temp.houseType" placeholder="请选择或输入" filterable allow-create default-first-option clearable>
            <el-option v-for="item in houseTypeList" :key="item.text" :label="item.text" :value="item.value" />
          </el-select>
        </el-form-item>
        <el-form-item label="公寓面积" prop="roomArea">
          <el-input v-model="temp.roomArea" type="number" :min="1" placeholder="请输入面积(平方米)" />
        </el-form-item>
        <el-form-item label="公寓朝向" prop="face">
          <el-select v-model="temp.face" placeholder="请选择或输入" filterable allow-create default-first-option clearable>
            <el-option v-for="item in faceList" :key="item.text" :label="item.text" :value="item.value" />
          </el-select>
        </el-form-item>
        <el-form-item label="标价(￥)" prop="price">
          <el-input v-model="temp.price" type="number" :min="1" placeholder="请输入对外标价(元)" />
        </el-form-item>
        <el-form-item label="支付模式" prop="pattern">
          <el-select v-model="temp.pattern" placeholder="请选择或输入" filterable allow-create default-first-option clearable>
            <el-option v-for="item in patternList" :key="item.text" :label="item.text" :value="item.value" />
          </el-select>
        </el-form-item>
        <el-form-item label="地址" prop="address">
          <el-input v-model.trim="temp.address" :autosize="{ minRows: 2, maxRows: 4}" type="textarea" placeholder="请输入公寓地址" />
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
    <!-- 租户信息添加与修改 -->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogUserVisible">
      <el-form ref="userForm" :model="userinfo" label-position="center" label-width="37%">
        <el-form-item label="请选择租户:" prop="name">
          <el-select v-model="userinfo" :remote="true" placeholder="请输入租户姓名" :remote-method="getUserList" :loading.sync="userListLoading" clearable filterable>
            <el-option v-for="item in userList" :key="item.id" :label="item.name" :value="item">
              <span style="float: left">{{ item.name }}</span>
              <span style="float: right; color: #8492a6; font-size: 13px">ID:{{ item.idNumber }}</span>
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <el-table v-if="userinfo!=null" :data="[userinfo]" border fit highlight-current-row>
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-form label-position="left" inline class="demo-table-expand">
              <el-form-item label="租户姓名">
                <span>{{ props.row.name }}</span>
              </el-form-item>
              <el-form-item label="手机号码">
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
                <span>{{ props.row.isLive===0?'未租住':'已租住' }}</span>
              </el-form-item>
            </el-form>
          </template>
        </el-table-column>
        <el-table-column prop="name" label="租户姓名" />
        <el-table-column prop="phone" label="租户手机号" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template slot-scope="{row}">
            <el-button size="mini" type="danger" @click="delHolderFromApartment()">
              删除
            </el-button>
            <el-button size="mini" type="parmary" @click="sendMessageHandler(row)">
              发送短信
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogUserVisible = false">
          取消
        </el-button>
        <el-button type="primary" @click="dialogStatus==='usercreate'?insertUserData():updateUserData()">
          {{ dialogStatus === 'usercreate' ? '确认添加' : '确认修改' }}
        </el-button>
      </div>
      <!-- 发送短信 -->
      <el-dialog :title="textMap[dialogStatus]" :visible.sync="innerVisible" append-to-body>
        <el-form ref="messageForm" :rules="messageRules" :model="message" label-width="100px" label-position="right">
          <el-form-item label="短信签名:" prop="signName">
            <el-select v-model="message.signName" placeholder="请选择" @change="changeSign($event)">
              <el-option v-for="item in signList" :key="item.requestId" :label="item.signName" :value="item.signName" />
            </el-select>
          </el-form-item>
          <el-form-item label="短信模板:" prop="templateCode">
            <el-select v-model="message.templateCode" placeholder="请选择" @change="changeTemplate($event)">
              <el-option v-for="item in templateList" :key="item.requestId" :label="item.templateName" :value="item.templateCode" />
            </el-select>
          </el-form-item>
          <el-form-item label="短信内容:" prop="templateContent">
            <el-input v-model="message.templateContent" type="textarea" :rows="4" />
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="innerVisible = false">
            取消
          </el-button>
          <el-button type="primary" @click="sendMessage()">
            确认发送
          </el-button>
        </div>
      </el-dialog>
    </el-dialog>
  </div>
</template>

<script>
import { fetchList, patternList, houseTypeList, faceList, createApartment, updateApartment, deleteApartment } from '@/api/apartment'
import { userInfoList, userInfoById } from '@/api/apartmentuser'
import waves from '@/directive/waves' // waves directive
import { parseTime } from '@/utils'
import Pagination from '@/components/Pagination' // secondary package based on el-pagination
import { selectAllSuccessSign, selectAllSuccessTemplate, sendMsg } from '@/api/message'

export default {
  name: 'ComplexTable',
  components: { Pagination },
  directives: { waves },
  data() {
    return {
      innerVisible: false,
      // 防止添加操作重复点击
      confirmInsertUserData: false,
      // 防止更新操作操作重复点击
      confirmUpdateApartment: false,
      status: [
        { text: '已出租', value: 1 },
        { text: '未出租', value: 0 }
      ],
      faceList: null,
      patternList: null,
      houseTypeList: null,
      userList: null,
      userListLoading: false,
      // 租户信息
      userinfo: {
        id: null,
        name: null,
        sex: null,
        phone: '',
        idNumber: '',
        idType: '',
        isLive: null
      },
      tableKey: 0,
      list: null,
      total: 1,
      listLoading: false,
      // 查询条件
      listQuery: {
        page: 1,
        limit: 10,
        priceMin: null,
        priceMan: null,
        roomAreaMin: null,
        roomAreaMan: null,
        username: null,
        address: null,
        houseTypeSet: null,
        faceSet: null,
        statusSet: null,
        patternSet: null
      },
      // 某个公寓信息
      temp: {
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
      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        usercreate: '添加公寓租户信息',
        userupdate: '修改公寓租户信息',
        update: '修改公寓信息',
        create: '添加公寓信息',
        sendmessage: '发送短信给租户'
      },
      dialogUserVisible: false,
      rules: {
        roomArea: [{ required: true, message: '公寓面积必填', trigger: 'blur' }],
        houseType: [{ required: true, message: '公寓户型必填', trigger: 'change' }],
        type: [{ required: true, message: '公寓朝向必填', trigger: 'change' }],
        face: [{ required: true, message: '公寓朝向必填', trigger: 'change' }],
        pattern: [{ required: true, message: '公寓支付模式必填', trigger: 'change' }],
        address: [{ required: true, message: '公寓地址必填', trigger: 'blur' }],
        price: [{ required: true, message: '公寓对外标价必填', trigger: 'blur' }]
      },
      signList: null,
      templateList: null,
      sending: true,
      templateContent: null,
      message: {
        phone: null,
        signName: null,
        templateCode: null,
        templateContent: null
      },
      messageRules: {
        signName: [
          { required: true, message: '短信签名必选', trigger: 'change' }
        ],
        templateCode: [
          { required: true, message: '短信模板必选', trigger: 'change' }
        ]
      }
    }
  },
  created() {
    this.getList()
    this.getPatternList()
    this.getHouseTypeList()
    this.getFaceList()
    this.getAllSuccessSign()
    this.getAllSuccessTemplate()
  },
  methods: {
    // 获取分页公寓的数据
    getList() {
      this.listLoading = true
      fetchList(this.listQuery).then(response => {
        this.list = response.data.records
        this.total = response.data.total
        this.listLoading = false
      })
    },
    // 获取所有可用的签名
    getAllSuccessSign(query) {
      selectAllSuccessSign(query).then((response) => {
        this.signList = response.data
      })
    },
    // 获取所有可用模板
    getAllSuccessTemplate(query) {
      selectAllSuccessTemplate(query).then((response) => {
        this.templateList = response.data
      })
    },
    // 判断是新增租户还是修改租户
    handleUserData(row) {
      if (row.status === 1) {
        this.dialogStatus = 'userupdate'
        this.dialogUserVisible = true
        this.temp = Object.assign({}, row) // copy obj
        userInfoById(this.temp.userid).then((response) => {
          this.userinfo = response.data
        })
      } else {
        this.userinfo = null
        this.temp = Object.assign({}, row) // copy obj
        this.dialogStatus = 'usercreate'
        this.dialogUserVisible = true
      }
    },
    // 获取所有租户
    getUserList(query) {
      this.userListLoading = true
      userInfoList(query).then((response) => {
        this.userList = response.data
        this.userListLoading = false
      })
    },
    // 为公寓添加租户
    insertUserData() {
      // 防止重复点击
      if (this.confirmInsertUserData) {
        return
      }
      this.confirmInsertUserData = true
      // 进行校验
      if (this.userinfo != null) {
        this.temp.userid = this.userinfo.id
        this.temp.status = 1
        updateApartment(this.temp).then((response) => {
          const result = response.data
          if (result) {
            for (const v of this.list) {
              if (v.id === this.temp.id) {
                const index = this.list.indexOf(v)
                // 替换数据
                this.list.splice(index, 1, this.temp)
                break
              }
            }
            this.dialogUserVisible = false
            this.$notify({
              title: '成功',
              message: '添加租户成功',
              type: 'success',
              duration: 2000
            })
          } else {
            this.$notify({
              title: '失败',
              message: '添加租户失败',
              type: 'error',
              duration: 2000
            })
          }
        })
      } else {
        this.$notify({
          title: '信息',
          message: '您未做添加',
          type: 'info',
          duration: 2000
        })
      }
      this.confirmInsertUserData = false
    },
    // 为公寓删除租户（假删除）
    delHolderFromApartment() {
      this.$confirm('您确定将该租户从此公寓中删除, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.userinfo = null
      })
    },
    // 为公寓修改或删除租户
    updateUserData() {
      if (this.userinfo === null) {
        this.temp.userid = null
        this.temp.status = 0
      } else {
        if (this.temp.userid === this.userinfo.id) {
          this.$notify({
            title: '提示',
            message: '您并没有修改',
            type: 'info',
            duration: 2000
          })
          return
        } else {
          this.temp.userid = this.userinfo.id
        }
      }
      // 防止重复点击
      if (this.confirmUpdateApartment) {
        return
      }
      this.confirmUpdateApartment = true
      updateApartment(this.temp).then((response) => {
        const result = response.data
        if (result) {
          for (const v of this.list) {
            if (v.id === this.temp.id) {
              const index = this.list.indexOf(v)
              this.list.splice(index, 1, this.temp)
              break
            }
          }
          this.dialogUserVisible = false
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
      this.confirmUpdateApartment = false
    },
    // 获取支付模式所有类型
    getPatternList() {
      patternList().then(response => {
        console.log(response)
        this.patternList = response.data
      })
    },
    // 获取户型所有类型
    getHouseTypeList() {
      houseTypeList().then(response => {
        this.houseTypeList = response.data
      })
    },
    // 获取朝向所有类型
    getFaceList() {
      faceList().then(response => {
        this.faceList = response.data
      })
    },
    // 过滤公寓状态类型
    handleFilter() {
      this.listQuery.page = 1
      this.getList()
    },
    resetTemp() {
      this.temp = {
        id: null,
        userid: null,
        roomArea: '',
        houseType: '',
        face: '',
        price: '',
        pattern: '',
        address: '',
        status: 0
      }
    },
    // 添加公寓信息
    handleCreate() {
      this.resetTemp()
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    // 添加公寓信息
    createData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          createApartment(this.temp).then((response) => {
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
          }).catch(() => {
            this.$notify({
              title: '失败',
              message: '添加异常',
              type: 'error',
              duration: 2000
            })
          })
        }
      })
    },
    // 修改公寓信息
    handleUpdate(row) {
      this.temp = Object.assign({}, row) // copy obj
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    // 修改公寓信息
    updateData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          updateApartment(this.temp).then((response) => {
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
    // 删除公寓信息
    deleteDate(row) {
      this.$confirm('此操作将永久删除该公寓信息, 并且会对当前公寓中的租户信息进行修改, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteApartment(row).then((response) => {
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
    // 模板更改时更改信息内容
    changeSign(signName) {
      this.message.signName = signName
      this.message.templateContent = '【' + signName + '】' + this.templateContent
    },
    // 模板更改时更改信息内容
    changeTemplate(templateCode) {
      for (const v of this.templateList) {
        if (v.templateCode === templateCode) {
          this.message.templateContent = '【' + this.message.signName + '】' + v.templateContent
          this.templateContent = v.templateContent
          break
        }
      }
    },
    // 发送消息的准备
    sendMessageHandler(row) {
      this.innerVisible = true
      this.dialogStatus = 'sendmessage'
      this.message.phone = row.phone
      this.$nextTick(() => {
        this.$refs['messageForm'].clearValidate()
      })
    },
    // 发送消息
    sendMessage() {
      if (!this.sending) {
        return
      }
      this.sending = false
      this.$refs['messageForm'].validate((valid) => {
        if (valid) {
          var sendMessageInfo = {
            PhoneNumberJson: '[\"' + this.message.phone + '\"]',
            SignNameJson: '[\"' + this.message.signName + '\"]',
            TemplateCode: this.message.templateCode
          }
          console.log(sendMessageInfo)
          sendMsg(sendMessageInfo).then((response) => {
            const result = response.message
            const msg = response.data
            if (result) {
              this.$message({ type: 'success', message: '短信发送成功!' })
              this.innerVisible = false
              this.sending = true
            } else {
              this.$alert('<div>请求状态码：<strong>' + msg.code + '</strong><button class=\"el-button el-button--primary el-button--mini\" style=\"margin-left: 15px;\"><a href=\"https://help.aliyun.com/document_detail/101346.html\" target=\"_blank\">(错误码详见错误码列表)</a></button></div>', '发送失败原因', {
                dangerouslyUseHTMLString: true
              })
              this.sending = true
            }
          })
        } else {
          this.sending = true
        }
      })
    },
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
    // 状态过滤
    filterStatus(value, row) {
      return row.status === value
    },
    // 支付模式过滤
    filterPattern(value, row) {
      return row.pattern === value
    },
    // 朝向过滤
    filterFace(value, row) {
      return row.face === value
    },
    // 户型过滤
    filterHouseType(value, row) {
      return row.houseType === value
    },
    // 过滤条件改变时触发
    filterHanderChange(filters) {
      if (filters.pattern !== undefined) {
        if (filters.pattern.length >= 1) {
          this.listQuery.patternSet = []
          for (var i in filters.pattern) {
            this.listQuery.patternSet.push(filters.pattern[i])
          }
        } else {
          this.listQuery.patternSet = null
        }
      } else if (filters.face !== undefined) {
        if (filters.face.length >= 1) {
          this.listQuery.faceSet = []
          for (var j in filters.face) {
            this.listQuery.faceSet.push(filters.face[j])
          }
        } else {
          this.listQuery.faceSet = null
        }
      } else if (filters.houseType !== undefined) {
        if (filters.houseType.length >= 1) {
          this.listQuery.houseTypeSet = []
          for (var m in filters.houseType) {
            this.listQuery.houseTypeSet.push(filters.houseType[m])
          }
        } else {
          this.listQuery.houseTypeSet = null
        }
      } else if (filters.status !== undefined) {
        if (filters.status.length >= 1) {
          this.listQuery.statusSet = []
          for (var n in filters.status) {
            this.listQuery.statusSet.push(filters.status[n])
          }
        } else {
          this.listQuery.statusSet = null
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
