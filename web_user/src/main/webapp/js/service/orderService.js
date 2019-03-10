//服务层
app.service('orderService',function($http) {

    this.findOrderList = function () {
        return $http.get('order/getOrderList.do');
    }
});