"use strict";
const common_vendor = require("../common/vendor.js");
const axiosBasic = common_vendor.axios.create({
  baseURL: "http://127.0.0.1/front-end",
  adapter: common_vendor.UniAdapter
});
exports.axiosBasic = axiosBasic;
