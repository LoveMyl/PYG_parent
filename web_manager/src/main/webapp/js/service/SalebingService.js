app.service('SalebingService',function($http){
	//折线图
	this.getsale=function(){
		return $http.get('Sale/getsale.do');
	}
	
	//饼状图
	this.queryPayStatus=function(out_trade_no){
		return $http.get('Sale/queryPayStatus.do');
	}
});