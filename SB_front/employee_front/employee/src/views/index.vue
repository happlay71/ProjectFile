<template>
  <div class="theme-toggler-content theme-toggler">

    <div aria-label="切换暗色主题" aria-checked="false">
      <div class="el-switch">
        <input class="el-switch__input" type="checkbox" role="switch" aria-checked="false" aria-disabled="false" name="" true-value="true" false-value="false" id="el-id-9023-96">
          <span class="el-switch__core">
            <div class="el-switch__action">
              <i class="el-icon">
                <svg viewBox="0 0 24 24" class="light-icon">
                  <path d="M6.05 4.14l-.39-.39a.993.993 0 0 0-1.4 0l-.01.01a.984.984 0 0 0 0 1.4l.39.39c.39.39 1.01.39 1.4 0l.01-.01a.984.984 0 0 0 
                  0-1.4zM3.01 10.5H1.99c-.55 0-.99.44-.99.99v.01c0 .55.44.99.99.99H3c.56.01 1-.43 1-.98v-.01c0-.56-.44-1-.99-1zm9-9.95H12c-.56 0-1 
                  .44-1 .99v.96c0 .55.44.99.99.99H12c.56.01 1-.43 1-.98v-.97c0-.55-.44-.99-.99-.99zm7.74 3.21c-.39-.39-1.02-.39-1.41-.01l-.39.39a.9
                  84.984 0 0 0 0 1.4l.01.01c.39.39 1.02.39 1.4 0l.39-.39a.984.984 0 0 0 0-1.4zm-1.81 15.1l.39.39a.996.996 0 1 0 1.41-1.41l-.39-.39
                  a.993.993 0 0 0-1.4 0c-.4.4-.4 1.02-.01 1.41zM20 11.49v.01c0 .55.44.99.99.99H22c.55 0 .99-.44.99-.99v-.01c0-.55-.44-.99-.99-.99h-
                  1.01c-.55 0-.99.44-.99.99zM12 5.5c-3.31 0-6 2.69-6 6s2.69 6 6 6s6-2.69 6-6s-2.69-6-6-6zm-.01 16.95H12c.55 0 .99-.44.99-.99v-.96c0
                  -.55-.44-.99-.99-.99h-.01c-.55 0-.99.44-.99.99v.96c0 .55.44.99.99.99zm-7.74-3.21c.39.39 1.02.39 1.41 0l.39-.39a.993.993 0 0 0 0-
                  1.4l-.01-.01a.996.996 0 0 0-1.41 0l-.39.39c-.38.4-.38 1.02.01 1.41z" fill="currentColor"></path>
                </svg>
              </i>
            </div>
          </span>
      </div>

      <div class="el-switch is-checked">
        <input class="el-switch__input" type="checkbox" role="switch" aria-checked="true" aria-disabled="false" name="" true-value="true" false-value="false" id="el-id-3615-96">
          <!--v-if-->
          <span class="el-switch__core">
            <!--v-if-->
            <div class="el-switch__action">
              <i class="el-icon">
                <svg viewBox="0 0 24 24" class="dark-icon">
                  <path d="M11.01 3.05C6.51 3.54 3 7.36 3 12a9 9 0 0 0 9 9c4.63 0 8.45-3.5 8.95-8c.09-.79-.78-1.42-1.54-.95A5.403 5.403 0 0 1 11.1
                  7.5c0-1.06.31-2.06.84-2.89c.45-.67-.04-1.63-.93-1.56z" fill="currentColor">
                  </path>
                </svg>
            </i>
          </div>
        </span>
        <!--v-if-->
      </div>
    </div>

    <div>
      <el-form 
        :model="searchFormData"
        label-width="80px"
        @submit.prevent  
      >
        <el-row :gutter="5">
          <el-col :span="5">
            <!-- input输入 Fuzzy表示支持模糊搜索-->
            <el-form-item label="手机号">
              <el-input
                clearable
                placeholder="支持模糊搜索"
                v-model.trim="searchFormData.phoneFuzzy"
              ></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="5">
            <!-- input输入 Fuzzy表示支持模糊搜索-->
            <el-form-item label="用户名">
              <el-input
                clearable
                placeholder="支持模糊搜索"
                v-model.trim="searchFormData.userNameFuzzy"
              ></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="5">
            <el-button type="primary" @click="loadData">搜索</el-button>
            <el-button type="primary" @click="showEdit()">新增用户</el-button>
          </el-col>

        </el-row>
      </el-form>
    </div>

    <el-table :data="tableData" style="width: 100%">
      <el-table-column label="用户ID" prop="userId"> </el-table-column>
      <el-table-column label="用户名" prop="userName"> </el-table-column>
      <el-table-column label="手机号" prop="phone"> </el-table-column>
      <el-table-column label="出生日期" prop="birthday"> </el-table-column>
      <el-table-column label="性别" prop="sex"> 
        <template #default="scope">
          {{ SEX_MAP[scope.row.sex] }}
        </template>
      </el-table-column>
      <el-table-column label="职位" prop="position"> 
        <template #default="scope">
          {{ POSITION_MAP[scope.row.position] }}
        </template>
      </el-table-column>
      <el-table-column label="角色" prop="roles"> 
        <template #default="scope">
          {{ ROLES_MAP[scope.row.roles] }}
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template #default="scope">
          <el-button-group>
            <el-button 
              type="primary" 
              @click="showEdit(scope.row)"
            >修改</el-button
            >
            <el-button 
              type="primary" 
              @click="updatePwd(scope.row.userId)"
            >修改密码</el-button
            >
            <el-popconfirm title="你确定要删除吗？" @confirm="delUser(scope.row.userId)">
              <template #reference>
                <el-button
                  type="danger"
                  >删除</el-button
                >
              </template>
            </el-popconfirm>
            
          </el-button-group>
        </template>
      </el-table-column>
    </el-table>
    <div :style="{ 'margin-top': '20px' }">
      <el-pagination 
        background 
        layout="prev, pager, next" 
        :total="total" 
        :page-size="2" 
        @current-change="pageChange"
      />
    </div>
    <UserEdit ref="userEditRef" @reload="loadData"></UserEdit>
    <UpdatePassword ref="updatePasswordRef"></UpdatePassword>
  </div>

