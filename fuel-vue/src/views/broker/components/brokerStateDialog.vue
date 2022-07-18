<template>
  <div class="dialogWapper">
    <el-dialog
      :title="title"
      :visible.sync="isShowForm"
      :before-close="beforeClose"
      width="45%"
      custom-class="dialogContainer"
    >
      <el-row type="flex" gutter="center">
        <el-table
          stripe
          :header-cell-style="{ background: '#F0F4FF', color: '#333333' }"
          :data="tableData"
          style="width: 100%"
        >
          <el-table-column
            align="center"
            prop="key"
            label="type"
            show-overflow-tooltip
          />
          <el-table-column
            align="center"
            prop="value"
            label="value"
            show-overflow-tooltip
          />
        </el-table>
      </el-row>
      <el-row type="flex" justify="center">
        <el-button
          type="primary"
          @click="handleClose()"
        >关闭</el-button>
      </el-row>
    </el-dialog>

  </div>
</template>

<script>

export default {
  name: 'BrokerStateDialog',
  props: {
    visible: {
      type: Boolean,
      default: false
    },
    stateData: {
      type: Array,
      default() {
        return []
      }
    }
  },
  data() {
    return {
      formLabelWidth: '50px',
      btnLabelWidth: '50px',
      isShowForm: false,
      tableData: [],
      title: 'broker状态信息'
    }
  },
  computed: {
  },
  watch: {
    visible: function(val) {
      this.isShowForm = val
    },
    stateData: {
      immediate: true,
      handler(data) {
        if (data && Array.isArray(data)) {
          this.tableData = data
        }
      }
    }
  },
  created() {
  },
  methods: {
    beforeClose() {
      this.$emit('on-config-cancel')
      this.isShowForm = false
    },
    handleClose() {
      this.$emit('on-config-cancel', {
      })
      this.isShowForm = false
    }
  }
}
</script>

<style>
  .dialogWapper {
    padding: 20;
  }
  .dialogContainer {
    overflow-x: auto;
    overflow-y: auto;
  }
</style>
