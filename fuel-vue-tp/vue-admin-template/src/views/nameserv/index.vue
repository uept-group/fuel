<template>
  <div class="indexContainer">
    <div class="tableBox">
      <el-row type="flex" class="row-bg" justify="end">
        <el-button type="primary" size="medium" icon="el-icon-plus">新增</el-button>
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
            label="服务名称"
            show-overflow-tooltip
          />
          <el-table-column
            align="center"
            prop="code"
            label="服务编码"
            show-overflow-tooltip
          />
          <el-table-column
            align="center"
            prop="addr"
            label="ip地址"
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
                size="mini"
                icon="el-icon-edit"
                @click="handleEdit(scope.$index, scope.row)"
              >编辑</el-button>
              <el-button
                size="mini"
                type="danger"
                icon="el-icon-delete"
                @click="handleDelete(scope.$index, scope.row)"
              >删除</el-button>
            </template>
          </el-table-column>

        </el-table>
      </el-row>
    </div>
  </div>
</template>

<script>

import { getNameServList } from '@/api/nameserv'

export default {
  name: 'Nameserve',
  data() {
    return {
      tableData: {
        list: [],
        page: 1,
        pageSize: 10,
        total: 0
      }
    }
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
      console.log(index, row)
    },
    handleDelete(index, row) {
      console.log(index, row)
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
    padding: 10px 55px;
    background-color: #f9fafc;
  }
  .tableBox {
    padding: 20px 10px;
    border-radius: 10px;
    overflow-x: hidden;
    overflow-y: auto;
  }
</style>
