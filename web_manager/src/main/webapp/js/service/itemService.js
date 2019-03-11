//服务层
app.service('itemService',function($http){

	this.findOne=function(id){
		return $http.get('../item/findOne.do?id='+id);
	}
	//增加 
	this.add=function(entity){
		return  $http.post('../item/add.do',entity );
	}
	//修改 
	this.update=function(entity){
		return  $http.post('../item/update.do',entity );
	}

	this.search=function(page,rows,searchEntity){
		return $http.post('../item/search.do?page='+page+"&rows="+rows, searchEntity);
	}    	
});
