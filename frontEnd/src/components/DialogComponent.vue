<template>
  <div class="dialog-contain">

    <el-dialog
        :close-on-click-modal="false"
        v-model="isShow"

        :title="title"
        width="30%"
        :before-close="cancel"
        @open="handleOpen"

    >
      <el-form :model="form" :rules="rules" ref="formRef">
        <el-form-item prop="name" label="分类名称" label-width="80">
          <el-input v-model="form.name"></el-input>

        </el-form-item >
        <el-form-item prop="sort" label="排序" label-width="80">
          <el-input v-model="form.sort"></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
      <span class="dialog-footer">
        <el-button @click="cancel">取消</el-button>
        <el-button type="warning" @click="addBtn">
          {{form.type!=3?'修改':'添加'}}
        </el-button>
        <el-button type="primary" v-if="typeDialog!=3">
          保存并继续添加
        </el-button>
      </span>
      </template>

    </el-dialog>
  </div>


</template>

<script setup>

import {defineProps, reactive, ref, watch,defineEmits} from "vue"
import {sendAddCategory,sendUpdateCategory} from "@/api/backEnd";
import {ElMessage} from "element-plus";
import {deepCopy,isUpdateList} from "@/untils";

const props=defineProps({
  dialogTitle:{
    type:String,
  },
  dialogShow:{
    type:Boolean,
  },
  data:{
    type:Object
  },
  typeDialog:{
    type:Number
  }
})
const formRef=ref()

const form=reactive({
  id:'',
  name:'',
  sort:'',
  type:1
})
const oldForm=reactive({})

const emit=defineEmits(["changeStatus"])
const isShow=ref(false)
const title=ref('')

const validateName=(rule,value,callback)=>{
  const reg=/^[\u4e00-\u9fa5]{2,}$/
  if(reg.test(value)){
    callback()
  }else{
    return callback(new Error("输入中文1个字以上"))
  }
}
const rules=reactive({
  name:[
      {required:true,message:"名字不能为空",trigger:"blur"},
    {validator:validateName,trigger: 'blur'}
  ],
  sort:[
    {required:true,message:"排序不能为空",trigger:"blur"}
  ]
})




watch(()=>props.dialogShow,(newValue)=>{
  isShow.value=newValue
})
watch(()=>props.dialogTitle,(newValue)=>{
  //点击新增按钮的时候的标题修改
  title.value=newValue
  form.type=props.typeDialog

})

watch(()=>props.data.sort,(newValue)=>{
  form.sort=newValue//编辑的时候，改变form表单值

})
watch(()=>props.data.id,(newValue)=>{

  form.id=newValue//编辑的时候，改变form表单值

})
watch(()=>props.data.name,(newValue)=>{
  form.name=newValue//编辑的时候，改变form表单值
  deepCopy(oldForm,form)

})





const addBtn=()=>{

    formRef.value.validate().then(async()=>{

      if (form.type==3){
          console.log(form)
          if (!isUpdateList(form,oldForm) ){

                  const result=await sendUpdateCategory(form,oldForm)
                  console.log(result)
                  if(result.data.code==1){
                    ElMessage.success("更新成功")
                    emit("changeStatus","success")
                  }
                  else{
                    ElMessage.error("更新失败")
                  }
          }else{
            ElMessage.error("内容没有修改")
          }

      }else{
        const result=await sendAddCategory(form)
        if(result.data.code==1){

          ElMessage.success("添加成功")

          emit("changeStatus","success")

        }else{
          ElMessage.error("添加的菜名重复")
        }
      }



    }).catch(()=>{

    })
}

const cancel=()=>{

  emit("changeStatus","cancel")//当点击取消或者点击叉
}



const handleOpen=()=>{
  formRef.value.resetFields()
  if(form.type!=3) {

    form.name= ""
    form.sort = ""
    form.id = ""

  }else{
    console.log('这是form',form)
    form.name=oldForm.name
    form.id=oldForm.id
    form.sort=oldForm.sort
    form.name=oldForm.name
  }

  console.log('hello',form)


}

</script>

<style scoped>
/deep/.el-dialog__title{
  font-family: '微软雅黑',serif!important;
}
</style>
