// file是指上传的图片，obj是压缩的品质，越低越模糊
export function imgCompress(file, obj) {
  // 这里的this是把vue的实例对象指向改变为_this
  var img = new Image()
  img.src = URL.createObjectURL(file)
  img.onload = function() {
    // 这里的this是把img的对象指向改变为that
    var that = this
    // 默认按比例压缩
    var w = that.width
    var h = that.height
    var scale = w / h
    w = file.width || w
    h = file.height || (w / scale)
    // 默认图片质量为0.7
    var quality = 0.7
    // 生成canvas
    var canvas = document.createElement('canvas')
    var ctx = canvas.getContext('2d')
    // 创建属性节点
    var anw = document.createAttribute('width')
    anw.nodeValue = w
    var anh = document.createAttribute('height')
    anh.nodeValue = h
    canvas.setAttributeNode(anw)
    canvas.setAttributeNode(anh)
    ctx.drawImage(that, 0, 0, w, h)
    // 图像质量
    if (obj.quality && obj.quality <= 1 && obj.quality > 0) {
      quality = obj.quality
    }
    // quality值越小，所绘制出的图像越模糊
    var base64 = canvas.toDataURL(file.type, quality)
    // 回调函数返回base64的值
    var urlFile = convertBase64UrlToBlob(base64)
    // 这个地方的处理是为了把压缩的base64转化为对象，获得压缩后图片的大小size，方便对压缩后的图片再次进行判断；
    // 允许的最大图片大小为2M
    if (urlFile.size / 2048 > 1024) {
      urlFile = imgCompress(urlFile, { quality: 0.5 })
    }
    console.log(urlFile)
    return urlFile
  }
}

// 将base64码转化为file（Blob）
// 此处函数对压缩后的base64经过处理返回{size:'',type:''}
export function convertBase64UrlToBlob(urlData) {
  var arr = urlData.split(',')
  var mime = arr[0].substr(arr[0].indexOf(':') + 1, arr[0].indexOf(';') - 5).toLowerCase()
  var bstr = atob(arr[1])
  var n = bstr.length
  var u8arr = new Uint8Array(n)
  while (n--) {
    u8arr[n] = bstr.charCodeAt(n)
  }
  return new Blob([u8arr], { type: mime })
}

