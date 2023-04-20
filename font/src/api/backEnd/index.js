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
        url:"/font-end/add-employee",
        method:'post',
        data
    })
}

export const getEmployeeList=(params)=>{
    if (params.name){
        return axiosBasic({
            url:"/font-end/get-employee",
            methods:'get',
            params
        })
    }
    else{
        return axiosBasic({
            url:"/font-end/get-employee",
            methods:'get',
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
