"use strict";
const axiosInterceptors_index = require("../axiosInterceptors/index.js");
const axiosBasic = axiosInterceptors_index.axiosInterceptors();
const sendMsg = (username) => {
  return axiosBasic({
    url: "/send-mail",
    method: "post",
    data: {
      email: username
    }
  });
};
const verifyCode = (data) => {
  return axiosBasic({
    url: "/verify-code",
    method: "post",
    data: {
      code: data.code,
      email: data.username
    }
  });
};
const sendGetcategories = () => {
  return axiosBasic({
    url: "/get-categories"
  });
};
const sendGetFoods = (id, type) => {
  return axiosBasic({
    url: "/get-foods",
    method: "GET",
    params: {
      id,
      type
    }
  });
};
const sendAddShopCart = (data, type) => {
  return axiosBasic({
    url: "/add-shop-cart",
    method: "POST",
    data: {
      ...data,
      type
    }
  });
};
const sendUpdateShopCart = (data) => {
  return axiosBasic({
    url: "/update-shop-cart",
    data,
    method: "POST"
  });
};
const sendGetShopCart = (id) => {
  return axiosBasic({
    url: "/get-shop-cart/" + id
  });
};
const sendDeleteShopCarts = (userId) => {
  return axiosBasic({
    url: "/delete-shop-carts/" + userId,
    method: "POST"
  });
};
const sendDeleteShopCart = (data) => {
  return axiosBasic({
    url: "/delete-shop-cart",
    data,
    method: "post"
  });
};
const sendGetOrders = (userId) => {
  return axiosBasic({
    url: "/get-shop-cart/" + userId
  });
};
const sendAddAddress = (data) => {
  return axiosBasic({
    url: "/add-address",
    method: "POST",
    data
  });
};
const sendGetAddAddress = (userId) => {
  return axiosBasic({
    url: "/get-address/" + userId
  });
};
const sendGetAddressByAddressbookId = (addressbookId) => {
  return axiosBasic({
    url: "/get-address-by-addressbookId/" + addressbookId
  });
};
const sendAddOrder = (data) => {
  return axiosBasic({
    url: "/add-order",
    data,
    method: "POST"
  });
};
exports.sendAddAddress = sendAddAddress;
exports.sendAddOrder = sendAddOrder;
exports.sendAddShopCart = sendAddShopCart;
exports.sendDeleteShopCart = sendDeleteShopCart;
exports.sendDeleteShopCarts = sendDeleteShopCarts;
exports.sendGetAddAddress = sendGetAddAddress;
exports.sendGetAddressByAddressbookId = sendGetAddressByAddressbookId;
exports.sendGetFoods = sendGetFoods;
exports.sendGetOrders = sendGetOrders;
exports.sendGetShopCart = sendGetShopCart;
exports.sendGetcategories = sendGetcategories;
exports.sendMsg = sendMsg;
exports.sendUpdateShopCart = sendUpdateShopCart;
exports.verifyCode = verifyCode;
