<template>
  <div style="width:100%;height: 100vh;background-color: blueviolet;overflow: hidden">
    <div style="width:400px;margin:150px auto">
      <div style="color:#cccccc;font-size: 30px;text-align:center;padding:30px">欢迎注册</div>
      <el-form ref="form" :model="form" size="normal">
        <el-form-item prop="name">
          <el-input prefix-icon="el-icon-user-solid" v-model="form.name"></el-input>
        </el-form-item>

        <el-form-item prop="password">
          <el-input prefix-icon="el-icon-lock" v-model="form.password" show-password></el-input>
        </el-form-item>
        <el-form-item>
          <el-input prefix-icon="el-icon-lock" v-model="form.confirm" show-password></el-input>
        </el-form-item>
        <el-form-item prop="confirm">
          <el-button style="width: 100%" type="primary" @click="login">注册</el-button>
        </el-form-item>

      </el-form>
    </div>

  </div>
</template>

<script>

import request from "../utils/request";

export default {
  name: 'register',
  components: {

  },
  data() {
    return {
      form:{},
      rules:{
        name:[{required:true,message:'请输入姓名',trigger:'blur'}],
        password:[{required:true,message:'请输入密码',trigger:'blur'}],

        confirm:[{required:true,message:'请确认密码',trigger:'blur'}]
      }
    }
  },
  /*页面刷新时就会调用*/

  methods:{
    register() {
      if (this.form.password !== this.form.confirm) {
        this.$message({
          type: "error",
          message: '两次密码不一致'
        })
        return
      }
    },

      login()
      {
        this.$refs['form'].validate((valid) => {
          if (valid) {

            request.post("/api/user/login", this.form).then(res => {
              if (res.code === '0') {
                this.$message({
                  type: "success",
                  message: "注册成功"

                })
                this.$router.push("/login")
              } else {
                this.$message({
                  type: "error",
                  message: res.msg
                })
              }
            })

          }
        })




    }
}}
</script>
