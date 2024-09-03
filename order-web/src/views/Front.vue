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
            @click="goToLogin"
            class="login-button"
            v-if="!isLoggedIn"
          >登录</el-button>
          <el-button
            type="success"
            @click="LoginOut"
            class="login-button"
            v-if="isPerson"
          >退出登录</el-button>
          <el-button
            type="success"
            @click="goToPerson"
            class="login-button"
            v-if="isPerson"
          >{{this.username}}</el-button>
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
          <el-tabs
            v-model="activeTabName"
            tab-position="left"
          >
            <el-tab-pane
              label="推荐"
              name="recommend"
            >
              <el-menu
                :default-active="recommendActiveIndex"
                @select="handleSelect('recommend',$event)"
              >
                <el-menu-item
                  index="1"
                  class="menu-item-custom"
                >风味小吃</el-menu-item>
                <el-menu-item
                  index="2"
                  class="menu-item-custom"
                >特色小炒</el-menu-item>
                <el-menu-item
                  index="3"
                  class="menu-item-custom"
                >饭后甜点</el-menu-item>
                <el-menu-item
                  index="4"
                  class="menu-item-custom"
                >主食专区</el-menu-item>
                <el-menu-item
                  index="5"
                  class="menu-item-custom"
                >冰爽饮品</el-menu-item>
              </el-menu>
            </el-tab-pane>
            <el-tab-pane
              label="最新"
              name="new"
            >
              <el-menu
                :default-active="newActiveIndex"
                @select="handleSelect('new',$event)"
              >
                <el-menu-item
                  index="1"
                  class="menu-item-custom"
                >新品上市</el-menu-item>
                <el-menu-item
                  index="2"
                  class="menu-item-custom"
                >经典复刻</el-menu-item>
                <!-- 其他菜单项 -->
              </el-menu>
            </el-tab-pane>
            <el-tab-pane
              label="热门"
              name="hot"
            >
              <el-menu
                :default-active="hotActiveIndex"
                @select="handleSelect('hot',$event)"
              >
                <el-menu-item
                  index="1"
                  class="menu-item-custom"
                >热销套餐</el-menu-item>
                <el-menu-item
                  index="2"
                  class="menu-item-custom"
                >推荐套餐</el-menu-item>
                <!-- 其他菜单项 -->
              </el-menu>
            </el-tab-pane>
          </el-tabs>
        </el-aside>

        <!-- 已废弃 -->
        <!-- <el-aside width="200px" class="categories">
          <el-tabs tab-position="left">
            <el-tab-pane label="推荐">
              <el-menu :default-active="activeMenu" @select="handleSelect">
                <el-menu-item index="1" class="menu-item-custom">风味小吃</el-menu-item>
                <el-menu-item index="2" class="menu-item-custom">特色小炒</el-menu-item>
                <el-menu-item index="3" class="menu-item-custom">饭后甜点</el-menu-item>
                <el-menu-item index="4" class="menu-item-custom">主食专区</el-menu-item>
                <el-menu-item index="5" class="menu-item-custom">冰爽饮品</el-menu-item>
              </el-menu>
            </el-tab-pane>
            <el-tab-pane label="最新">
              <el-menu :default-active="activeMenu" @select="handleSelect">
                <el-menu-item index="1" class="menu-item-custom">新品上市</el-menu-item>
                <el-menu-item index="2" class="menu-item-custom">经典复刻</el-menu-item>
              </el-menu>
            </el-tab-pane>
            <el-tab-pane label="热门">
              <el-menu :default-active="activeMenu" @select="handleSelect">
                <el-menu-item index="1" class="menu-item-custom">热销套餐</el-menu-item>
                <el-menu-item index="2" class="menu-item-custom">推荐套餐</el-menu-item>
              </el-menu>
            </el-tab-pane>
          </el-tabs>
        </el-aside> -->

        <!-- 右侧食品展示区域 -->
        <div class="food-area">
          <!-- 搜索栏和按钮在上方 -->
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
                @click="toggleCart"
              >购物车</el-button>
              <el-drawer
                title="购物车"
                :visible.sync="isCartVisible"
                direction="rtl"
                size="30%"
              >
                <div class="cart-content">
                  <el-list>
                    <el-list-item
                      v-for="(item, index) in cart"
                      :key="index"
                      class="cart-item"
                    >
                      <div class="cart-item-details">
                        <span
                          class="cart-item-name">{{ item.dishes.dishesName }} x
                          {{ item.dishesCount }}</span>
                        <span
                          class="cart-item-price">￥{{ item.dishes.price }}</span>
                      </div>
                      <el-button
                        type="danger"
                        size="mini"
                        @click="removeFromCart(index)"
                      >删除</el-button>
                    </el-list-item>
                  </el-list>
                </div>

                <div class="drawer-footer">
                  <div class="total-amount">总金额: ￥{{ this.totalPrice }}</div>
                  <el-button
                    type="success"
                    class="submit-order-button"
                    @click="paycartToOrder"
                  >提交订单</el-button>
                  <el-button
                    type="warn"
                    class="submit-order-button"
                    @click="cartClr"
                  >清空购物车</el-button>
                </div>
              </el-drawer>

              <el-button
                type="success"
                class="payment-button"
                @click="payOrder"
              >支付订单</el-button>
            </div>
          </div>

          <!-- 黑框区域 -->
          <div class="food-display-container">
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
      </div>
    </el-main>
  </div>
