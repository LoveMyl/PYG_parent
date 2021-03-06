//购物车服务层
app.service('collectService',function($http){
	//购物车列表
    this.findCartList=function(){
        return $http.get('cart/findCartList.do');
    }
    //查询收藏列表
    this.findCollectList=function(){
        return $http.get('collect/findCollectList.do');
    }

    //添加收藏列表
	this.addItemToCollectList=function (itemId) {
		return $http.get('collect/addItemToCollectList.do?itemId='+itemId);
    }

	//求合计数
	this.sum=function(cartList){
		var totalValue={totalNum:0,totalMoney:0 };
			
		for(var i=0;i<cartList.length ;i++){
			var cart=cartList[i];//购物车对象
			for(var j=0;j<cart.orderItemList.length;j++){
				var orderItem=  cart.orderItemList[j];//购物车明细
				totalValue.totalNum+=orderItem.num;//累加数量
				totalValue.totalMoney+=orderItem.totalFee;//累加金额				
			}			
		}
		return totalValue;
		
	}
	
	//获取当前登录账号的收货地址
	this.findAddressList=function(){
		return $http.get('address/findListByLoginUser.do');
	}
	
	//提交订单
	this.submitOrder=function(order){
		return $http.post('order/add.do',order);		
	}

	//删除
    this.dele = function(itemId){
    	return $http.get('cart/deleCartListByItemId.do?itemId='+itemId);

	}
	
});