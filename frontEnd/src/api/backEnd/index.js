import {axiosInterceptors} from "@/axiosInterceptors/index";

const axiosBasic=axiosInterceptors()
export const backEndLogin=(data)=>{

    return axiosBasic({
        url:"/font-end/login",
        method:"post",
        data

    })
}
export const addEmployee=(data)=>{

    return axiosBasic({
        url:"/font-end/add-employees",
        method:'post',
        data
    })
}

export const getEmployeeList=(params)=>{
    if (params.name){
        return axiosBasic({
            url:"/font-end/get-employee",
            method:'get',
            params
        })
    }
    else{
        return axiosBasic({
            url:"/font-end/get-employees",
            method:'get',
            params:{
                currentPage:params.currentPage,
                pageSize:params.pageSize,

                total:params.total
            }
        })
    }

}

export const sendStatus=(data)=>{
    return axiosBasic({
        url:"/font-end/switch-status",
        method:"post",
        data

    })
}
export const sendUpdateEmployee=(data)=>{
    return axiosBasic({
        url:"/font-end/update-employee",
        method:"post",
        data

    })
}

export const sendSingleEmployee=(id)=>{
    return axiosBasic({
        url:"/font-end/get-employee/"+id,
        method:"get",
    })
}

export const sendAddCategory=(data)=>{

    return axiosBasic({
        url:"/font-end/add-category",
        method:"POST",
        data
    })
}

export const sendGetCurrentPageData=(params)=>{
    return axiosBasic({
        url:"/font-end/get-categories",
        method:"GET",
        params
    })
}

export const sendDeleteCategory=(id,type)=>{
    return axiosBasic({
        url:"/font-end/delete-category/"+id+"/"+type,
        method:"DELETE",


    })
}

export const sendUpdateCategory=(data)=>{
    return axiosBasic({
        url:"/font-end/update-category",
        method:'PUT',
        data
    })
}

export const sendGetFoods=(params)=>{
        return axiosBasic({
            url:"/font-end/get-foods",
            params
        })
}

export const sendGetCategoriesByType=(type)=>{
    return axiosBasic({
        url:"/font-end/get-categories-by-type/"+type

    })
}

export const sendAddFood=(data)=>{
    return axiosBasic({
        url:"/font-end/add-food",
        method:"POST",
        data:{
            ...data,
            detailFlavor:JSON.stringify(data.detailFlavor)
        }
    })
}

export const sendGetFood=(id)=>{
    return axiosBasic({
        url:"/font-end/get-food/"+id,
    })
}

export const sendUpdateFood=(data)=>{
    return axiosBasic({
        url:"/font-end/update-food",
        method:"POST",
        data:{
            ...data,
            detailFlavor:JSON.stringify(data.detailFlavor)
        }
    })
}

export const sendGetCombos=(params)=>{
    return axiosBasic({
        url:"/font-end/get-combos",
        method:"GET",
        params

    })
}

export const sendAddCombo=(data)=>{
    return axiosBasic({
        url:"/font-end/add-combo",
        method:"post",
        data
    })
}

export const sendUpdateCombo=(data)=>{
    return axiosBasic({
        url:"/font-end/update-combo",
        method:"post",
        data
    })
}

export const sendGetDishByCategory=(id)=>{
    return axiosBasic({
        url:'/font-end/get-food-by-category/'+id
    })
}

export const sendRemoveCombo=(id)=>{
    return axiosBasic({
        url:"/font-end/delete-combo/"+id,
        method:"POST"
    })
}


