 //控制层 
app.controller('seckillorderController' ,function($scope,$controller,seckillorderService){
	
	$controller('baseController',{$scope:$scope});//继承
	


	 
	//批量删除 
	$scope.dele=function(){			
		//获取选中的复选框			
        orderService.dele( $scope.selectIds ).success(
			function(response){
				if(response.success){
					$scope.reloadList();//刷新列表
					$scope.selectIds = [];
				}						
			}		
		);				
	}
    $scope.status = ["1","未付","已付款","已发货","未发货","交易成功","交易关闭","待评价"]
	$scope.searchEntity={};//定义搜索对象 
	
	//搜索
	$scope.search=function(page,rows){
        seckillorderService.search(page,rows,$scope.searchEntity).success(
			function(response){
				$scope.list=response.rows;
				alert(response.rows.get(0));
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
    

});	
