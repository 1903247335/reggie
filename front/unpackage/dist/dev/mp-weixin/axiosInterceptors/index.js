"use strict";
const axiosBasic_index = require("../axiosBasic/index.js");
const axiosInterceptors = () => {
  axiosBasic_index.axiosBasic.interceptors.request.use(
    (config) => {
      console.log("请求", config);
      return config;
    },
    (error) => {
      return Promise.reject(error);
    }
  );
  axiosBasic_index.axiosBasic.interceptors.response.use((response) => {
    console.log("响应", response);
    return response;
  }, (error) => {
    return Promise.reject(error);
  });
  return axiosBasic_index.axiosBasic;
};
exports.axiosInterceptors = axiosInterceptors;
