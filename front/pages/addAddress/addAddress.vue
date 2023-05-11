<template>
	<view class="addAddress-contain">
		<uni-forms :modelValue="form">
			<uni-forms-item label="姓名" width="25%" name="consignee">
				<uni-easyinput v-model="form.consignee" placeholder="输入收货人" />
			</uni-forms-item>
			<uni-forms-item label="电话" name="phone">
				<uni-easyinput type="text" v-model="form.phone" placeholder="输入电话号码" />
			</uni-forms-item>
			<uni-forms-item label="性别" name="sex">
				<radio-group @change="radioChange">
					<label>
						<radio value="1" /><text>男</text>
					</label>
					<label>
						<radio value="0" /><text>女</text>
					</label>
				</radio-group>
			</uni-forms-item>
			<uni-forms-item label="地址">

				<uni-data-picker :localdata="items" popup-title="请选择地址" @nodeclick="onnodeclick">
				</uni-data-picker>


			</uni-forms-item>
			<uni-forms-item label="详细地址">
				<textarea :value="form.detail" @input="inputTextarea" placeholder="填入详细地址" />


			</uni-forms-item>
			<button type="primary" @click="addAddressBtn">添加收货地址</button>
		</uni-forms>

	</view>
</template>

<script>
	import {
		sendAddAddress,

	} from '../../api'
	export default {
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
					detail: "",
				},
				items: [{
					text: "广东省",
					value: "广东省",
					children: [{
						text: "广州市",
						value: "广州市",
						children: [{
							text: "天河区",
							value: "天河区",
						}]
					}]
				}, ]

			}
		},
		methods: {
			inputTextarea(value) {
				this.form.detail = value.detail.value
			},
			radioChange(value) {
				this.form.sex = value.detail.value
			},
			onnodeclick(node) {
				if (node.value.indexOf("省") != -1) {
					this.form.provinceName = node.value
				} else if (node.value.indexOf("市") != -1) {
					this.form.cityName = node.value
				} else if (node.value.indexOf("区" != -1)) {
					this.form.districtName = node.value
				}

			},
			async addAddressBtn() {
				const result = await sendAddAddress(this.form)
				uni.navigateBack()
			}
		}
	}
</script>

<style lang="less">
	.addAddress-contain {
		padding: 20px;
		background-color: white
	}
</style>
