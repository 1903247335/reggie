"use strict";
const common_vendor = require("../../common/vendor.js");
const api_index = require("../../api/index.js");
const untils_untils = require("../../untils/untils.js");
const common_assets = require("../../common/assets.js");
require("../../axiosInterceptors/index.js");
require("../../axiosBasic/index.js");
const FoodCompoent = () => "../../components/FoodComponet/FoodComponet.js";
const _sfc_main = {
  components: {
    FoodCompoent
  },
  data() {
    return {
      userId: this.$store.state.user.id,
      categories: [],
      active: 0,
      foods: [],
      foodType: 1,
      shopCart: []
    };
  },
  computed: {
    price() {
      return this.shopCart.reduce((sums, current, index) => {
        return sums += current.amount;
      }, 0);
    },
    count() {
      return this.shopCart.length;
    }
  },
  methods: {
    handleInShop() {
      return handleFindIsInShop();
    },
    payBtn() {
      common_vendor.index.navigateTo({
        url: "/pages/orderInfo/orderInfo?userId=" + this.userId
      });
    },
    async clearShopCart() {
      await api_index.sendDeleteShopCarts(this.userId);
      this.getShopCart();
    },
    showShopList() {
      this.$refs.popup.open("bottom");
    },
    async getShopCart() {
      const result = await api_index.sendGetShopCart(this.userId);
      this.shopCart = result.data.data;
      console.log("触发了", this.shopCart);
    },
    async getCategories() {
      const result = await api_index.sendGetcategories();
      this.categories = result.data.data;
      this.activeItem(0, this.categories[0]);
    },
    async activeItem(index, item) {
      this.foodType = item.type;
      this.active = index;
      const result = await api_index.sendGetFoods(item.id, item.type);
      this.foods = untils_untils.handleUrl(result.data.data);
    }
  },
  created() {
    this.getShopCart();
    this.getCategories();
  }
};
if (!Array) {
  const _easycom_FoodComponet2 = common_vendor.resolveComponent("FoodComponet");
  const _easycom_uni_icons2 = common_vendor.resolveComponent("uni-icons");
  const _easycom_uni_popup2 = common_vendor.resolveComponent("uni-popup");
  (_easycom_FoodComponet2 + _easycom_uni_icons2 + _easycom_uni_popup2)();
}
const _easycom_FoodComponet = () => "../../components/FoodComponet/FoodComponet.js";
const _easycom_uni_icons = () => "../../uni_modules/uni-icons/components/uni-icons/uni-icons.js";
const _easycom_uni_popup = () => "../../uni_modules/uni-popup/components/uni-popup/uni-popup.js";
if (!Math) {
  (_easycom_FoodComponet + _easycom_uni_icons + _easycom_uni_popup)();
}
function _sfc_render(_ctx, _cache, $props, $setup, $data, $options) {
  return common_vendor.e({
    a: common_assets._imports_0,
    b: common_assets._imports_1,
    c: common_assets._imports_2,
    d: common_assets._imports_3,
    e: common_assets._imports_4,
    f: common_assets._imports_5,
    g: common_vendor.f($data.categories, (item, index, i0) => {
      return {
        a: common_vendor.t(item.name),
        b: index,
        c: common_vendor.o(($event) => $options.activeItem(index, item), index),
        d: index == $data.active ? 1 : ""
      };
    }),
    h: common_vendor.f($data.foods, (item, index, i0) => {
      return {
        a: common_vendor.o($options.getShopCart, item.id),
        b: item.id,
        c: "36506b94-0-" + i0,
        d: common_vendor.p({
          shopCart: $data.shopCart,
          item: item || {},
          foodType: $data.foodType
        })
      };
    }),
    i: $data.shopCart.length == 0
  }, $data.shopCart.length == 0 ? {
    j: common_assets._imports_6
  } : {
    k: common_assets._imports_7
  }, {
    l: common_vendor.t($options.count),
    m: common_vendor.o((...args) => $options.showShopList && $options.showShopList(...args)),
    n: common_vendor.t($options.price),
    o: $data.shopCart.length !== 0 ? 1 : "",
    p: common_vendor.o((...args) => $options.payBtn && $options.payBtn(...args)),
    q: common_vendor.p({
      type: "trash-filled",
      size: "20"
    }),
    r: common_vendor.o((...args) => $options.clearShopCart && $options.clearShopCart(...args)),
    s: common_vendor.f($data.shopCart, (item, index, i0) => {
      return {
        a: common_vendor.o($options.getShopCart, item.id),
        b: item.id,
        c: "36506b94-3-" + i0 + ",36506b94-1",
        d: common_vendor.p({
          item: item || {},
          ["shop-cart-open"]: true
        })
      };
    }),
    t: common_vendor.sr("popup", "36506b94-1"),
    v: common_vendor.p({
      ["background-color"]: "#fff"
    })
  });
}
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["render", _sfc_render], ["__file", "E:/python/save/作品/web/project/reggie/front/pages/index/index.vue"]]);
wx.createPage(MiniProgramPage);
