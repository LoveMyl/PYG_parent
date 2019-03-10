//服务层
app.service('userService',function($http){

	//删除
	this.dele=function(ids){
		return $http.get('../user/delete.do?ids='+ids)
	}
	//搜索
	this.search=function(page,rows,searchEntity){
		return $http.post('../user/search.do?page='+page+"&rows="+rows,searchEntity);
	}    	
});
