<template>
	<view class="container">
		<uni-forms ref="form" :rules="rules" :model="form">
			<uni-forms-item label="邮箱" name="username">
				<uni-easyinput v-model="form.username" placeholder="输入邮箱" />
			</uni-forms-item>
			<uni-forms-item label="验证码" name="code">
				<uni-easyinput v-model="form.code" placeholder="输入验证码"></uni-easyinput>
				<CodeButton class="send-code" :status="codeStatus" @tap="sendCode()" @changeStatusEvent="changeStatus">
				</CodeButton>
			</uni-forms-item>
			<button type="primary" @tap="loginBtn">登录</button>
		</uni-forms>
		<uni-popup ref="message" type="message">
			<uni-popup-message :type="msgType" :message="messageText" :duration="2000"></uni-popup-message>
		</uni-popup>


	</view>
</template>

<script>
	import {
		sendMsg,
		verifyCode
	} from "../../api"
	import
	CodeButton
	from "../../components/CodeButton/CodeButton.vue"

	export default {
		components: {
			CodeButton
		},
		mounted() {
			//user {"id":"21232097404734","name":"1668694954@qq.com","phone":"1668694954@qq.com","sex":"0","idNumber":"","avatar":null,"status":1}

			if (this.$store.state.user.id) {
				uni.switchTab({
					url: "/pages/index/index"
				})
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
							errorMessage: '用户名不能为空'
						}, {
							validateFunction: function(rule, value, data, callback) {
								const reg = /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/
								if (!reg.test(value)) {
									callback("邮箱格式不正确")
								}
								uni.hideLoading()
								return true
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
			}
		},
		onReady() {
			this.$refs.form.setRules(this.rules)
		},

		methods: {

			sendCode() {
				this.$refs.form.validateField('username', (valid, message) => {
					if (!valid && this.codeStatus) {

						sendMsg(this.form.username)
						this.codeStatus = false
					}
				})
			},
			loginBtn() {
				uni.showLoading()
				this.$refs.form.validate().then(async () => {
					const result = await verifyCode(this.form)


					if (result.data.code == 1) {
						this.msgType = "success"
						this.messageText = "登录成功"
						this.$refs.message.open()
						this.$store.dispatch("login", result.data.data)
						new Promise((resolve, reject) => {
							setTimeout(() => {
								resolve()
							}, 2000)
						}).then(() => {

							uni.switchTab({
								url: "/pages/index/index"
							})
						})
					} else {
						this.msgType = "error"
						this.messageText = "验证码错误"
						this.$refs.message.open()
					}
				}).catch(() => {

				})
				uni.hideLoading()
			},
			changeStatus() {
				this.codeStatus = !this.codeStatus
			}



		},

	}
</script>

<style lang="less">
	.container {
		padding: 20px;
		font-size: 14px;
		line-height: 24px;
	}

	.uni-forms-item__content {
		display: flex;

		.uni-easyinput {
			flex: 3;
		}

		.uni-easyinput__content-input {
			height: 80rpx;
		}

		.send-code {
			flex: 1;
		}
	}
</style>
