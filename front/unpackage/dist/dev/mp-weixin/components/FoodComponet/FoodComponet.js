"use strict";
const api_index = require("../../api/index.js");
const common_vendor = require("../../common/vendor.js");
const common_assets = require("../../common/assets.js");
require("../../axiosInterceptors/index.js");
require("../../axiosBasic/index.js");
const _sfc_main = {
  name: "FoodComponet",
  props: {
    item: {
      type: Object,
      default: () => {
      }
    },
    foodType: Number,
    shopCart: {
      type: Array,
      default: () => []
    },
    shopCartOpen: {
      type: Boolean,
      default: false
    }
  },
  watch: {
    item() {
      this.count = this.item.number;
      console.log("更新了", this.item);
    }
  },
  mounted() {
    const find = this.handleFindIsInShop(this.item.id, this.shopCart);
    if (find) {
      this.count = find.number;
    }
  },
  data() {
    return {
      user: this.item.userId,
      count: this.item.number || 0,
      type: "center",
      msgType: "success",
      messageText: "这是一条成功提示",
      value: "",
      image: this.item.image,
      flavor: [{
        name: "",
        options: []
      }],
      chooseFlavor: [],
      food: {
        name: "",
        image: "",
        dishId: "",
        setmealId: "",
        dishFlavor: [],
        number: this.count,
        amount: this.count * this.item.price
      }
    };
  },
  methods: {
    test() {
      console.log(this.shopCart);
    },
    async changeValue(value) {
      const food = this.item;
      console.log("value变化", value);
      this.count = value;
      if (!this.shopCartOpen) {
        if (this.foodType == 1) {
          this.food = {
            name: food.name,
            image: food.image,
            userId: this.$store.state.user.id,
            dishId: food.id,
            setmealId: "",
            dishFlavor: JSON.stringify([]),
            number: this.count,
            amount: this.count * food.price
          };
        } else {
          this.food = {
            name: food.name,
            image: food.image,
            userId: this.$store.state.user.id,
            dishId: "",
            setmealId: food.id,
            dishFlavor: this.dishFlavor,
            number: this.count,
            amount: this.count * food.price
          };
        }
        if (value == 0) {
          await api_index.sendDeleteShopCart(this.food);
        } else {
          await api_index.sendUpdateShopCart(this.food);
        }
        this.$emit("changeShopCart");
      } else {
        if (food.dishId) {
          this.food = {
            id: food.id,
            name: food.name,
            image: food.image,
            userId: this.$store.state.user.id,
            dishId: food.dishId,
            setmealId: "",
            dishFlavor: JSON.stringify([]),
            number: this.count,
            amount: parseInt(food.amount / food.number) * value
          };
        } else {
          this.food = {
            id: food.id,
            name: food.name,
            image: food.image,
            userId: this.$store.state.user.id,
            dishId: "",
            setmealId: food.setmealId,
            dishFlavor: food.dishFlavor,
            number: this.count,
            amount: parseInt(food.amount / food.number) * food.value
          };
        }
        if (value == 0) {
          await api_index.sendDeleteShopCart(this.food);
        } else {
          await api_index.sendUpdateShopCart(this.food);
        }
        this.$emit("changeShopCart");
      }
    },
    async addBtn(food, type) {
      this.count = 1;
      console.log("type是", type);
      if (type == 1) {
        this.food = {
          name: food.name,
          image: food.image,
          userId: this.$store.state.user.id,
          dishId: food.id,
          setmealId: "",
          dishFlavor: JSON.stringify([]),
          number: this.count,
          amount: this.count * food.price
        };
      } else {
        this.food = {
          name: food.name,
          image: food.image,
          userId: this.$store.state.user.id,
          dishId: "",
          setmealId: food.id,
          dishFlavor: JSON.stringify(this.dishFlavor),
          number: this.count,
          amount: this.count * food.price
        };
      }
      await api_index.sendAddShopCart(this.food, type);
      this.$emit("changeShopCart");
    },
    tagBtn(item, id) {
      this.flavor = this.flavor.map((flavor) => {
        if (item.name == flavor.name) {
          return {
            name: flavor.name,
            options: flavor.options.map((option) => {
              option.inverted = true;
              return option;
            })
          };
        }
        return flavor;
      });
      this.flavor = this.flavor.map((flavor) => {
        if (flavor.name == item.name) {
          return {
            name: flavor.name,
            options: flavor.options.map((option) => {
              if (option.id == id) {
                option.inverted = !option.inverted;
                this.chooseFlavor = [];
                this.flavor.forEach((item2) => {
                  item2.options.forEach((item3) => {
                    if (!item3.inverted) {
                      this.chooseFlavor.push(item3.value);
                    }
                  });
                });
              }
              return option;
            })
          };
        }
        return flavor;
      });
    },
    async toggle(type, item) {
      this.type = type;
      this.$refs.popup.open(type);
      console.log(this.flavor);
    },
    handleError(event) {
      event.detail.errMsg = "hrlloe";
      this.image = "https://1903247335-1316551243.cos.ap-guangzhou.myqcloud.com/noImg.png";
      console.log(event.detail);
    },
    handleFindIsInShop(foodId, list) {
      return list.find((item) => {
        return foodId == item.dishId || item.setmealId;
      });
    }
  },
  created() {
    if (!this.shopCartOpen) {
      if (this.foodType == 1) {
        const mainFlavor = JSON.parse(this.item.mainFlavor);
        const detailFlavor = JSON.parse(this.item.detailFlavor);
        this.flavor = mainFlavor.map((item, index) => {
          return {
            name: item,
            options: detailFlavor[index].map(
              (option, index2) => {
                return {
                  id: index2,
                  value: option,
                  inverted: true
                };
              }
            )
          };
        });
      }
    } else {
      console.log("创建了");
    }
  }
};
if (!Array) {
  const _easycom_uni_number_box2 = common_vendor.resolveComponent("uni-number-box");
  const _easycom_uni_tag2 = common_vendor.resolveComponent("uni-tag");
  const _easycom_uni_popup2 = common_vendor.resolveComponent("uni-popup");
  (_easycom_uni_number_box2 + _easycom_uni_tag2 + _easycom_uni_popup2)();
}
const _easycom_uni_number_box = () => "../../uni_modules/uni-number-box/components/uni-number-box/uni-number-box.js";
const _easycom_uni_tag = () => "../../uni_modules/uni-tag/components/uni-tag/uni-tag.js";
const _easycom_uni_popup = () => "../../uni_modules/uni-popup/components/uni-popup/uni-popup.js";
if (!Math) {
  (_easycom_uni_number_box + _easycom_uni_tag + _easycom_uni_popup)();
}
function _sfc_render(_ctx, _cache, $props, $setup, $data, $options) {
  return common_vendor.e({
    a: $data.image,
    b: common_vendor.o((...args) => $options.handleError && $options.handleError(...args)),
    c: common_vendor.o((...args) => $options.test && $options.test(...args)),
    d: common_vendor.t($props.item.name),
    e: common_vendor.t($props.item.description),
    f: common_vendor.t($props.item.price || $props.item.amount),
    g: $data.count == 0
  }, $data.count == 0 ? common_vendor.e({
    h: $props.foodType == 1 || $data.flavor.length == 0
  }, $props.foodType == 1 || $data.flavor.length == 0 ? {
    i: common_vendor.o(($event) => $options.toggle("center", $props.item))
  } : {
    j: common_assets._imports_0$1,
    k: common_vendor.o(($event) => $options.addBtn($props.item, 2))
  }) : {
    l: common_vendor.o($options.changeValue),
    m: common_vendor.o(($event) => $data.count = $event),
    n: common_vendor.p({
      background: "#ffb406",
      modelValue: $data.count
    })
  }, {
    o: common_vendor.t($props.item.name),
    p: common_vendor.f($data.flavor, (item1, index, i0) => {
      return {
        a: common_vendor.t(item1.name),
        b: common_vendor.f(item1.options, (value, k1, i1) => {
          return {
            a: value.id,
            b: common_vendor.o(($event) => $options.tagBtn(item1, value.id), value.id),
            c: "16204fbc-2-" + i0 + "-" + i1 + ",16204fbc-1",
            d: common_vendor.p({
              type: "warning",
              text: value.value,
              inverted: value.inverted
            })
          };
        }),
        c: index
      };
    }),
    q: common_vendor.t($props.item.price),
    r: $data.count == 0
  }, $data.count == 0 ? {
    s: common_vendor.o(($event) => $options.addBtn($props.item, 1))
  } : {
    t: common_vendor.o($options.changeValue),
    v: common_vendor.o(($event) => $data.count = $event),
    w: common_vendor.p({
      background: "#ffb406",
      modelValue: $data.count
    })
  }, {
    x: common_vendor.sr("popup", "16204fbc-1"),
    y: common_vendor.p({
      ["background-color"]: "#fff"
    })
  });
}
const Component = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["render", _sfc_render], ["__file", "E:/python/save/作品/web/project/reggie/front/components/FoodComponet/FoodComponet.vue"]]);
wx.createComponent(Component);
