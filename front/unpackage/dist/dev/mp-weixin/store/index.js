"use strict";
const common_vendor = require("../common/vendor.js");
const store = common_vendor.createStore({
  state: {
    user: JSON.parse(common_vendor.index.getStorageSync("user") || "{}"),
    addressBookId: ""
  },
  mutations: {
    setStorage(state, playload) {
      common_vendor.index.setStorageSync("user", JSON.stringify(playload));
    },
    clearStorage(state) {
      common_vendor.index.removeStorageSync("user");
    },
    setAddressBookId(state, playload) {
      state.addressBookId = playload;
    }
  },
  actions: {
    login(context, playload) {
      context.commit("setStorage", playload);
    },
    logout(context) {
      context.commit("clearStorage");
    }
  }
});
exports.store = store;
