<template>
  <div class="dashboard-container">
    <div class="dashboard-text">欢迎来到{{ name }}服务管理平台</div>
  </div>
</template>

<script>

export default {
  name: 'RocketMQ',
  data() {
    return {
      name: 'RocketMQ'
    }
  }
}
</script>

<style lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
</style>
