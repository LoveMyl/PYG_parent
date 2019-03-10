// 定义服务层:
app.service("orService",function($http){
	this.findAll = function(){
		return $http.get("../goods/findAll.do");
	}

   /* this.search=function(){
        return $http.post('../goods/findAll.do');
    }*/
    this.findByParentId=function(goodsid){
        return $http.get("../item/findByGoodsId.do?goodsId="+goodsid);
    }
    this.findOne=function(id){
        return $http.get("../item/findOne.do?id="+id);
    }
    this.add=function(entity){
        return $http.post("../kill/add.do",entity );
    }

});