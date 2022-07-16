<template>
  <div class="indexContainer">
    <div class="tableBox">
      <el-row type="flex">
        <el-table
          stripe
          :header-cell-style="{ background: '#F0F4FF', color: '#333333' }"
          :data="tableData.list"
        >
          <el-table-column
            align="center"
            prop="namesrvCode"
            label="namesrv"
            show-overflow-tooltip
          />
          <el-table-column
            align="center"
            prop="cluster"
            label="cluster"
            show-overflow-tooltip
          />
          <el-table-column
            align="center"
            prop="name"
            label="name"
            show-overflow-tooltip
          />
          <el-table-column
            align="center"
            prop="no"
            label="编号(0主)"
            show-overflow-tooltip
          />
          <el-table-column
            align="center"
            prop="address"
            label="地址"
            show-overflow-tooltip
          />
          <el-table-column
            label="主从"
            align="center"
            show-overflow-tooltip
          >
            <template slot-scope="scope">
              <el-tag
                :type="scope.row.no === 0 ? 'warning': 'success'"
              >
                {{ scope.row.no === 0 ? '主' : '从' }}
              </el-tag>
            </template>
          </el-table-column>

          <el-table-column
            label="操作"
            align="center"
          >
            <template slot-scope="scope">
              <el-button
                type="text"
                @click="handleConfig(scope.$index, scope.row)"
              >配置</el-button>
              <el-button
                type="text"
                @click="handleState(scope.$index, scope.row)"
              >状态</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-row>
      <BrokerConfigDialog
        :v-if="showConfigDialog"
        :visible="showConfigDialog"
        :config-data="configData"
        @on-config-cancel="handleConfigClose"
      />
      <BrokerStateDialog
        :v-if="showStateDialog"
        :visible="showStateDialog"
        :state-data="stateData"
        @on-config-cancel="handleStateClose"
      />
    </div>
  </div>
</template>

<script>

import { getBrokerList, getBrokerConfig, getBrokerState } from '@/api/broker'
import BrokerConfigDialog from './components/brokerConfigDiaglog.vue'
import BrokerStateDialog from './components/brokerStateDialog.vue'

export default {
  name: 'Nameserve',
  components: {
    BrokerConfigDialog,
    BrokerStateDialog
  },
  data() {
    return {
      tableData: {
        list: [],
        page: 1,
        pageSize: 10,
        total: 0
      },
      showConfigDialog: false,
      showStateDialog: false,
      configData: [],
      stateData: [],
      formLabelWidth: '120px'
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      getBrokerList().then(res => {
        this.tableData.list = res.data
      })
    },
    handleConfig(index, row) {
      getBrokerConfig({ ...row }).then(res => {
        this.configData = res.data
      })
      this.showConfigDialog = true
    },
    handleState(index, row) {
      getBrokerState({ ...row }).then(res => {
        this.stateData = res.data
      })
      this.showStateDialog = true
    },
    handleConfigClose() {
      this.showConfigDialog = false
    },
    handleStateClose() {
      this.showStateDialog = false
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
