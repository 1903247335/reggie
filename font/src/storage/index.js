import {createStore} from "vuex";

export const store=createStore({
    state: {
        user:JSON.stringify(window.localStorage.getItem('user'))
    },
    mutations:{
        storageUser:(state,payload)=>{
            state.user=payload
            window.localStorage.setItem('user',payload)
        },
        getUser:(state)=>{
            const str=window.localStorage.getItem('user')

            state.user=JSON.parse(str)
        },
        exitUser:(state)=>{
            window.localStorage.removeItem('user')
            state.user='null'
        }
    },
    actions:{},

})
