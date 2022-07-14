<template>
  <div class="indexContainer">
    <div class="tableBox">
      <el-row type="flex" class="row-bg" justify="start">
        <el-button type="primary" size="medium" icon="el-icon-plus" @click="handleShowDialog(true)">新增名称服务</el-button>
      </el-row>
      <el-row type="flex">
        <el-table
          stripe
          :header-cell-style="{ background: '#F0F4FF', color: '#333333' }"
          :data="tableData.list"
        >
          <el-table-column
            align="center"
            prop="id"
            label="ID"
            show-overflow-tooltip
          />
          <el-table-column
            align="center"
            prop="name"
            label="名称"
            show-overflow-tooltip
          />
          <el-table-column
            align="center"
            prop="code"
            label="编码"
            show-overflow-tooltip
          />
          <el-table-column
            align="center"
            prop="addr"
            label="namesrv 地址"
            show-overflow-tooltip
          />
          <el-table-column
            align="center"
            prop="note"
            label="备注"
            show-overflow-tooltip
          />
          <el-table-column
            label="状态"
            align="center"
            show-overflow-tooltip
          >
            <template slot-scope="scope">
              <el-tag
                :type="scope.row.status === '1' ? 'warning': 'success'"
              >
                {{ scope.row.status === '1' ? 'lock' : 'unlock' }}
              </el-tag>
            </template>
          </el-table-column>

          <el-table-column
            label="操作"
            align="center"
          >
            <template slot-scope="scope">
              <el-button
                icon="el-icon-edit"
                type="text"
                @click="handleEdit(scope.$index, scope.row)"
              >编辑</el-button>
              <el-button
                type="text"
                icon="el-icon-delete"
                @click="handleDelete(scope.$index, scope.row)"
              >删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-row>
    </div>
    <NameservDialog
      :title="title"
      :v-if="showDialog"
      :visible="showDialog"
      :form-data="formData"
      :btn-name="btnName"
      @on-config-cancel="configCancelCallback"
      @on-save-nameserv="saveNameservCallback"
    />
  </div>
</template>
<script>

import { getNameServList, addNameServ, delNameServ, modNameServ } from '@/api/nameserv'
import NameservDialog from './components/nameservDialog.vue'
import { errorHandler } from '@/utils/index'

export default {
  name: 'Nameserve',
  components: {
    NameservDialog
  },
  data() {
    return {
      tableData: {
        list: [],
        page: 1,
        pageSize: 10,
        total: 0
      },
      showDialog: false,
      title: '新增名称服务',
      btnName: '立即创建',
      formData: {
        id: '',
        addr: '',
        name: '',
        code: '',
        note: ''
      }
    }
  },
  computed: {

  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      getNameServList().then(res => {
        this.tableData.list = res.data
      })
    },
    handleEdit(index, row) {
      this.showDialog = true
      this.title = '修改名称服务'
      this.btnName = '保存修改'
      this.formData = { ...row }
    },
    handleDelete(index, row) {
      this.$confirm('此操作将永久删除该配置信息, 是否继续?', '', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        delNameServ(row).then(res => {
          if (res.code === 20000) {
            this.$message({
              type: 'success',
              message: '删除成功!'
            })
            this.fetchData()
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },
    handleShowDialog(val) {
      this.title = '新增名称服务'
      this.btnName = '立即创建'
      this.showDialog = val
    },
    configCancelCallback() {
      this.showDialog = false
    },
    saveNameservCallback(ruleForm) {
      if (ruleForm.id) {
        modNameServ(ruleForm).then(res => {
          if (res.code === 20000) {
            this.$message({
              showClose: true,
              message: '修改成功',
              type: 'success'
            })
            this.fetchData()
          }
          /**
           * 错误码拦截
           */
          errorHandler(res)
        })
      } else {
        addNameServ(ruleForm).then(res => {
          if (res.code === 20000) {
            this.$message({
              showClose: true,
              message: '创建成功',
              type: 'success'
            })
            this.fetchData()
          }
          /**
           * 错误码拦截
           */
          console.log('error', res)
          errorHandler(res)
        })
      }
      this.showDialog = false
    }
  }

}
</script>

<style>
    .indexContainer {
        width: 100%;
        height: 100%;
    }
    .el-row {
    margin-bottom: 10px;
    border-radius: 10px;
    &:last-child {
        margin-bottom: 0;
    }
  }
   .row-bg {
    padding: 10px 40px;
    background-color: #f9fafc;
  }
  .tableBox {
    padding: 20px 10px;
    border-radius: 10px;
    overflow-x: hidden;
    overflow-y: auto;
  }
</style>
