// 定义控制器:
app.controller("orController",function($scope,$controller,$http,orService){
	// AngularJS中的继承:伪继承
	$controller('baseController',{$scope:$scope});



//查询一级分类
    $scope.selectItemCat1List = function(){
        orService.findAll().success(
            function(response){
                $scope.list=response;

            }
        );
    }
//查询二级分类列表:
	$scope.$watch("entity.goodsId",function(newValue,oldValue){
        orService.findByParentId(newValue).success(function(response){
			$scope.itemCat2List = response;
		});
	});
/*
// 查询三级分类列表:
    $scope.$watch("entity.goods.category2Id",function(newValue,oldValue){
        itemCatService.findByParentId(newValue).success(function(response){
            $scope.itemCat3List = response;
        });
    });
*/
// 查询模块ID
    $scope.$watch("entity.itemId",function(newValue,oldValue){
        orService.findOne(newValue).success(function(response){
            $scope.entity.stockCount = response.num;
        });
    });
    // 查询模块ID
    $scope.$watch("entity.itemId",function(newValue,oldValue){
        orService.findOne(newValue).success(function(response){
            $scope.entity.smallPic = response.image;
        });
    });
// 查询模块ID
    $scope.$watch("entity.itemId",function(newValue,oldValue){
        orService.findOne(newValue).success(function(response){
            $scope.entity.price = response.price;
        });
    });
    //保存
    $scope.save=function(){
       /* // 再添加之前，获得富文本编辑器中的内容。
        $scope.entity.goodsDesc.introduction=editor.html();*/
        var serviceObject;//服务层对象
        if($scope.entity.id!=null){//如果有ID
            serviceObject=orService.update( $scope.entity ); //修改
        }else{
            serviceObject=orService.add( $scope.entity  );//增加
        }
        serviceObject.success(
            function(response){
                if(response.success){
                    //重新查询
                    alert(response.message);
                    location.href="index.html";
                }else{
                    alert(response.message);
                }
            }
        );
    }
	$scope.searchEntity={};
	
	// 假设定义一个查询的实体：searchEntity
    $scope.search=function(){
        orService.search().success(
            function(response){
                $scope.list=response;

            }
        );
    }
	
});
