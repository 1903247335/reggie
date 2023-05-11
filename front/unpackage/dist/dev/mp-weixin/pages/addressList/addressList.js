"use strict";
const common_vendor = require("../../common/vendor.js");
const api_index = require("../../api/index.js");
require("../../axiosInterceptors/index.js");
require("../../axiosBasic/index.js");
const _sfc_main = {
  data() {
    return {
      userId: this.$store.state.user.id,
      address: []
    };
  },
  methods: {
    chooseAddress(id) {
      this.$store.commit("setAddressBookId", id);
      common_vendor.index.navigateBack();
    },
    async getAddress() {
      const result = await api_index.sendGetAddAddress(this.userId);
      this.address = result.data.data;
    },
    addAddressBtn() {
      common_vendor.index.navigateTo({
        url: "/pages/addAddress/addAddress"
      });
    }
  },
  created() {
    this.getAddress();
  }
};
if (!Array) {
  const _easycom_uni_list_item2 = common_vendor.resolveComponent("uni-list-item");
  const _easycom_uni_list2 = common_vendor.resolveComponent("uni-list");
  (_easycom_uni_list_item2 + _easycom_uni_list2)();
}
const _easycom_uni_list_item = () => "../../uni_modules/uni-list/components/uni-list-item/uni-list-item.js";
const _easycom_uni_list = () => "../../uni_modules/uni-list/components/uni-list/uni-list.js";
if (!Math) {
  (_easycom_uni_list_item + _easycom_uni_list)();
}
function _sfc_render(_ctx, _cache, $props, $setup, $data, $options) {
  return {
    a: common_vendor.f($data.address, (item, index, i0) => {
      return {
        a: index,
        b: common_vendor.o(($event) => $options.chooseAddress(item.id), index),
        c: "9397b2d8-1-" + i0 + ",9397b2d8-0",
        d: common_vendor.p({
          showArrow: true,
          title: item.consignee,
          note: item.provinceName + item.cityName + item.districtName + item.detail,
          clickable: "true"
        })
      };
    }),
    b: common_vendor.o((...args) => $options.addAddressBtn && $options.addAddressBtn(...args))
  };
}
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["render", _sfc_render], ["__file", "E:/python/save/作品/web/project/reggie/front/pages/addressList/addressList.vue"]]);
wx.createPage(MiniProgramPage);
