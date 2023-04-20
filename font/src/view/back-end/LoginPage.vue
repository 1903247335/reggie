<template>
	<div id="login">
		<div class="login-contain">
      <img src="@/assets/images/login/login-l.png" alt="">

      <el-form ref="formRef" class="login-form" :model="form" :rules="rules">
          <el-form-item >
            <div class="title">

              <img src="@/assets/images/login/logo.png" alt="" class="logo">
            </div>

          </el-form-item>

         <el-form-item prop="username"
         >
          <el-input v-model="form.username" prefix-icon="User"></el-input>
         </el-form-item>
         <el-form-item prop="password">
           <el-input v-model="form.password" prefix-icon="Lock"></el-input>

         </el-form-item>
         <el-form-item>
           <el-button type="primary" :loading="btnStatus" @click="btnLogin" style="background: #ffc200;border: none;width: 100%;border-radius: 8px">登录</el-button>
         </el-form-item>
       </el-form>
    </div>
	</div>
</template>

<script setup>
import {reactive, ref} from "vue";
import {ElMessage} from "element-plus";
import {backEndLogin} from "@/api/backEnd";
import {useRouter} from "vue-router"
import {addStorageUser} from "@/untils";

const btnStatus =ref(false)
const formRef=ref()
const router=useRouter()
 const form=reactive({
   username:'',
   password:''
 })
const rules=reactive({
   userName:[
       {required:true,message:'请输入用户名',trigger:'blur'},
     {min:4,max:10,message: '用户名长度4-10',trigger: 'blur'},

   ],
   password:[
       {required:true,message:'输入密码',trigger:'blur'},
     {min:4,max:10,message: '密码长度4-10',trigger: 'blur'},
   ]

 })

 const changeBtnStatus=()=>{
   //按钮状态切换
   btnStatus.value=!btnStatus.value;
 }
 const btnLogin =()=>{
//获取form的ref执行validate()进行验证，‘
   //成功就进入then 失败就进入catch
   formRef.value.validate().then(

       async ()=>{
         console.log(formRef.value)
         changeBtnStatus()

         const result= await backEndLogin(form)
         if (result.data.code===1&&result.data.data.status!==0){

           console.log(result)
           addStorageUser(JSON.stringify(result.data.data))
           ElMessage.success("登录成功")
           router.push('/backend/admin/member')
         }
         else if (result.data.code===1&&result.data.data.status===0){
           ElMessage.error("账号被禁用")
           changeBtnStatus()
         }
         else {
           ElMessage.error("登录失败")
           changeBtnStatus()
         }
       }
   ).catch(()=>{

   })

 }




</script>



<style scoped lang="less">
	#login {
		background: #383339;
    height: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
    .login-contain{
      width: 1000px;
      height: 475px;
      display: flex;
      img{
        width: 60%;
        height: auto;
      }
      .login-form{
        .username{
          width: 100%;
          border-bottom: 1px solid #e9e9e8;
        }
        .password{
          width: 100%;
          border-bottom: 1px solid #e9e9e8;
        }
        .el-form-item{
          margin-bottom: 30px;

          .el-icon{
            font-size: 20px;
            color: gray;
          }

        }
        padding: 100px 75px;
        width: 40%;
        background: white;
        display: flex;
        flex-direction: column;
        justify-content: center;
        height: 100%;
        box-sizing: border-box;

        .title{
          text-align: center;
          .login-box {
            width: 1000px;
            height: 474.38px;
            border-radius: 8px;
            display: flex;
          }
        }

      }

    }

  }

</style>
