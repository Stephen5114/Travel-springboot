<template>
  <div>
    <div class="card" style="margin-bottom: 5px">
      <el-input clearable @clear="load" style="width: 260px; margin-right: 5px" v-model="data.username" placeholder="请输入账号查询" :prefix-icon="Search"></el-input>
      <el-input clearable @clear="load" style="width: 260px; margin-right: 5px" v-model="data.name" placeholder="请输入名称查询" :prefix-icon="Search"></el-input>
      <el-button type="primary" @click="load">查 询</el-button>
      <el-button @click="reset">重 置</el-button>
    </div>
    <div class="card" style="margin-bottom: 5px">
      <el-button type="danger">批量删除</el-button>
      <el-button type="primary" @click="handleAdd">新 增</el-button>
      <el-button type="success">批量导入</el-button>
      <el-button type="info">批量导出</el-button>
    </div>

    <div class="card" style="margin-bottom: 5px">
      <el-table :data="data.tableData" style="width: 100%" :header-cell-style="{ color: '#333', backgroundColor: '#eaf4ff' }">
        <el-table-column type="selection" width="55" />
        <el-table-column prop="username" label="Account" />
        <el-table-column prop="name" label="Name" />
        <el-table-column prop="phone" label="PhoneNumber" />
        <el-table-column prop="email" label="Email" />
      </el-table>
    </div>
    <div class="card">
      <el-pagination
          v-model:current-page="data.pageNum"
          v-model:page-size="data.pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :page-size="[5, 10, 20]"
          :total="data.total"
          @current-change="load"
          @size-change="load"
      />
    </div>

    <el-dialog title="管理员信息" v-model="data.formVisible" width="30%" destroy-on-close>
      <el-form ref="formRef" :model="data.form" :rules="data.rules" label-width="80px" style="padding: 20px 30px 10px 0">
        <el-form-item prop="username" label="账号">
          <el-input v-model="data.form.username" autocomplete="off" />
        </el-form-item>
        <el-form-item prop="name" label="名称">
          <el-input v-model="data.form.name" autocomplete="off" />
        </el-form-item>
        <el-form-item prop="phone" label="电话">
          <el-input v-model="data.form.phone" autocomplete="off" />
        </el-form-item>
        <el-form-item prop="email" label="邮箱">
          <el-input v-model="data.form.email" autocomplete="off" />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="data.formVisible = false">取 消</el-button>
          <el-button type="primary" @click="save">保 存</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { reactive } from "vue";
import {Search} from "@element-plus/icons-vue";
import axios from "axios";
import request from "@/utils/request.js";
import {ElMessage} from "element-plus"

const data = reactive({
  username: null,
  name: null,
  pageNum: 1,
  pageSize: 5,
  total: 0,
  tableData: [],
  formVisible: false,
  form: {}
})

const load = () => {
  request.get('/admin/selectPage', {
    params: {
      pageNum: data.pageNum,
      pageSize: data.pageSize,
      username: data.username,
      name: data.name
    }
  }).then(res => {
    if (res.code === '200'){
      data.tableData = res.data.list;
      data.total = res.data.total;
      console.log(res.data);
    } else {
      ElMessage.error(res.msg)
    }

  })
}
load();

const reset = () => {
  data.name = null
  data.username = null
  load()
}

const handleAdd = () => {
  data.formVisible = true
  data.form = {}
}

const add = () => {
  request.post('/admin/add', data.form).then(res => {
    if (res.code === '200') {
      ElMessage.success('ADD SUCCESS')
    }else{
      ElMessage.error(res.msg)
    }
  })
}

</script>

