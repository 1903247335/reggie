import {
	axiosInterceptors
}
from "../axiosInterceptors/index.js"
const axiosBasic = axiosInterceptors()
export const sendMsg = (username) => {

	return axiosBasic({
		url: '/send-mail',
		method: "post",
		data: {
			email: username
		}
	})
}

export const verifyCode = (data) => {
	return axiosBasic({
		url: "/verify-code",
		method: "post",
		data: {
			code: data.code,
			email: data.username
		}
	})
}

export const sendGetcategories = () => {
	return axiosBasic({
		url: "/get-categories",
	})
}

export const sendGetFoods = (id, type) => {
	return axiosBasic({
		url: "/get-foods",
		method: "GET",
		params: {
			id: id,
			type: type
		}
	})
}

export const sendGetFlavor = (id) => {
	return axiosBasic({
		url: "/get-flavors/" + id
	})
}

export const sendAddShopCart = (data, type) => {
	return axiosBasic({
		url: "/add-shop-cart",
		method: "POST",
		data: {
			...data,
			type
		}
	})
}

export const sendUpdateShopCart = (data) => {
	return axiosBasic({
		url: "/update-shop-cart",
		data,
		method: "POST"
	})
}
export const sendGetShopCart = (id) => {
	return axiosBasic({
		url: "/get-shop-cart/" + id,

	})
}
export const sendDeleteShopCarts = (userId) => {
	return axiosBasic({
		url: "/delete-shop-carts/" + userId,
		method: "POST"
	})
}

export const sendDeleteShopCart = (data) => {
	return axiosBasic({
		url: "/delete-shop-cart",
		data,
		method: "post"
	})
}

export const sendGetOrders = (userId) => {
	return axiosBasic({
		url: "/get-shop-cart/" + userId,

	})
}

export const sendAddAddress = (data) => {
	return axiosBasic({
		url: "/add-address",
		method: "POST",
		data
	})
}

export const sendGetAddAddress = (userId) => {
	return axiosBasic({
		url: "/get-address/" + userId

	})
}
export const sendGetAddressByAddressbookId = (addressbookId) => {
	return axiosBasic({
		url: "/get-address-by-addressbookId/" + addressbookId

	})
}

export const sendAddOrder = (data) => {
	return axiosBasic({
		url: "/add-order",
		data,
		method: "POST"
	})
}
