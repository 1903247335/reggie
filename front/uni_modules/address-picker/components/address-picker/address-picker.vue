<template>
	<view class="">
		<u-picker :show="show" ref="uPicker" :title="title" :showToolbar="showToolbar" :itemHeight="itemHeight"
			:cancelText="cancelText" :cancelColor="cancelColor" :confirmText="confirmText" :confirmColor="confirmColor"
			:loading="loading" :visibleItemCount="visibleItemCount" :defaultIndex="indexs" :columns="columns"
			:closeOnClickOverlay="closeOnClickOverlay" @confirm="confirm" @close="close" @cancel="cancel"
			@change="changeHandler">
		</u-picker>
	</view>
</template>
<script>
	import area from "../../province-city-county.json"
	export default {
		props: {
			show: {
				type: Boolean,
				default: () => false
			},
			title: {
				type: String,
				default: () => ""
			},
			showToolbar: {
				type: Boolean,
				default: () => true
			},
			itemHeight: {
				type: [String, Number],
				default: () => 44
			},
			cancelText: {
				type: String,
				default: () => "取消"
			},
			cancelColor: {
				type: String,
				default: () => "#909193"
			},
			confirmText: {
				type: String,
				default: () => "确认"
			},
			confirmColor: {
				type: String,
				default: () => "#3c9cff"
			},
			visibleItemCount: {
				type: [String, Number],
				default: () => 5
			},
			loading: {
				type: Boolean,
				default: () => false
			},
			indexs: Array,
			areaId: Array,
			closeOnClickOverlay: Boolean,
			addressData: Array,
		},
		data() {
			return {
				columns: [],
				province: [],
				city: [],
				area: [],
			}
		},
		mounted() {
			this.formatData()
		},
		methods: {
			changeHandler(e) {
				const {
					columnIndex,
					value,
					values, // values为当前变化列的数组内容
					indexs,
					picker = this.$refs.uPicker
				} = e
				if (columnIndex === 0) {
					picker.setColumnValues(1, this.city[indexs[0]].map(v => v.name))
					picker.setColumnValues(2, this.area[indexs[0]][0].map(v => v.name))
				}
				if (columnIndex === 1) {
					picker.setColumnValues(2, this.area[indexs[0]][indexs[1]].map(v => v.name))
				}
			},
			formatData() {
				this.province = area.map(t => {
					return {
						name: t.name,
						areaId: t.areaId
					}
				})
				this.city = area.map(t => t.children.map(v => {
					return {
						name: v.name,
						areaId: v.areaId
					}
				}))
				this.area = area.map(t => t.children.map(v => v.children.map(i => {
					return {
						name: i.name,
						areaId: i.areaId
					}
				})))
				//默认显示数据
				this.columns = [
					this.province.map(res => res.name),
					this.city[0].map(res => res.name),
					this.area[0][0].map(res => res.name)
				]
				//数据回显
				if (this.addressData === 3) {
					//省索引
					let pIdx = this.province.findIndex(v => v.name == this.addressData[0]);
					//根据省索引设置默认市数据
					this.columns[1] = this.city[pIdx].map(res => res.name)
					//市索引
					let cIdx = this.city[pIdx].findIndex(v => v.name == this.addressData[1]);
					//根据市索引设置默认区数据
					this.columns[2] = this.area[pIdx][cIdx].map(res => res.name)
					//区索引
					let aIdx = this.area[pIdx][cIdx].findIndex(v => v.name == this.addressData[2]);
					this.indexs = [pIdx, cIdx, aIdx];
				} else if (this.indexs.length === 3) {
					this.columns[1] = this.city[this.indexs[0]].map(res => res.name)
					this.columns[2] = this.area[this.indexs[0]][this.indexs[1]].map(res => res.name)
				} else if (this.areaId === 3) {
					console.log(this.areaId);
					//省索引
					let pIdx = this.province.findIndex(v => v.areaId === this.areaId[0]);
					//根据省索引设置默认市数据
					this.columns[1] = this.city[pIdx].map(res => res.name)
					//市索引
					let cIdx = this.city[pIdx].findIndex(v => v.areaId == this.areaId[1]);
					//根据市索引设置默认区数据
					this.columns[2] = this.area[pIdx][cIdx].map(res => res.name)
					//区索引
					let aIdx = this.area[pIdx][cIdx].findIndex(v => v.areaId == this.areaId[2]);
					this.indexs = [pIdx, cIdx, aIdx];
				}
			},
			confirm(e) {
				let provinceId = area[e.indexs[0]].areaId;
				let cityId = area[e.indexs[0]].children[e.indexs[1]].areaId;
				let districtId = area[e.indexs[0]].children[e.indexs[1]].children[e.indexs[2]].areaId;
				e.areaId = [provinceId, cityId, districtId]
				this.$emit("confirm", e)
			},
			close() {
				this.$emit("close")
			},
			open() {
				this.$emit("open")
				this.formatData()
			},
			cancel() {
				this.$emit("cancel")
			}
		}
	}
</script>
<style>
</style>