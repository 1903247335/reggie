<template>
	<view class="contain">
		<text v-if="status==true">获取验证码</text>
		<text v-else>{{count}}s</text>
	</view>
</template>

<script>
	export default {
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
					this.count = 60
					let timer = setInterval(() => {
						if (this.count != 0) {
							this.count = this.count - 1
						} else {
							clearInterval(timer)
							this.$emit("changeStatusEvent")
						}
					}, 1000)
				}
			}
		},
		created() {
			console.log(this.status)
		}
	}
</script>

<style lang="less" scoped>
	.contain {
		background: #1aad19;
		height: 80rpx;
		font-size: 12px;
		line-height: 80rpx;
		text-align: center;

		text {
			color: white;
		}
	}
</style>
