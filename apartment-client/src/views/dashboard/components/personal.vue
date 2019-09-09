<template>
  <div class="persinal">
    <el-form :model="userInfo" status-icon class="userInfo" label-position="top">
      <el-form-item label="您的用户名: " prop="username">
        <el-input v-model.trim="userInfo.username" :disabled="true">
          <span slot="prepend" class="svg-container">
            <svg-icon icon-class="people" />
          </span>
          <el-tooltip slot="append" class="item" effect="light" content="点击修改" placement="top">
            <span @click="showUsername">
              <svg-icon icon-class="edit" />
            </span>
          </el-tooltip>
        </el-input>
      </el-form-item>
      <el-form-item label="您的手机号: " prop="phone">
        <el-input v-model.number="userInfo.phone" :disabled="true" type="number" :minlength="11" :maxlength="11" placeholder="手机号码" class="filter-item">
          <span slot="prepend" class="svg-container">
            <i class="el-icon-phone" />
          </span>
          <el-tooltip slot="append" class="item" effect="light" content="点击修改" placement="top">
            <span @click="showPhone">
              <svg-icon icon-class="edit" />
            </span>
          </el-tooltip>
        </el-input>
      </el-form-item>
      <el-form-item label="您的账户密码: " prop="password">
        <el-input :key="passwordType" ref="password" v-model="userInfo.password" :disabled="true" :type="passwordType" placeholder="密码" name="password" tabindex="2">
          <span slot="prepend" class="svg-container">
            <svg-icon icon-class="password" />
          </span>
          <el-tooltip slot="append" class="item" effect="light" content="点击修改" placement="top">
            <span @click="showPassword">
              <svg-icon icon-class="edit" />
            </span>
          </el-tooltip>
        </el-input>
      </el-form-item>
      <el-form-item label="您的角色: " prop="roleNote">
        <el-input v-model.trim="userInfo.roleNote" :disabled="true">
          <span slot="prepend" class="svg-container">
            <svg-icon icon-class="example" />
          </span>
        </el-input>
      </el-form-item>
    </el-form>
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogVisible" align-center>
      <el-steps :active="stepActive" finish-status="success" simple>
        <el-step title="验证" />
        <el-step title="修改" />
      </el-steps>
      <!-- 密码验证 -->
      <el-form v-if="stepActive === 1" ref="formPwd" :rules="rulePwd" :model="userPassword" status-icon class="stepsDown" label-position="top">
        <el-form-item label="您现在的账户密码: " prop="password">
          <el-input :key="passwordType" ref="password" v-model.trim="userPassword.password" :type="passwordType" placeholder="密码" name="password" tabindex="2">
            <span slot="append" class="show-pwd" @click="showPwd">
              <svg-icon :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'" />
            </span>
          </el-input>
        </el-form-item>
      </el-form>
      <!-- 修改的内容 -->
      <div v-if="dialogStatus==='updateUsername' && stepActive === 2" class="stepsDown">
        <el-form ref="formName" :rules="ruleName" :model="userName" status-icon label-position="top">
          <el-form-item label="您新的账户名称: " prop="username">
            <el-input ref="username" v-model.trim="userName.username" placeholder="新的账户名称" />
          </el-form-item>
        </el-form>
      </div>
      <div v-if="dialogStatus==='updatePassword' && stepActive === 2" class="stepsDown">
        <el-form ref="formPwdNew" :rules="rulePwdNew" :model="userPasswordNew" status-icon label-position="top">
          <el-form-item label="您新的账户密码: " prop="password">
            <el-input :key="passwordType" v-model.trim="userPasswordNew.password" :type="passwordType" placeholder="请输入密码">
              <span slot="append" class="show-pwd" @click="showPwd">
                <svg-icon :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'" />
              </span>
            </el-input>
          </el-form-item>
          <el-form-item label="请再次输入账户密码: " prop="againpassword">
            <el-input :key="passwordType" v-model.trim="userPasswordNew.againpassword" :type="passwordType" placeholder="请次输入密码">
              <span slot="append" class="show-pwd" @click="showPwd">
                <svg-icon :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'" />
              </span>
            </el-input>
          </el-form-item>
        </el-form>
      </div>
      <div v-if="dialogStatus==='updatePhone' && stepActive === 2" class="stepsDown">
        <el-form ref="formPhone" :rules="rulePhone" :model="userPhone" status-icon label-position="top">
          <el-form-item label="您新的手机号: " prop="phone">
            <el-input v-model.trim="userPhone.phone" :disabled="disabled" placeholder="手机号码">
              <el-button slot="append" type="primary" icon="el-icon-message" :disabled="disabled" @click="sendBarcode">
                <template v-if="sending">获取验证码</template>
                <template v-else>{{ second }}秒后重发</template>
              </el-button>
            </el-input>
          </el-form-item>
          <el-form-item label="验证码: " prop="barcode">
            <el-input v-model.trim="userPhone.barcode" placeholder="验证码" />
          </el-form-item>
        </el-form>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogChanelClick">
          取消
        </el-button>
        <el-button type="primary" @click="stepActive===1?nextStep():updateUserData()">
          {{ stepActive === 1 ? '下一步' : '确认修改' }}
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import { isPassword, validatePhone, validateValueIsEmity, validatePhoneOther } from '@/utils/validate'
import { checkPassword, checkUsernameHave, changePassword, changeUsername, changePhone } from '@/api/user'
import { getCode } from '@/api/message'

