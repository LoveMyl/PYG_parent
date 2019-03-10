//购物车服务层
app.service('cartService',function($http){
	//购物车列表
	this.findCartList=function(){
		return $http.get('cart/findCartList.do');
	}

    //设置默认地址信息
	this.updateDefaultStatus = function(id) {
		alert(id);
        return $http.post('../address/updateDefaultStatus.do?id='+id)
    }


    //添加用户地址信息
    this.add=function(entity){
        return  $http.post('../address/addUserAddress.do',entity );
    }


    //修改用户地址信息
	this.update=function (entity) {
		return $http.post('../address/updateAddress.do',entity);
    }

    /*上传头像功能*/
    // $scope.entity={goods:{},goodsDesc:{},itemList:[]}

    $scope.uploadFile = function(){
        // 调用uploadService的方法完成文件的上传
        uploadService.uploadFile().success(function(response){
            if(response.success){
                // 获得url
                $scope.image_entity.url =  response.message;
            }else{
                alert(response.message);
            }
        });
    }

    // 获得了image_entity的实体的数据{"color":"褐色","url":"http://192.168.209.132/group1/M00/00/00/wKjRhFn1bH2AZAatAACXQA462ec665.jpg"}
    $scope.entity={goods:{},goodsDesc:{itemImages:[],specificationItems:[]}};

    $scope.add_image_entity = function(){
        $scope.entity.itemImages.push($scope.image_entity);
    }

    $scope.remove_iamge_entity = function(index){
        $scope.entity.goodsDesc.itemImages.splice(index,1);
    }



    //查询当前用户地址
	this.findOne = function(id) {
		return $http.post('../address/findOne.do?id='+id)
	}

    //删除用户地址
	this.dele = function (id) {
		return $http.post('../address/dele.do?id='+id);
    }

	//添加商品到购物车
	this.addGoodsToCartList=function(itemId,num){
		return $http.get('cart/addGoodsToCartList.do?itemId='+itemId+'&num='+num);
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
		return $http.get('../address/findListByLoginUser.do');
	}
	
	//提交订单
	this.submitOrder=function(order){
		return $http.post('../order/add.do',order);
	}
	
	
});