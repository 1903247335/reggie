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

export const deepCopy=(list,copyList)=>{
    return Object.keys(copyList).forEach((key)=>{
        list[key]=copyList[key]
    })
}

export const isUpdateList=(newList,oldList)=>{//如果相同就是true 如果不同就是false
    return Object.keys(oldList).every((key)=>{
        return oldList[key]==newList[key]
    })
}

export const deleteIndexList=(index,list)=>{
   //点击删除按钮的时候删除列表对应的
    return list.filter((item)=>{
        return item.index!==index
    })
}

export const handleNoCount=(list)=>{
    return list.map((item)=>{
        if (!item.count){
            item['count']=1
        }

        return item
    })
}
