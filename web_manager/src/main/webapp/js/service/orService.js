// 定义服务层:
app.service("orService",function($http){
	this.findAll = function(){
		return $http.get("../brand/findAll.do");
}
	
	this.findByPage = function(page,rows){
		return $http.get("../brand/findByPage.do?page="+page+"&rows="+rows);
	}
	
	this.save = function(entity){
		return $http.post("../brand/add.do",entity);
	}
	
	this.update=function(entity){
		return $http.post("../brand/update.do",entity);
	}
	
	this.findById=function(id){
		return $http.get("../brand/findOne.do?id="+id);
	}
	
	this.dele = function(ids){
		return $http.get("../brand/delete.do?ids="+ids);
	}
    this.search=function(){
        return $http.post('../kk/search.do');
    }

	
	this.selectOptionList = function(){
		return $http.get("../brand/selectOptionList.do");
	}
});