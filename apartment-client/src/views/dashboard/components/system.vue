<template>
  <div class="system">
    <div class="filter-container">
      <el-input v-model.trim="listQuery.username" placeholder="账号名称" style="width: 150px;" class="filter-item" @keyup.enter.native="handleFilter" />
      <el-input v-model.number="listQuery.phone" :minlength="11" :maxlength="11" placeholder="手机号码" style="width:200px" auto-complete="on" @keyup.enter.native="handleFilter" />
      <el-button v-waves type="primary" icon="el-icon-search" @click="handleFilter">
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
      @filter-change="filterHanderChange"
    >
      <el-table-column label="ID" type="index" align="center" width="60" />
      <el-table-column label="账号名称" prop="username" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.username }}</span>
        </template>
      </el-table-column>
      <el-table-column label="账号密码" prop="password" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.password }}</span>
        </template>
      </el-table-column>
      <el-table-column label="手机号码" prop="phone" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.phone }}</span>
        </template>
      </el-table-column>
      <el-table-column label="账号角色" prop="roleNote" column-key="roleNote" align="center" :filter-multiple="true" :filter-method="filterRoleNote" :filters="roleNoteList" filter-placement="bottom-end">
        <template slot-scope="scope">
          <el-tag type="success">{{ scope.row.roleNote }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" prop="createTime" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.createTime | parseTime }}</span>
        </template>
      </el-table-column>
      <el-table-column label="上次登录时间" prop="loginTime" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.loginTime | parseTime }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="241px" class-name="small-padding fixed-width">
        <template slot-scope="{row}">
          <el-button v-if="row.roleId===2 || row.roleId===3" type="success" size="mini" @click="handleHolderDetail(row)">
            详细信息
          </el-button>
          <el-button size="mini" :type="row.status ===0 ? 'success' : 'primary'" @click="statusChangeData(row)">
            {{ row.status ===0 ? '启用' : '禁用' }}
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页插件 -->
    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />
    <!-- 租户信息查看 -->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogUserVisible">
      <el-table v-if="userinfo!=null" :data="[userinfo]" border fit highlight-current-row style="width: 100%;">
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-form label-position="left" inline class="demo-table-expand">
              <el-form-item :label="roleNote+'ID：'">
                <el-tag type="danger">{{ props.row.id }}</el-tag>
              </el-form-item>
              <el-form-item :label="roleNote+'姓名：'">
                <el-tag type="warning">{{ props.row.name }}</el-tag>
              </el-form-item>
              <el-form-item :label="roleNote+'联系电话：'">
                <el-tag type="success">{{ props.row.phone }}</el-tag>
              </el-form-item>
              <el-form-item :label="roleNote+'性别：'">
                <el-tag>{{ props.row.sex===0?'女':'男' }}</el-tag>
              </el-form-item>
              <el-form-item :label="roleNote+'证件类型：'">
                <el-tag type="info">{{ props.row.idType }}</el-tag>
              </el-form-item>
              <el-form-item :label="roleNote+'证件号码：'">
                <el-tag>{{ props.row.idNumber }}</el-tag>
              </el-form-item>
              <el-form-item :label="roleNote+'状态：'">
                <el-tag v-if="roleNote === '租户'" type="success">{{ props.row.isLive===0?'未租住':'已租住' }}</el-tag>
                <el-tag v-else type="success">{{ props.row.isLive===0?'在职':'离职' }}</el-tag>
              </el-form-item>
            </el-form>
          </template>
        </el-table-column>
        <el-table-column prop="id" label="ID" />
        <el-table-column prop="name" label="姓名" />
        <el-table-column prop="phone" label="手机号" />
      </el-table>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogUserVisible = false">
          关闭
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import { fetchList, changeStatus } from '@/api/user'
import { userInfoById } from '@/api/apartmentuser'
import { repairInfoById } from '@/api/apartmentrepair'
import waves from '@/directive/waves' // waves directive
import Pagination from '@/components/Pagination' // secondary package based on el-pagination

export default {
  name: 'System',
  components: { Pagination },
  directives: { waves },
  data() {
    return {
      roleNoteList: [
        { text: '租户', value: '租户' },
        { text: '售后人员', value: '售后人员' }
      ],
      total: 0,
      tableKey: 0,
      listLoading: false,
      list: [],
      listQuery: {
        page: 1,
        limit: 10,
        username: null,
        phone: null,
        roleId: null,
        roleNoteSet: null
      },
      dialogUserVisible: false,
      dialogStatus: '',
      textMap: {
        userinfo: '查看人员信息'
      },
      // 更改账户状态
      statusChage: {
        id: null,
        status: null
      },
      // 查看人员信息
      roleNote: null,
      userinfo: null
    }
  },
  computed: {
    ...mapGetters([
      'userInfo',
      'name',
      'id'
    ])
  },
  created() {
    this.getList()
  },
  methods: {
    // 获取人员列表
    getList() {
      this.listLoading = true
      this.listQuery.roleId = 2
      fetchList(this.listQuery).then(response => {
        const { records, total } = response.data
        this.list = records
        this.total = total
        this.listLoading = false
      })
    },
    // 查看密码
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
    // 过滤角色类型
    handleFilter() {
      this.listQuery.page = 1
      this.getList()
    },
    // 过滤条件改变时触发
    filterHanderChange(filters) {
      if (filters.roleNote !== undefined) {
        if (filters.roleNote.length >= 1) {
          this.listQuery.roleNoteSet = []
          for (var i in filters.roleNote) {
            this.listQuery.roleNoteSet.push(filters.roleNote[i])
          }
        } else {
          this.listQuery.roleNoteSet = null
        }
      }
      this.handleFilter()
    },
    // 角色过滤
    filterRoleNote(value, row) {
      return row.roleNote === value
    },
    // 查看租户详细信息
    handleHolderDetail(row) {
      this.roleNote = row.roleNote
      if (this.roleNote === '租户') {
        userInfoById(row.userId).then((response) => {
          this.userinfo = response.data
        })
      } else {
        repairInfoById(row.repairId).then((response) => {
          this.userinfo = response.data
        })
      }
      this.dialogStatus = 'userinfo'
      this.dialogUserVisible = true
    },
    // 账号的启用和禁用
    statusChangeData(row) {
      this.$confirm('此操作禁用或开启此账户, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.statusChage.id = row.id
        this.statusChage.status = row.status
        changeStatus(this.statusChage).then((response) => {
          const result = response.data
          if (result) {
            row.status = row.status === 0 ? 1 : 0
            var messgaeinfo = row.status === 1 ? '【启用】!' : '【禁用】!'
            this.$message({
              type: 'success',
              message: '您已将该账户' + messgaeinfo
            })
            this.statusChage.id = null
            this.statusChage.status = null
          } else {
            this.$message({
              type: 'info',
              message: '账户状态更改失败!'
            })
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '账户状态更改异常!'
        })
      })
    }
  }
}
</script>

<style lang="scss" scoped>
  .system {
    padding-left: 20px;
    .filter-container {
      margin-bottom: 20px;
    }
  }
  .show-pwd {
    font-size: 15px;
  }
</style>