</template>

<script setup>
import UpdatePassword from './UpdatePassword.vue'
import UserEdit from "./UserEdit.vue"
import { ElMessage } from 'element-plus';
import axios from "axios";
import { ref, reactive, getCurrentInstance, nextTick } from "vue"
import { SEX_MAP, POSITION_MAP, ROLES_MAP } from "@/utils/Constants.js"

const { proxy } = getCurrentInstance();

const api = {
  loadDataList:"/api/userInfo/loadDataList",
  delUser:"/api/userInfo/delUser",
};



const searchFormData = ref({});

// ref 函数来创建一个响应式的数据引用 tableData，并将其初始化为空数组 []
const tableData = ref([]);
const total = ref(0);
const pageSize = ref(2);
const pageNo = ref(1);

// loadData 的常量，它被赋值为一个异步函数。关键字 async 表明这是一个异步函数，
// 它允许在函数体内使用 await 来等待异步操作的完成
const loadData = async () => {
  const params = Object.assign({}, searchFormData.value);
  params.pageNo = pageNo.value;
  // await 关键字来等待 Axios 发起 POST 请求
  // 先传路径，后传数据
  let result = await axios.post(
    api.loadDataList, 
    params,
    {
      headers: {
        'Content-Type': 'multipart/form-data'
      },
    }
  );
  const data = result.data;
  if (data.status != "success") {
    ElMessage.error(data.info);
    return;
  }
  // 加载数据函数
  const start = (pageNo.value - 1) * pageSize.value;
  const end = pageNo.value * pageSize.value;
  // 数据处理
  tableData.value = data.data.list.slice(start, end);
  total.value = data.data.totalCount;
};

loadData();

const pageChange = (e) => {
  pageNo.value = e;
  loadData();
};

const userEditRef = ref();
const showEdit = (data) => {
  let params = null;
  if (data) {
    params = Object.assign({}, data);
  }
  userEditRef.value.show(params);
};

const updatePasswordRef = ref();
const updatePwd = (userId) => {
  updatePasswordRef.value.show(userId);
};

const delUser = async (userId) => {
  let result = await axios.post(
    api.delUser, 
    {
      userId,
    },
    {
      headers: {
        'Content-Type': 'multipart/form-data'
      },
    }
  );
  const data = result.data;
  if (data.status != "success") {
    ElMessage.error(data.info);
    return;
  };
  ElMessage.success("删除成功");
  loadData();
};
</script>

<style lang="scss" scoped>
</style>


