<template>
  <div>
    <div>
      <el-container>
        <el-main>
          <WelcomeMessage/>
        </el-main>
      </el-container>
    </div>
  </div>
</template>

<script>
  // @ is an alias to /src
  import WelcomeMessage from '@/components/homePageDetail/WelcomeMessage.vue'

  export default {
    name: 'home',
    components: {
      WelcomeMessage,
    }
  }
</script>
<style scoped>
  .el-main {
    margin: 50px 200px;
  }
</style>