//服务层
app.service('seckillService',function($http){
	    	
	//读取列表数据绑定到表单中
	this.findAll=function(){
		return $http.get('seckill/findAll.do');
	}
	/*//分页
	this.findPage=function(page,rows){
		return $http.get('seckill/findPage.do?page='+page+'&rows='+rows);
	}
	*/
	//查询实体
	this.findOne=function(id){
		return $http.get('seckill/findOne.do?id='+id);
	}

});
