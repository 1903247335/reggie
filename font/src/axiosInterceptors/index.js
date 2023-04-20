import axiosBasic from "@/axiosBasic";
export const axiosInterceptors=()=> {
    axiosBasic.interceptors.request.use(
        (config) => {
            console.log('请求', config)
            return config
        }, (error) => {
            return Promise.reject(error)
        }
    )
    axiosBasic.interceptors.response.use((response) => {
        console.log('响应', response)
        return response
    }, (error) => {
        return Promise.reject(error)
    })
    return axiosBasic
}
