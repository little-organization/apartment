<template>
  <div class="dashboard-container">
    <el-tabs @tab-click="handleClick">
      <el-tab-pane id="pane-0" label="个人信息">
        <span slot="label">
          <i class="el-icon-edit-outline" /> 个人信息
        </span>
        <persinal>个人信息</persinal>
      </el-tab-pane>
      <el-tab-pane v-if="checkPermission(['超级管理员', '系统管理员'])" id="pane-1" label="管理人员">
        <span slot="label">
          <i class="el-icon-tickets" /> 管理人员
        </span>
        <div>
          <!-- 系统管理员 can see this -->
          <admin v-permission="['超级管理员']">超级管理员</admin>
          <!-- 系统管理员 can see this -->
          <system v-permission="['系统管理员']">系统管理员</system>
          <!-- 租户 can see this -->
          <!-- <holder v-permission="['租户']">租户</holder> -->
          <!-- 租户 can see this -->
          <!-- <aftersales v-permission="['售后人员']">售后人员</aftersales> -->
        </div>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import { Admin, System, Persinal } from './components'
import permission from '@/directive/permission/index.js' // 权限判断指令
import checkPermission from '@/utils/permission' // 权限判断函数
import { mapGetters } from 'vuex'

export default {
  name: 'Dashboard',
  directives: { permission },
  components: {
    Admin,
    System,
    Persinal
  },
  data() {
    return {
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
  .el-tabs__content {
    margin-right: 20px;
  }
</style>
