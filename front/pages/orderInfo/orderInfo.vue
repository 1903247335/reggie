<template>
	<view class="orderInfo-contain">
		<view class="order-head">
			<uni-list v-if="!form.addressBookId">
				<uni-list-item title="添加地址" @click="addAddressBtn" showArrow clickable="true"></uni-list-item>
			</uni-list>
			<uni-list else>
				<uni-list-item showArrow :title="address.consignee"
					:note="address.provinceName+address.cityName+address.districtName+address.detail">


				</uni-list-item>
			</uni-list>
			<view class="predict-time">
				预计{{time}}送达
			</view>
		</view>
		<view class="order-main">
			<view class="order-detail">
				<view class="title">
					<text>订单明细</text>
				</view>
				<view class="list">
					<view class="item" v-for="(item,index) in orders" :key="item.id">
						<view class="left">
							<image :src="item.image" mode=""></image>
						</view>
						<view class="right">
							<view>
								<text class="name">{{item.name}}</text>
							</view>
							<view class="bottom">
								<text class="number">x{{item.number}}</text>
								<text class="price">￥{{item.amount}}</text>
							</view>

						</view>

					</view>
					<view class="remarks">
						<view class="title">
							<text>
								备注
							</text>
						</view>
						<textarea :value="form.remark" placeholder="备注需要的口味" />
					</view>
				</view>
				<view class="btn">
					<text
						style="font-size: 15px;color: red;margin: 10px 0;text-align: center;display: block;">需支付：{{amount}}</text>
					<button type="primary" @tap="addOrderBtn">支付</button>

				</view>
			</view>
		</view>

	</view>
</template>

<script>
	import {

		sendGetAddressByAddressbookId,
		sendGetOrders,
		sendAddOrder

	} from '../../api'
	export default {
		onLoad: function(options) {
			const userId = options.userId

			this.userId = userId

			this.getOrders()
		},
		computed: {
			amount() {
				return this.orders.reduce((sums, item) => {
					return sums + item.amount
				}, 0)
			},
			time() {
				const date = new Date()
				let hours = date.getHours()
				let minute = date.getMinutes()
				if (minute + 30 >= 60) {
					hours += 1
					minute -= 60
				}
				return `${hours}:${minute+30<10?'0'+minute+30:minute+30}`
			}
		},
		mounted() {
			this.form.amount = this.amount
		},
		data() {
			return {
				userId: "",
				address: {
					consignee: '',
					provinceName: "",
					cityName: "",
					districtName: "",
					detail: ""
				}

				,
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

			}
		},
		methods: {
			async addOrderBtn() {
				console.log(this.form)
				const result = await sendAddOrder(this.form)
			},
			addAddressBtn() {

				uni.navigateTo({
					url: "/pages/addressList/addressList"
				})
			},
			async getOrders() {
				const result = await sendGetOrders(this.userId)
				this.orders = result.data.data
			},
			async getAddress() {
				const result = await sendGetAddressByAddressbookId(this.$store.state.addressBookId)
				this.address = result.data.data
				console.log(this.address)
			}
		},
		onShow: function() {

			if (this.$store.state.addressBookId) {
				this.getAddress()
				this.form.addressBookId = this.$store.state.addressBookId
			}
		}

	}
</script>

<style lang="less">
	.orderInfo-contain {
		padding: 10px;
	}

	.order-head {
		background-color: white;
		padding: 15px;
	}

	.predict-time {
		margin-top: 20rpx;
		font-size: 12px;
		color: gray;
	}

	.order-main {
		background: white;
		padding: 10px;

		.order-detail {
			.title {}
		}


		.remarks {
			margin-top: 40rpx;

			.title {
				text {
					font-weight: bold;
				}
			}

			textarea {
				margin-top: 20px
			}
		}

		.list {
			margin-top: 40rpx;

			.item {
				display: flex;

				.left {
					width: 180rpx;
					height: 180rpx;
					margin-right: 10rpx;

					image {
						width: 100%;
						height: 100%;
					}
				}

				.right {
					display: flex;
					flex-direction: column;
					justify-content: space-between;
					width: 100%;

					.name {

						font-weight: bold;

					}

					.bottom {
						display: flex;
						justify-content: space-between;


						.number {
							font-size: 12px;
						}

						.price {
							color: #cf7865;

						}

					}
				}
			}
		}

	}
</style>
