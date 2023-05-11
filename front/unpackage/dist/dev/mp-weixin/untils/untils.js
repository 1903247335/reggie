"use strict";
const handleUrl = (list) => {
  return list.map((item) => {
    item.image = "https://1903247335-1316551243.cos.ap-guangzhou.myqcloud.com/" + item.image;
    return item;
  });
};
exports.handleUrl = handleUrl;
