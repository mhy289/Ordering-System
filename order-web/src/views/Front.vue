<template>
  <div id="app">
    <!-- 顶部导航栏 -->
    <el-header
      height="60px"
      class="header"
    >
      <div class="header-content">
        <div class="title">餐厅点餐系统</div>
        <div class="user-actions">
          <el-button
            type="primary"
            @click="login"
            class="login-button"
          >登录</el-button>
          <el-button @click="goToAdmin">后台管理</el-button>
        </div>
      </div>
    </el-header>

    <!-- 主体内容 -->
    <el-main>
      <div class="content">
        <!-- 左侧Tab栏 -->
        <el-aside
          width="200px"
          class="categories"
        >
          <el-tabs tab-position="left">
            <el-tab-pane label="推荐">
              <el-menu :default-active="activeMenu">
                <el-menu-item index="1">风味小吃</el-menu-item>
                <el-menu-item index="2">特色小炒</el-menu-item>
                <el-menu-item index="3">饭后甜点</el-menu-item>
              </el-menu>
            </el-tab-pane>
            <el-tab-pane label="最新">
              <!-- 其他分类内容 -->
            </el-tab-pane>
            <el-tab-pane label="热门">
              <!-- 其他分类内容 -->
            </el-tab-pane>
          </el-tabs>
        </el-aside>

        <!-- 右侧食品展示区域 -->
        <div class="food-area">
          <div class="search-and-payment">
            <div class="search-bar-container">
              <el-input
                v-model="searchQuery"
                placeholder="搜索菜品..."
                class="search-bar"
              >
                <template #append>
                  <el-button
                    icon="el-icon-search"
                    @click="search"
                  ></el-button>
                </template>
              </el-input>
            </div>
            <div class="button-container">
              <!-- 购物车按钮 -->
              <el-button
                type="warning"
                class="cart-button"
                icon="el-icon-shopping-cart-full"
                @click="viewCart"
              >购物车</el-button>
              <el-button
                type="success"
                class="payment-button"
                @click="payOrder"
              >支付订单</el-button>
            </div>
          </div>

          <el-main class="food-display">
            <el-row :gutter="20">
              <el-col
                :span="6"
                v-for="(food, index) in foods"
                :key="index"
                class="food-item"
              >
                <el-card :body-style="{ padding: '10px' }">
                  <img
                    :src="food.image"
                    class="food-image"
                    alt="food image"
                  />
                  <div style="padding: 14px;">
                    <span>{{ food.dishesName }}</span>
                    <div class="bottom-info">
                      <span>价格: {{ food.price }} 元</span>
                      <span>销量: {{ food.sales }}</span>
                    </div>
                    <el-button
                      type="primary"
                      icon="el-icon-plus"
                      @click="addToOrder(food)"
                    >加入购物车</el-button>
                  </div>
                </el-card>
              </el-col>
            </el-row>
          </el-main>
        </div>
      </div>
    </el-main>
  </div>
</template>

<script>
export default {
  data () {
    return {
      searchQuery: '',
      activeMenu: '1',
      foods: [],
      name: '',
      price: '',
      sales: '',
      image: ''
    };
  },
  beforeMount: function () {
    this.queryAllfoods()
  },
  methods: {
    async queryAllfoods () {
      let res = await this.$http.get("/dishes")
      console.log(res)
      if (res.code == 200) {
        this.foods = res.data
      }
    },
    login () {
      // 执行登录操作
    },
    goToAdmin () {
      console.log("Welcome");
      this.$router.push({
        name: 'manage'
      });
    },
    search () {
      // 搜索功能
      console.log('搜索内容:', this.searchQuery);
    },
    addToOrder (food) {
      // 添加菜品到订单
    },
    payOrder () {
      // 支付订单
    },
    viewCart () {
      // 查看购物车
    },
  },
};

</script>

<style scoped>
/* 全局样式 */
body {
  font-family: Arial, sans-serif;
  margin: 0;
  padding: 0;
  background-color: #f7f7f7;
  color: #333;
}

.header {
  background-color: #e6f7ff;
  /* 淡蓝色背景 */
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 20px;
}

.header-content {
  display: flex;
  align-items: center;
  width: 100%;
  justify-content: space-between;
}

.title {
  font-size: 24px;
  font-weight: bold;
  text-align: center;
  flex: 1;
}

.user-actions {
  display: flex;
  align-items: center;
}

.login-button {
  background-color: #409eff;
  color: white;
  margin-right: 10px;
}

.content {
  display: flex;
}

.categories {
  background-color: #ffffff;
  border-radius: 8px;
  padding: 10px;
}

.food-area {
  flex: 1;
  padding: 20px;
}

.search-and-payment {
  display: flex;
  align-items: center;
  margin-bottom: 20px;
  padding: 0 10px;
  /* 让内容在食品展示区域内有一定的内边距 */
}

.search-bar-container {
  flex: 1;
  /* 使搜索栏容器占据剩余空间 */
}

.search-bar {
  width: 25%;
  /* 使搜索栏充满其容器 */
}

.button-container {
  display: flex;
  align-items: center;
}

.cart-button {
  background-color: #ffc107;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-left: 10px;
  /* 按钮之间的间距 */
}

.payment-button {
  background-color: #67c23a;
  /* 修改按钮颜色以匹配整体设计 */
  margin-left: 10px;
  /* 确保按钮之间的间距 */
}

.food-display {
  background-color: #ffffff;
  padding: 20px;
  border-radius: 8px;
}

.food-item {
  margin-bottom: 20px;
  /* 增加食品项之间的空白区域 */
}

.food-image {
  width: 100%;
  height: 150px;
  object-fit: cover;
  border-radius: 8px;
}

.bottom-info {
  display: flex;
  justify-content: space-between;
  margin: 10px 0;
}
</style>
