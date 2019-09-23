<template>
  <div class="appcontainer">
    <el-tabs @tab-click="handleClick">
      <el-tab-pane id="pane-0" label="签名中心">
        <span slot="label">
          <i class="el-icon-edit-outline" /> 签名管理
        </span>
        <!-- 签名搜索与添加 -->
        <div class="filter-container">
          <el-input v-model.trim="listSignQuery.name" type="text" :min="1" placeholder="请输入签名名称搜索" style="width:300px" class="filter-item" @keyup.enter.native="getQuerySmsSign" />
          <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" @click="getQuerySmsSign">
            搜索
          </el-button>
          <el-button v-waves class="filter-item" type="primary" icon="el-icon-edit" @click="handleSignCreate('signForm')">
            添加签名
          </el-button>
          <el-button v-waves class="filter-item" type="primary" icon="el-icon-refresh" @click="getQuerySmsSign">
            刷新
          </el-button>
        </div>
        <!-- 签名列表 -->
        <el-table
          :key="tableSignKey"
          v-loading="signListLoading"
          :data="listSign"
          border
          fit
          stripe
          highlight-current-row
          style="width: 100%;"
        >
          <el-table-column label="ID" type="index" align="center" />
          <el-table-column label="签名名称" prop="signName" column-key="signName" align="center">
            <template slot-scope="scope">
              <span>{{ scope.row.signName }}</span>
            </template>
          </el-table-column>
          <el-table-column label="审核状态" prop="signStatus" column-key="signStatus" align="center" :filter-method="filterSignStatus" :filters="StatusList" filter-placement="bottom-end">
            <template slot-scope="scope">
              <el-tag :type="scope.row.signStatus | filterStatusType ">{{ scope.row.signStatus | filterStatusText }}</el-tag>
            </template>
          </el-table-column>
          <el-table-column label="响应信息" prop="message" align="center">
            <template slot-scope="scope">
              <el-tooltip class="item" effect="light" content="点击查看详细信息" placement="top">
                <el-button type="text" size="mini" @click="detailMsg(scope.row)">{{ scope.row.message }}</el-button>
              </el-tooltip>
            </template>
          </el-table-column>
          <el-table-column label="审核备注" prop="reason" align="center">
            <template slot-scope="scope">
              <el-tooltip v-if="scope.row.reason.length > 0" class="item" effect="light" content="点击查看详细信息" placement="top">
                <el-button type="text" size="mini" @click="detailMsg(scope.row)">{{ scope.row.reason }}</el-button>
              </el-tooltip>
            </template>
          </el-table-column>
          <el-table-column label="创建时间" prop="createDate" align="center">
            <template slot-scope="scope">
              <span>{{ scope.row.createDate | parseTime }}</span>
            </template>
          </el-table-column>
          <el-table-column label="操作" align="center" class-name="small-padding">
            <template slot-scope="{row}">
              <el-button type="danger" size="mini" @click="deleteSignData(row)">
                删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>
        <!-- 分页插件 -->
        <pagination v-show="totalSign>0" :total="totalSign" :page.sync="listSignQuery.page" :limit.sync="listSignQuery.limit" @pagination="getQuerySmsSign" />
      </el-tab-pane>
      <!-- 添加签名 -->
      <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogSignVisible">
        <el-form ref="signForm" :rules="rulesSign" :model="insertSign" label-position="center">
          <el-form-item label="请填写签名名称:" prop="SignName">
            <el-input v-model.trim="insertSign.SignName" placeholder="在阿里云平台申请的签名名称" />
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button v-waves @click="dialogSignVisible = false">
            取消
          </el-button>
          <el-button v-waves type="primary" @click="insertSignData('signForm')">
            确认添加
          </el-button>
        </div>
      </el-dialog>
      <el-tab-pane id="pane-1" label="模板中心">
        <span slot="label">
          <i class="el-icon-tickets" /> 模板管理
        </span>
        <!-- 模板搜索与添加 -->
        <div class="filter-container">
          <el-input v-model.trim="listTemplateQuery.TemplateCode" type="text" :min="1" placeholder="请输入模版名称或模版CODE搜索" style="width:300px" class="filter-item" @keyup.enter.native="getQuerySmsTemplate" />
          <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" @click="getQuerySmsTemplate">
            搜索
          </el-button>
          <el-button v-waves class="filter-item" type="primary" icon="el-icon-edit" @click="handleTemplateCreate('temForm')">
            添加模板
          </el-button>
          <el-button v-waves class="filter-item" type="primary" icon="el-icon-refresh" @click="getQuerySmsTemplate">
            刷新
          </el-button>
        </div>
        <!-- 模板列表 -->
        <el-table
          :key="tableTemplateKey"
          v-loading="templateListLoading"
          :data="listTemplate"
          border
          fit
          stripe
          highlight-current-row
          style="width: 100%;"
        >
          <el-table-column label="ID" type="index" align="center" />
          <el-table-column label="模版名称" prop="templateName" column-key="templateName" align="center">
            <template slot-scope="scope">
              <span>{{ scope.row.templateName }}</span>
            </template>
          </el-table-column>
          <el-table-column label="模版CODE" prop="templateCode" align="center">
            <template slot-scope="scope">
              <span>{{ scope.row.templateCode }}</span>
            </template>
          </el-table-column>
          <el-table-column label="模版类型" prop="templateType" column-key="templateType" align="center" :filter-method="filterTemplateType" :filters="TemplateTypeList" filter-placement="bottom-end">
            <template slot-scope="scope">
              {{ scope.row.templateType | filterTemplateType }}
            </template>
          </el-table-column>
          <el-table-column label="响应信息" prop="message" align="center">
            <template slot-scope="scope">
              <el-button type="text" size="mini" @click="detailMsg(scope.row)">{{ scope.row.message }}</el-button>
              <el-tooltip class="item" effect="light" content="点击查看详细信息" placement="right">
                <el-button type="success" size="mini" @click="detailMsg(scope.row)">详情</el-button>
              </el-tooltip>
            </template>
          </el-table-column>
          <el-table-column label="创建时间" prop="createDate" align="center">
            <template slot-scope="scope">
              <span>{{ scope.row.createDate | parseTime }}</span>
            </template>
          </el-table-column>
          <el-table-column label="审核状态" prop="templateStatus" column-key="templateStatus" align="center" :filter-method="filterTemplateStatus" :filters="StatusList" filter-placement="bottom-end">
            <template slot-scope="scope">
              <el-tag :type="scope.row.templateStatus | filterStatusType ">{{ scope.row.templateStatus | filterStatusText }}</el-tag>
            </template>
          </el-table-column>
          <el-table-column label="操作" align="center" class-name="small-padding">
            <template slot-scope="{row}">
              <!-- <el-button :disabled="row.templateStatus !== 1" type="primary" size="mini" @click="handleTemplateUpdate(row, 'temUpdateForm')">
                编辑
              </el-button> -->
              <el-button v-if="row.status!='deleted'" size="mini" type="danger" @click="deleteTemplateData(row)">
                删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>
        <!-- 分页插件 -->
        <pagination v-show="totalTem>0" :total="totalTem" :page.sync="listTemplateQuery.page" :limit.sync="listTemplateQuery.limit" @pagination="getQuerySmsTemplate" />
      </el-tab-pane>
      <!-- 添加或修改模板 -->
      <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogTemplateVisible">
        <el-form v-if="textMap[dialogStatus] === textMap.insertTemplate" ref="temForm" :rules="rulesTemplate" :model="insertTemplate" label-position="center">
          <el-form-item label="请填写模板CODE:" prop="TemplateCode">
            <el-input id="TemplateCode" v-model.trim="insertTemplate.TemplateCode" placeholder="在阿里云平台申请的模板CODE">
              <template slot="prepend">SMS_</template>
            </el-input>
          </el-form-item>
        </el-form>
        <!-- <el-form v-if="textMap[dialogStatus] === textMap.updateTemplate" ref="temUpdateForm" :rules="updaterRulesTemplate" :model="updateTemplate" label-position="center">
          <el-form-item :label="'将模板名称为【' + templateName + '】的模板名称修改为:'" prop="TemplateName">
            <el-input id="TemplateName" v-model.trim="updateTemplate.TemplateName" placeholder="请填写修改后模板名称" />
          </el-form-item>
        </el-form> -->
        <div slot="footer" class="dialog-footer">
          <el-button v-waves @click="dialogTemplateVisible = false">
            取消
          </el-button>
          <el-button v-waves type="primary" @click="textMap[dialogStatus] === textMap.insertTemplate ? insertTemplateData('temForm') : updateTemplateData('temUpdateForm') ">
            {{ textMap[dialogStatus] === textMap.insertTemplate ? '确认添加' : '确认修改' }}
          </el-button>
        </div>
      </el-dialog>
      <el-tab-pane id="pane-2" label="消息中心">
        <span slot="label">
          <i class="el-icon-time" /> 发送记录
        </span>
        <!-- 发送记录搜索 -->
        <div class="filter-container">
          <el-input v-model.number="listMsgQuery.PhoneNumber" type="number" :minlength="11" :maxlength="11" placeholder="手机号码" style="width:200px" class="filter-item" auto-complete="on" @keyup.enter.native="getQuerySmsSendMsgDetail" />
          <el-date-picker v-model="listMsgQuery.SendDate" type="date" placeholder="发送时间" format="yyyy 年 MM 月 dd 日" />
          <el-button v-waves type="primary" class="filter-item" icon="el-icon-search" @click="getQuerySmsSendMsgDetail">
            搜索
          </el-button>
          <el-button v-waves class="filter-item" type="primary" icon="el-icon-refresh" @click="getQuerySmsSendMsgDetail">
            刷新
          </el-button>
        </div>
        <!-- 签名列表 -->
        <el-table
          :key="tableDetailKey"
          v-loading="detailListLoading"
          :data="listDetail"
          border
          fit
          stripe
          highlight-current-row
          style="width: 100%;"
        >
          <el-table-column label="ID" type="index" align="center" />
          <!-- <el-table-column label="手机号码" prop="phoneNum	" column-key="phoneNum" align="center">
            <template slot-scope="scope">
              <span>{{ scope.row.phoneNum }}</span>
            </template>
          </el-table-column> -->
          <!-- <el-table-column label="发送时间" prop="sendDate" align="center">
            <template slot-scope="scope">
              <span>{{ scope.row.sendDate | parseTime }}</span>
            </template>
          </el-table-column> -->
          <el-table-column label="模板类型" prop="templateCode" column-key="templateCode" align="center" :filter-method="filterSendCodeName" :filters="sendCodeNameList" filter-placement="bottom-end">
            <template slot-scope="scope">
              <el-tag type="success">{{ scope.row.templateCode | filterTemplateCodeNameType(sendCodeNameList) }}</el-tag>
            </template>
          </el-table-column>
          <el-table-column label="短信内容" prop="content" align="center">
            <template slot-scope="scope">
              <span>{{ scope.row.content }}</span>
            </template>
          </el-table-column>
          <el-table-column label="发送状态" prop="sendStatus" column-key="sendStatus" align="center" :filter-method="filterSendStatus" :filters="SendStatusList" filter-placement="bottom-end">
            <template slot-scope="scope">
              <el-tag :type="scope.row.sendStatus | filterSendStatusType">{{ scope.row.sendStatus | filterSendStatusText }}</el-tag>
            </template>
          </el-table-column>
          <el-table-column label="接收时间" prop="receiveDate" align="center">
            <template slot-scope="scope">
              <span>{{ scope.row.receiveDate | parseTime }}</span>
            </template>
          </el-table-column>
          <el-table-column label="操作" align="center" class-name="small-padding">
            <template slot-scope="{row}">
              <el-button type="success" size="mini" @click="CheckMsgReasion(row)">
                查看原因
              </el-button>
              <el-button :disabled="row.sendStatus !== '2'" type="danger" size="mini" @click="henderSendMsgAgain(row)">
                重新发送
              </el-button>
            </template>
          </el-table-column>
        </el-table>
        <!-- 分页插件 -->
        <pagination v-show="totaMsg>0" :total="totaMsg" :page.sync="listMsgQuery.page" :limit.sync="listMsgQuery.limit" @pagination="getQuerySmsSendMsgDetail" />
      </el-tab-pane>
      <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogMsgVisible">
        <el-form ref="signSelectForm" label-position="center" label-width="37%" style="width: 100%;">
          <el-form-item label="请选择签名:" prop="signName">
            <el-select v-model="signName" placeholder="请选择签名">
              <el-option v-for="item in listSign" :key="item.requestId" :label="item.signName" :value="item.signName" />
            </el-select>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogMsgVisible = false">
            取消
          </el-button>
          <el-button type="primary" @click="SendMsgAgain()">
            确认
          </el-button>
        </div>
      </el-dialog>
    </el-tabs>
    <el-dialog title="详细信息" :visible.sync="dialogVisible" width="50%">
      <div class="code">
        响应编码：{{ code }}
        <el-button type="text" size="small">
          <a href="https://help.aliyun.com/document_detail/101346.html" target="_blank">(错误码详见错误码列表)</a>
        </el-button>
      </div>
      <div class="messageInfo">
        响应信息：{{ messageInfo }}
      </div>
      <div class="reason">
        审核备注：{{ reason }}
      </div>
      <div v-if="showTemplateContent" class="templateContent">
        <div style="margin-top: 20px;">
          模版类型：{{ templateType | filterTemplateType }}
        </div>
        <div style="margin-top: 20px;">
          模版名称：{{ templateName }}
        </div>
        <div style="margin-top: 20px;">
          模版名称：{{ templateCode }}
        </div>
        <div style="margin-top: 20px;">
          模板内容：
        </div>
        <div style="margin-left: 65px;">
          {{ templateContent }}
        </div>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import waves from '@/directive/waves' // waves directive
