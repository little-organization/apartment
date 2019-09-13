<template>
  <div class="sendmessage">
    <el-dialog
      width="30%"
      title="内层 Dialog"
      :visible.sync="visiblemessage"
      append-to-body
    />
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'Sendmessage',
  props: {
    visiblemessage: {
      type: Boolean,
      default: false
    }
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
  created() {
    // this.changeDialogWidth()
  },
  methods: {}
}
</script>

<style lang="scss" scoped>
  .message {
    margin-left: 20px;
  }
</style>
