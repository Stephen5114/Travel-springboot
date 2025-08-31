<template>
  <div class="card" style="width: 50%;">
    <div>个人中心</div>
    <el-form ref="formRef" :model="data.user" :rules="data.user" label-width="80px" style="padding: 20px 30px 10px 0">
      <el-form-item prop="username" label="账号">
        <el-input v-model="data.user.username" autocomplete="off" placeholder="Please enter the account" />
      </el-form-item>
      <el-form-item prop="name" label="名称">
        <el-input v-model="data.user.name" autocomplete="off" placeholder="Please enter the username" />
      </el-form-item>
      <el-form-item prop="phone" label="电话">
        <el-input v-model="data.user.phone" autocomplete="off" placeholder="Please enter the mobile phone" />
      </el-form-item>
      <el-form-item prop="email" label="邮箱">
        <el-input v-model="data.user.email" autocomplete="off" placeholder="Please enter the email address" />
      </el-form-item>
      <el-form-item prop="avatar" label="头像">
        <el-upload
            action="http://localhost:9999/files/upload"
            :headers="{token: data.user.token}"
            :on-success="handleAvatarSuccess"
            list-type="picture"
        >
          <el-button type="primary">Upload Avatar</el-button>
        </el-upload>
      </el-form-item>
    </el-form>
    <div style="text-align: center">
      <el-button type="primary" style="padding: 20px 30px" @click="update">保存</el-button>
    </div>
  </div>
</template>

<script setup>
import {reactive} from "vue";
import request from "@/utils/request.js";
import {ElMessage} from 'element-plus';

const data = reactive({
  user: JSON.parse(localStorage.getItem('code_user') || '{}'),
})

const handleAvatarSuccess = (res) => {
  data.user.avatar = res.data
}

const emit = defineEmits(['updateUser'])

const update = () => {
  let url
  if (data.user.role === 'ADMIN'){
    url = '/admin/update'
  }
  if (data.user.role === 'USER'){
    url = '/user/update'
  }
  request.put(url,data.user).then(res => {
    if (res.code === '200') {
      ElMessage.success('Update success')
      localStorage.setItem("code_user", JSON.stringify(data.user))
      emit('updateUser')
    }
  })
}
</script>