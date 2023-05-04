<template>
  <div class="contain">
    <el-form :model="form" :rules="rules" ref="formRef" >

      <el-form-item >

        <el-col :span="6">
          <el-form-item label="套餐名称" prop="name">

            <el-input placeholder="填写套餐名称" v-model="form.name"> </el-input>

          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="套餐分类" prop="categoryId">
            <el-select v-model="form.categoryId" class="m-2" placeholder="菜品分类" size="large">
              <el-option
                  v-for="(item,index) in categoryDish"
                  :key="index"
                  :label="item.name"
                  :value="item.id"
              />
            </el-select>
          </el-form-item>
        </el-col>


      </el-form-item>

      <el-form-item >
        <el-col :span="5">
          <el-form-item label="套餐价格" prop="price">

            <el-input placeholder="设置套餐价格" v-model="form.price"></el-input>

          </el-form-item>

        </el-col>
      </el-form-item>
      <el-form-item label="套餐菜品">
        <el-col :span="4">
          <el-button type="warning" @click="addDishBtn" >
            <template v-slot:icon>
              <el-icon><Plus /></el-icon>
            </template>
            添加菜品
          </el-button>
        </el-col>
        <el-col>
          <AddComboDialog v-if="dialogShow" @updateComboList="updateComboList" @cancel="dialogCancel"></AddComboDialog>
          <el-table :data="form.comboList" v-if="tableShow">
            <el-table-column label="名称"  >
              <template v-slot="{row}">
                <el-input v-model="row.name"></el-input>
              </template>
            </el-table-column>
            <el-table-column label="原价" prop="price">
              <template v-slot="{row}">
                <el-input v-model="row.price"></el-input>
              </template>


            </el-table-column>
            <el-table-column label="份数" prop="count">
              <template v-slot="{row}">
                <el-input-number :min="1" :max="10" v-model="row.count"></el-input-number>
              </template>
            </el-table-column>
            <el-table-column label="操作">
              <template v-slot="{row}">
                <el-button type="danger" @click="deleteBtn(row)">删除</el-button>
              </template>

            </el-table-column>
          </el-table>
        </el-col>
      </el-form-item>
      <el-form-item label="图片上传" prop="image">
        <el-upload
            class="uploader"
            action="http://localhost/font-end/upload-template"
            :show-file-list="false"
            :on-change="handleChange"
            :on-success="handleSuccess"
            :before-upload="beforeUpload"

        >
          <img v-if="srcTemplate" :src="srcTemplate" class="png" />
          <el-icon v-else class="uploader-icon"><Plus /></el-icon>
        </el-upload>
      </el-form-item>
      <el-form-item label="文字描述">
        <el-input
            v-model="form.description"
            :rows="4"
            type="textarea"
            placeholder="菜品描述，最长200字"
        />
      </el-form-item>
      <el-row justify="center">
        <el-col :span="10">
          <el-button @click="router.back()">取消</el-button>
          <el-button type="warning" @click="addCombo">保存</el-button>
          <el-button type="primary">保存并继续</el-button>
        </el-col>
      </el-row>
    </el-form>
  </div>
</template>

<script setup>

import {router} from "@/router";
import {onMounted, reactive, ref,defineProps} from "vue";
import {sendAddCombo, sendGetCategoriesByType, sendGetFood, sendUpdateCombo} from "@/api/backEnd";
import {ElMessage} from "element-plus";
import {deepCopy, deleteIndexList, handleNoCount, isUpdateList} from "@/untils";
import AddComboDialog from "@/components/AddComboDialog.vue";


const props=defineProps({
  typeComponent:String
});
const oldForm=reactive({})
const rules=reactive({
  name:{required:true,message:"套餐名不能为空",trigger:"blur"},
  price:{required:true,message:"价格不能为空",trigger:"blur"},
  categoryId:{required:true,message:"套餐分类不能为空",trigger:"blur"}

})
const formRef=ref()

const srcTemplate=ref("")
const tableShow=ref(false);

const dialogShow=ref(false)


let form=reactive({
  id:router.currentRoute.value.query.id,
  categoryId:"",
  name:"",
  price:"",
  image:"",
  description:"",
  comboList:[]
})


const updateComboList=(comboList)=>{
  tableShow.value=true
  dialogShow.value=false

  form.comboList= handleNoCount(comboList)
}

const categoryDish=ref([])
const getCategory=async()=>{
  //获得套餐种类
  const result=await sendGetCategoriesByType(2)
  categoryDish.value=result.data.data
}

const addCombo=()=>{
  if(props.typeComponent=="Edit"){
    //点击编辑时候执行的
    if(isUpdateList(form,oldForm)){
      //会进行是否相同判断
      ElMessage.error("内容不能相同")
      return false
    }else{
      formRef.value.validate().then(async()=>{

        const result=await sendUpdateCombo(form)
        if(result.data.code==1){
          ElMessage.success("添加成功")
        }
        formRef.value.resetFields()
        router.back()
      }).catch(()=>{

      })
    }
  }
  else{
    //点击添加时候执行的
    formRef.value.validate().then(async()=>{

      console.log(form)

      const result=await sendAddCombo(form)
      if(result.data.code==1){
        ElMessage.success("添加成功")
      }
      formRef.value.resetFields()
      router.back()
    }).catch(()=>{

    })
  }


}

const dialogCancel=()=>{
  //当dialog点击取消按钮的时候
  dialogShow.value=false

}
const addDishBtn=()=>{
  //添加口味的按钮
  if(!dialogShow.value){
    dialogShow.value=true
  }


}



const handleSuccess=(response)=>{
  form.image=response.data
}


const handleChange=(file)=>{
  //当上传但还没提交的时候

  const url=URL.createObjectURL(file.raw)//创建临时url

  srcTemplate.value=url//临时url的地址给img

}
const beforeUpload=(rawFile)=>{
//上传的时候对格式进行校验
  if(rawFile.type!=='image/jpeg'){
    ElMessage.error("上传jpeg格式文件")
    return false

  }
  else{

    return true
  }
}
const getCombo=async(id)=>{
  //在获取id的数据
  const {data:{data}}=await sendGetFood(id);

  form.categoryId=data.categoryId
  form.name=data.name
  form.price=data.price
  form.image='https://1903247335-1316551243.cos.ap-guangzhou.myqcloud.com/'+data.image
  form.description=data.description
  srcTemplate.value=form.image
  deepCopy(oldForm,form)//把form拷贝到oldform中
}

const deleteBtn=(row)=>{
  form.comboList=deleteIndexList(row.index,form.comboList)
}

onMounted(()=>{

  getCategory()
  if(props.typeComponent=="Edit"){

    const id=router.currentRoute.value.query.id
    getCombo(id)
  }
})
</script>

<style scoped>
.el-col-6{
  margin-right: 20px;
}
.png{
  width: 100%;
  height: 100%;
  display: block;
}

</style>
<style>

.uploader .el-upload{
  width: 178px;
  height: 178px;
  display: block;
  line-height: 178px;
  border: 1px dashed deepskyblue;
  text-align: center;
}

</style>
