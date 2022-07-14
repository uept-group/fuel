<template>
  <el-dialog
    :title="title"
    :visible.sync="isShowForm"
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
      <el-form-item label="备注" :label-width="formLabelWidth" prop="desc">
        <el-input
          v-model="ruleForm.desc"
          type="textarea"
          maxlength="50"
          show-word-limit
        />
      </el-form-item>
      <el-form-item :label-width="btnLabelWidth">
        <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
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
    }
  },
  data() {
    return {
      formLabelWidth: '120px',
      btnLabelWidth: '238px',
      ruleForm: {
        name: '',
        code: '',
        addr: '',
        desc: ''
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
        desc: [
          { required: false, message: '请输入备注信息', trigger: 'blur' }
        ]
      }
    }
  },
  computed: {
  },
  watch: {
    visible: {
      immediate: true,
      handler(val) {
        this.isShowForm = val
      }
    }
  },
  methods: {
    cancel() {
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
    }
  }
}
</script>

<style>

</style>
