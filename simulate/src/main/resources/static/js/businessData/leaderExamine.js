var layerid;//当前弹层id;这个id可以定义多个，主要的目的是为了在回调函数关闭弹层时使用的

var tableid;
$(function () {
     //页面加载完成之后执行
     pageInit();

     layui.use(['layer','form','layedit','laydate'], function(){
         var layer = layui.layer,
         layedit = layui.layedit,
         laydate = layui.laydate,
         $ = layui.$,
         form = layui.form;

         //创建一个编辑器
         var editIndex = layedit.build('LAY_demo_editor');
         //自定义验证规则
         form.verify({
            
         });
     });
 });
 


function toEdit(data){
	mask = layer.load(1, {shade: [0.8, '#393D49']});
	layerid = layer.open({
		title: "业务数据录入",
		type: 2, 
		skin: 'layui-layer-molv',
		area: ['80%', '90%'],
		content: 'busi/leaderExamine/toBusinessDataAdd?type=2&id='+data ,
		success: function(layero, index){
			//关闭弹出层遮罩
			layer.close(mask);
		},
		cancel: function(index, layero){ 
			layer.closeAll();
			return false; 
		},
		end:function(){
			// 加载数据
			tableReload();
		}
	}); 
}

function tableReload(){
	tableid.reload({
        page: {
          curr: 1 //重新从第 1 页开始
        }
        ,where: {
        	month  : $('#month').val()
        }
	});
}


function deptExamine(orderNosList, url,type){
	$.ajax({
        type: "POST",
        url:url,
        data:{"Ids":orderNosList.toString()},
        async: false,
        error: function(request) {
            layer.alert("与服务器连接失败/(ㄒoㄒ)/~~");
        },
        success: function(data) {
            if(data.state=='fail'){
                layer.alert(data.mesg);
            }
            if(data.state=='success'){
           	 layer.alert(data.mesg, function(){
           		 if(type == "1"){
        			layer.closeAll();
        		 } else {
        			 parent.layer.closeAll();
        		 }
           		 tableReload();
           	 	});
            }
        }
    });
}


 function pageInit() {
	 //初始化表格
	 layui.use('table', function(){
		  tableid = layui.table.render({
		    elem: '#businessDataTable'
		    ,url:'busi/leaderExamine/businessDataList'
		    ,method:'post'
		    ,cols: [[
		    	 {type:'checkbox'}
		    	,{field:'month',  title: '月份', templet: '#usernameTpl',width:'10%'}
		    	,{field:'dept',  title: '所属部门',width:'10%'}
		    	,{field:'totalPrice',  title: '总金额',width:'12%'}
		    	,{field:'deptExamine',  title: '部门审批状态',width:'12%'}
		    	,{field:'deptExamineDate',  title: '部门审批时间',width:'13%'}
		    	,{field:'leaderExamine',  title: '财务审批状态',width:'12%'}
		    	,{field:'leaderExamineDate',  title: '财务审批时间',width:'13%'}
		    	,{field:'submitDate',  title: '提交审批时间'}
		    ]]
		    ,page: true
		    ,skin: 'row'
		    ,size: 'sm'
		    ,height : 'full-150'
		    ,even: true
			,limit:10  //默认20条数据一页
			,limits:[10,20,30,50]  //数据分页条
	  });
});
	//查询
	$('#busiDataSearch').on('click', function(){
		tableid.reload({
	        page: {
	          curr: 1 //重新从第 1 页开始
	        }
	        ,where: {
	        	month  : $('#month').val()
	        }
	      });
	  }); 
	
	//初始化日期控件
	 layui.use('laydate', function(){
		 var laydate = layui.laydate;
		 laydate.render({
			 elem: '#month'
			 ,type: 'month'
		 });
		 laydate.render({
			 elem: '#addMonth'
			 ,type: 'month'
		 });
	});
	
	 
	$("#examineListYes").click(function () {
		var selectData = layui.table.checkStatus('businessDataTable');
    	var orderNosList = new Array();
     	if(selectData.data.length > 0){
	     	for (var i = 0; i < selectData.data.length; i++) {
				orderNosList[i] = selectData.data[i].id;
			}
	     	deptExamine(orderNosList,"busi/leaderExamine/examineYes","1");
     	} else {
     		layer.alert("请选择要审批的记录");
     	}
	});
	
	$("#examineListNo").click(function () {
		var selectData = layui.table.checkStatus('businessDataTable');
    	var orderNosList = new Array();
     	if(selectData.data.length > 0){
	     	for (var i = 0; i < selectData.data.length; i++) {
				orderNosList[i] = selectData.data[i].id;
			}
	     	deptExamine(orderNosList,"busi/leaderExamine/examineNo","1");
     	} else {
     		layer.alert("请选择要审批的记录");
     	}
	});
	
	$("#deptExamineYes").click(function () {
		var id = $("#editid").val();
		var orderNosList = new Array();
		orderNosList[0] = id;
		deptExamine(orderNosList,"busi/leaderExamine/examineYes","2");
		return false;
	});
	
	$("#deptExamineNo").click(function () {
		var id = $("#editid").val();
		var orderNosList = new Array();
		orderNosList[0] = id;
		deptExamine(orderNosList,"busi/leaderExamine/examineNo","2");
		return false;
	});
	
 }
 