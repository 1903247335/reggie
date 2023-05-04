
import axios from "axios"
export default axios.create({
    baseURL:"http://localhost:80",
    timeout:10000,
    withCredentials:true
})

