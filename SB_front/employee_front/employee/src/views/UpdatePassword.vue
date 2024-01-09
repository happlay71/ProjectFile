<template>
    <div>
        <el-dialog
            v-model="dialogVisible"
            :title="title"
            width="30%"
            :close-on-click-modal="false"
            center
        >

            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="dialogVisible = false">取消</el-button>
                    <el-button type="primary" @click="submitForm">
                        继续
                    </el-button>
                </span>
            </template>

            <el-form
                :model="formData"
                :rules="rules"
                ref="formDataRef"
                label-width="80px"
                @submit.prevent
            >
                <!-- input输入 -->

                <template v-if="!formData.userId">
                    <el-form-item label="密码" prop="password" >
                        <el-input 
                            clearable 
                            placeholder="请输入密码" 
                            v-model.trim="formData.password"
                            type="password"
                            show-password
                        ></el-input>
                    </el-form-item>

                    <el-form-item label="重复密码" prop="rePassword" >
                        <el-input 
                            clearable 
                            placeholder="请再次输入密码" 
                            v-model.trim="formData.rePassword"
                            type="password"
                            show-password
                        ></el-input>
                    </el-form-item>

                </template>

            </el-form>
            
        </el-dialog>
    </div>
</template>

<script setup>
import { ref, reactive, getCurrentInstance, nextTick } from "vue"
import { ElMessage } from "element-plus";
import axios from "axios";

const dialogVisible = ref(false);
const title = ref("修改密码");

const formData = ref({});
const formDataRef = ref();

const regs = {
    password: /^(?=.*\d)(?=.*[a-zA-Z])[\da-zA-Z~!@#￥%……&*_]{8,18}$/,
};

const check = (reg, rule, value, callback) => {
  if (!value) {
    callback(new Error(rule.message))
  } else {
    if (reg.test(value)) {
        callback();
    } else {
        callback(new Error(rule.message));
    }
  }
};

const checkPassword = (rule, value, callback) => {
  check(regs.password, rule, value, callback);
};
const checkRePassword = (rule, value, callback) => {
  if (value && formData.value.password != value) {
    callback(new Error(rule.message));
  } else {
    callback();
  }
};

const rules = {
    password: [
        { required: true, message: "请输入密码" },
        {
            validator: checkPassword, 
            message: "密码只能是数字，字母，特殊字符8-18位",

        },],
    rePassword: [
        { required: true, message: "请再次输入密码" },
        {
            validator: checkRePassword, 
            message: "两次密码不一致",

        },],
};

const submitForm = () => {
    formDataRef.value.validate(async (valid) => {
        if (!valid) {
            return;
        }
        let params = {};
        Object.assign(params, formData.value);

        let result = await axios.post(
            "/api/userInfo/updatePassword",
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
        ElMessage.success("密码修改成功");
        dialogVisible.value = false;
    });
};

const show = (userId) => {
    dialogVisible.value = true;
    nextTick(() => {
        // 重置表单
        formDataRef.value.resetFields();
        formData.value = {
            userId,
        };
    });
};

defineExpose ({
    show,
});

</script>

<style lang="scss" scoped>

</style>
