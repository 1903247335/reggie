import {
	createStore
} from "vuex"

export const store = createStore({
	state: {
		user: JSON.parse(uni.getStorageSync("user") || "{}"),
		addressBookId: ""
	},
	mutations: {
		setStorage(state, playload) {
			uni.setStorageSync("user", JSON.stringify(playload))
		},
		clearStorage(state) {
			uni.removeStorageSync("user")
		},
		setAddressBookId(state, playload) {
			state.addressBookId = playload
		}
	},
	actions: {
		login(context, playload) {

			context.commit("setStorage", playload)

		},
		logout(context) {
			context.commit("clearStorage")
		}
	},

})
