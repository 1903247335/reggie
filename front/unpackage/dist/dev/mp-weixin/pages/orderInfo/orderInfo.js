"use strict";
const common_vendor = require("../../common/vendor.js");
const api_index = require("../../api/index.js");
require("../../axiosInterceptors/index.js");
require("../../axiosBasic/index.js");
const _sfc_main = {
  onLoad: function(options) {
    const userId = options.userId;
    this.userId = userId;
    this.getOrders();
  },
  computed: {
    amount() {
      return this.orders.reduce((sums, item) => {
        return sums + item.amount;
      }, 0);
    },
    time() {
      const date = new Date();
      let hours = date.getHours();
      let minute = date.getMinutes();
      if (minute + 30 >= 60) {
        hours += 1;
        minute -= 60;
      }
      return `${hours}:${minute + 30 < 10 ? "0" + minute + 30 : minute + 30}`;
    }
  },
  mounted() {
    this.form.amount = this.amount;
  },
  data() {
    return {
      userId: "",
      address: {
        consignee: "",
        provinceName: "",
        cityName: "",
        districtName: "",
        detail: ""
      },
      orders: [],
      form: {
        status: 1,
        userId: this.userId,
        addressBookId: "",
        orderTime: new Date(),
        payMethod: 1,
        amount: "",
        remark: "",
        phone: "",
        address: "",
        userName: "",
        consignee: ""
      }
    };
  },
  methods: {
    async addOrderBtn() {
      console.log(this.form);
      await api_index.sendAddOrder(this.form);
    },
    addAddressBtn() {
      common_vendor.index.navigateTo({
        url: "/pages/addressList/addressList"
      });
    },
    async getOrders() {
      const result = await api_index.sendGetOrders(this.userId);
      this.orders = result.data.data;
    },
    async getAddress() {
      const result = await api_index.sendGetAddressByAddressbookId(this.$store.state.addressBookId);
      this.address = result.data.data;
      console.log(this.address);
    }
  },
  onShow: function() {
    if (this.$store.state.addressBookId) {
      this.getAddress();
      this.form.addressBookId = this.$store.state.addressBookId;
    }
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
  return common_vendor.e({
    a: !$data.form.addressBookId
  }, !$data.form.addressBookId ? {
    b: common_vendor.o($options.addAddressBtn),
    c: common_vendor.p({
      title: "添加地址",
      showArrow: true,
      clickable: "true"
    })
  } : {}, {
    d: common_vendor.p({
      showArrow: true,
      title: $data.address.consignee,
      note: $data.address.provinceName + $data.address.cityName + $data.address.districtName + $data.address.detail
    }),
    e: common_vendor.p({
      else: true
    }),
    f: common_vendor.t($options.time),
    g: common_vendor.f($data.orders, (item, index, i0) => {
      return {
        a: item.image,
        b: common_vendor.t(item.name),
        c: common_vendor.t(item.number),
        d: common_vendor.t(item.amount),
        e: item.id
      };
    }),
    h: $data.form.remark,
    i: common_vendor.t($options.amount),
    j: common_vendor.o((...args) => $options.addOrderBtn && $options.addOrderBtn(...args))
  });
}
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["render", _sfc_render], ["__file", "E:/python/save/作品/web/project/reggie/front/pages/orderInfo/orderInfo.vue"]]);
wx.createPage(MiniProgramPage);
