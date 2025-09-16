<template>
  <div>
    <div class="card" style="margin-bottom: 5px">
      <el-input clearable @clear="load" style="width: 260px; margin-right: 5px" v-model="data.title" placeholder="请输入标题查询" :prefix-icon="Search"></el-input>
      <el-button type="primary" @click="load">查 询</el-button>
      <el-button @click="reset">重 置</el-button>
    </div>

    <div class="card" style="margin-bottom: 5px" v-if="data.user.role === 'ADMIN'">
      <el-button type="primary" @click="handleAdd">新 增</el-button>
    </div>

    <div class="card" style="margin-bottom: 5px">
      <el-table :data="data.tableData" style="width: 100%" :header-cell-style="{ color: '#333', backgroundColor: '#eaf4ff' }">
        <el-table-column prop="title" label="分类标题" />
        <el-table-column label="操作" width="100px" >
          <template #default="scope" >
            <el-button type="primary" circle="true" icon="Edit" @click="handleEdit(scope.row)"></el-button>
            <el-button type="danger" circle="true" icon="Delete" @click="del(scope.row.id)"></el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <el-dialog title="分类信息" v-model="data.formVisible" width="30%" destroy-on-close>
      <el-form ref="formRef" :model="data.form" :rules="data.rules" label-width="80px" style="padding: 20px 30px 10px 0">
        <el-form-item prop="title" label="分类标题">
          <el-input v-model="data.form.title" autocomplete="off" placeholder="Please enter the category title" />
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
import {Search} from "@element-plus/icons-vue";
import {reactive, ref} from "vue";
import request from "@/utils/request.js";
import {ElMessage, ElMessageBox} from "element-plus";

const formRef = ref()

const data = reactive({
  user: JSON.parse(localStorage.getItem('code_user') || "{}"),
  title: null,
  pageNum: 1,
  pageSize: 5,
  total: 0,
  tableData: [],
  form: {},
  formVisible: false,
  rules: {
    title: [
      {required: true, message: 'please enter the category title', trigger: 'blur'}
    ]
  }
})

const load = () => {
  request.get('/category/selectPage', {
    params: {
      pageNum: data.pageNum,
      pageSize: data.pageSize,
      title: data.title,
    }
  }).then(res => {
    if (res.code === '200'){
      data.tableData = res.data?.list
      data.total = res.data?.total
    } else {
      ElMessage.error(res.msg)
    }
  })
}
load()

const handleAdd = () => {
  data.form = {}
  data.formVisible = true
}

const handleEdit = (row) => {
  data.form = { ...row };
  data.formVisible = true
}

const add = () => {
  request.post('/category/add', data.form).then(res => {
    if (res.code === '200'){
      ElMessage.success('Add Success')
      data.formVisible = false
      load()
    } else {
      ElMessage.error(res.msg)
    }
  })
}

const update = () => {
  request.put('/category/update', data.form).then(res => {
    if (res.code === '200'){
      ElMessage.success('Update Success')
      data.formVisible = false
      load()
    } else{
      ElMessage.error(res.msg)
    }
  })
}

const save = () => {
  formRef.value.validate(valid => {
    if (valid){
      data.form.id ? update() : add()
    }
  })
}

const del = (id) => {
  ElMessageBox.confirm('Deletion will not convert, please confirm!', 'delete confirm', {type: 'warning'}).then(res => {
    request.delete('/category/delete/' + id).then(res => {
      if (res.code === '200'){
        ElMessage.success('Delete Success')
        load()
      } else {
        ElMessage.error(res.msg)
      }
    })
  }).catch(err => {})
}

const reset = () => {
  data.title = null
  load()
}
</script>