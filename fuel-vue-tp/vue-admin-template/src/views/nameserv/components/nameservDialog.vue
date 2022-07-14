<template>
  <el-dialog
    :title="title"
    :visible.sync="isShowForm"
    :before-close="beforeClose"
    @close="cancel"
  >
    <el-form ref="ruleForm" :model="ruleForm" :rules="rules">
      <el-form-item label="名称" :label-width="formLabelWidth" prop="name">
        <el-input
          v-model="ruleForm.name"
          autocomplete="off"
          maxlength="15"
          show-word-limit
        />
      </el-form-item>
      <el-form-item label="编码" :label-width="formLabelWidth" prop="code">
        <el-input
          v-model="ruleForm.code"
          autocomplete="off"
          maxlength="15"
          show-word-limit
        />
      </el-form-item>
      <el-form-item label="地址" :label-width="formLabelWidth" prop="addr">
        <el-input v-model="ruleForm.addr" autocomplete="off" maxlength="100" />
      </el-form-item>
      <el-form-item label="备注" :label-width="formLabelWidth" prop="note">
        <el-input
          v-model="ruleForm.note"
          type="textarea"
          maxlength="50"
          show-word-limit
        />
      </el-form-item>
      <el-form-item :label-width="btnLabelWidth">
        <el-button type="primary" @click="submitForm('ruleForm')">{{ btnName }}</el-button>
        <el-button @click="cancel()">取消</el-button>
      </el-form-item>
    </el-form>
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
    formData: {
      type: Object,
      default: null
    },
    title: {
      type: String,
      default: ''
    },
    btnName: {
      type: String,
      default: ''
    }
  },
  data() {
    return {
      formLabelWidth: '120px',
      btnLabelWidth: '120px',
      ruleForm: {
        id: '',
        name: '',
        code: '',
        addr: '',
        note: ''
      },
      isShowForm: false,
      rules: {
        name: [
          { required: true, message: '请输入NameServer名称', trigger: 'blur' }
        ],
        code: [
          { required: true, message: '请输入NameSerer编码', trigger: 'blur' }
        ],
        addr: [
          { required: true, trigger: 'blur' }
        ],
        note: [
          { required: false, message: '请输入备注信息', trigger: 'blur' }
        ]
      }
    }
  },
  computed: {
  },
  watch: {
    visible: function(val) {
      this.isShowForm = val
    },
    formData: {
      immediate: true,
      handler(data) {
        if (data) {
          this.ruleForm = { ...data }
        }
      }
    }
  },
  created() {
    this.initForm()
  },
  methods: {
    cancel() {
      this.initForm()
      this.$emit('on-config-cancel', {
      })
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$emit('on-save-nameserv', {
            ...this.ruleForm
          })
        } else {
          return false
        }
      })
    },
    resetForm(formName) {
      this.$refs[formName].resetFields()
    },
    initForm() {
      this.ruleForm = {
        id: '',
        name: '',
        code: '',
        addr: '',
        note: ''
      }
    },
    beforeClose() {
      this.$emit('update:visible', false)
      this.isShowForm = false
      this.initForm()
      this.$emit('on-config-cancel')
    }
  }
}
</script>

<style>

</style>
