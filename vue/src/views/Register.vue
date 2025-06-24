<template>
  <div class="bg">
    <div style="width: 350px; background-color: #e4e8ee; border-radius: 5px; box-shadow: 0 0 10px rgba(0,0,0,0.1); padding: 20px">
      <el-form status-icon="true" ref="formRef" :model="data.form" :rules="data.rules">
        <div style="margin-bottom: 40px; text-align: center; font-weight: bold; font-size: 24px">欢 迎 注 册</div>
        <el-form-item prop="username" label="账号">
          <el-input size="large" v-model="data.form.username" auto-complete="off" prefix-icon="User" placeholder="请输入账号" />
        </el-form-item>
        <el-form-item prop="password" label="密码">
          <el-input size="large" show-password v-model="data.form.password" auto-complete="off" prefix-icon="lock" placeholder="请输入密码" />
        </el-form-item>
        <el-form-item prop="confirmPassword" label="密码">
          <el-input size="large" show-password v-model="data.form.confirmPassword" auto-complete="off" prefix-icon="lock" placeholder="请再次确认密码" />
        </el-form-item>
        <div style="margin-bottom: 20px">
          <el-button style="width: 100%; background-color: #248243; border-color: #248243" type="primary" size="large" @click="register">注 册</el-button>
        </div>
        <div style="text-align: right">
          已有账号？请 <a style="color: #248243" href="/login">登录</a>
        </div>
      </el-form>
    </div>
  </div>
</template>

<script setup>
  import {reactive, ref} from "vue";
  import request from "@/utils/request.js";
  import router from "@/router/index.js";

  const validatePass = (rule, value, callback) => {
    if (value !== data.form.password) {
      callback(new Error("Two inputs don't match!"))
    } else {
      callback()
    }
  }

  const formRef = ref()

  const data = reactive({
    form: {},
    rules: {
      username: [
        { required: true, message: '请输入账号', trigger: 'blur' },
        { min: 3, message: '账号最少3位', trigger: 'blur' },
      ],
      password: [
        { required: true, message: '请输入密码', trigger: 'blur' }
      ],
      confirmPassword: [
        { required: true, message: '请再次确认密码', trigger: 'blur' },
        {validator: validatePass, trigger: 'blur'}
      ]
    }
  })

  const register = () => {
    formRef.value.validate((valid) => {
      if (valid) {
        request.post('/register', data.form).then(res => {
          if (res.code === '200') {
            ElMessage.success('Register success')
            router.push('/login')
          } else {
            ElMessage.error(res.msg)
          }
        })
      }
    })
  }


</script>

<style scoped>
.bg{
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  overflow: hidden;
  background-image: url("@/assets/imgs/bg1.jpg");
  background-size: cover;
}
</style>