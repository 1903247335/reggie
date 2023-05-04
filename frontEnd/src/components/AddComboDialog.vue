<template>
  <el-card class="box-card" >
  <template #header>
    <div class="card-header">
      <span>添加菜品</span>
    </div>
  </template>
  <div class="card-contain">

    <el-menu class="menu" default-active="0">
      <el-menu-item v-for="(item,index) in categoryList" :key="index" @click="getDish(item.id)" :index="index.toString()">{{item.name}}</el-menu-item>

    </el-menu>
    <div class="dishes-contain">
      <el-row class="dishes" justify="space-between"
              v-for="(item,index) in dishList"
              :key="index"
      >
        <el-col :span="8"
        >
          <el-checkbox
              v-model="chooseDish" :label="item"  size="large">{{item.name}}</el-checkbox>
        </el-col>

        <el-col :span="4">
          <el-text>{{item.status}}</el-text>
        </el-col>
      </el-row>
    </div>
    <div class="chose-contain">

      <el-row class="chose" justify="space-between"
              v-for="(item,index) in chooseDish"

              :key="index"
      >
        <el-col :span="8">
          <el-text>{{item.name}}</el-text>
        </el-col>
        <el-col :span="4">
          <el-text>{{item.price}}</el-text>
        </el-col>
        <el-col :span="4">
          <el-button type="danger" :icon="Delete" circle size="small" @click="removeDish(item)"></el-button>
        </el-col>



      </el-row>


    </div>
    <div class="btn-contain">
    <el-row justify="center">
        <el-col :span="4">
          <el-button @click="cancelBtn">取消</el-button>
          <el-button type="warning" @click="rightBtn">确定</el-button>
        </el-col>
    </el-row>
    </div>
  </div>

  </el-card>
</template>

<script setup>
import {onMounted, ref,defineEmits} from "vue";
import {sendGetCategoriesByType, sendGetDishByCategory} from "@/api/backEnd";
import {Delete} from "@element-plus/icons-vue";

const categoryList=ref([])
const dishList=ref([])
const chooseDish=ref([])
const emit=defineEmits(['updateComboList'])

const getCategoryList=async()=>{
  //会获取种类的列表
  const result=await sendGetCategoriesByType(1)
  categoryList.value=result.data.data
}

const getDish=async (id)=>{
  //点击种类后会请求种类内的菜品
  const result=await sendGetDishByCategory(id)
  dishList.value=result.data.data

}

const removeDish=(item)=>{
  //选择中进行删除的按钮
  chooseDish.value=chooseDish.value.filter(value=>{
    return item.id!=value.id
  })
}

const rightBtn=()=>{
  //点击确定按钮
  emit('updateComboList',chooseDish.value)
}
const cancelBtn=()=>{
  emit('cancel')
}
onMounted(()=>{
  getCategoryList()
})
</script>

<style scoped>
.menu{
  overflow: auto;
  height: 100px;
  width: 33%;
}
.btn-contain{
  width: 100%;
}
.dishes-contain{
  margin-bottom: 10px;
  width: 33%;
  overflow: auto;
  height: 100px;
}
.dishes{

  border-right:1px solid #e4e7ed;
  height: 20%;

}

.chose-contain{
  width: 33%;
  overflow: auto;
  height: 100px;

}
.chose{
  margin-bottom: 10px;
  height: 20%;

}
.card-contain{
  display: flex;
  flex-wrap: wrap;
}
.el-row{
  align-items: center;
}

</style>