// import { parseTime } from '@/utils'
import Pagination from '@/components/Pagination' // secondary package based on el-pagination
import { querySmsTemplate, querySmsSign, insertSmsSign, delSmsSign, insertSmsTemplate, delSmsTemplate, updateSmsTemplate, querySmsSendMsgDetail, selectTemplateCodeAndNameList, sendMsg } from '@/api/message'
import { validatePhoneOther } from '@/utils/validate'

export default {
  name: 'MessageTable',
  components: { Pagination },
  directives: { waves },
  filters: {
    filterStatusText(val) {
      if (val === 0) {
        return '审核中'
      } else if (val === 1) {
        return '审核通过'
      } else if (val === 2) {
        return '审核失败'
      } else {
        return '无状态'
      }
    },
    filterStatusType(val) {
      if (val === 0) {
        return 'danger'
      } else if (val === 1) {
        return 'success'
      } else if (val === 2) {
        return 'info'
      } else {
        return 'danger'
      }
    },
    filterSendStatusType(val) {
      if (val === '1') {
        return 'info'
      } else if (val === '2') {
        return 'danger'
      } else if (val === '3') {
        return 'success'
      } else {
        return 'danger'
      }
    },
    filterSendStatusText(val) {
      if (val === '1') {
        return '等待回执'
      } else if (val === '2') {
        return '发送失败'
      } else if (val === '3') {
        return '发送成功'
      } else {
        return val
      }
    },
    filterTemplateType(val) {
      if (val === 0) {
        return '验证码'
      } else if (val === 1) {
        return '短信通知'
      } else if (val === 2) {
        return '推广短信'
      } else if (val === 3) {
        return '国际/港澳台消息'
      } else {
        return val
      }
    },
    filterTemplateCodeNameType(val, sendCodeNameList) {
      for (const v of sendCodeNameList) {
        if (v.value === val) {
          return v.text
        }
      }
    }
  },
  data() {
    return {
      dialogVisible: false,
      messageInfo: null,
      code: null,
      reason: null,
      signName: null,
      templateContent: null,
      templateCode: null,
      templateName: null,
      templateType: null,
      showTemplateContent: false,
      StatusList: [
        { text: '审核中', value: 0 },
        { text: '审核通过', value: 1 },
        { text: '审核失败', value: 2 },
        { text: '无状态', value: null }
      ],
      TemplateTypeList: [
        { text: '验证码', value: 0 },
        { text: '短信通知', value: 1 },
        { text: '推广短信', value: 2 },
        { text: '国际/港澳台消息', value: 3 }
      ],
      SendStatusList: [
        { text: '等待回执', value: 1 },
        { text: '发送失败', value: 2 },
        { text: '发送成功', value: 3 }
      ],
      // 签名 tab
      listSignQuery: {
        page: 1,
        limit: 10,
        SignName: null
      },
      insertSign: {
        SignName: null
      },
      rulesSign: {
        SignName: [{ required: true, message: '请输入签名名称', trigger: 'blur' }]
      },
      tableSignKey: 0,
      signListLoading: false,
      listSign: [],
      totalSign: 0,
      dialogSignVisible: false,
      // 模板 tab
      listTemplateQuery: {
        page: 1,
        limit: 10,
        TemplateCode: null,
        TemplateName: null
      },
      insertTemplate: {
        TemplateCode: null
      },
      rulesTemplate: {
        TemplateCode: [{ required: true, message: '请输入模板CODE', trigger: 'blur' }]
      },
      updateTemplate: {
        TemplateName: null
      },
      updaterRulesTemplate: {
        TemplateName: [{ required: true, message: '请输入修改后的模板名称', trigger: 'blur' }]
      },
      updateTemplatePush: null,
      tableTemplateKey: 1,
      templateListLoading: false,
      listTemplate: [],
      totalTem: 0,
      dialogTemplateVisible: false,
      // 记录 tab
      tableDetailKey: 2,
      detailListLoading: false,
      dialogMsgVisible: false,
      listDetail: [],
      sendCodeNameList: [],
      sendParams: null,
      totaMsg: 0,
      listMsgQuery: {
        CurrentPage: 1,
        PageSize: 10,
        SendDate: null,
        PhoneNumber: null
      },
      msgQueryRules: {
        SendDate: [{ required: true, message: '请输入修改后的模板名称', trigger: 'blur' }],
        PhoneNumber: [{ required: true, message: '请输入修改后的模板名称', trigger: 'blur' }]
      },
      // el-dialog
      dialogStatus: '',
      textMap: {
        updateSign: '添加签名',
        insertTemplate: '添加模板',
        updateTemplate: '修改模板',
        chouseSign: '选择签名重新发送'
      }
    }
  },
  created() {
    this.getQuerySmsSign()
  },
  methods: {
    // 选项卡更改触发
    handleClick(tab, event) {
      if (tab.$el.id === 'pane-0') {
        this.getQuerySmsSign()
      } else if (tab.$el.id === 'pane-1') {
        this.getQuerySmsTemplate()
      } else if (tab.$el.id === 'pane-2') {
        this.selectTemplateCodeAndNameList()
      }
    },
    // 查询所有签名
    getQuerySmsSign() {
      this.signListLoading = true
      querySmsSign(this.listSignQuery).then(response => {
        this.totalSign = response.data.total
        this.listSign = response.data.records
        this.signListLoading = false
      })
    },
    // 查询所有模板
    getQuerySmsTemplate() {
      this.templateListLoading = true
      querySmsTemplate(this.listTemplateQuery).then(response => {
        this.totalTem = response.data.total
        this.listTemplate = response.data.records
        this.templateListLoading = false
      })
    },
    // 查询所有模板类型
    selectTemplateCodeAndNameList() {
      selectTemplateCodeAndNameList().then(response => {
        this.sendCodeNameList = response.data
      })
    },
    // 查询短信记录
    getQuerySmsSendMsgDetail() {
      this.detailListLoading = true
      var flag = validatePhoneOther(this.listMsgQuery.PhoneNumber)
      if (!flag) {
        this.$message({ type: 'warning', message: '手机号不正确，请检查手机号码!' })
        this.detailListLoading = flag
        return flag
      }
      if (this.listMsgQuery.SendDate === undefined || this.listMsgQuery.SendDate === null) {
        this.$message({ type: 'warning', message: '时间不正确，请选择发送时间!' })
        this.detailListLoading = false
        return false
      }
      querySmsSendMsgDetail(this.listMsgQuery).then(response => {
        const { code, message, totalCount, smsSendDetailDTOs } = response.data
        this.code = code
        this.messageInfo = message
        this.totaMsg = totalCount
        this.listDetail = smsSendDetailDTOs.smsSendDetailDTO
        this.detailListLoading = false
      })
    },
    // 添加签名
    handleSignCreate(form) {
      this.dialogStatus = 'updateSign'
      this.dialogSignVisible = true
      this.$nextTick(() => {
        this.$refs[form].clearValidate()
      })
    },
    insertSignData(form) {
      for (const v of this.listSign) {
        if (v.signName === this.insertSign.SignName) {
          this.$message({ type: 'info', message: '已存在无法新增!' })
          this.insertSign.SignName = null
          return false
        }
      }
      this.$refs[form].validate((valid) => {
        if (valid) {
          insertSmsSign(this.insertSign.SignName).then((response) => {
            const result = response.data
            if (result === null) {
              this.$message({ type: 'info', message: '此签名在阿里云短信服务中不存在，无法新增!' })
              this.dialogSignVisible = false
            } else {
              // 添加到第一个
              this.listSign.unshift(response.data)
              this.dialogSignVisible = false
            }
            this.insertSign.SignName = null
          }).catch((response) => {
            console.log(response)
            this.$message({
              type: 'info',
              message: '已取消删除或删除失败!'
            })
            this.dialogSignVisible = false
          })
        } else {
          return false
        }
      })
    },
    // 添加模板
    handleTemplateCreate(form) {
      this.dialogStatus = 'insertTemplate'
      this.dialogTemplateVisible = true
      this.$nextTick(() => {
        this.$refs[form].clearValidate()
      })
    },
    insertTemplateData(form) {
      this.$refs[form].validate((valid) => {
        if (valid) {
          if (this.insertTemplate.TemplateCode.indexOf('SMS_') < 0) {
            this.insertTemplate.TemplateCode = 'SMS_' + this.insertTemplate.TemplateCode
          }
          for (const v of this.listTemplate) {
            if (v.templateCode === this.insertTemplate.TemplateCode) {
              this.$message({ type: 'info', message: '已存在无法新增!' })
              this.insertTemplate.TemplateCode = null
              return false
            }
          }
          insertSmsTemplate(this.insertTemplate.TemplateCode).then((response) => {
            const result = response.data
            if (result === null) {
              this.$message({ type: 'info', message: '此模板在阿里云短信服务中不存在，无法新增!' })
              this.dialogTemplateVisible = false
            } else {
              // 添加到第一个
              this.listTemplate.unshift(result)
              this.dialogTemplateVisible = false
            }
            this.insertTemplate.TemplateCode = null
          }).catch((response) => {
            console.log(response)
            this.$message({
              type: 'info',
              message: '已取消删除或删除失败!'
            })
            this.dialogTemplateVisible = false
          })
        } else {
          return false
        }
      })
    },
    // 删除签名
    deleteSignData(row) {
      this.$confirm('此操作将永久删除该签名信息, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        delSmsSign(row.signName).then((response) => {
          const result = response.data
          if (result) {
            for (const v of this.listSign) {
              if (v.signName === row.signName) {
                const index = this.listSign.indexOf(row)
                this.listSign.splice(index, 1)
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
    // 删除模板
    deleteTemplateData(row) {
      this.$confirm('此操作将永久删除该模板信息, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        delSmsTemplate(row.templateCode).then((response) => {
          const result = response.data
          if (result) {
            for (const v of this.listTemplate) {
              if (v.templateCode === row.templateCode) {
                const index = this.listTemplate.indexOf(row)
                this.listTemplate.splice(index, 1)
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
    // 更新模板名称（废弃）
    handleTemplateUpdate(row, form) {
      this.dialogStatus = 'updateTemplate'
      this.templateName = row.templateName
      this.updateTemplatePush = Object.assign({}, row) // copy obj
      this.$nextTick(() => {
        this.$refs[form].clearValidate()
      })
      this.dialogTemplateVisible = true
    },
    // （废弃）
    updateTemplateData(form) {
      this.$refs[form].validate((valid) => {
        if (valid) {
          for (const v of this.listTemplate) {
            if (v.templateName === this.updateTemplate.TemplateName) {
              this.$message({ type: 'info', message: '您并没有修改!' })
              this.updateTemplate.TemplateName = null
              return false
            }
          }
          this.updateTemplatePush.templateName = this.updateTemplate.TemplateName
          updateSmsTemplate(this.updateTemplatePush).then((response) => {
            const result = response.data
            if (result === null) {
              this.$message({ type: 'info', message: '您并没有修改!' })
              this.dialogTemplateVisible = false
            } else {
              // 修改指定的
              for (const v of this.listTemplate) {
                if (v.templateCode === this.updateTemplatePush.TemplateCode) {
                  const index = this.listTemplate.indexOf(v)
                  this.listTemplate.splice(index, 1, this.updateTemplatePush)
                  break
                }
              }

              this.dialogTemplateVisible = false
            }
            this.updateTemplate.TemplateName = null
          }).catch((response) => {
            this.$message({
              type: 'info',
              message: '已取消修改或修改失败!'
            })
            this.dialogTemplateVisible = false
          })
        } else {
          return false
        }
      })
    },
    // 签名状态过滤
    filterSignStatus(value, row) {
      return row.signStatus === value
    },
    // 模板状态过滤
    filterTemplateStatus(value, row) {
      return row.templateStatus === value
    },
    // 模板状态类型过滤
    filterTemplateType(value, row) {
      return row.templateType === value
    },
    // 发送状态过滤
    filterSendStatus(value, row) {
      return row.sendStatus === value
    },
    // 发送模板类型
    filterSendCodeName(value, row) {
      return row.templateCode === value
    },
    detailMsg(row) {
      this.code = row.code
      this.messageInfo = row.message
      this.reason = row.reason
      if (row.templateContent !== undefined) {
        this.showTemplateContent = true
        this.templateContent = row.templateContent
        this.templateCode = row.templateCode
        this.templateName = row.templateName
        this.templateType = row.templateType
      } else {
        this.showTemplateContent = false
      }
      this.dialogVisible = true
    },
    // 查看短信发送失败原因
    CheckMsgReasion(row) {
      this.$alert('<div>请求状态码：<strong>' + this.code + ':' + this.messageInfo + '</strong><button class=\"el-button el-button--primary el-button--mini\" style=\"margin-left: 15px;\"><a href=\"https://help.aliyun.com/document_detail/101346.html\" target=\"_blank\">(错误码详见错误码列表)</a></button></div><div style=\"margin-top: 10px;\">运营商短信状态码：<strong>' + row.errCode + '</strong><button class=\"el-button el-button--primary el-button--mini\" style=\"margin-left: 5px;\"><a href=\"https://help.aliyun.com/document_detail/101347.html\" target=\"_blank\">(错误码详见错误码列表)</a></button></div>', '发送失败原因', {
        dangerouslyUseHTMLString: true
      })
    },
    // 重新发送此短信
    henderSendMsgAgain(row) {
      this.sendParams = {
        PhoneNumberJson: '[\"' + row.phoneNum + '\"]',
        SignNameJson: null,
        TemplateCode: row.templateCode
      }
      this.dialogStatus = 'chouseSign'
      this.dialogMsgVisible = true
    },
    SendMsgAgain() {
      var flag = this.signName !== null && this.signName.length > 0 && this.signName !== undefined
      if (!flag) {
        this.$message({ type: 'warning', message: '签名未选择，请选择签名!' })
        return flag
      }
      this.sendParams.SignNameJson = '[\"' + this.signName + '\"]'
      sendMsg(this.sendParams).then((response) => {
        const { code, message } = response.data
        if (code === 'OK') {
          this.$message({
            type: 'success',
            message: '信息发送成功，请刷新查看发送状态'
          })
          this.dialogMsgVisible = false
        } else {
          this.$message({
            type: 'info',
            message: '信息发送成功，失败原因：' + message
          })
        }
      }).catch(() => {
        this.$message({
          type: 'error',
          message: '重新发送信息异常!'
        })
      })
    }
  }
}
</script>
<style scoped>
  .el-tabs--border-card {
    -webkit-box-shadow: 0 0px 0px 0 rgba(0,0,0,0), 0 0 0px 0 rgba(0,0,0,0);
    box-shadow: 0 0px 0px 0 rgba(0,0,0,0), 0 0 0px 0 rgba(0,0,0,0);
    border-bottom-width: 0px;
  }
</style>
<style>
  .appcontainer {
    margin-top: 2px;
  }
  .el-tabs__nav-scroll {
    margin-left: 20px;
  }
  .el-tabs__content {
    margin-left: 20px;
    margin-right: 20px;
  }
  .filter-container {
    margin-bottom: 20px;
  }
  .code {
    margin-top: -10px;
  }
  .messageInfo {
    margin-top: 10px;
  }
  .reason {
    margin-top: 20px;
  }
  .templateContent {
    margin-top: 20px;
  }
</style>
