
<template>
  <div class="contain">
    <el-row justify="space-between">
      <el-col :span="3">
        <el-input prefix-icon="Search" placeholder="输入套餐名称"></el-input>

      </el-col>
      <el-col :span="7">
        <el-button type="danger" plain style="width: 20%">批量删除</el-button>
        <el-button type="primary" plain style="width: 20%">批量起售</el-button>
        <el-button type="warning" plain style="width: 20%">批量停售</el-button>
        <el-button type="warning" style="width: 25%" @click="router.push('/backend/admin/add-combo')">
          <template v-slot:icon>
            <el-icon><Plus /></el-icon>
          </template>
          新建套餐
        </el-button>
      </el-col>
    </el-row>
    <el-row>
      <el-table :data="combos" style="width: 100%">
        <el-table-column prop="name" label="套餐名称"  />
        <el-table-column prop="image" label="图片"  >
          <template v-slot="{row}">
            <img :src="src+row.image" alt="" style="width: 213px;height: 141px">
          </template>
        </el-table-column>
        <el-table-column prop="sort" label="套餐分类" />
        <el-table-column prop="price" label="套餐价" />
        <el-table-column prop="status" label="售卖状态" />
        <el-table-column prop="updateTime" label="最后操作时间" />
        <el-table-column  label="操作" >
          <template v-slot="{row}">

            <el-button type="primary" @click="editBtn(row)">编辑</el-button>
            <el-button type="danger" @click="removeBtn(row.id)">删除</el-button>
          </template>

        </el-table-column>
      </el-table>
    </el-row>
    <el-row justify="center">
      <el-col :span="7">   <el-pagination layout="prev, pager, next" :total="page.toTal" :current-page="page.currentPage" :page-size="page.pageSize" @current-change="getCombos"/></el-col>
    </el-row>
  </div>
</template>



<script setup>
import {router} from "@/router";
import {reactive, onMounted, ref} from "vue";
import {sendGetCombos, sendRemoveCombo} from "@/api/backEnd";
import {ElMessage} from "element-plus";

const src=ref("https://1903247335-1316551243.cos.ap-guangzhou.myqcloud.com/");

const combos=ref([])

const page=reactive({
  toTal:0,
  currentPage:1,
  pageSize:5
})

const getCombos=async (currentPage)=>{
  if(currentPage){

    page.currentPage=currentPage
  }

 const result=await sendGetCombos(page)

    page.toTal=result.data.data.toTal
    combos.value=result.data.data.list
}
const removeBtn=async (id)=>{
  console.log(id)
  const result=await sendRemoveCombo(id)
  if(result.data.code==1){
    ElMessage.success("删除成功")
    getCombos()
  }else{
    ElMessage.error("删除失败")
  }
}

const editBtn=(row)=>{
  console.log(row)
}
onMounted(()=>{
  getCombos()
})
</script>
<style>

</style>
