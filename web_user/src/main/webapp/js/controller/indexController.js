//首页控制器
app.controller('indexController',function($scope,loginService,orderService){
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

});