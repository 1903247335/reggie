<template>
  <div class="contain">
    <el-form :rules="rules" :model="form" ref="formRef">
      <el-form-item label="账号" prop="username">
        <el-input  placeholder="请输入账号" v-model="form.username"></el-input>
      </el-form-item>
      <el-form-item label="员工姓名" prop="name">
        <el-input  placeholder="请输入员工姓名" v-model="form.name"></el-input>
      </el-form-item>
      <el-form-item label="手机号" prop="phone">
        <el-input  placeholder="请输入手机号" v-model="form.phone"></el-input>
      </el-form-item>
      <el-form-item label="性别" prop="sex
">
        <el-radio-group  v-model="form.sex" fill="#ffb606">
          <el-radio :label="1">男</el-radio>
          <el-radio :label="0">女</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="身份证号" prop="idNumber">
        <el-input placeholder="请输入身份证号" v-model="form.idNumber"></el-input>
      </el-form-item>


      <el-row justify="center">
        <el-col :span="10">
          <el-form-item>
            <el-button type="info" @click="cancel" >取消</el-button>
            <el-button type="primary" @click="updateBtn" v-if="props.typeComponent==='Edit'" >提交</el-button>
            <div v-else>
              <el-button type="warning" @click="save" >保存</el-button>
              <el-button type="primary" @click="saveAddContinue" >保存并继续添加</el-button>
            </div>
          </el-form-item>

        </el-col>

      </el-row>


    </el-form>
  </div>
</template>

<script setup>
import {onMounted, reactive, ref} from "vue";
import {router} from "@/router";
import {addEmployee, sendUpdateEmployee,sendSingleEmployee} from "@/api/backEnd";
import {ElMessage} from "element-plus";
import {defineProps} from "vue"
import {deepCopy,isUpdateList} from "@/untils";

const props=defineProps({
  typeComponent:{
    type:String,

  }
})
const validateName=(rule,value,callback)=>{
  const reg = /^[\u4E00-\u9FA5]{2,4}$/
  if (reg.test(value)){
    callback()
  }
  else{
    callback(new Error("输入正确的姓名"))
  }

}
const validatePhone=(rule,value,callback)=>{
  const reg=/^1[3-9]\d{9}$/
  if (reg.test(value)){
    callback()
  }else{
    callback(new Error('输入正确的电话号码'))
  }

}

const validateIdNumber=(rule,value,callback)=>{
  const reg=/^[1-9]\d{5}(19|20)\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/
  if (reg.test(value)){
    callback()
  }else{
    callback(new Error('输入正确的身份证号'))
  }

}




const formRef=ref()
const form=reactive({
  id:"",
  username:'',
  name:'',
  phone:'',
  sex:'',
  idNumber:''
})
const oldForm=reactive({})
const rules=reactive({
  username:[
    {required:true,message:'账号不能为空',trigger:'blur'},
    {min:6,max:20,message:'长度必须在6-20',trigger: 'blur'}
  ],
  name:[
    {required:true,message:'姓名不能为空',trigger:'blur'},
    {validator:validateName,trigger: 'blur'}
  ]
  ,
  phone:[
    {required:true,message:'手机号不能为空',trigger:'blur'},
    {validator:validatePhone,trigger: 'blur'}

  ],
  idNumber:[
    {required:true,message:'身份证号不能为空',trigger:'blur'},
    {validator:validateIdNumber,trigger: 'blur'}
  ],
})

const cancel=()=>{

  router.back()
  formRef.value.resetFields()
}
const save=()=>{
  formRef.value.validate().then(async ()=>{
    const result=await addEmployee(form)
    if (result.data.code===1){
      ElMessage.success("添加成功")
      cancel()
    }
    else{
      ElMessage.error("添加失败")
    }
  }).catch(()=>{

  })
}

const saveAddContinue=()=>{
  formRef.value.validate().then(async ()=>{
    const result=await addEmployee(form)
    if (result.data.code===1){
      ElMessage.success("添加成功")
      formRef.value.resetFields()
    }
    else{
      ElMessage.error("添加失败")
    }
  }).catch(()=>{

  })}


  const updateBtn=async()=>{

    formRef.value.validate().then(async ()=>{

      if (!isUpdateList(form,oldForm)){

        console.log(form,oldForm)
        const result=await sendUpdateEmployee(form)
        if (result.data.code===1){
          ElMessage.success("修改成功")
          router.back()
          formRef.value.resetFields()

        }
        else{
          ElMessage.error("修改失败")

        }
      }else{
        ElMessage.error("修改内容不能相同")
      }



    }).catch(()=>{

    })
  }




  const loadEmployee=async ()=>{
    const id=router.currentRoute.value.query.id

    const {data:{data:{ username,name, phone, sex, idNumber}}}=await sendSingleEmployee(id)

    form.name=name
    form.username=username
    form.phone=phone
    form.sex=parseInt(sex)
    form.idNumber=idNumber
    deepCopy(oldForm,form)
    console.log(oldForm,form)
  }

  onMounted(()=>{
    form.id=router.currentRoute.value.query.id
    if(props.typeComponent=="Edit"){

      loadEmployee()
    }

  })


</script>

<style scoped>

</style>
