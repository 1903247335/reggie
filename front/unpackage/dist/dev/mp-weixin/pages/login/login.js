"use strict";
const common_vendor = require("../../common/vendor.js");
const api_index = require("../../api/index.js");
require("../../axiosInterceptors/index.js");
require("../../axiosBasic/index.js");
const CodeButton = () => "../../components/CodeButton/CodeButton.js";
const _sfc_main = {
  components: {
    CodeButton
  },
  mounted() {
    if (this.$store.state.user.id) {
      common_vendor.index.switchTab({
        url: "/pages/index/index"
      });
    }
  },
  data() {
    return {
      codeStatus: true,
      shopCart: [],
      rules: {
        username: {
          rules: [{
            required: true,
            errorMessage: "用户名不能为空"
          }, {
            validateFunction: function(rule, value, data, callback) {
              const reg = /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/;
              if (!reg.test(value)) {
                callback("邮箱格式不正确");
              }
              common_vendor.index.hideLoading();
              return true;
            }
          }]
        },
        code: {
          rules: [{
            required: true,
            errorMessage: "验证码不能为空"
          }]
        }
      },
      form: {
        username: "",
        code: ""
      },
      msgType: "success",
      messageText: "登录成功"
    };
  },
  onReady() {
    this.$refs.form.setRules(this.rules);
  },
  methods: {
    sendCode() {
      this.$refs.form.validateField("username", (valid, message) => {
        if (!valid && this.codeStatus) {
          api_index.sendMsg(this.form.username);
          this.codeStatus = false;
        }
      });
    },
    loginBtn() {
      common_vendor.index.showLoading();
      this.$refs.form.validate().then(async () => {
        const result = await api_index.verifyCode(this.form);
        if (result.data.code == 1) {
          this.msgType = "success";
          this.messageText = "登录成功";
          this.$refs.message.open();
          this.$store.dispatch("login", result.data.data);
          new Promise((resolve, reject) => {
            setTimeout(() => {
              resolve();
            }, 2e3);
          }).then(() => {
            common_vendor.index.switchTab({
              url: "/pages/index/index"
            });
          });
        } else {
          this.msgType = "error";
          this.messageText = "验证码错误";
          this.$refs.message.open();
        }
      }).catch(() => {
      });
      common_vendor.index.hideLoading();
    },
    changeStatus() {
      this.codeStatus = !this.codeStatus;
    }
  }
};
if (!Array) {
  const _easycom_uni_easyinput2 = common_vendor.resolveComponent("uni-easyinput");
  const _easycom_uni_forms_item2 = common_vendor.resolveComponent("uni-forms-item");
  const _easycom_CodeButton2 = common_vendor.resolveComponent("CodeButton");
  const _easycom_uni_forms2 = common_vendor.resolveComponent("uni-forms");
  const _easycom_uni_popup_message2 = common_vendor.resolveComponent("uni-popup-message");
  const _easycom_uni_popup2 = common_vendor.resolveComponent("uni-popup");
  (_easycom_uni_easyinput2 + _easycom_uni_forms_item2 + _easycom_CodeButton2 + _easycom_uni_forms2 + _easycom_uni_popup_message2 + _easycom_uni_popup2)();
}
const _easycom_uni_easyinput = () => "../../uni_modules/uni-easyinput/components/uni-easyinput/uni-easyinput.js";
const _easycom_uni_forms_item = () => "../../uni_modules/uni-forms/components/uni-forms-item/uni-forms-item.js";
const _easycom_CodeButton = () => "../../components/CodeButton/CodeButton.js";
const _easycom_uni_forms = () => "../../uni_modules/uni-forms/components/uni-forms/uni-forms.js";
const _easycom_uni_popup_message = () => "../../uni_modules/uni-popup/components/uni-popup-message/uni-popup-message.js";
const _easycom_uni_popup = () => "../../uni_modules/uni-popup/components/uni-popup/uni-popup.js";
if (!Math) {
  (_easycom_uni_easyinput + _easycom_uni_forms_item + _easycom_CodeButton + _easycom_uni_forms + _easycom_uni_popup_message + _easycom_uni_popup)();
}
function _sfc_render(_ctx, _cache, $props, $setup, $data, $options) {
  return {
    a: common_vendor.o(($event) => $data.form.username = $event),
    b: common_vendor.p({
      placeholder: "输入邮箱",
      modelValue: $data.form.username
    }),
    c: common_vendor.p({
      label: "邮箱",
      name: "username"
    }),
    d: common_vendor.o(($event) => $data.form.code = $event),
    e: common_vendor.p({
      placeholder: "输入验证码",
      modelValue: $data.form.code
    }),
    f: common_vendor.o(($event) => $options.sendCode()),
    g: common_vendor.o($options.changeStatus),
    h: common_vendor.p({
      status: $data.codeStatus
    }),
    i: common_vendor.p({
      label: "验证码",
      name: "code"
    }),
    j: common_vendor.o((...args) => $options.loginBtn && $options.loginBtn(...args)),
    k: common_vendor.sr("form", "08c8c482-0"),
    l: common_vendor.p({
      rules: $data.rules,
      model: $data.form
    }),
    m: common_vendor.p({
      type: $data.msgType,
      message: $data.messageText,
      duration: 2e3
    }),
    n: common_vendor.sr("message", "08c8c482-6"),
    o: common_vendor.p({
      type: "message"
    })
  };
}
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["render", _sfc_render], ["__file", "E:/python/save/作品/web/project/reggie/front/pages/login/login.vue"]]);
wx.createPage(MiniProgramPage);
