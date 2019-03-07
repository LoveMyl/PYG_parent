app.service("contentService",function($http){
		//根据paraent_id查询分类集合列表
    this.findByCategoryParentId = function(categoryId){
        return $http.get("itemCat/findByCategoryParentId.do?parentId="+categoryId);
    }

});