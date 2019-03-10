 //控制层 
app.controller('seckillController' ,function($location,$scope,$controller,seckillService){
	
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
	}*/

    var uuid = window.location.search.substring(1);
    uuid =uuid.split('=')

    //查询实体
    //var id = $location.search()['id'];
    $scope.findOne=function(){

        seckillService.findOne(uuid).success(
        function(response){
                $scope.entity= response;
			}
		);				
	}



    //跳转到静态页面
    $scope.openDetailPage = function(id){

        window.open("http://localhost:8080/seckill-item.html?id="+id);
    }

    //添加商品到购物车
    $scope.addToCart=function(){
        //alert('SKUID:'+$scope.sku.id );

        seckillService.addToCart($scope.entity.seckillGoods.itemId).success(
         function(response){
                if(response.success){
                	alert("方法运行!");

                    location.href='cart.html';
                }else{
                    alert(response.message);
                }
            }
        );

    }

    
});	
