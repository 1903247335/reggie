<template>
  <div class="font-end-contain">
    <div class="left">
      <el-menu background-color="#37384c"
          :default-active="activeIndex"
          class="el-menu-vertical-demo"
               text-color="#fff"
               active-text-color="#5d3737"
          @open="handleOpen"
          @close="handleClose"
               router
               >
        <el-menu-item>
          <template v-slot:title>

            <img src="@/assets/images/login/login-logo.png" alt="" style="width: 100%;height: auto;margin: 20px">
          </template>
        </el-menu-item>
          <el-menu-item :index="item.url" v-for="item in menuList" :key="item.id" @click="subTitle(item.name)">
            <el-icon >
              <component :is="item.icon"></component>
            </el-icon>
            <span>{{item.name}}</span>
          </el-menu-item>



      </el-menu>
    </div>



    <div class="right">
      <el-menu
          :default-active="1"
          class="el-menu-demo"
          mode="horizontal"
          :ellipsis="false"
          @select="handleSelect"
      >


        <el-menu-item index="0">{{title}}</el-menu-item>
        <div class="flex-grow" />
        <el-sub-menu>
          <template #title>{{user.name}}</template>
          <el-menu-item @click="exit">退出</el-menu-item>
        </el-sub-menu>
      </el-menu>
      <div class="router-contain">
        <router-view v-slot="{ Component }" :user="user">
          <transition name="fade" mode="out-in">
            <component :is="Component" />
          </transition>
        </router-view>

      </div>

    </div>
    <el-dialog
        v-model="dialogVisible"
        title="Tips"
        width="30%"
        :before-close="handleClose"
    >
      <span>This is a message</span>
      <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="logOut">
          确认退出
        </el-button>
      </span>
      </template>
    </el-dialog>
  </div>

</template>

<script setup>
import {onMounted, reactive, ref} from "vue";
import {getUser} from "@/untils";
import {exitUser} from "@/untils";
import {router} from "@/router";
import {ElMessage} from "element-plus";

const activeIndex=ref()
const dialogVisible=ref(false)
const user=getUser()
const title=ref('员工管理')
const menuList=reactive([

  {
    id: '1',
    name: '员工管理',
    url: '/backend/admin/member',
    icon: 'infoFilled'
  },
  {
    id: '2',
    name: '分类管理',
    url: '/backend/admin/category',
    icon: 'Menu'
  },
  {
    id: '3',
    name: '菜品管理',
    url: '/backend/admin/food',
    icon: 'Food'
  },
  {
    id: '4',
    name: '套餐管理',
    url: '/backend/admin/combo',
    icon: 'Present'
  },
  {
    id: '5',
    name: '订单明细',
    url: '/backend/admin/order',
    icon: 'Document'
  }])
const logOut=()=>{//点击确认退出后
  exitUser()
}
const exit=()=>{//点击退出的选项
  dialogVisible.value=true//对话框显示

  logOut()


  router.push('/login')

  ElMessage.success('退出成功')
}

const subTitle=(name)=>{
  title.value=name


}
onMounted(()=>{
 activeIndex.value=router.currentRoute.value['fullPath']
})



</script>

<style scoped lang="less">
.contain{padding: 20px}
.font-end-contain{
  display: flex;
}
.left{
  width: 20%;
  height: 100%;
}
.right{
  width: 100%;
  background: #f7f4fe;

}
.router-contain{
  margin: 15px;

  background: white;
  border-radius: 3px;
}

.flex-grow {
  flex-grow: 1;
}
.is-active{
  background-color: #ffb606;
  border-radius: 0 40px 40px 0 ;
}
.font-end-contain{
  .el-menu-item:first-child{
    height: 25%;
    &:hover{

        background-color: inherit;
    }

  }


  .left{
    ul{
      height: 100%;
    }
  }
  height: 100%;
}
.fade-enter {
  opacity: 0;
}

.fade-enter-active {
  transition: opacity 0.5s ease;
  opacity: 1;
}

.fade-leave {
  opacity: 1;
}

.fade-leave-active {
  transition: opacity 0.5s ease;
  opacity: 0;
}

</style>
