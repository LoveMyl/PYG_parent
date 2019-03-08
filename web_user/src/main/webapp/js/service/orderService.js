//服务层
app.service('orderService',function($http) {
    //查询订单集合
    this.findOrderList = function () {
        return $http.get('order/getOrderList.do');
    }

    this.search = function(page,rows){
        return $http.get("order/search.do?page="+page+"&rows="+rows);
    }

});