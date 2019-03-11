//服务层
app.service('seckillorderService',function($http){

	//删除
	this.dele=function(ids){
		return $http.get('../seckillorder/delete.do?ids='+ids);
	}
	//搜索
	this.search=function(page,rows,searchEntity){
		return $http.post('../seckillorder/search.do?page='+page+"&rows="+rows,searchEntity);
	}    	
});
