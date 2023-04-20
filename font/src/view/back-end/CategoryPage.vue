<template>

  <div class="contain">
    <el-row >
      <el-col :span="4">
        <el-button>
          <el-icon><Plus /></el-icon>
          新增菜品分类
        </el-button>
      </el-col>
      <el-col :span="4">
        <el-button type="warning" style="color: #974a00;" >
          <el-icon><Plus /></el-icon>
          新增套餐分类</el-button>
      </el-col>
    </el-row>
    <el-row>
      <el-table :data="tableData" style="width: 100%">
        <el-table-column prop="name" label="分类名称"  />
        <el-table-column prop="type" label="分类类型" width="180" />
        <el-table-column prop="time" label="操作时间" width="180"/>
        <el-table-column prop="order" label="排序" />
        <el-table-column label="操作">
          <el-col>
            <el-button type="primary" plain>编辑</el-button>
            <el-button type="danger" plain>删除</el-button>
          </el-col>
        </el-table-column>
      </el-table>
    </el-row>
    <el-row justify="center">
      <el-pagination layout="prev, pager, next" :total="50" />
    </el-row>
  </div>
</template>

<script setup>

</script>

<style scoped>

</style>
