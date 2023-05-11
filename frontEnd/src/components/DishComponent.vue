<template>
  <div class="contain">
    <el-form :model="form" :rules="rules" ref="formRef">

      <el-form-item >

        <el-col :span="6">
          <el-form-item label="菜品名称" prop="name">

            <el-input placeholder="填写菜品名称" v-model="form.name"> </el-input>

          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="菜品分类" prop="categoryId">
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
          <el-form-item label="菜品价格" prop="price">

            <el-input placeholder="设置菜品价格" v-model="form.price"></el-input>

          </el-form-item>

        </el-col>
      </el-form-item>
      <el-form-item label="口味做法配置">
        <el-col :span="4">
          <el-button type="warning" @click="flavorBtn" >
            <template v-slot:icon>
              <el-icon><Plus /></el-icon>
            </template>
            添加口味
          </el-button>
        </el-col>
        <el-col >
          <el-select @change="changeSelect" v-model="form.mainFlavor" class="m-2" placeholder="选择你的口味" size="large" v-if="chooseShow">
            <el-option
                v-for="(item,index) in flavorList"
                :key="index"
                :label="item.type"
                :value="item.type"

            />

          </el-select>
        </el-col>
        <el-col>
          <el-tag v-for="(item,index) in form.detailFlavor" :key="index" closable @close="tarClose(item)">{{item}}</el-tag>
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
          <el-button type="warning" @click="addFood">保存</el-button>
          <el-button type="primary">保存并继续</el-button>
        </el-col>
      </el-row>
    </el-form>
  </div>
</template>

<script setup>

import {router} from "@/router";
import {onMounted, reactive, ref,defineProps} from "vue";
import {sendAddFood, sendGetCategoriesByType, sendGetFood, sendUpdateFood} from "@/api/backEnd";
import {ElMessage} from "element-plus";
import {deepCopy, isUpdateList} from "@/untils";


const props=defineProps({
  typeComponent:String
});
const oldForm=reactive({})
const rules=reactive({
  name:{required:true,message:"菜名不能为空",trigger:"blur"},
  price:{required:true,message:"价格不能为空",trigger:"blur"},
  categoryId:{required:true,message:"菜品分类不能为空",trigger:"blur"}

})
const formRef=ref()

const srcTemplate=ref("")
const chooseShow=ref(false);



const flavorList=ref([


  {
    type:'甜味',
    detail:['无糖','少糖','半糖','多糖','全糖']
  },
  {
    type:'温度',
    detail:["热饮","常温","去冰","少冰","多冰"]
  },

  {
    type:'忌口',
    detail:["不要葱","不要蒜","不要香菜","不要辣"]
  },

  {type:'辣度',
    detail:["不辣","微辣","中辣","重辣"]
  }

])





let form=reactive({
  id:router.currentRoute.value.query.id,
  categoryId:"",
  name:"",
  price:"",
  image:"",
  description:"",

  mainFlavor:"",
  detailFlavor:[]
})


const categoryDish=ref([])
const getCategory=async()=>{
  //获得菜品种类
  const result=await sendGetCategoriesByType(1)
  categoryDish.value=result.data.data
}

const addFood=()=>{
  if(props.typeComponent=="Edit"){
    //点击编辑时候执行的
    if(isUpdateList(form,oldForm)){
      //会进行是否相同判断
      ElMessage.error("内容不能相同")
      return false
    }else{
      formRef.value.validate().then(async()=>{
        if(form.image==oldForm.image){
          form.image=null
        }
        if(form.detailFlavor==oldForm.detailFlavor){
          form.detailFlavor=null
        }
        console.log(form)
        const result=await sendUpdateFood(form)
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

      const result=await sendAddFood(form)
      if(result.data.code==1){
        ElMessage.success("添加成功")
      }
      formRef.value.resetFields()
      router.back()
    }).catch(()=>{

    })
  }


}

const changeSelect=(value)=>{
  form.mainFlavor=value
  flavorList.value.forEach((item)=> {

    if (item.type == form.mainFlavor) {
      form.detailFlavor = item.detail
    }
  })
}

const flavorBtn=()=>{
  //添加口味的按钮
  chooseShow.value=true
}

const tarClose=(item)=>{
  //点击标签后会消失
  form.detailFlavor=form.detailFlavor.filter((value)=>{
    return item!=value
  })
  if(form.detailFlavor.length==0){
    form.mainFlavor=''
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
const getFood=async(id)=>{
  //在获取id的数据
  const {data:{data}}=await sendGetFood(id);

  form.categoryId=data.categoryId
  form.name=data.name
  form.price=data.price
  form.image='https://1903247335-1316551243.cos.ap-guangzhou.myqcloud.com/'+data.image
  form.description=data.description
  form.mainFlavor=data.mainFlavor
  form.detailFlavor=JSON.parse(data.detailFlavor)
  console.log("获得的内容",form)
  srcTemplate.value=form.image


  deepCopy(oldForm,form)//把form拷贝到oldform中
}

onMounted(()=>{

  getCategory()
  if(props.typeComponent=="Edit"){

    const id=router.currentRoute.value.query.id
    getFood(id)
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
