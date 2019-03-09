 //控制层 
app.controller('seckillController' ,function($scope,$controller,seckillService){
	
	// $controller('baseController',{$scope:$scope});//继承
	
    //读取列表数据绑定到表单中  
	$scope.findAll=function(){
		seckillService.findAll().success(
			function(response){
				$scope.list=response;
			}			
		);
	}    
	
	/*//分页
	$scope.findPage=function(page,rows){			
		seckillService.findPage(page,rows).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
	
	//查询实体 
	$scope.findOne=function(id){				
		seckillService.findOne(id).success(
			function(response){
				$scope.entity= response;					
			}
		);				
	}*/

    
});	
