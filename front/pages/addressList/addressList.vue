<template>
	<view>
		<uni-list>
			<uni-list-item showArrow v-for="(item,index) in address" :title="item.consignee"
				:note="item.provinceName+item.cityName+item.districtName+item.detail" :key="index"
				@click="chooseAddress(item.id)" clickable="true">


			</uni-list-item>
		</uni-list>
		<button type="default" @tap="addAddressBtn">增加新地址</button>

	</view>
</template>

<script>
	import {
		sendGetAddAddress
	} from '../../api'
	export default {
		data() {
			return {
				userId: this.$store.state.user.id,
				address: []
			}
		},

		methods: {
			chooseAddress(id) {

				this.$store.commit("setAddressBookId", id)

				uni.navigateBack()
			},
			async getAddress() {
				const result = await sendGetAddAddress(this.userId)
				this.address = result.data.data
			},
			addAddressBtn() {
				uni.navigateTo({
					url: "/pages/addAddress/addAddress"
				})
			}
		},

		created() {
			this.getAddress()
		},
	}
</script>

<style>

</style>
