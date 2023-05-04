import {
	createApp
} from 'vue'

import App from './App.vue'
import "@/assets/reset.css"
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import {registerIcons} from "@/icons/registerIcons";
import {router} from "@/router";
import {store} from "@/storage";
import axiosBasic from "@/axiosBasic";

const app = createApp(App)
app.config.globalProperties.$http=axiosBasic

app.use(store)
app.use(ElementPlus)
app.use(router)

//icon组件注册
registerIcons(app)

app.mount('#app')