export default {
  name: 'Persinal',
  data() {
    return {
      // 用户密码
      userPassword: {
        id: null,
        password: null
      },
      rulePwd: {
        password: [
          { validator: isPassword, trigger: 'blur' }
        ]
      },
      // 用户名称
      userName: {
        id: null,
        username: null
      },
      ruleName: {
        username: [
          { trigger: 'blur', validator: validateValueIsEmity },
          { min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur' }
        ]
      },
      userPasswordNew: {
        id: null,
        password: null,
        againpassword: null
      },
      rulePwdNew: {
        password: [
          { trigger: 'blur', validator: validateValueIsEmity },
          { trigger: 'blur', validator: isPassword },
          { min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur' }
        ],
        againpassword: [
          { trigger: 'blur', validator: validateValueIsEmity },
          { trigger: 'blur', validator: isPassword },
          { min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur' }
        ]
      },
      // 用户手机号
      // 是否发送验证码
      sending: true,
      // 是否禁发验证码
      disabled: false,
      // 倒计时间
      second: 60,
      userPhone: {
        id: null,
        phone: null,
        barcode: null,
        userId: null
      },
      rulePhone: {
        phone: [
          { trigger: 'blur', validator: validatePhone }
        ],
        barcode: [
          { trigger: 'blur', validator: validateValueIsEmity },
          { min: 6, max: 6, message: '长度6个数字', trigger: 'blur' }
        ]
      },
      passwordType: 'password',
      // el-dialog
      dialogVisible: false,
      stepActive: 1,
      dialogStatus: '',
      textMap: {
        updateUsername: '修改您的账号名称',
        updatePassword: '修改您的账号密码',
        updatePhone: '修改您的手机号码'
      }
    }
  },
  computed: {
    ...mapGetters([
      'userInfo'
    ])
  },
  created() {
    // this.changeDialogWidth()
  },
  methods: {
    showPwd() {
      if (this.passwordType === 'password') {
        this.passwordType = ''
      } else {
        this.passwordType = 'password'
      }
      this.$nextTick(() => {
        this.$refs.password.focus()
      })
    },
    showUsername() {
      this.dialogVisible = true
      this.dialogStatus = 'updateUsername'
      this.$nextTick(() => {
        this.$refs.password.focus()
      })
    },
    showPassword() {
      this.dialogVisible = true
      this.dialogStatus = 'updatePassword'
      this.$nextTick(() => {
        this.$refs.password.focus()
      })
    },
    showPhone() {
      this.dialogVisible = true
      this.dialogStatus = 'updatePhone'
      this.$nextTick(() => {
        this.$refs.password.focus()
      })
    },
    dialogChanelClick() {
      this.dialogVisible = false
      this.stepActive = 1
      this.dialogStatus = ''
      this.userPassword.password = null
    },
    // 点击下一步验证密码
    nextStep() {
      this.$refs['formPwd'].validate((valid) => {
        if (valid) {
          this.userPassword.id = this.userInfo.id
          checkPassword(this.userPassword).then((response) => {
            const result = response.data
            if (result) {
              this.stepActive = 2
            } else {
              this.$message({ type: 'error', message: '您输入的密码错误!' })
            }
          }).catch((response) => {
            this.$message({ type: 'info', message: '检查密码异常!' })
          })
        } else {
          console.log('密码验证失败！')
          return false
        }
      })
    },
    updateUserData() {
      // 修改手机号，密码，用户名
      if (this.dialogStatus === 'updatePhone') {
        // 修改手机号
        this.$refs['formPhone'].validate((valid) => {
          if (valid) {
            this.userPhone.id = this.userInfo.id
            this.userPhone.userId = this.userInfo.userId
            changePhone(this.userPhone).then((response) => {
              const result = response.data
              if (result) {
                this.$message({ type: 'success', message: '手机号修改成功!' })
                this.userInfo.phone = this.userPhone.phone
                this.dialogVisible = false
                this.stepActive = 1
                this.dialogStatus = ''
                this.userInfo.password = null
                this.$refs.formPhone.clearFiles()
              } else {
                this.$message({ type: 'error', message: '修改手机号失败!' })
              }
            })
          } else {
            console.log('手机号验证失败！')
            return false
          }
        })
      } else if (this.dialogStatus === 'updatePassword') {
        // 修改密码
        this.$refs['formPwdNew'].validate((valid) => {
          if (valid) {
            if (this.userPassword.password === this.userPasswordNew.password) {
              this.$message({ type: 'warning', message: '您并没有修改密码!' })
              return false
            } else if (this.userPasswordNew.password !== this.userPasswordNew.againpassword) {
              this.$message({ type: 'warning', message: '您两次输入的密码不一致!' })
              return false
            } else {
              var finalPassword = {
                id: this.userInfo.id,
                password: this.userPasswordNew.againpassword
              }
              changePassword(finalPassword).then((response) => {
                const result = response.data
                if (result) {
                  this.$message({ type: 'success', message: '密码修改成功!' })
                  this.dialogVisible = false
                  this.stepActive = 1
                  this.dialogStatus = ''
                  this.userInfo.password = null
                  this.userPasswordNew.password = null
                  this.userPasswordNew.againpassword = null
                } else {
                  this.$message({ type: 'error', message: '密码修改失败!' })
                }
              })
            }
          } else {
            console.log('密码验证失败！')
            return false
          }
        })
      } else if (this.dialogStatus === 'updateUsername') {
        // 修改用户名
        this.$refs['formName'].validate((valid) => {
          if (valid) {
            this.userName.id = this.userInfo.id
            checkUsernameHave(this.userName).then((response) => {
              const result = response.data
              if (!result) {
                this.changeUsername(this.userName)
              } else {
                this.$message({ type: 'error', message: '您输入新账号名称已存在!' })
              }
            }).catch((response) => {
              this.$message({ type: 'info', message: '检查账号名称异常!' })
            })
          } else {
            console.log('密码验证失败！')
            return false
          }
        })
      }
    },
    // 修改用户名
    changeUsername(userName) {
      changeUsername(userName).then((response) => {
        const result = response.data
        if (result) {
          this.userInfo.username = userName.username
          this.$message({ type: 'success', message: '修改用户名成功!' })
          this.logout()
        } else {
          this.$message({ type: 'error', message: '修改用户名失败!' })
        }
      }).catch((response) => {
        this.$message({ type: 'info', message: '修改用户名异常!' })
      })
    },
    // 退出登录
    async logout() {
      await this.$store.dispatch('user/logout')
      this.$router.push(`/login?redirect=${this.$route.fullPath}`)
    },
    // 发送验证码
    sendBarcode() {
      if (this.userPhone.phone === this.userInfo.phone) {
        this.$message({ type: 'warning', message: '您并没有修改手机号!' })
        return false
      }
      var flag = validatePhoneOther(this.userPhone.phone)
      if (!flag) {
        this.$message({ type: 'warning', message: '手机号不正确，请检查手机号码!' })
        return flag
      }
      // this.sending原为true,请求成功，!this.sending == true，主要是防止有人把disabled属性去掉，多次点击；
      if (!this.sending) {
        return false
      }
      this.sending = false
      var paramsSendCode = {
        phone: this.userPhone.phone,
        username: this.userInfo.username
      }
      getCode(paramsSendCode).then((response) => {
        const result = response.data
        if (result) {
          this.$message({ type: 'success', message: '短信发送成功!' })
          this.disabled = true
          var tomeDown = setInterval(() => {
            this.second--
            if (this.second < 0) {
              clearInterval(tomeDown)
              this.sending = true
              this.disabled = false
              this.second = 60
            }
          }, 1000)
        } else {
          this.$message({ type: 'warning', message: '短信发送失败，请重试!' })
          this.sending = true
          this.disabled = false
        }
      })
    }
  }
}
</script>

<style lang="scss" scoped>
  .persinal {
    margin-left: 20px;
    .userInfo {
      position: relative;
      width: 520px;
      max-width: 100%;
      padding: 10px 10px 0;
      margin: 0 auto;
      overflow: hidden;
    }
    .el-icon-phone {
      font-weight: 900;
    }
    .show-pwd {
      font-size: 15px;
    }
    .stepsDown {
      margin-top: 30px;
    }
  }
</style>
<style >
  .el-dialog {
    width: 70%;
  }
</style>
