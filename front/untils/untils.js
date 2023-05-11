export const handleUrl = (list) => {

	return list.map((item) => {
		item.image = 'https://1903247335-1316551243.cos.ap-guangzhou.myqcloud.com/' + item.image
		return item
	})
}

export const handleListFlavor = (list) => {
	//[['啦'，不辣],[]]
	return list.map((item) => {
		return item.map((value, index) => {
			return {
				id: index,
				value: value,
				inverted: false
			}
		})
	})

}

export const handleUpdateFlavor = (list, id) => {
	return list((item) => {
		if (item.id == id) {
			item.inverted != item.inverted
		}
		return item
	})
}
