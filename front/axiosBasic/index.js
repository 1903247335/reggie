import axios from "axios"
import {
	UniAdapter
} from "uniapp-axios-adapter"
export default axios.create({
	baseURL: "http://127.0.0.1/front-end",
	adapter: UniAdapter
})

// axios.interceptors.request.use(function(config) {
// 	return config;
// }, function(error) {
// 	return Promise.reject(error);
// });

// axios.interceptors.response.use(function(response) {
// 	return response;
// }, function(error) {
// 	return Promise.reject(error);
// });
// export default {
// 	request
// }
