app.controller('SalebingController' ,function($scope ,$location,SalebingService){
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));
    myChart.setOption({
        title : {
            text: '销售数量统计',
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: []
        },
        series : [
            {
                name: '销售数量',
                type: 'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data:[
                    /* {value:335, name:'电器'},
                     {value:310, name:'电子书'},
                     {value:234, name:'其他'},
                     {value:135, name:'休闲'},
                     {value:1548, name:'服装'}*/
                ],
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    });
    //获取数据
	$scope.getsale=function(){
        // 向后台发送请求:
        SalebingService.getsale().success(function(response){
				// 填入数据
            myChart.setOption({
                legend: {
                    orient: 'vertical',
                    left: 'left',
                    data: [response.list]
                },
                series : [
                    {
                        name: '销售数量',
                        type: 'pie',
                        radius : '55%',
                        center: ['50%', '60%'],
                        data:[],
					}
                ]
            });
            });
	}
	
});