var cols;
var tableid;
var type;
function init(){
	type = $("#calculationType").val();
	initTableCols(type);
	
	layui.use('table', function(){
		  tableid = layui.table.render({
		    elem: '#indexCalculationTable'
		    ,url:'busi/indexCalculation/indexList'
		    ,method:'post'
		    ,cols: cols
		    //,page: true
		    ,skin: 'row'
		    ,size: 'sm'
		    ,even: true
		    ,height : 'full-150'
		  });
	});
	
	//设置报表名称
	var year = new Date().getFullYear();
	var testSpan = "电科院" + year + "年内模指标测算表";
	$("#indexTabelSpan").text(testSpan)
	
	
	//查询
	$('#search').on('click', function(){
		type = $("#calculationType").val();
		initTableCols(type);
		tableid.reload({
			cols:cols
	        ,where: {
	        	type  : type
	        }
	      });
	  });
	
}


function initTableCols(type){
	var month = new Date().getMonth();
	var year = new Date().getFullYear();
	var season1 = year+"年一季度";
	var season2 = year+"年二季度";
	var season3 = year+"年三季度";
	var season4 = year+"年四季度";
	var oldYear = year-1;
	var lastsSeason1 = oldYear + "年一季度";
	var lastsSeason2 = oldYear + "年二季度";
	var lastsSeason3 = oldYear + "年三季度";
	var lastsSeason4 = oldYear + "年四季度";
	var yearAll = year+"年全年";
	var oldYearAll = oldYear+"年全年";
	var index = year+"年下达";
	
	var remonth = month+ 1;
	
	var month1 = year+"年一月";
	var month2 = year+"年二月";
	var month3 = year+"年三月";
	var month4 = year+"年四月";
	var month5 = year+"年五月";
	var month6 = year+"年六月";
	var month7 = year+"年七月";
	var month8 = year+"年八月";
	var month9 = year+"年九月";
	var month10 = year+"年十月";
	var month11= year+"年十一月";
	var month12 = year+"年十二月";
	
	var oldYear = year-1;
	var oldMonth1 = oldYear+"年一月";
	var oldMonth2 = oldYear+"年二月";
	var oldMonth3 = oldYear+"年三月";
	var oldMonth4 = oldYear+"年四月";
	var oldMonth5 = oldYear+"年五月";
	var oldMonth6 = oldYear+"年六月";
	var oldMonth7 = oldYear+"年七月";
	var oldMonth8 = oldYear+"年八月";
	var oldMonth9 = oldYear+"年九月";
	var oldMonth10 = oldYear+"年十月";
	var oldMonth11= oldYear+"年十一月";
	var oldMonth12 = oldYear+"年十二月";
	
	
	
	if(type == "month"){
		if(remonth == 1){
			cols = [[
			     {field: 'subject', title: '' }
			    ,{field: 'lastsSeason1', title: lastsSeason1}
			    ,{field: 'season1', title: season1}
			    ,{field: 'oldYearAll', title: oldYearAll,width:'9%'}
			    ,{field: 'index', title: index,width:'9%'}
			    ,{field: 'yearAll', title: yearAll,width:'9%'}
			 ]]
		} else if(remonth == 2){
			cols = [[
				{field: 'subject', title: '',width:'11%'}
			    ,{field: 'oldMonth1', title: oldMonth1,width:'9%'}
			    ,{field: 'month1', title: month1,width:'9%'}
			    ,{field: 'oldMonth2', title: oldMonth2,width:'9%'}
			    ,{field: 'month2', title: month2,width:'9%'}
			    ,{field: 'oldYearAll', title: oldYearAll,width:'9%'}
			    ,{field: 'index', title: index,width:'9%'}
			    ,{field: 'yearAll', title: yearAll,width:'9%'}
				 ]]
		} else if(remonth == 3){
			cols = [[
				{field: 'subject', title: '',width:'11%'}
			    ,{field: 'oldMonth1', title: oldMonth1,width:'9%'}
			    ,{field: 'month1', title: month1,width:'9%'}
			    ,{field: 'oldMonth2', title: oldMonth2,width:'9%'}
			    ,{field: 'month2', title: month2,width:'9%'}
			    ,{field: 'oldMonth3', title: oldMonth3,width:'9%'}
			    ,{field: 'month3', title: month3,width:'9%'}
			    ,{field: 'oldYearAll', title: oldYearAll,width:'9%'}
			    ,{field: 'index', title: index,width:'9%'}
			    ,{field: 'yearAll', title: yearAll,width:'9%'}
				 ]]
		} else if(remonth == 4){
			cols = [[
				{field: 'subject', title: '',width:'11%'}
			    ,{field: 'oldMonth1', title: oldMonth1,width:'9%'}
			    ,{field: 'month1', title: month1,width:'9%'}
			    ,{field: 'oldMonth2', title: oldMonth2,width:'9%'}
			    ,{field: 'month2', title: month2,width:'9%'}
			    ,{field: 'oldMonth3', title: oldMonth3,width:'9%'}
			    ,{field: 'month3', title: month3,width:'9%'}
			    ,{field: 'oldMonth4', title: oldMonth4,width:'9%'}
			    ,{field: 'month4', title: month4,width:'9%'}
			    ,{field: 'oldYearAll', title: oldYearAll,width:'9%'}
			    ,{field: 'index', title: index,width:'9%'}
			    ,{field: 'yearAll', title: yearAll,width:'9%'}
			]]
		} else if(remonth == 5){
			cols = [[
				{field: 'subject', title: '',width:'11%'}
			    ,{field: 'oldMonth1', title: oldMonth1,width:'9%'}
			    ,{field: 'month1', title: month1,width:'9%'}
			    ,{field: 'oldMonth2', title: oldMonth2,width:'9%'}
			    ,{field: 'month2', title: month2,width:'9%'}
			    ,{field: 'oldMonth3', title: oldMonth3,width:'9%'}
			    ,{field: 'month3', title: month3,width:'9%'}
			    ,{field: 'oldMonth4', title: oldMonth4,width:'9%'}
			    ,{field: 'month4', title: month4,width:'9%'}
			    ,{field: 'oldMonth5', title: oldMonth5,width:'9%'}
			    ,{field: 'month5', title: month5,width:'9%'}
			    ,{field: 'oldYearAll', title: oldYearAll,width:'9%'}
			    ,{field: 'index', title: index,width:'9%'}
			    ,{field: 'yearAll', title: yearAll,width:'9%'}
				 ]]
		} else if(remonth == 6){
			cols = [[
				{field: 'subject', title: '',width:'11%'}
			    ,{field: 'oldMonth1', title: oldMonth1,width:'9%'}
			    ,{field: 'month1', title: month1,width:'9%'}
			    ,{field: 'oldMonth2', title: oldMonth2,width:'9%'}
			    ,{field: 'month2', title: month2,width:'9%'}
			    ,{field: 'oldMonth3', title: oldMonth3,width:'9%'}
			    ,{field: 'month3', title: month3,width:'9%'}
			    ,{field: 'oldMonth4', title: oldMonth4,width:'9%'}
			    ,{field: 'month4', title: month4,width:'9%'}
			    ,{field: 'oldMonth5', title: oldMonth5,width:'9%'}
			    ,{field: 'month5', title: month5,width:'9%'}
			    ,{field: 'oldMonth6', title: oldMonth6,width:'9%'}
			    ,{field: 'month6', title: month6,width:'9%'}
			    ,{field: 'oldYearAll', title: oldYearAll,width:'9%'}
			    ,{field: 'index', title: index,width:'9%'}
			    ,{field: 'yearAll', title: yearAll,width:'9%'}
			]]
		} else if(remonth == 7){
			cols = [[
				{field: 'subject', title: '',width:'11%'}
			    ,{field: 'oldMonth1', title: oldMonth1,width:'9%'}
			    ,{field: 'month1', title: month1,width:'9%'}
			    ,{field: 'oldMonth2', title: oldMonth2,width:'9%'}
			    ,{field: 'month2', title: month2,width:'9%'}
			    ,{field: 'oldMonth3', title: oldMonth3,width:'9%'}
			    ,{field: 'month3', title: month3,width:'9%'}
			    ,{field: 'oldMonth4', title: oldMonth4,width:'9%'}
			    ,{field: 'month4', title: month4,width:'9%'}
			    ,{field: 'oldMonth5', title: oldMonth5,width:'9%'}
			    ,{field: 'month5', title: month5,width:'9%'}
			    ,{field: 'oldMonth6', title: oldMonth6,width:'9%'}
			    ,{field: 'month6', title: month6,width:'9%'}
			    ,{field: 'oldMonth7', title: oldMonth7,width:'9%'}
			    ,{field: 'month7', title: oldMonth7,width:'9%'}
			    ,{field: 'oldYearAll', title: oldYearAll,width:'9%'}
			    ,{field: 'index', title: index,width:'9%'}
			    ,{field: 'yearAll', title: yearAll,width:'9%'}
				 ]]
		} else if(remonth == 8){
			cols = [[
				{field: 'subject', title: '',width:'11%'}
			    ,{field: 'oldMonth1', title: oldMonth1,width:'9%'}
			    ,{field: 'month1', title: month1,width:'9%'}
			    ,{field: 'oldMonth2', title: oldMonth2,width:'9%'}
			    ,{field: 'month2', title: month2,width:'9%'}
			    ,{field: 'oldMonth3', title: oldMonth3,width:'9%'}
			    ,{field: 'month3', title: month3,width:'9%'}
			    ,{field: 'oldMonth4', title: oldMonth4,width:'9%'}
			    ,{field: 'month4', title: month4,width:'9%'}
			    ,{field: 'oldMonth5', title: oldMonth5,width:'9%'}
			    ,{field: 'month5', title: month5,width:'9%'}
			    ,{field: 'oldMonth6', title: oldMonth6,width:'9%'}
			    ,{field: 'month6', title: month6,width:'9%'}
			    ,{field: 'oldMonth7', title: oldMonth7,width:'9%'}
			    ,{field: 'month7', title: oldMonth7,width:'9%'}
			    ,{field: 'oldMonth8', title: oldMonth8,width:'9%'}
			    ,{field: 'month8', title: month8,width:'9%'}
			    ,{field: 'oldYearAll', title: oldYearAll,width:'9%'}
			    ,{field: 'index', title: index,width:'9%'}
			    ,{field: 'yearAll', title: yearAll,width:'9%'}
				 ]]
		} else if(remonth == 9){
			cols = [[
				{field: 'subject', title: '',width:'11%'}
			    ,{field: 'oldMonth1', title: oldMonth1,width:'9%'}
			    ,{field: 'month1', title: month1,width:'9%'}
			    ,{field: 'oldMonth2', title: oldMonth2,width:'9%'}
			    ,{field: 'month2', title: month2,width:'9%'}
			    ,{field: 'oldMonth3', title: oldMonth3,width:'9%'}
			    ,{field: 'month3', title: month3,width:'9%'}
			    ,{field: 'oldMonth4', title: oldMonth4,width:'9%'}
			    ,{field: 'month4', title: month4,width:'9%'}
			    ,{field: 'oldMonth5', title: oldMonth5,width:'9%'}
			    ,{field: 'month5', title: month5,width:'9%'}
			    ,{field: 'oldMonth6', title: oldMonth6,width:'9%'}
			    ,{field: 'month6', title: month6,width:'9%'}
			    ,{field: 'oldMonth7', title: oldMonth7,width:'9%'}
			    ,{field: 'month7', title: oldMonth7,width:'9%'}
			    ,{field: 'oldMonth8', title: oldMonth8,width:'9%'}
			    ,{field: 'month8', title: month8,width:'9%'}
			    ,{field: 'oldMonth9', title: oldMonth9,width:'9%'}
			    ,{field: 'month9', title: month9,width:'9%'}
			    ,{field: 'oldYearAll', title: oldYearAll,width:'9%'}
			    ,{field: 'index', title: index,width:'9%'}
			    ,{field: 'yearAll', title: yearAll,width:'9%'}
				 ]]
		} else if(remonth == 10){
			cols = [[
				{field: 'subject', title: '',width:'11%'}
			    ,{field: 'oldMonth1', title: oldMonth1,width:'9%'}
			    ,{field: 'month1', title: month1,width:'9%'}
			    ,{field: 'oldMonth2', title: oldMonth2,width:'9%'}
			    ,{field: 'month2', title: month2,width:'9%'}
			    ,{field: 'oldMonth3', title: oldMonth3,width:'9%'}
			    ,{field: 'month3', title: month3,width:'9%'}
			    ,{field: 'oldMonth4', title: oldMonth4,width:'9%'}
			    ,{field: 'month4', title: month4,width:'9%'}
			    ,{field: 'oldMonth5', title: oldMonth5,width:'9%'}
			    ,{field: 'month5', title: month5,width:'9%'}
			    ,{field: 'oldMonth6', title: oldMonth6,width:'9%'}
			    ,{field: 'month6', title: month6,width:'9%'}
			    ,{field: 'oldMonth7', title: oldMonth7,width:'9%'}
			    ,{field: 'month7', title: oldMonth7,width:'9%'}
			    ,{field: 'oldMonth8', title: oldMonth8,width:'9%'}
			    ,{field: 'month8', title: month8,width:'9%'}
			    ,{field: 'oldMonth9', title: oldMonth9,width:'9%'}
			    ,{field: 'month9', title: month9,width:'9%'}
			    ,{field: 'oldMonth10', title: oldMonth10,width:'9%'}
			    ,{field: 'month10', title: month10,width:'9%'}
			    ,{field: 'oldYearAll', title: oldYearAll,width:'9%'}
			    ,{field: 'index', title: index,width:'9%'}
			    ,{field: 'yearAll', title: yearAll,width:'9%'}
				 ]]
		} else if(remonth == 11){
			cols = [[
				  {field: 'subject', title: '',width:'11%'}
				    ,{field: 'oldMonth1', title: oldMonth1,width:'9%'}
				    ,{field: 'month1', title: month1,width:'9%'}
				    ,{field: 'oldMonth2', title: oldMonth2,width:'9%'}
				    ,{field: 'month2', title: month2,width:'9%'}
				    ,{field: 'oldMonth3', title: oldMonth3,width:'9%'}
				    ,{field: 'month3', title: month3,width:'9%'}
				    ,{field: 'oldMonth4', title: oldMonth4,width:'9%'}
				    ,{field: 'month4', title: month4,width:'9%'}
				    ,{field: 'oldMonth5', title: oldMonth5,width:'9%'}
				    ,{field: 'month5', title: month5,width:'9%'}
				    ,{field: 'oldMonth6', title: oldMonth6,width:'9%'}
				    ,{field: 'month6', title: month6,width:'9%'}
				    ,{field: 'oldMonth7', title: oldMonth7,width:'9%'}
				    ,{field: 'month7', title: oldMonth7,width:'9%'}
				    ,{field: 'oldMonth8', title: oldMonth8,width:'9%'}
				    ,{field: 'month8', title: month8,width:'9%'}
				    ,{field: 'oldMonth9', title: oldMonth9,width:'9%'}
				    ,{field: 'month9', title: month9,width:'9%'}
				    ,{field: 'oldMonth10', title: oldMonth10,width:'9%'}
				    ,{field: 'month10', title: month10,width:'9%'}
				    ,{field: 'oldMonth11', title: oldMonth11,width:'9%'}
				    ,{field: 'month11', title: month11,width:'9%'}
			    ,{field: 'oldYearAll', title: oldYearAll,width:'9%'}
			    ,{field: 'index', title: index,width:'9%'}
			    ,{field: 'yearAll', title: yearAll,width:'9%'}
				 ]]
		} else if(remonth == 12){
			cols = [[
			     {field: 'subject', title: '',width:'11%'}
			    ,{field: 'oldMonth1', title: oldMonth1,width:'9%'}
			    ,{field: 'month1', title: month1,width:'9%'}
			    ,{field: 'oldMonth2', title: oldMonth2,width:'9%'}
			    ,{field: 'month2', title: month2,width:'9%'}
			    ,{field: 'oldMonth3', title: oldMonth3,width:'9%'}
			    ,{field: 'month3', title: month3,width:'9%'}
			    ,{field: 'oldMonth4', title: oldMonth4,width:'9%'}
			    ,{field: 'month4', title: month4,width:'9%'}
			    ,{field: 'oldMonth5', title: oldMonth5,width:'9%'}
			    ,{field: 'month5', title: month5,width:'9%'}
			    ,{field: 'oldMonth6', title: oldMonth6,width:'9%'}
			    ,{field: 'month6', title: month6,width:'9%'}
			    ,{field: 'oldMonth7', title: oldMonth7,width:'9%'}
			    ,{field: 'month7', title: oldMonth7,width:'9%'}
			    ,{field: 'oldMonth8', title: oldMonth8,width:'9%'}
			    ,{field: 'month8', title: month8,width:'9%'}
			    ,{field: 'oldMonth9', title: oldMonth9,width:'9%'}
			    ,{field: 'month9', title: month9,width:'9%'}
			    ,{field: 'oldMonth10', title: oldMonth10,width:'9%'}
			    ,{field: 'month10', title: month10,width:'9%'}
			    ,{field: 'oldMonth11', title: oldMonth11,width:'9%'}
			    ,{field: 'month11', title: month11,width:'9%'}
			    ,{field: 'oldMonth12', title: oldMonth12,width:'9%'}
			    ,{field: 'month12', title: month12,width:'9%'}
			    ,{field: 'oldYearAll', title: oldYearAll,width:'9%'}
			    ,{field: 'index', title: index,width:'9%'}
			    ,{field: 'yearAll', title: yearAll,width:'9%'}
				 ]]
		}
		
	} else {
		//第一季度
		if(month == 0 || month == 1 || month == 2){
			cols = [[
			     {field: 'subject', title: '',width:'11%'}
			     ,{field: 'lastsSeason1', title: lastsSeason1}
			    ,{field: 'season1', title: season1}
			    ,{field: 'oldYearAll', title: oldYearAll,width:'9%'}
			    ,{field: 'index', title: index,width:'9%'}
			    ,{field: 'yearAll', title: yearAll,width:'9%'}
				 ]]
		} else if(month == 3 || month == 4 || month == 5){
			cols = [[
			     {field: 'subject', title: '',width:'11%'}
			     ,{field: 'season1', title: season1}
			    ,{field: 'lastsSeason1', title: lastsSeason1}
			    ,{field: 'season2', title: season2}
			    ,{field: 'lastsSeason2', title: lastsSeason2}
			    ,{field: 'oldYearAll', title: oldYearAll,width:'9%'}
			    ,{field: 'index', title: index,width:'9%'}
			    ,{field: 'yearAll', title: yearAll,width:'9%'}
				 ]]
		} else if(month == 6 || month == 7 || month == 8){
			cols = [[
			     {field: 'subject', title: '',width:'11%'}
			    ,{field: 'lastsSeason1', title: lastsSeason1}
			    ,{field: 'season1', title: season1}
			    ,{field: 'lastsSeason2', title: lastsSeason2}
			    ,{field: 'season2', title: season2}
			    ,{field: 'lastsSeason3', title: lastsSeason3}
			    ,{field: 'season3', title: season3}
			    ,{field: 'oldYearAll', title: oldYearAll,width:'9%'}
			    ,{field: 'index', title: index,width:'9%'}
			    ,{field: 'yearAll', title: yearAll,width:'9%'}
				 ]]
		} else if(month == 9 || month == 10 || month == 11){
			cols = [[
			     {field: 'subject', title: '',width:'11%'}
			     ,{field: 'lastsSeason1', title: lastsSeason1}
			    ,{field: 'season1', title: season1}
			    ,{field: 'lastsSeason2', title: lastsSeason2}
			    ,{field: 'season2', title: season2}
			    ,{field: 'lastsSeason3', title: lastsSeason3}
			    ,{field: 'season3', title: season3}
			    ,{field: 'lastsSeason4', title: lastsSeason4}
			    ,{field: 'season4', title: season4}
			    ,{field: 'oldYearAll', title: oldYearAll,width:'9%'}
			    ,{field: 'index', title: index,width:'9%'}
			    ,{field: 'yearAll', title: yearAll,width:'9%'}
				 ]]
		}
	}
	
	
	$("#export").click(function () {
		type = $("#calculationType").val();
        $.ajax({
             type: "POST",
             url:"busi/indexCalculation/export",
             data:{"type":type},
             async: false,
             error: function(request) {
                 layer.alert("与服务器连接失败/(ㄒoㄒ)/~~");
             },
             success: function(data) {
            	 
             }
                
         });
    });
}


$(function () {
	init();
});