"use strict";
const common_vendor = require("../../common/vendor.js");
const api_index = require("../../api/index.js");
require("../../axiosInterceptors/index.js");
require("../../axiosBasic/index.js");
const _sfc_main = {
  data() {
    return {
      change: "",
      form: {
        userId: this.$store.state.user.id,
        phone: "",
        consignee: "",
        sex: 1,
        provinceName: "",
        cityName: "",
        districtName: "",
        detail: ""
      },
      items: [{
        text: "广东省",
        value: "广东省",
        children: [{
          text: "广州市",
          value: "广州市",
          children: [{
            text: "天河区",
            value: "天河区"
          }]
        }]
      }]
    };
  },
  methods: {
    inputTextarea(value) {
      this.form.detail = value.detail.value;
    },
    radioChange(value) {
      this.form.sex = value.detail.value;
    },
    onnodeclick(node) {
      if (node.value.indexOf("省") != -1) {
        this.form.provinceName = node.value;
      } else if (node.value.indexOf("市") != -1) {
        this.form.cityName = node.value;
      } else if (node.value.indexOf("区" != -1)) {
        this.form.districtName = node.value;
      }
    },
    async addAddressBtn() {
      await api_index.sendAddAddress(this.form);
      common_vendor.index.navigateBack();
    }
  }
};
if (!Array) {
  const _easycom_uni_easyinput2 = common_vendor.resolveComponent("uni-easyinput");
  const _easycom_uni_forms_item2 = common_vendor.resolveComponent("uni-forms-item");
  const _easycom_uni_data_picker2 = common_vendor.resolveComponent("uni-data-picker");
  const _easycom_uni_forms2 = common_vendor.resolveComponent("uni-forms");
  (_easycom_uni_easyinput2 + _easycom_uni_forms_item2 + _easycom_uni_data_picker2 + _easycom_uni_forms2)();
}
const _easycom_uni_easyinput = () => "../../uni_modules/uni-easyinput/components/uni-easyinput/uni-easyinput.js";
const _easycom_uni_forms_item = () => "../../uni_modules/uni-forms/components/uni-forms-item/uni-forms-item.js";
const _easycom_uni_data_picker = () => "../../uni_modules/uni-data-picker/components/uni-data-picker/uni-data-picker.js";
const _easycom_uni_forms = () => "../../uni_modules/uni-forms/components/uni-forms/uni-forms.js";
if (!Math) {
  (_easycom_uni_easyinput + _easycom_uni_forms_item + _easycom_uni_data_picker + _easycom_uni_forms)();
}
function _sfc_render(_ctx, _cache, $props, $setup, $data, $options) {
  return {
    a: common_vendor.o(($event) => $data.form.consignee = $event),
    b: common_vendor.p({
      placeholder: "输入收货人",
      modelValue: $data.form.consignee
    }),
    c: common_vendor.p({
      label: "姓名",
      width: "25%",
      name: "consignee"
    }),
    d: common_vendor.o(($event) => $data.form.phone = $event),
    e: common_vendor.p({
      type: "text",
      placeholder: "输入电话号码",
      modelValue: $data.form.phone
    }),
    f: common_vendor.p({
      label: "电话",
      name: "phone"
    }),
    g: common_vendor.o((...args) => $options.radioChange && $options.radioChange(...args)),
    h: common_vendor.p({
      label: "性别",
      name: "sex"
    }),
    i: common_vendor.o($options.onnodeclick),
    j: common_vendor.p({
      localdata: $data.items,
      ["popup-title"]: "请选择地址"
    }),
    k: common_vendor.p({
      label: "地址"
    }),
    l: $data.form.detail,
    m: common_vendor.o((...args) => $options.inputTextarea && $options.inputTextarea(...args)),
    n: common_vendor.p({
      label: "详细地址"
    }),
    o: common_vendor.o((...args) => $options.addAddressBtn && $options.addAddressBtn(...args)),
    p: common_vendor.p({
      modelValue: $data.form
    })
  };
}
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["render", _sfc_render], ["__file", "E:/python/save/作品/web/project/reggie/front/pages/addAddress/addAddress.vue"]]);
wx.createPage(MiniProgramPage);
