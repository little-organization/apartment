<template>
  <div class="app-container">
    <el-steps :active="stepActive" simple>
      <el-step title="公寓" icon="el-icon-edit" />
      <el-step title="描述" icon="el-icon-upload" />
      <el-step title="图片" icon="el-icon-picture" />
    </el-steps>
    <div class="checkApartmentDescPic">
      <template>
        <div class="add-container">
          <el-form ref="dataForm" :rules="rules" :model="repairInfo" label-position="left" style="margin-top: 30px;">
            <el-form-item label="选择公寓地址" prop="apartmentAddress">
              <el-table v-if="apartmentList!=null" :data="apartmentList" border fit highlight-current-row style="width: 100%; margin-top:30px;" @current-change="handleCurrentChange">
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
                      <el-form-item label="支付模式">
                        <el-tag type="success" close-transition>{{ props.row.pattern }}</el-tag>
                      </el-form-item>
                      <el-form-item label="地址">
                        <span>{{ props.row.address }}</span>
                      </el-form-item>
                    </el-form>
                  </template>
                </el-table-column>
                <el-table-column type="index" width="50" align="center" />
                <el-table-column prop="address" label="公寓地址" />
              </el-table>
            </el-form-item>
            <el-form-item label="填写报修内容" prop="content">
              <el-input v-model="contentText" :disabled="stepActive < 1" type="textarea" :autosize="{ minRows: 5, maxRows: 10}" placeholder="请输入报修内容" />
            </el-form-item>
            <el-form-item label="选择上传报修图片描述" prop="fileListSave">
              <el-upload
                ref="uploadPic"
                class="upload-demo"
                :action="uploadUrl"
                :headers="headersToken"
                :data="fileFinalParams"
                :limit="6"
                :on-exceed="checkLimit"
                :on-preview="handlePictureCardPreview"
                :on-remove="handleRemove"
                :on-change="handlePictureChange"
                :on-error="handlePictureError"
                :on-success="handlePictureSuccess"
                :multiple="true"
                :file-list="fileList"
                :auto-upload="autoUpload"
                :before-upload="beforeAvatarUpload"
                :disabled="stepActive != 2"
                accept="image/png, image/jpeg, image/gif, image/jpg"
                list-type="picture-card"
                style="margin-top: 40px;margin-down: 30px; margin-left: 0px;"
              >
                <i class="el-icon-plus" />
                <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过6张图片</div>
              </el-upload>
            </el-form-item>
          </el-form>
          <el-button v-waves v-loading.fullscreen.lock="loading" type="primary" style="width:100%;margin-bottom:30px;" @click.native.prevent="pushRepairInfo">提交</el-button>
        </div>
      </template>
    </div>
    <!-- 查看图片 -->
    <el-dialog :visible.sync="showCard" width="100%" height="100%">
      <img width="100%" :src="imageUrl" alt="">
    </el-dialog>
    <!-- <viewer ref="viewer" :images="images" class="viewer" @inited="inited">
      <img v-if="showCard" :key="imageKey" :src="imageUrl">
    </viewer> -->
    <!-- <img
      key="src"
      :src="'data:image/jpeg;base64,' + imgStr"
      class="avatar"
    > -->
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import { apartmentListByUserid } from '@/api/apartment'
import waves from '@/directive/waves' // waves directive
import { delImage, addRepairAndFile, getHostAddress } from '@/api/repairs'

