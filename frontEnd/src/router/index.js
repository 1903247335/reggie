import { createRouter, createWebHashHistory } from 'vue-router'
import FontEndPage from "@/view/back-end/FontEndPage.vue";
import LoginPage from "@/view/back-end/LoginPage.vue";
import {store} from "@/storage";
import {ElMessage} from "element-plus";
import MemberPage from "@/view/back-end/MemberPage.vue";
import CategoryPage from "@/view/back-end/CategoryPage.vue";
import FoodPage from "@/view/back-end/FoodPage.vue";
import ComboPage from "@/view/back-end/ComboPage.vue";
import OrderPage from "@/view/back-end/OrderPage.vue";
import AddMemberPage from "@/view/back-end/AddMemberPage/AddMemberPage.vue";
import EditMemberPage from "@/view/back-end/EditMemberPage/EditMemberPage.vue";
import AddDishPage from "@/view/back-end/AddDishPage/AddDishPage.vue";
import EditDishPage from "@/view/back-end/EditDishPage/EditDishPage.vue";
import AddComboPage from "@/view/back-end/AddComboPage/AddComboPage.vue";
import EditComboPage from "@/view/back-end/EditComboPage/EditComboPage.vue";
//路由映射
export const router = createRouter({
    history: createWebHashHistory(),
    routes: [

        {path:'/login',component:LoginPage},
        { path: '/backend/admin',
            component: FontEndPage,
            children:[
                {path:'member',component:MemberPage},
                {path:'addmember',component:AddMemberPage},

                {path:'category',component:CategoryPage},
                {path:'editmember',component:EditMemberPage},

                {path:'food',component:FoodPage},
                {path:'addfood',component:AddDishPage},
                {path:"edit-food",component:EditDishPage},


                {path:'combo',component:ComboPage},
                {path:'add-combo',component:AddComboPage},
                {path:"edit-combo",component:EditComboPage},

                {path:'order',component:OrderPage}
            ]
        },


    ]
})

//路由守卫
router.beforeEach((to, from,next )=>{
    console.log(to.path)
    console.log(store.state.user,to.path)

    if(store.state.user!=='null'&&store.state.user){

        if(to.path==='/login'){

            ElMessage.error('您已经登录')
            next({path:"/backend/admin"})
        }else{

            next()
        }

    }else{
        if(to.path==='/login'){
            next()
        }
        else{

            ElMessage.error('您还未登录')
            next({path:'/login'})
        }

    }


})
