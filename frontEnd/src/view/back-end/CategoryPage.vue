<template>

  <div class="contain">
    <el-row >
      <el-col :span="4">
        <el-button @click="addDishName">
          <el-icon><Plus /></el-icon>
          新增菜品分类
        </el-button>
      </el-col>
      <el-col :span="4">
        <el-button @click="addPackageName" type="warning" style="color: #974a00;" >
          <el-icon><Plus /></el-icon>
          新增套餐分类</el-button>
      </el-col>
    </el-row>
    <el-row>
      <el-table :data="categoryList" style="width: 100%">
        <el-table-column prop="name" label="分类名称"  >

        </el-table-column>
        <el-table-column prop="type" label="分类类型" width="180" >
          <template v-slot="{row}">
            {{row.type==1?"菜品分类":"套餐分类"}}
          </template>
        </el-table-column>
        <el-table-column prop="updateTime" label="操作时间" width="180"/>
        <el-table-column prop="sort" label="排序" />
        <el-table-column label="操作">
          <template v-slot="{row}">
          <el-col>

              <el-button type="primary" plain @click="editName(row)">编辑</el-button>
              <el-button type="danger" plain @click="deleteBtn(row)">删除</el-button>


          </el-col>
          </template>
        </el-table-column>
      </el-table>
    </el-row>
    <el-row justify="center">
      <el-pagination layout="prev, pager, next" :total="page.total" :page-size="page.pageSize" :current-page="page.currentPage" @current-change="getCurrentPageData" />
    </el-row>
    <DialogComponent :dialog-show="show" :dialog-title="title" @changeStatus="change" :data="cateOrSet" :typeDialog="typeDialog">

    </DialogComponent>

  </div>
</template>

<script setup>

import {onMounted, reactive, ref} from "vue";
import DialogComponent from "@/components/DialogComponent.vue";
import {sendGetCurrentPageData,sendDeleteCategory} from "@/api/backEnd";
import {ElMessage} from "element-plus";

const show=ref(false)
const title=ref("")
const typeDialog=ref(1)//1是增加菜品 2是增加套餐 3是修改
const categoryList=ref([])
const cateOrSet=reactive({
  id:"",
  name:"",
  sort:""

})
const page=reactive({
  total:0,
  pageSize:5,
  currentPage:1

})

const change=(choose)=>{

  if (choose=="success"){
    console.log("hellow")
    getCurrentPageData()

  }


    show.value=!show.value




}
const addDishName=()=>{
  show.value=true
  title.value='新增菜品分类'
  typeDialog.value=1

}
const addPackageName=()=>{
 show.value=true
 title.value="新增套餐分类"
  typeDialog.value=2
}

const editName=(row)=>{

  show.value=true
  title.value="编辑"

  typeDialog.value=3

  cateOrSet.name=row.name


  cateOrSet.id=row.id

  cateOrSet.sort=row.sort
}

const getCurrentPageData=async (currentPage)=>{
  if (currentPage){
    page.currentPage=currentPage

  }
  const result=await sendGetCurrentPageData(page)
  categoryList.value=result.data.data.list
  page.total=result.data.data.toTal

  if(result.data.code==0){
    ElMessage.error("获取错误")
  }
}

const deleteBtn=async(row)=>{

    const result=await sendDeleteCategory(row.id,row.type)
    if(result.data.code==1){
      ElMessage.success("删除成功")
      getCurrentPageData()
    }
    else{
      ElMessage.error("已经和其他菜品绑定")
    }
}

onMounted(()=>{
  getCurrentPageData()
})
</script>

<style scoped>

</style>
