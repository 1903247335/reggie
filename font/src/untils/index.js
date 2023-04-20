import {store} from "@/storage";
export const getUser=()=>{
    console.log('hello')
    store.commit('getUser')
    return store.state.user
}

export const exitUser=()=>{
    store.commit('exitUser')
}

export const isLowerAndUpper=(value)=>{
    return value!==value.toUpperCase()&&value!==value.toLowerCase();
}
export const addStorageUser=(user)=>{
    store.commit('storageUser',user)
}

export const transfromData=(list)=>{
    return list.map((item)=>{
        item.status=item.status==1?"正常":"禁用"
        return item
    })
}
