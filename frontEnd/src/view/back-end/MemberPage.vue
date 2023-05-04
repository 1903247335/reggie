<template>
  <div class="contain">
    <el-row justify="space-between">
      <el-col :span="4">
        <el-input :prefix-icon="Search"  placeholder="输入员工姓名" v-model="queryPage.name" style="width: 75%"></el-input>
        <el-button @click="loadEmployeeList" type="primary" style="width: 25%" >查询</el-button>
      </el-col>
      <el-col :span="4">
        <el-button type="warning" style="color: #974a00" @click="router.push('/backend/admin/addmember')">
          <el-icon ><Plus /></el-icon>
          新增员工</el-button>
      </el-col>
    </el-row>
    <el-row>
      <el-table :data="employeeList" style="width: 100%">
        <el-table-column prop="name" label="姓名"  />
        <el-table-column prop="username" label="账号" width="180" />
        <el-table-column prop="phone" label="手机号" width="180"/>
        <el-table-column prop="status" label="账号状态" >
          <template v-slot="{row}">
            {{row.status=='1'?'正常':'禁用'}}
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template v-slot="{ row}">
          <el-col>


              <el-button type="primary" @click="editBtn(row)">编辑</el-button>
              <el-button type="danger" v-if="props.user.username=='admin'" @click="switchStatus(row.id,row.status)">
                {{row.status=='1'?'禁用':'启用'}}
              </el-button>


          </el-col>
          </template>
        </el-table-column>
      </el-table>
    </el-row>
    <el-row justify="center">
      <el-pagination layout="prev, pager, next"
                     @current-change="currentPageChange"
                     :page-size="queryPage.pageSize"
                     :current-page="queryPage.currentPage"

                     :total="queryPage.total" />

    </el-row>
  </div>


</template>

<script setup>

import {Search} from "@element-plus/icons-vue";
import {router} from "@/router";
import {getEmployeeList, sendStatus} from "@/api/backEnd";
import {onMounted, reactive, ref,defineProps} from "vue";


const props=defineProps({
  user:{
    type:Object
  }
})
const employeeList=ref([])

const queryPage=reactive(({
  currentPage:1,
  pageSize:2,
  name:'',
  total:0
}))
const loadEmployeeList=async ()=>{

  const result=await getEmployeeList(queryPage)

  queryPage.total=result.data.data.toTal
  employeeList.value=result.data.data.list

  console.log(result)

}

//页数发生变化的时候执行
const currentPageChange=(current)=>{
  queryPage.currentPage=current
  loadEmployeeList()
}

const switchStatus=async (id,status)=>{
  status=status=="1"?"0":"1"
  const result=await sendStatus({id,status})
  loadEmployeeList()
  console.log(result)
}

const editBtn=(row)=>{

  router.push({
    path:'/backend/admin/editmember',
    query:{
      id:row.id
    }
  })
}

onMounted(()=>{
  loadEmployeeList()
})
</script>

<style scoped>

</style>
