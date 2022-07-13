<template>
  <div class="indexContainer">
    <div class="tableBox">
      <el-table
        stripe
        :header-cell-style="{ background: '#F0F4FF', color: '#333333' }"
        :data="tableData.list"
        style="width: 100%"
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
          width="120px"
          align="center"
        >
          <template slot-scope="scope">
            <el-tag
              :type=" scope.row.status === '1' ? 'warning': 'success' "
              disable-transitions
            >
              {{ scope.row.status === '1' ? 'lock' : 'unlock' }}
            </el-tag>
          </template>
        </el-table-column>
      </el-table>
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
      },
      state: ''
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      getNameServList().then(res => {
        console.log('this.tableData.list', res.data)
        this.tableData.list = res.data
      })
    },
    getTagName(val) {
      console.log('val', val)
      this.state = val === 1 ? 'lock' : 'unlock'
    }
  }

}
</script>

<style>
    .indexContainer {
        width: 100%;
        height: 100%;
    }

  .tableBox {
    padding: 20px 0;
    overflow-x: hidden;
    overflow-y: auto;
  }
</style>
