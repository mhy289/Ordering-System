<template>
  <div class="tabs-wrapper">
    <el-tabs
      :tab-position="tabPosition"
      v-model="activeTab"
      class="tabs"
    >
      <el-tab-pane
        v-for="tab in tabs"
        :label="tab.label"
        :key="tab.index"
        :name="tab.index"
      >
        <div class="tab-content">
          <component :is="currentComponent" />
        </div>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>


<script>
import Test1 from './Test1.vue';
// 根据需要导入其他组件

export default {
  data() {
    return {
      tabPosition: 'left',
      activeTab: '/',
      tabs: [
        //美食分类
        { label: '推荐', index: '/' },
        { label: '风味小吃', index: '/Config' },
        { label: '特色小炒', index: '/Role' },
        { label: '经典冰淇淋', index: '/Task' },
        // 其他类目

      ]
    };
  },
  computed: {
    currentComponent() {
      switch (this.activeTab) {
        case '/Test1':
          return Test1;
        // 添加其他组件的情况
        default:
          return null;  // 或者一个默认组件
      }
    }
  },
  components: {
    Test1,
    // 其他组件
  }
};

</script>

<style scoped>
.tabs-wrapper {
  display: flex;
  height: 100vh; /* 确保高度足够展示内容和分页按钮 */
}

.tabs {
  flex: 1;
  overflow: hidden;
}

.tab-content {
  padding: 20px;
  height: calc(100vh - 60px); /* 根据需要调整，留出分页按钮的空间 */
  overflow-y: auto;
}

</style>
