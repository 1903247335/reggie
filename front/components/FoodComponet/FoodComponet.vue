<template>
	<view class="contain">
		<image :src="image" class="left" @error="handleError" @click="test">
		</image>
		<view class="right">
			<text class="title">{{item.name}}</text>
			<text class="introduction">{{item.description}}</text>

			<view class="bottom">
				<text class="price">￥{{item.price||item.amount}}</text>
				<template v-if="count==0">
					<button type="warn" size="mini" class="scale" @tap="toggle('center',item)"
						v-if="foodType==1||flavor.length==0">选择规模</button>
					<image src="../../asset/images/add.png" class="add" v-else @tap="addBtn(item,2)"></image>
				</template>

				<uni-number-box background="#ffb406" @change="changeValue" v-model="count" v-else />
			</view>
		</view>
		<view>
			<!-- 普通弹窗 -->
			<uni-popup ref="popup" background-color="#fff">
				<view class="popup-content">
					<text class="title">{{item.name}}</text>
					<view class="flavor-contain">
						<view class="flavor" v-for="(item1,index) in flavor" :key="index">
							<text class="mainFlavor">{{item1.name}}</text>
							<uni-tag type="warning" class="tag" :text="value.value" :inverted="value.inverted"
								v-for="(value) in item1.options" :key="value.id" @click="tagBtn(item1,value.id)">
							</uni-tag>
						</view>
					</view>

					<view class="bottom">
						<text>${{item.price}}</text>
						<button type="warn" size="mini" class="add-cart" v-if="count==0"
							@tap="addBtn(item,1)">加入购物车</button>
						<uni-number-box background="#ffb406" @change="changeValue" v-model="count" v-else />
					</view>

				</view>
			</uni-popup>
		</view>
	</view>
</template>

<script>
	import {
		sendUpdateShopCart,
		sendAddShopCart,
		sendDeleteShopCart
	} from '../../api';
	export default {
		name: "FoodComponet",
		props: {

			item: {
				type: Object,
				default: () => {}
			},
			foodType: Number,
			shopCart: {
				type: Array,
				default: () => []
			},
			shopCartOpen: {
				type: Boolean,
				default: false
			},


		},
		watch: {
			item() {
				this.count = this.item.number
				console.log("更新了", this.item)
			}
		},
		mounted() {
			const find = this.handleFindIsInShop(this.item.id, this.shopCart)
			if (find) {
				this.count = find.number
			}
		},
		data() {
			return {

				user: this.item.userId,
				count: this.item.number || 0,
				type: 'center',
				msgType: 'success',
				messageText: '这是一条成功提示',
				value: '',
				image: this.item.image,
				flavor: [{
					name: '',
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
					amount: this.count * this.item.price,
				},

			};
		},

		methods: {
			test() {
				console.log(this.shopCart)
			},
			async changeValue(value) {
				const food = this.item
				console.log("value变化", value)
				this.count = value
				if (!this.shopCartOpen) {


					//右侧列表的渲染数字变化
					if (this.foodType == 1) {
						this.food = {
							name: food.name,
							image: food.image,
							userId: this.$store.state.user.id,
							dishId: food.id,
							setmealId: "",
							dishFlavor: JSON.stringify([]),
							number: this.count,
							amount: this.count * food.price,
						}
					} else {
						this.food = {

							name: food.name,
							image: food.image,
							userId: this.$store.state.user.id,
							dishId: "",
							setmealId: food.id,
							dishFlavor: this.dishFlavor,
							number: this.count,
							amount: this.count * food.price,
						}
					}
					if (value == 0) {
						await sendDeleteShopCart(this.food)
					} else {
						const result = await sendUpdateShopCart(this.food)
					}
					this.$emit("changeShopCart")
				} else {
					//进入购物车的列表渲染数字编号
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
							amount: parseInt(food.amount / food.number) * value,
						}
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
							amount: parseInt(food.amount / food.number) * food.value,
						}
					}
					if (value == 0) {
						await sendDeleteShopCart(this.food)
					} else {
						const result = await sendUpdateShopCart(this.food)
					}

					this.$emit("changeShopCart")
				}

			},
			async addBtn(food, type) {
				this.count = 1
				console.log("type是", type)
				if (type == 1) {
					this.food = {
						name: food.name,
						image: food.image,
						userId: this.$store.state.user.id,
						dishId: food.id,
						setmealId: "",
						dishFlavor: JSON.stringify([]),
						number: this.count,
						amount: this.count * food.price,
					}
				} else {
					this.food = {
						name: food.name,
						image: food.image,
						userId: this.$store.state.user.id,
						dishId: "",
						setmealId: food.id,
						dishFlavor: JSON.stringify(this.dishFlavor),
						number: this.count,
						amount: this.count * food.price,
					}
				}
				const result = await sendAddShopCart(this.food, type)
				this.$emit("changeShopCart")

			},

			tagBtn(item, id) {
				//[{'name',option:[]}]
				//重置全部为true
				this.flavor = this.flavor.map((flavor) => {
					if (item.name == flavor.name) {
						return {
							name: flavor.name,
							options: flavor.options.map((option) => {

								option.inverted = true


								return option
							})
						}
					}
					return flavor


				})
				//只能选一个
				this.flavor = this.flavor.map((flavor) => {
					if (flavor.name == item.name) {
						return {
							name: flavor.name,
							options: flavor.options.map((option) => {
								if (option.id == id) {
									option.inverted = !option.inverted
									//在每次点击后都会进行遍历查看找出inverte为false的
									this.chooseFlavor = []
									this.flavor.forEach((item2) => {

										item2.options.forEach((item3) => {
											if (!item3.inverted) {
												this.chooseFlavor.push(item3.value)
											}


										})
									})

								}
								return option
							})
						}
					}
					return flavor
				})






			},
			async toggle(type, item) {
				this.type = type
				// open 方法传参数 等同在 uni-popup 组件上绑定 type属性
				this.$refs.popup.open(type)
				console.log(this.flavor)


			},
			handleError(event) {
				event.detail.errMsg = "hrlloe"
				this.image = "https://1903247335-1316551243.cos.ap-guangzhou.myqcloud.com/noImg.png"
				console.log(event.detail)
			},
			handleFindIsInShop(foodId, list) {
				return list.find((item) => {
					return foodId == item.dishId || item.setmealId
				})
			}

		},


		created() {


			if (!this.shopCartOpen) {
				if (this.foodType == 1) {
					const mainFlavor = JSON.parse(this.item.mainFlavor)
					const detailFlavor = JSON.parse(this.item.detailFlavor)

					this.flavor = mainFlavor.map((item, index) => {
						return {
							name: item,
							options: detailFlavor[index].map(
								(option, index) => {
									return {
										id: index,
										value: option,
										inverted: true
									}
								}
							)
						}
					})
				}
			} else {
				console.log("创建了")

			}



		},




	}
