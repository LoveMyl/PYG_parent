app.controller("contentController",function($scope,contentService){
   /* $scope.kkk = "";
    $scope.kkkd = $scope.kkk;*/
    $scope.contentList = [];
	//根据一级分类查询分类集合
    $scope.findByCategoryId = function(categoryId){
        contentService.findByCategoryId(categoryId).success(function(response){
            $scope.contentList[categoryId] = response;
        });}

    $scope.findByCategoryParentId = function(categoryId){
        contentService.findByCategoryParentId(categoryId).success(function(response){
            $scope.categoryList= response;
        });
    }

	//搜索,跳转到portal系统查询列表页面(传递参数）
	$scope.search=function(){
		location.href="http://localhost:8080/search.html#?keywords="+$scope.keywords;
	}

    //跳转到静态页面
    $scope.openDetailPage = function(){
        //alert(goodsId);
        window.open("http://localhost:8083/home-index.html");
    }
	
});