</template>

<script>
export default {
  data () {
    return {
      searchQuery: '',
      activeTabName: 'recommend', // 控制激活的标签页  
      recommendActiveIndex: '1', // 推荐分类的默认激活菜单项  
      newActiveIndex: '1', // 最新分类的默认激活菜单项  
      hotActiveIndex: '1', // 热门分类的默认激活菜单项  
      foods: [],
      cart: [],
      isCartVisible: false,
      isLoggedIn: false, // 假设已登录
      isPerson: false,
      username: '',
      totalPrice: 0
    };
  },
  beforeMount () {
    //this.queryAllfoods();
    this.handleSelect('recommend', 1)

  },
  mounted () {
    this.isLogged();
  },
  methods: {
    async queryAllfoods () {
      let res = await this.$http.get('/dishes');
      if (res.code == 200) {
        this.foods = res.data;
      }
    },
    /* async queryfoodBycondition() {
      let cod = this.activeMenu
      let
      let res = await this.$http.get('/dishes')
    } */
    async handleSelect (tabName, event) {
      console.log(tabName, event)
      let cod
      let index = event
      // 根据 tabName 来更新对应的 activeIndex  
      if (tabName === 'recommend') {
        //this.recommendActiveIndex = index;
        cod = 1
      } else if (tabName === 'new') {
        //this.newActiveIndex = index;
        cod = 2
      } else if (tabName === 'hot') {
        //this.hotActiveIndex = index;
        cod = 3
      }
      let res = await this.$http.get('/dishes/cod/' + cod + '/index/' + index)
      if (res.code == 200) {
        this.$message.success("查询成功")
        this.foods = res.data;
      } else {
        this.$message.error("查询失败")
      }
    },
    //检测登录状态
    async isLogged () {
      //获取token
      let token = localStorage.getItem('token');
      console.log(token);
      if (token) {
        console.log("success");
        this.isLoggedIn = true;
        this.isPerson = true;
        let res = await this.$http.get('/user/token');
        if (res.code == 200) {
          this.username = res.data.username;
        } else {
          this.$message.error("获取用户信息失败")
        }
      } else {
        this.$message.error("请先登录")
      }
    },
    LoginOut () {
      //删除token
      localStorage.removeItem('token');
      this.isLoggedIn = false;
      this.isPerson = false;
      this.username = '';
      this.$router.push({
        name: 'Front'
      });
      //刷新页面
      //window.location.reload();
    },
    goToLogin () {
      this.$router.push({
        name: 'Login'
      });
    },
    goToAdmin () {
      this.$router.push({
        name: 'manage'
      });
    },
    search () {
      console.log('搜索内容:', this.searchQuery);
    },
    async addToOrder (food) {
      console.log(food)
      let res = await this.$http.post('/cart/add/' + food.id)
      console.log(res)
      if (res.code == 200) {
        this.$message.success("加入购物车成功")
        let res = await this.$http.get('/cart/list')
        if (res.code == 200) {
          this.getAllCount()
          this.cart = res.data;
        } else {
          this.$message.error("查询购物车失败")
        }
      } else {
        this.$message.error("加入购物车失败")
      }
      /* const itemIndex = this.cart.findIndex(item => item.name === food.name);
      if (itemIndex === -1) {
        this.cart.push({
          ...food,
          count: 1
        });
      } else {
        this.cart[itemIndex].count += 1;
      } */

    },
    async toggleCart () {
      console.log("toggleCart")
      this.isCartVisible = !this.isCartVisible;
      let res = await this.$http.get('/cart/list')
      if (res.code == 200) {
        this.getAllCount()
        console.log("zzz")
        console.log(res)
        this.cart = res.data;
      } else {
        this.$message.error("查询购物车失败")
      }
    },
    removeFromCart (index) {
      this.cart.splice(index, 1);
    },
    calculateTotal () {
      return this.cart.reduce((total, item) => total + item.price * item.count, 0);
    },
    payOrder () {
      // 支付订单逻辑
    },
    goToPerson () {

    },
    paycartToOrder () {

    },
    async getAllCount () {
      let res = await this.$http.get('/cart/total')
      if (res.code == 200) {
        this.totalPrice = res.data
      } else {
        this.$message.error("计算失败")
      }
    },
    async cartClr () {
      let res = await this.$http.delete('/cart/clear')
      if (res.code == 200) {
        this.$message.success("清空购物车成功")
        this.cart = []
        this.totalPrice = 0
      } else {
        this.$message.error("清空购物车失败")
      }
    }
  },
};

