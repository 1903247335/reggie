"use strict";
const axiosInterceptors_index = require("../axiosInterceptors/index.js");
const axiosBasic = axiosInterceptors_index.axiosInterceptors();
const sendMsg = (email) => {
  return axiosBasic({
    url: "/send-mail",
    method: "post",
    data: {
      email
    }
  });
};
const verifyCode = (data) => {
  return axiosBasic({
    url: "/verify-code",
    method: "post",
    data: {
      code: data.code
    }
  });
};
exports.sendMsg = sendMsg;
exports.verifyCode = verifyCode;
