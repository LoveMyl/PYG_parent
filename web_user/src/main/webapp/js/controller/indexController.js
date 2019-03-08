//首页控制器
<<<<<<< Updated upstream
app.controller('indexController',function($scope,$controller,loginService,orderService){

    // AngularJS中的继承:伪继承
    $controller('baseController',{$scope:$scope});

    //订单支付状态
    $scope.status=["未付款","未付款","已付款","等待发货","已发货","交易成功","交易关闭","待评价"]
    //退款/退货
    $scope.out=["","","退款/退货","退款/退货","退款/退货","退款/退货"]
    //支付按钮提示
    $scope.payStatus=["立即付款","立即付款","等待发货","确认收货","确认收货","交易成功","交易成功"]

    $scope.showName=function(){
		loginService.showName().success(
				function(response){
					$scope.loginName=response.loginName;
				}
		);
	}
    //查询订单集合
    $scope.findOrderList=function () {
        orderService.findOrderList().success(
            function (response) {
                $scope.orderList=response;
            }
        )
    }

   // 分页查询
    $scope.search = function(page,rows){
        // 向后台发送请求获取数据:
        orderService.search(page,rows).success(function(response){
            $scope.paginationConf.totalItems = response.total;
            $scope.list = response.rows;
        });
    }

    //跳转到静态页面
    $scope.openCartPage = function(){
        window.open("http://localhost:8080/cart.html");
    }

=======
app.controller('indexController',function($scope,loginService){
	$scope.showName=function(){
        loginService.showName().success(
            function(response){
                $scope.loginName=response.loginName;
            }
        );
    }
    $scope.findAllCollect=function(){
        loginService.findAllCollect().success(
            function(response){
                $scope.items = response;
            }
        );
    }
>>>>>>> Stashed changes
});