</script>

<style scoped>
body {
  background-color: #aec6f7;
  margin: 0;
  padding: 0;
  font-family: Arial, sans-serif;
}

.header {
  background-color: #89cff0;
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
  background-color: #aec6f7;
  display: flex;
  padding: 20px;
  border-radius: 8px;
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
}

.button-container {
  display: flex;
  align-items: center;
}

.cart-button {
  background-color: #ffc107;
  margin-left: 10px;
  /* 按钮之间的间距 */
}

.payment-button {
  background-color: #67c23a;
  /* 修改按钮颜色以匹配整体设计 */
  margin-left: 10px;
}

.food-display-container {
  margin-top: 20px;
}

.food-display {
  background-color: #ffffff;
  padding: 20px;
  border-radius: 8px;
  border: 2px solid #000;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  min-height: 600px;
  /* 设置黑框区域的最小高度 */
  overflow: hidden;
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

.cart-content {
  max-height: 60%;
  overflow-y: auto;
}
.cart-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px 0;
  border-bottom: 1px solid #e0e0e0;
}
.cart-item-details {
  display: flex;
  flex-direction: column;
}
.cart-item-name {
  font-weight: bold;
  color: #333;
}
.cart-item-price {
  font-size: 14px;
  color: #999;
}
.el-drawer__body {
  padding: 20px;
  background-color: #f5f5f5; /* 设置背景色，增加视觉层次 */
}

.el-list-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px 0;
}

.drawer-footer {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 20px;
  padding-top: 10px;
  border-top: 1px solid #e0e0e0; /* 添加顶部边框，与内容区分 */
}

.total-amount {
  flex: 1;
  text-align: left;
  font-size: 18px;
  color: #000; /* 总金额字体颜色和大小 */
}

.submit-order-button {
  flex: 0;
}

.el-main {
  background-color: #aec6f7;
  /* 设置 el-main 的背景颜色 */
  padding: 20px;
  border-radius: 8px;
}

.el-aside {
  background-color: #e9eaec;
  /* 设置 el-aside 的背景颜色 */
  border-radius: 8px;
  padding: 10px;
}

.menu-item-custom {
  background-color: #e9eaec;
  /* 背景 */
}
</style>
