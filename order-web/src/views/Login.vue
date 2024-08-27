<!--
 * 用户登录页
-->
<template>
  <div class="login-index" :style="backgroundDiv">

    <div class="login-window-index">

      <div class="title">
        <b><img src="../assets/logo.png" style="width: 40px;position: relative; top: 13px;right: 6px">
          <span style="color: #e75c09">登录点餐系统</span>
        </b>
      </div>

      <div style="margin-top: 30px">

        <el-form label-width="70px">

          <el-form-item label="用户名">
            <el-input v-model.trim="user.username" aria-required="true"></el-input>
          </el-form-item>

          <el-form-item label="密码" style="margin-top: 25px">
            <el-input v-model.trim="user.password" show-password aria-required="true"></el-input>
          </el-form-item>

          <el-form-item style="margin: 30px 80px">
            <el-button type="success" @click="onSubmit">登录</el-button>
            <el-button @click="$router.push('/register')">注册</el-button>
          </el-form-item>

        </el-form>
      </div>
    </div>
  </div>
</template>

<script>
  //import md5 from 'js-md5'
  export default {
    name: "Login",
    //初始化数据
    data() {
      return {
        to: '/', //登陆成功跳转的页面
        user: {},
        backgroundDiv: {
          backgroundImage: "url(" + require("../assets/img/login_back.png") + ")",
          backgroundRepeat: "no-repeat",
          backgroundSize: "100% 100%",
        },
      }
    },
    //页面创建
    created() {
      this.to = this.$route.query.to ? this.$route.query.to : "/"
    },
    //方法
    methods: {
      //登录提交
      async onSubmit() {
        if (this.user.username === '' || this.user.password === '') {
          this.$message.error("账号或密码不能为空")
          return false;
        }
        let res = await this.$http.post("/login", this.user)
        //this.user.password = md5(this.user.password);
        //this.request.post("/login", this.user).then(res => {
        if (res.code === 200) {
          this.$message.success({
            message: "登陆成功",
            showClose: true
          })
          let token = res.data
          console.log(token);
          localStorage.setItem('token', token); // 存储token
          this.$router.push('/')
          //localStorage.setItem("user", JSON.stringify(res.data))
        } else {
          this.$message.error({
            type: 'error',
            message: '登录失败'
          });
        }
        //})
      }
    }
  }

</script>

<style scoped>
  .login-index {
    background: #ffffff;
    background-image: url("@/assets/img/login_back.png");
    height: 100%;
    position: relative;
  }

  .login-window-index {
    padding: 20px;
    width: 450px;
    height: 280px;
    background: #ffffff;
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translate(-50%, -50%);
  }

  .title {
    text-align: center;
    margin: 30px auto;
    font-size: 25px;
  }

</style>