</script>

<style lang="less">
	.popup-content {
		width: 450rpx;
		height: 500rpx;
		padding: 10px;
		box-sizing: border-box;

		.flavor-contain {
			height: 300rpx;
		}

		.flavor {
			margin-bottom: 10px;
		}

		.title {
			display: block;
			width: 100%;
			text-align: center;
			font-weight: bold;
		}

		.mainFlavor {
			margin-bottom: 5px;
			display: block;
			font-size: 14px;

		}

		.tag {
			margin-right: 10px;
			margin-top: 2px;
		}

		.bottom {
			text {
				color: #c58a79;
			}

			.add-cart,
			.scale {
				border-radius: 10px;
				padding: 0;
				width: 70px;
				height: 30px;
				background: #ffc00b;
				margin: 0;
			}

			height: 100rpx;
			display: flex;
			justify-content: space-between;
			align-items: center;
		}
	}

	.contain {
		margin-top: 10px;
		display: flex;





		.left {

			width: 35%;
			height: auto;

			image {
				width: 100%;
				height: 100%;
			}
		}

		.right {
			width: 65%;
			padding: 10px;
			box-sizing: border-box;

			.title {
				font-size: 16px;
				font-weight: bold;
			}

			.introduction {
				margin-top: 10px;
				display: block;
				font-size: 13px;
				color: #d0cbd1;
				overflow: hidden;
				text-overflow: ellipsis;
				white-space: nowrap;

			}

			.bottom {
				margin-top: 10px;
				display: flex;
				justify-content: space-between;
				align-items: center;

				.scale {
					border-radius: 10px;
					padding: 0;
					width: 70px;
					height: 30px;
					background: #ffc00b;
					margin: 0;
				}

				.price {
					font-size: 16px;
					color: #d47b5b;
				}

				.add {
					width: 30px;
					height: 30px;


				}
			}
		}
	}
</style>
