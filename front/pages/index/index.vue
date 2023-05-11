<template>
	<view class="container">

		<view class="top-contain">
			<view class="top">
				<view class="top-left">
					<image src="../../asset/images/logo.png" mode=""></image>
				</view>
				<view class="top-right">
					<view class="right-title">
						<text>菩提阁餐厅</text>
					</view>
					<view class="right-info">

						<view class="item">
							<image src="../../asset/images/time.png"></image>
							<text class='item-text'>距离1.5km</text>
						</view>
						<view class="item">
							<image src="../../asset/images/money.png"></image>
							<text class='item-text'>配送费6元</text>
						</view>
						<view class="item">
							<image src="../../asset/images/location.png"></image>
							<text class='item-text'>预计时长12</text>
						</view>

					</view>
				</view>
			</view>
			<view class="bottom">
				<text class="introduction">
					简介:菩提阁中餐厅是菩提阁点餐的独立的品牌，定位"大众化美食的美食外送餐饮"，皆为顾客打造专业美食.
				</text>
			</view>
		</view>


		<image src="../../asset/images/mainBg.png" class="backgroundImage"></image>
		<view class="user">
			<image src="../../asset/images/user.png" mode=""></image>
		</view>
		<view class="main-contain">
			<view class="left-menu">
				<view class="item" v-for="(item,index) in categories" :key="index" @tap="activeItem(index,item)"
					:class="{active:index==active}">
					{{item.name}}
				</view>
			</view>
			<view class="right-goods">
				<FoodComponet :shopCart="shopCart" @changeShopCart="getShopCart" :item="item||{}"
					v-for="(item,index) in foods" :foodType=foodType :key="item.id">
				</FoodComponet>

			</view>
		</view>
		<view class="bottom-contain">

			<view class="left">
				<view class="img" @click="showShopList">
					<image src="../../asset/images/cart.png" mode="" v-if="shopCart.length==0">

					</image>
					<image src="../../asset/images/cart_active.png" mode="" v-else></image>

					<i class="badge">{{count}}</i>
				</view>


				<text class="price">￥{{price}}</text>
			</view>
			<view class="right">
				<button :class="{'active-btn':shopCart.length!==0}" @click="payBtn">去结算</button>
			</view>
		</view>
		<uni-popup ref="popup" background-color="#fff" class="popup">
			<head class="head">
				<text class="title">购物车</text>

				<view class="clear" @click="clearShopCart">
					<text>清空</text>
					<uni-icons type="trash-filled" size="20"></uni-icons>
				</view>

			</head>
			<view class="popup-content">
				<FoodComponet :item="item||{}" @changeShopCart="getShopCart" v-for="(item,index) in shopCart"
					:key="item.id" :shop-cart-open="true">
				</FoodComponet>
			</view>
		</uni-popup>
	</view>

</template>

<script>
	import {
		sendGetcategories,
		sendGetFoods,
		sendGetShopCart,
		sendDeleteShopCarts
	} from '../../api'
	import {
		handleUrl
	} from "../../untils/untils.js"
	import
	FoodCompoent
	from "../../components/FoodComponet/FoodComponet.vue"
	export default {
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
			}
		},
		computed: {
			price() {
				return this.shopCart.reduce((sums, current, index) => {
					return sums += current.amount
				}, 0)
			},
			count() {
				return this.shopCart.length
			}
		},
		methods: {
			handleInShop() {
				return handleFindIsInShop()
			},
			payBtn() {
				uni.navigateTo({
					url: "/pages/orderInfo/orderInfo?userId=" + this.userId,
				})
			},
			async clearShopCart() {
				await sendDeleteShopCarts(this.userId)
				this.getShopCart()
			},
			showShopList() {

				this.$refs.popup.open("bottom")
			},
			async getShopCart() {
				const result = await sendGetShopCart(this.userId)
				this.shopCart = result.data.data
				console.log("触发了", this.shopCart)
			},
			async getCategories() {
				const result = await sendGetcategories();
				this.categories = result.data.data
				this.activeItem(0, this.categories[0])
			},
			async activeItem(index, item) {
				this.foodType = item.type
				this.active = index
				const result = await sendGetFoods(item.id, item.type)

				this.foods = handleUrl(result.data.data)

			}
		},
		created() {
			this.getShopCart()
			this.getCategories()


		},

	}
</script>

<style lang="less">
	.popup {
		border: 5px;

		.head {
			padding: 15px;
			display: flex;
			justify-content: space-between;

			.title {
				font-size: 50rpx;
				font-weight: bold;
			}
		}

		.popup-content {
			flex-direction: row;
			align-items: center;
			justify-content: center;
			padding: 15px;
			height: 450rpx;
			background-color: #fff;
			overflow: auto;

		}
	}


	.active-btn {
		background: #ffb100 !important;
	}

	.main-contain {
		margin-top: 130rpx;

		width: 100%;
		display: flex;

		.left-menu {

			width: 25%;
			height: 500rpx;
			overflow: auto;

			.item {
				font-size: 26rpx;
				padding: 30rpx;
				color: #847f84;
			}

			.active {
				background: white;
				color: black;
			}
		}

		.right-goods {
			height: 700rpx;
			overflow: auto;
			width: 75%;

		}
	}


	.user {

		width: 70rpx;
		height: 70rpx;

		image {
			width: 100%;
			height: 100%;
		}

		position: absolute;
		top: 80rpx;
		left: 40rpx;
		z-index: 999;
	}

	.top-contain {
		position: absolute;
		background: white;
		width: 90%;
		z-index: 9;
		margin: 20px;

		border-radius: 10px;
		height: 240rpx;
		top: 120rpx;
		padding: 20rpx;
		box-sizing: border-box;

		.top {
			display: flex;

			.top-left {
				margin-right: 2rpx;

				image {
					width: 94rpx;
					height: 94rpx;
				}


			}

			.top-right {
				margin-left: 3px;

				.right-title {
					letter-spacing: 1px;
				}

				.right-info {
					margin-top: 8px;
					display: flex;


					.item {
						margin-right: 5px;

						image {
							width: 35rpx;
							height: 35rpx;
							margin-right: 2px;
						}

						display: flex;

						text {

							font-size: 25rpx;
							letter-spacing: 1px;
						}
					}
				}
			}
		}

		.bottom {
			margin-top: 20rpx;

			.introduction {

				color: #c1bcc2;
				font-size: 12px;
			}
		}

	}

	.bottom-contain {
		position: absolute;
		bottom: 40rpx;
		display: flex;
		width: 80%;
		justify-content: space-between;
		align-items: center;
		left: 0;
		right: 0;
		background: #040109;
		margin: 0 auto;
		height: 100rpx;
		border-radius: 50px;

		.img {
			position: relative;
		}

		.left {

			display: flex;
		}

		.badge {
			right: 0;
			top: 0;
			display: block;
			position: absolute;
			color: white;
			width: 50rpx;
			height: 50rpx;
			background: #ff9e72;
			border-radius: 50%;
			font-size: 27rpx;

			text-align: center;
			line-height: 50rpx;
		}

		image {
			width: 130rpx;
			height: 130rpx;

		}

		.price {
			vertical-align: bottom;
			color: wheat;
			font-size: 40rpx;
			line-height: 130rpx;
		}

		button {
			background: #9a989d;
			border-radius: 40rpx;
			height: 100rpx;
			width: 200rpx;
			font-size: 30rpx;
			line-height: 100rpx;
			color: #f1ecf6;

		}

	}



	.backgroundImage {
		width: 100%;
		height: 304rpx;
	}
</style>
