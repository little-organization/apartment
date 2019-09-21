<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input v-model.trim="listQuery.node_no" placeholder="网关编码" style="width: 150px;" class="filter-item" @keyup.enter.native="handleFilter" />
      <el-input v-model.trim="listQuery.lock_no" placeholder="门锁编码" style="width: 150px;" class="filter-item" @keyup.enter.native="handleFilter" />
      <el-input v-model.trim="listQuery.house_code" placeholder="房源编码" style="width: 150px;" class="filter-item" @keyup.enter.native="handleFilter" />
      <el-input v-model.trim="listQuery.room_code" placeholder="房间编码" style="width: 150px;" class="filter-item" @keyup.enter.native="handleFilter" />
      <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">
        搜索
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
    >
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand" label-width="100px">
            <el-form-item label="门锁类型:">
              <span>{{ props.row.lock_kind | lock_kind }}</span>
            </el-form-item>
            <el-form-item label="门锁型号:">
              <span>{{ props.row.type | lock_type }}</span>
            </el-form-item>
            <el-form-item label="软件版本:">
              <el-tag type="success">{{ props.row.software_version }}</el-tag>
            </el-form-item>
            <el-form-item label="硬件版本:">
              <el-tag>{{ props.row.hardware_version }}</el-tag>
            </el-form-item>
            <el-form-item label="房源编码:">
              <el-tag type="success">{{ props.row.house_code.length === 0 ? '未知' : props.row.house_code }}</el-tag>
            </el-form-item>
            <el-form-item label="房间编码:">
              <span>{{ props.row.room_code.length === 0 ? '未知' : props.row.room_code }}</span>
            </el-form-item>
            <el-form-item label="网关编码:">
              <span>{{ props.row.node_no }}</span>
            </el-form-item>
            <el-form-item label="门状态:">
              <el-tag>{{ props.row.open_door_status | open_door_status }}</el-tag>
            </el-form-item>
            <el-form-item label="质保开始日期:">
              <span>{{ props.row.guarantee_time_start | parseTime }}</span>
            </el-form-item>
            <el-form-item label="质保截至日期:">
              <span>{{ props.row.guarantee_time_end | parseTime }}</span>
            </el-form-item>
            <el-form-item label="是否支持门磁:">
              <el-tag :type="props.row.magnet_flag === 0?'info' : ''">{{ props.row.magnet_flag | magnet_flag }}</el-tag>
            </el-form-item>
            <el-form-item label="网关通信状态:">
              <el-tag :type="props.row.node_comu_status === '00'?'success' : 'danger'">{{ props.row.node_comu_status | comu_status }}</el-tag>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="lock_no" label="门锁编码" :filter-method="filterLock_kind" :filters="lock_kindList" filter-placement="bottom-end">
        <template slot-scope="{row}">
          <span>{{ row.lock_no }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="address" label="安装地址">
        <template slot-scope="{row}">
          <el-button type="text" size="mini">{{ row.address }}</el-button>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="comu_status" label="门锁通信状态" :filter-method="filterComu_status" :filters="comu_statusList" filter-placement="bottom-end">
        <template slot-scope="{row}">
          <el-tag :type="row.comu_status === '00'?'success' : 'danger'">{{ row.comu_status | comu_status }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center" width="70px" prop="power" label="电量">
        <template slot-scope="{row}">
          <el-tag>{{ row.power }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="install_time" label="安装日期">
        <template slot-scope="{row}">
          <span>{{ row.install_time | parseTime }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="195px" class-name="small-padding fixed-width">
        <template slot-scope="{row}">
          <el-button v-waves type="success" size="mini" @click="getDetails(row)">
            详情信息
          </el-button>
          <el-button v-waves type="primary" size="mini" @click="getPwdInfo(row)">
            查看密码
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页插件 -->
    <pagination v-show="total>0" :total="total" :page.sync="listQuery.current_page" :limit.sync="listQuery.page_size" @pagination="getList" />
    <!-- 查看详细信息 -->
    <el-dialog title="详细信息" :visible.sync="dialogVisible" width="30%">
      <div>
        <el-tag>
          门锁分类：{{ lockDetail.lock_kind | lock_kind }}
        </el-tag>
      </div>
      <div>
        <el-tag type="success">
          门锁型号：{{ lockDetail.type | lock_type }}
        </el-tag>
      </div>
      <div>
        <el-tag type="info">
          软件版本：{{ lockDetail.software_version }}
        </el-tag>
      </div>
      <div>
        <el-tag type="warning">
          最新软件版本：{{ lockDetail.new_software_version }}
        </el-tag>
      </div>
      <div>
        <el-tag type="danger">
          硬件版本：{{ lockDetail.hardware_version }}
        </el-tag>
      </div>
      <div>
        <el-tag>
          门锁编码：{{ lockDetail.lock_no }}
        </el-tag>
      </div>
      <div>
        <el-tag type="success">
          网关编码：{{ lockDetail.node_no }}
        </el-tag>
      </div>
      <div>
        <el-tag type="info">
          电池电量：{{ lockDetail.power }}
        </el-tag>
      </div>
      <div>
        <el-tag type="warning">
          电量更新时间：{{ lockDetail.power_update_time | parseTime }}
        </el-tag>
      </div>
      <div>
        <el-tag type="danger">
          网关通信状态：{{ lockDetail.node_comu_status | comu_status }}
        </el-tag>
      </div>
      <div>
        <el-tag>
          门锁通信状态：{{ lockDetail.comu_status | comu_status }}
        </el-tag>
      </div>
      <div>
        <el-tag type="success">
          门锁通信状态更新：{{ lockDetail.comu_status_update_time | parseTime }}
        </el-tag>
      </div>
      <div>
        <el-tag type="info">
          门锁通信状态：{{ lockDetail.comu_status | comu_status }}
        </el-tag>
      </div>
      <div>
        <el-tag type="warning">
          门锁接收到的锁信号强度：{{ lockDetail.rssi }}0~4:信号强度 -1:未上报信号强度
        </el-tag>
      </div>
      <div>
        <el-tag type="danger">
          安装地址：{{ lockDetail.address }}
        </el-tag>
      </div>
      <div>
        <el-tag>
          房源编码：{{ lockDetail.house_code }}
        </el-tag>
      </div>
      <div>
        <el-tag type="success">
          房间编码：{{ lockDetail.room_code }}
        </el-tag>
      </div>
      <div>
        <el-tag type="info">
          是否支持门磁：{{ lockDetail.magnet_flag===0?'不支持':'支持' }}
        </el-tag>
      </div>
      <div>
        <el-tag type="warning">
          门状态：{{ lockDetail.open_door_status | open_door_status }}
        </el-tag>
      </div>
      <div>
        <el-tag type="danger">
          安装日期：{{ lockDetail.install_time | parseTime }}
        </el-tag>
      </div>
      <div>
        <el-tag>
          质保日期(起)：{{ lockDetail.guarantee_time_start | parseTime }}
        </el-tag>
      </div>
      <div>
        <el-tag type="success">
          质保日期(止)：{{ lockDetail.guarantee_time_end | parseTime }}
        </el-tag>
      </div>
      <div>
        <el-tag type="info">
          描述：{{ lockDetail.description }}
        </el-tag>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 查看密码信息 -->
    <el-dialog title="密码详情" :visible.sync="dialogPwdVisible" width="80%">
      <el-table
        :key="lockPwdKey"
        v-loading="listPwdLoading"
        :data="lockPwdList"
        border
        fit
        stripe
        highlight-current-row
        style="width: 100%;"
      >
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-form label-position="left" inline class="demo-table-expand" label-width="130px">
              <el-form-item label="密码:">
                <el-tag>{{ props.row.pwd_text }}</el-tag>
              </el-form-item>
              <el-form-item label="使用人姓名:">
                <el-tag type="success">{{ props.row.pwd_user_name }}</el-tag>
              </el-form-item>
              <el-form-item label="使用人证件号:">
                <el-tag type="info">{{ props.row.pwd_user_idcard }}</el-tag>
              </el-form-item>
              <el-form-item label="有效期(起):">
                <span>{{ props.row.valid_time_start | parseTime }}</span>
              </el-form-item>
              <el-form-item label="有效期(止):">
                <span>{{ props.row.valid_time_end | parseTime }}</span>
              </el-form-item>
            </el-form>
          </template>
        </el-table-column>
        <el-table-column align="center" prop="lock_no" label="门锁编码">
          <template slot-scope="{row}">
            <span>{{ row.lock_no }}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" prop="pwd_no" label="密码编号">
          <template slot-scope="{row}">
            <el-button type="text" size="mini">{{ row.pwd_no | pwd_no(lock_type) }}</el-button>
          </template>
        </el-table-column>
        <el-table-column align="center" prop="pwd_num" label="密码">
          <template slot-scope="{row}">
            <el-button type="text" size="mini">{{ row.pwd_num }}</el-button>
          </template>
        </el-table-column>
        <el-table-column align="center" prop="comu_status" label="密码状态">
          <template slot-scope="{row}">
            <el-tag :type="row.status === '11'?'success' : 'danger'">{{ row.status | pwd_status }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column align="center" prop="pwd_user_mobile" label="密码使用人手机号">
          <template slot-scope="{row}">
            <el-tag>{{ row.pwd_user_mobile }}</el-tag>
          </template>
        </el-table-column>
      </el-table>
      <span slot="footer" class="dialog-footer">
        <el-button v-waves type="primary" icon="el-icon-refresh" @click="refreshPwdInfo()">刷新</el-button>
        <el-button v-waves type="primary" @click="addPwdHander()">新增自定义密码</el-button>
        <el-button v-waves @click="dialogPwdVisible = false">关 闭</el-button>
      </span>
      <!-- 内层 dialog 新增自定义密码 -->
      <el-dialog title="新增自定义密码" :visible.sync="addinnerVisible" append-to-body>
        <el-form ref="pwdForm" :rules="pwdRules" :model="addPwdParams" label-width="170px" label-position="right">
          <el-form-item label="门锁编码:" prop="lock_no">
            <el-input v-model="addPwdParams.lock_no" disabled placeholder="门锁编码" />
          </el-form-item>
          <el-form-item label="门锁密码:" prop="pwd_text">
            <el-input v-model="addPwdParams.pwd_text" placeholder="门锁密码" @blur="checkPwd()" />
          </el-form-item>
          <el-form-item label="是否校验密码相似性:" prop="similarity_check">
            <el-switch v-model="addPwdParams.similarity_check" active-text="true" inactive-text="false" />
          </el-form-item>
          <el-form-item label="密码有效期(起):" prop="valid_time_start">
            <el-date-picker v-model="addPwdParams.valid_time_start" type="datetime" />
          </el-form-item>
          <el-form-item label="密码有效期(止):" prop="valid_time_end">
            <el-date-picker v-model="addPwdParams.valid_time_end" type="datetime" />
          </el-form-item>
          <el-form-item label="使用人姓名:" prop="pwd_user_name">
            <el-input v-model="addPwdParams.pwd_user_name" placeholder="使用人姓名" />
          </el-form-item>
          <el-form-item label="使用人手机号:" prop="pwd_user_mobile">
            <el-input v-model="addPwdParams.pwd_user_mobile" placeholder="使用人手机号" />
          </el-form-item>
          <el-form-item label="使用人身份证号:" prop="pwd_user_idcard">
            <el-input v-model="addPwdParams.pwd_user_idcard" placeholder="二代身份证号" />
          </el-form-item>
          <el-form-item label="描述:" prop="description">
            <el-input v-model="addPwdParams.description" type="textarea" :rows="4" placeholder="长度不能大于 120 个字符" />
          </el-form-item>
          <el-form-item label="辅助信息:" prop="extra">
            <el-input v-model="addPwdParams.extra" type="textarea" :rows="4" placeholder="长度不能大于 1024 个字符" />
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button v-waves @click="addinnerVisible = false">
            取消
          </el-button>
          <el-button v-waves type="primary" @click="addPassword('pwdForm')">
            确认添加
          </el-button>
        </div>
      </el-dialog>
    </el-dialog>
  </div>
</template>

<script>
import { selectDoorLockList, selectDoorLockDetails, selectDoorLockPwdInfo, changePassword } from '@/api/doorlock'
import waves from '@/directive/waves' // waves directive
import { parseTime, formatTime } from '@/filters'
import Pagination from '@/components/Pagination' // secondary package based on el-pagination
import { validatePhoneTwo, validateValueIsEmity } from '@/utils/validate'

export default {
  name: 'ComplexTable',
  components: { Pagination },
  directives: { waves },
  filters: {
    parseTime,
    formatTime,
    lock_kind(val) {
      if (val === '0') {
        return '一代协议 433 门锁'
      } else if (val === '1') {
        return '蓝牙门锁'
      } else if (val === '3') {
        return '二代协议 433 门锁'
      } else {
        return '未知'
      }
    },
    lock_type(val) {
      if (val === '01') {
        return '蓝牙外门锁 A221'
      } else if (val === '02') {
        return '433 内门锁 120T'
      } else if (val === '03') {
        return '蓝牙内门锁 A121'
      } else if (val === '04') {
        return '433 外门锁 A220T'
      } else if (val === '05') {
        return '433 内门锁 A120Q'
      } else if (val === '32') {
        return '433 内门锁 A130'
      } else if (val === '48') {
        return '433 外门锁 A230 门锁'
      } else if (val === '49') {
        return '刷身份证外门锁 A230ID'
      } else {
        return '未知'
      }
    },
    open_door_status(val) {
      if (val === '0') {
        return '门开'
      } else if (val === '1') {
        return '门关'
      } else {
        return '未知'
      }
    },
    comu_status(val) {
      if (val === '00') {
        return '通信正常'
      } else if (val === '01') {
        return '通信异常'
      } else {
        return '未知'
      }
    },
    pwd_no(val, lock_type) {
      if (lock_type === '0') {
        if (val <= 1 && val <= 29) {
          return '在线自定义密码'
        } else {
          return '未知'
        }
      } else if (lock_type === '1') {
        if (val >= 31 && val <= 50) {
          return '一次性密码'
        } else if (val >= 81 && val <= 100) {
          return '在线自定义密码'
        } else if (val <= 101 && val <= 120) {
          return '离线时效密码'
        } else {
          return '未知'
        }
      } else if (lock_type === '3') {
        if (val <= 1 && val <= 29) {
          return '在线自定义密码'
        } else if (val <= 31 && val <= 38) {
          return '一次性密码'
        } else if (val <= 101 && val <= 108) {
          return '离线时效密码'
        } else {
          return '未知'
        }
      } else {
        return '未知'
      }
    },
    pwd_status(val) {
      if (val === '01') {
        return '启用中'
      } else if (val === '03') {
        return ' 删除中'
      } else if (val === '11') {
        return '已启用'
      } else if (val === '13') {
        return '已删除'
      } else if (val === '21') {
        return '启用失败'
      } else if (val === '23') {
        return '删除失败'
      } else {
        return '未知'
      }
    },
    magnet_flag(val) {
      if (val === 0) {
        return '不支持'
      } else if (val === 1) {
        return '支持'
      } else {
        return '未知'
      }
    }
  },
  data() {
    return {
      comu_statusList: [
        { text: '通信正常', value: '00' },
        { text: '通信异常', value: '01' }
      ],
      lock_kindList: [
        { text: '一代协议 433 门锁', value: '0' },
        { text: '蓝牙门锁', value: '1' },
        { text: '二代协议 433 门锁', value: '3' },
        { text: '未知', value: null }
      ],
      tableKey: 0,
      list: null,
      total: 1,
      listLoading: false,
      // 查询条件
      listQuery: {
        current_page: 1,
        page_size: 10,
        node_no: null,
        lock_no: null,
        house_code: null,
        room_code: null
      },
      // 门锁详细信息
      lockDetail: {
        lock_kind: null,
        type: null,
        software_version: null,
        new_software_version: null,
        hardware_version: null,
        lock_no: null,
        node_no: null,
        power: null,
        power_update_time: null,
        node_comu_status: null,
        comu_status: null,
        comu_status_update_time: null,
        rssi: null,
        region: null,
        address: null,
        house_code: null,
        room_code: null,
        magnet_flag: null,
        open_door_status: null,
        install_time: null,
        guarantee_time_start: null,
        guarantee_time_end: null,
        description: null
      },
      dialogVisible: false,
      // 门锁密码信息
      lockPwdList: [],
      lockPwdKey: 2,
      listPwdLoading: false,
      dialogPwdVisible: false,
      lock_type: null,
      // 新增自定义密码
      addinnerVisible: false,
      addPwdParams: {
        lock_no: null,
        pwd_text: null,
        similarity_check: true,
        valid_time_start: null,
        valid_time_end: null,
        pwd_user_name: null,
        pwd_user_mobile: null,
        pwd_user_idcard: null,
        description: null,
        extra: null
      },
      pwdRules: {
        lock_no: [{ required: true, message: '门锁编码必填', trigger: 'blur' }],
        valid_time_start: [{ required: true, message: '密码有效期（起）必填', trigger: 'change' }],
        valid_time_end: [{ required: true, message: '密码有效期（止）必填', trigger: 'change' }],
        description: [{ min: 0, max: 120, message: '描述长度只能在在 3 到 120 个字符之间', trigger: 'blur' }],
        extra: [{ min: 0, max: 1024, message: '辅助信息长度只能在 3 到 1024 个字符之间', trigger: 'blur' }],
        pwd_user_mobile: [
          { required: true, trigger: 'blur', validator: validateValueIsEmity },
          { required: true, trigger: 'blur', validator: validatePhoneTwo }
        ]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    handleFilter() {
      this.listQuery.current_page = 1
      this.getList()
    },
    // 获取分页门锁列表的数据
    getList() {
      this.listLoading = true
      selectDoorLockList(this.listQuery).then(response => {
        console.log(response)
        if (response.data.rows === null || response.data.total === 0) {
          this.$notify({
            title: '成功',
            message: '请求成功，但未找到响应门锁，请查看搜索参数是否正确！',
            type: 'success',
            duration: 2000
          })
          this.list = response.data.rows
          this.total = response.data.total
          this.listLoading = false
        } else {
          this.list = response.data.rows
          this.total = response.data.total
          this.listLoading = false
        }
      }).catch((error) => {
        console.log(error)
        this.$notify({
          title: '失败',
          message: '请求失败，请查看搜索参数是否正确！',
          type: 'error',
          duration: 2000
        })
        this.listLoading = false
      })
    },
    // 获取门锁详情的数据
    getDetails(row) {
      var detailsrequest = {
        lock_no: row.lock_no
      }
      selectDoorLockDetails(detailsrequest).then(response => {
        this.dialogVisible = true
        this.lockDetail = response.data
      })
    },
    // 获取门锁密码的数据
    getPwdInfo(row) {
      this.lock_type = row.lock_kind
      var detailsrequest = {
        lock_no: row.lock_no
      }
      selectDoorLockPwdInfo(detailsrequest).then(response => {
        this.dialogPwdVisible = true
        this.lockPwdList = response.data
      })
    },
    // 刷新门锁信息
    refreshPwdInfo() {
      var detailsrequest = {
        lock_no: this.lockPwdList[0].lock_no
      }
      selectDoorLockPwdInfo(detailsrequest).then(response => {
        this.lockPwdList = response.data
      })
    },
    // 添加自定义密码
    addPwdHander() {
      this.addPwdParams.lock_no = this.lockPwdList[0].lock_no
      this.addinnerVisible = true
    },
    // 给门锁新增自定义密码
    addPassword(form) {
      this.$refs[form].validate((valid) => {
        if (valid) {
          console.log(this.addPwdParams.valid_time_start, this.addPwdParams.valid_time_end)
          if (this.addPwdParams.valid_time_start > this.addPwdParams.valid_time_end) {
            this.$message({ type: 'error', message: '密码有效期（止）小于 密码有效期（起）!' })
            return false
          }
          changePassword(this.addPwdParams).then((response) => {
            const result = response.data
            if (result === null) {
              this.$message({ type: 'error', message: '添加自定义密码失败，请重试!' })
            } else {
              this.$message({ type: 'success', message: '添加自定义密码成功，请稍后【刷新】密码信息查看!' })
              this.addinnerVisible = false
            }
            this.insertTemplate.TemplateCode = null
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '添加自定义密码异常，请重试!'
            })
          })
        } else {
          return false
        }
      })
    },
    // 检查密码是否合格
    checkPwd() {
      if (this.lock_type === '1') {
        if (this.addPwdParams.pwd_text !== null && this.addPwdParams.pwd_text.length !== 0) {
          if (!(this.addPwdParams.pwd_text.length >= 6 && this.addPwdParams.pwd_text.length <= 16)) {
            this.$message({ type: 'error', message: '蓝牙门锁需要 6~16 位密码!' })
          }
        } else {
          this.$message({ type: 'info', message: '密码若为空，随机生成 6~7 位密码!' })
        }
      } else {
        if (this.addPwdParams.pwd_text !== null && this.addPwdParams.pwd_text.length !== 0) {
          if (!(this.addPwdParams.pwd_text.length >= 4 && this.addPwdParams.pwd_text.length <= 16)) {
            this.$message({ type: 'error', message: '433门锁需要 4~16 位密码!' })
          }
        } else {
          this.$message({ type: 'info', message: '密码若为空，随机生成 6~7 位密码!' })
        }
      }
    },
    // 通讯状态过滤
    filterComu_status(value, row) {
      return row.comu_status === value
    },
    filterLock_kind(value, row) {
      return row.lock_kind === value
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
    width: 30%;
  }
  .el-form--label-left .el-form-item__label {
    text-align: right;
  }
</style>
