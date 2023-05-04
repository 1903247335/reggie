"use strict";
const common_vendor = require("../../common/vendor.js");
const _sfc_main = {
  name: "CodeButton",
  props: {
    status: {
      type: Boolean
    }
  },
  data() {
    return {
      count: 60
    };
  },
  watch: {
    status: function(newValue) {
      if (newValue == false) {
        this.count = 60;
        let timer = setInterval(() => {
          if (this.count != 0) {
            this.count = this.count - 1;
          } else {
            clearInterval(timer);
            this.$emit("changeStatusEvent");
          }
        }, 1e3);
      }
    }
  },
  created() {
    console.log(this.status);
  }
};
function _sfc_render(_ctx, _cache, $props, $setup, $data, $options) {
  return common_vendor.e({
    a: $props.status == true
  }, $props.status == true ? {} : {
    b: common_vendor.t($data.count)
  });
}
const Component = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["render", _sfc_render], ["__scopeId", "data-v-32d305ff"], ["__file", "E:/python/save/作品/web/project/reggie/front/components/CodeButton/CodeButton.vue"]]);
wx.createComponent(Component);
