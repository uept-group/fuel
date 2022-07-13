<template>
  <el-dialog
    :title="title"
    :visible.sync="isShowForm"
    :width="width"
    :before-close="handleClose"
    @close="cancel"
  >
    <el-form :model="form">
      <el-form-item label="服务名称" :label-width="formLabelWidth">
        <el-input v-model="form.name" autocomplete="off" />
      </el-form-item>
      <el-form-item label="服务编码" :label-width="formLabelWidth">
        <el-input v-model="form.code" autocomplete="off" />
      </el-form-item>
      <el-form-item label="服务IP" :label-width="formLabelWidth">
        <el-input v-model="form.ip" autocomplete="off" />
      </el-form-item>
      <!-- <el-form-item label="unlock" :label-width="formLabelWidth">
            <el-select v-model="form.region" placeholder="请选择活动区域">
              <el-option label="区域一" value="shanghai" />
              <el-option label="区域二" value="beijing" />
            </el-select>
          </el-form-item> -->
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="isShowForm = false">取 消</el-button>
      <el-button type="primary" @click="isShowForm = false">确 定</el-button>
    </div>
  </el-dialog>

</template>

<script>
export default {
  name: 'NameservDialog',
  props: {
    visible: {
      type: Boolean,
      default: false
    },
    btnDisable: {
      type: Boolean,
      default: false
    },
    info: {
      type: Array,
      default: function() {
        return []
      }
    },
    width: {
      type: String,
      default: '50%'
    }
  },
  data() {
    return {
      title: '',
      formLabelWidth: '120px',
      form: {
        name: '',
        code: '',
        ip: '',
        date2: '',
        delivery: false,
        type: [],
        resource: '',
        desc: ''
      },
      isShowForm: false
    }
  },
  computed: {
  },
  watch: {
    visible: function(val) {
      this.isShowForm = val
    }
  },
  methods: {
    handleClose(done) {
      this.visible = false
      done()
    },
    cancel() {
      this.$emit('on-config-cancel', {
      })
    }
  }
}
</script>

<style>

</style>