export default {
  directives: { waves },
  data() {
    return {
      uploadUrl: '',
      autoUpload: true,
      submitClick: false,
      showCard: false,
      stepActive: 0,
      apartmentList: null,
      listLoading: false,
      loading: false,
      contentText: '',
      repairInfo: {
        userid: null,
        username: null,
        apartmentid: null,
        apartmentAddress: null,
        content: null,
        fileListSave: []
      },
      rules: {
        content: [{ required: true, message: '报修内容必填', trigger: 'blur' }],
        apartmentAddress: [{ required: true, message: '公寓地址必须选择', trigger: 'blur' }]
      },
      fileInfo: {
        userid: null,
        username: null,
        resource: null,
        filename: null,
        fileType: null
      },
      fileList: [],
      passwordType: 'password',
      imageUrl: '',
      imageKey: '',
      fileT: null,
      height: null,
      width: null
    }
  },
  computed: {
    ...mapGetters([
      'name',
      'id',
      'token'
    ]),
    headersToken: function() {
      return { 'Authorization': this.token }
    },
    fileFinalParams: function() {
      if (this.fileT !== null) {
        return {
          'files': this.fileT
        }
      } else {
        return {}
      }
    }
  },
  watch: {
    contentText: function(newVal, oldVal) {
      if (newVal.length !== 0) {
        this.stepActive = 2
      } else {
        this.stepActive = 1
      }
      this.repairInfo.content = newVal
    }
  },
  created() {
    this.getHostAddress()
    this.getApartmentList()
  },
  methods: {
    getHostAddress() {
      getHostAddress().then((response) => {
        this.uploadUrl = response.data
      })
    },
    getApartmentList() {
      this.repairInfo.userid = this.id
      this.repairInfo.username = this.name
      this.listLoading = true
      apartmentListByUserid(this.id).then((response) => {
        this.apartmentList = response.data
        this.listLoading = false
      })
    },
    handleApartment() {
      this.fileList
    },
    handleCurrentChange(val) {
      this.repairInfo.apartmentid = val.id
      this.repairInfo.apartmentAddress = val.address
      this.stepActive = 1
    },
    resetRepairInfo() {
      this.repairInfo = {
        userid: null,
        username: null,
        apartmentid: null,
        apartmentAddress: null,
        content: null
      }
    },
    // 提交
    pushRepairInfo() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          // 判断是否都上传完成
          if (this.fileList.length !== 0) {
            if ((!this.submitClick)) {
              this.$message({ type: 'info', message: '请等待上传完成后，再提交！' })
              return
            }
          }
          this.loading = true
          // 先上传文件
          // this.$refs.uploadPic.submit()
          if (this.fileList.length === this.repairInfo.fileListSave.length) {
            // 再新增数据
            addRepairAndFile(this.repairInfo).then(response => {
              // 成功后清空现有数据
              if (response.data) {
                this.resetRepairInfo()
                this.repairInfo.fileListSave = []
                this.contentText = ''
                this.$refs.uploadPic.clearFiles()
                this.loading = false
                this.$message({ type: 'success', message: '报修成功' })
              } else {
                // 失败后询问是否重新上传
                this.$confirm('保修单提交失败，是否重试?', '提示', {
                  confirmButtonText: '确定',
                  cancelButtonText: '取消',
                  type: 'warning'
                }).then(() => {
                  // 重新上传
                  this.pushRepairInfo()
                  this.loading = false
                }).catch(() => {
                  // 取消上传，则什么也不动
                  this.loading = false
                  this.$message({
                    type: 'info',
                    message: '已取消'
                  })
                })
              }
            }).catch(() => {
              // 失败后询问是否重新上传
              this.$confirm('保修单提交失败，是否重试?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
              }).then(() => {
                // 重新上传
                this.pushRepairInfo()
                this.loading = false
              }).catch(() => {
                // 取消上传，则什么也不动
                this.loading = false
                this.$message({
                  type: 'info',
                  message: '已取消'
                })
              })
              this.loading = false
            })
          }
        } else {
          this.loading = false
          this.$message({ type: 'warning', message: '请检查必填项！' })
        }
      })
    },
    inited(viewer) {
      this.$viewer = viewer
    },
    handleRemove(file, fileList) {
      this.fileList = fileList
      delImage(file.name).then(response => {
        for (const v of this.repairInfo.fileListSave) {
          if (v.name === file.name) {
            const index = this.repairInfo.fileListSave.indexOf(v)
            this.repairInfo.fileListSave.splice(index, 1)
            break
          }
        }
      })
    },
    handlePictureCardPreview(file) {
      this.imageKey = file.name
      this.imageUrl = file.url
      this.showCard = true
    },
    handlePictureChange(file, fileList) {
      this.fileList = fileList
    },
    checkLimit(files, fileList) {
      if (fileList.length === 6) {
        this.$message.error('上传图片不可超过 6 个!')
      }
    },
    handlePictureError(err, file, fileList) {
      this.$message.error(err)
    },
    handlePictureSuccess(response, file, fileList) {
      if (response.message === 'true') {
        file.name = response.data.name
        this.fileInfo = {
          userid: this.repairInfo.userid,
          username: this.repairInfo.username,
          filename: response.data.filename,
          resource: response.data.resource,
          fileType: response.data.suffix
        }
        this.repairInfo.fileListSave.push(this.fileInfo)
      }
      if (this.fileList.length === this.repairInfo.fileListSave.length) {
        this.submitClick = true
      }
    },
    beforeAvatarUpload(param) {
      const isJPG = (param.type.indexOf('image') >= 0)
      if (!isJPG) {
        this.$message.error('上传图片的格式不正确!')
        return false
      }
      // 对图片进行压缩
      const imgSize = param.size / 1024 / 1024
      var quarity = 0.33
      if (imgSize > 20) {
        quarity = 0.3
      } else if (imgSize > 10 && imgSize < 20) {
        quarity = 0.31
      } else if (imgSize > 5 && imgSize < 10) {
        quarity = 0.32
      }
      const _this = this
      if (imgSize > 1) {
        return new Promise(resolve => {
          const reader = new FileReader()
          const image = new Image()
          image.onload = (imageEvent) => {
            const canvas = document.createElement('canvas')
            const context = canvas.getContext('2d')
            const width = image.width * quarity
            const height = image.height * quarity
            _this.imgWidthHeight = `${width}/${height}`
            // console.log(`图片上传前，压缩：${_this.imgWidthHeight}`)
            canvas.width = width
            canvas.height = height
            context.clearRect(0, 0, width, height)
            context.drawImage(image, 0, 0, width, height)
            const dataUrl = canvas.toDataURL(param.type)
            const blobData = _this.dataURItoBlob(dataUrl, param.type)
            resolve(blobData)
          }
          reader.onload = e => { image.src = e.target.result }
          reader.readAsDataURL(param)
        })
      } else {
        return new Promise(resolve => {
          const reader = new FileReader()
          const image = new Image()
          image.onload = (imageEvent) => {
            const width = image.width
            const height = image.height
            _this.imgWidthHeight = `${width}/${height}`
            // console.log(`图片上传前，不需要压缩：${_this.imgWidthHeight}`)
            resolve()
          }
          reader.onload = e => { image.src = e.target.result }
          reader.readAsDataURL(param)
        })
      }
    },
    dataURItoBlob(dataURI, type) {
      var binary = atob(dataURI.split(',')[1])
      var array = []
      for (var i = 0; i < binary.length; i++) {
        array.push(binary.charCodeAt(i))
      }
      return new Blob([new Uint8Array(array)], { type: type })
    }
  }
}
</script>
<style>
  .avatar {
    width: 178px;
    height: 178px;
  }
  .el-upload-list--picture-card .el-upload-list__item {
    width: 31.4%;
    margin: 0px 4px 4px 4px;
  }
  .el-upload--picture-card {
    width: 30.9%;
    margin: 0px 4px 4px 4px;
  }
</style>
