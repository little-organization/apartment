<template>
  <div class="dashboard-container">
    <el-tabs @tab-click="handleClick">
      <el-tab-pane id="pane-0" label="个人信息">
        <span slot="label">
          <i class="el-icon-edit-outline" /> 个人信息
        </span>
        <el-form ref="ruleForm" :model="ruleForm" :rules="rules" label-width="100px" style="width: 50%;" class="demo-ruleForm">
          <el-form-item label="活动名称" prop="name">
            <el-input v-model="ruleForm.name" />
          </el-form-item>
          <el-form-item label="活动区域" prop="region">
            <el-select v-model="ruleForm.region" placeholder="请选择活动区域">
              <el-option label="区域一" value="shanghai" />
              <el-option label="区域二" value="beijing" />
            </el-select>
          </el-form-item>
          <el-form-item label="活动时间" required>
            <el-col :span="11">
              <el-form-item prop="date1">
                <el-date-picker v-model="ruleForm.date1" type="date" placeholder="选择日期" style="width: 100%;" />
              </el-form-item>
            </el-col>
            <el-col class="line" :span="2">-</el-col>
            <el-col :span="11">
              <el-form-item prop="date2">
                <el-time-picker v-model="ruleForm.date2" type="fixed-time" placeholder="选择时间" style="width: 100%;" />
              </el-form-item>
            </el-col>
          </el-form-item>
          <el-form-item label="即时配送" prop="delivery">
            <el-switch v-model="ruleForm.delivery" />
          </el-form-item>
          <el-form-item label="活动性质" prop="type">
            <el-checkbox-group v-model="ruleForm.type">
              <el-checkbox label="美食/餐厅线上活动" name="type" />
              <el-checkbox label="地推活动" name="type" />
              <el-checkbox label="线下主题活动" name="type" />
              <el-checkbox label="单纯品牌曝光" name="type" />
            </el-checkbox-group>
          </el-form-item>
          <el-form-item label="特殊资源" prop="resource">
            <el-radio-group v-model="ruleForm.resource">
              <el-radio label="线上品牌商赞助" />
              <el-radio label="线下场地免费" />
            </el-radio-group>
          </el-form-item>
          <el-form-item label="活动形式" prop="desc">
            <el-input v-model="ruleForm.desc" type="textarea" />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
            <el-button @click="resetForm('ruleForm')">重置</el-button>
          </el-form-item>
        </el-form>
      </el-tab-pane>
      <el-tab-pane v-if="checkPermission(['超级管理员', '系统管理员'])" id="pane-1" label="管理人员">
        <span slot="label">
          <i class="el-icon-tickets" /> 管理人员
        </span>
        <div class="app-container">
          <!-- 系统管理员 can see this -->
          <admin v-permission="['超级管理员']">超级管理员</admin>
          <!-- 系统管理员 can see this -->
          <system v-permission="['系统管理员']">系统管理员</system>
          <!-- 租户 can see this -->
          <holder v-permission="['租户']">租户</holder>
          <!-- 租户 can see this -->
          <aftersales v-permission="['售后人员']">Both 系统管理员 or 租户 can see this</aftersales>
        </div>
      </el-tab-pane>
    </el-tabs>
    <!-- <el-dialog title="详细信息" :visible.sync="dialogVisible" width="50%">
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
    </el-dialog> -->
  </div>
</template>

<script>
import { Admin, Holder, Aftersales, System } from './components'
import permission from '@/directive/permission/index.js' // 权限判断指令
import checkPermission from '@/utils/permission' // 权限判断函数
import { mapGetters } from 'vuex'

export default {
  name: 'Dashboard',
  directives: { permission },
  components: {
    Admin,
    Holder,
    Aftersales,
    System
  },
  data() {
    return {
      ruleForm: {
        name: '',
        region: '',
        date1: '',
        date2: '',
        delivery: false,
        type: [],
        resource: '',
        desc: ''
      },
      rules: {
        name: [
          { required: true, message: '请输入活动名称', trigger: 'blur' },
          { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
        ],
        region: [{ required: true, message: '请选择活动区域', trigger: 'change' }],
        date1: [{ type: 'date', required: true, message: '请选择日期', trigger: 'change' }],
        date2: [{ type: 'date', required: true, message: '请选择时间', trigger: 'change' }],
        type: [{ type: 'array', required: true, message: '请至少选择一个活动性质', trigger: 'change' }],
        resource: [{ required: true, message: '请选择活动资源', trigger: 'change' }],
        desc: [{ required: true, message: '请填写活动形式', trigger: 'blur' }]
      }
    }
  },
  computed: {
    ...mapGetters([
      'userInfo'
    ])
  },
  methods: {
    checkPermission,
    // 选项卡更改触发
    handleClick(tab, event) {
      if (tab.$el.id === 'pane-0') {
        console.log(0)
      } else if (tab.$el.id === 'pane-1') {
        console.log(1)
      }
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          alert('submit!')
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    resetForm(formName) {
      this.$refs[formName].resetFields()
    }
  }
}
</script>
<style>
  .dashboard-container {
    margin-top: 2px;
  }
  .el-tabs__nav-scroll {
    margin-left: 20px;
  }
</style>
