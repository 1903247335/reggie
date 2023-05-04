import {
	axiosInterceptors
}
from "../axiosInterceptors/index.js"
const axiosBasic = axiosInterceptors()
export const sendMsg = (email) => {

	return axiosBasic({
		url: '/send-mail',
		method: "post",
		data: {
			email
		}
	})
}

export const verifyCode = (data) => {
	return axiosBasic({
		url: "/verify-code",
		method: "post",
		data: {
			code: data.code
		}
	})
}
