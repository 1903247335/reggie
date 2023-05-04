<template>
  <div class="contain">
    <el-row justify="space-between">
      <el-col :span="3">
        <el-input prefix-icon="Search" placeholder="输入菜品名称"></el-input>

      </el-col>
      <el-col :span="7">
        <el-button type="danger" plain style="width: 20%">批量删除</el-button>
        <el-button type="primary" plain style="width: 20%">批量起售</el-button>
        <el-button type="warning" plain style="width: 20%">批量停售</el-button>
        <el-button type="warning" style="width: 25%" @click="router.push('/backend/admin/addfood')">
          <template v-slot:icon>
            <el-icon><Plus /></el-icon>
          </template>
          新建菜品
        </el-button>
      </el-col>
    </el-row>
    <el-row>
      <el-table :data="foods" style="width: 100%">
        <el-table-column prop="name" label="菜品名称"  />
        <el-table-column prop="image" label="图片"  >
          <template v-slot="{row}">
            <img :src="src+row.image" alt="" style="width: 213px;height: 141px">
          </template>
        </el-table-column>
        <el-table-column prop="sort" label="菜品分类" >
          <template v-slot="{row}">
            {{row.sort==1?'菜品':"套餐"}}
          </template>
        </el-table-column>
        <el-table-column prop="price" label="售价" />
        <el-table-column prop="status" label="售卖状态" />
        <el-table-column prop="updateTime" label="最后操作时间" />
        <el-table-column  label="操作" >
          <template v-slot="{row}">

          <el-button type="primary" @click="editBtn(row)">编辑</el-button>
          <el-button type="danger">删除</el-button>
          </template>

        </el-table-column>
      </el-table>
    </el-row>
    <el-row justify="center">
      <el-col :span="7">   <el-pagination layout="prev, pager, next" :total="page.toTal" :current-page="page.currentPage" :page-size="page.pageSize" @current-change="getFoods"/></el-col>
    </el-row>
  </div>
</template>

<script setup>

import {router} from "@/router";
import {onMounted, reactive, ref} from "vue";
import {sendGetFoods} from "@/api/backEnd";


const src=ref("https://1903247335-1316551243.cos.ap-guangzhou.myqcloud.com/");
const page=reactive({
  currentPage:1,
  pageSize:5,
  toTal:0,
})
const foods=ref([])
const getFoods=async(currentPage)=>{

  if (currentPage){
      page.currentPage=currentPage
  }

  const result=await sendGetFoods(page);
  page.toTal=result.data.data.toTal
  foods.value=result.data.data.list
  console.log(result)
}

const editBtn=(row)=>{
  router.push({
    path:'/backend/admin/edit-food',
    query:{
      id:row.id
    }
  })
}
onMounted(()=>{

  getFoods()
})
</script>

<style scoped>

</style>
