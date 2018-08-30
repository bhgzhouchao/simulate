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
        	 num: [/^[1-9]\d{0,9}$/, '数量只能输入正整数！'],
         });

         //监听提交
         form.on('submit(addeditsubmitfilter)', function(data) {

             //为了防止form中的id值被重置后置空,将编辑的id存放在label中
             $("#editid").val($("#editlabelid").html() );
             $("#editlabelid").html("");
             var baseData = $('#addeditformid').serializeArray();
             var businessDataVo = {};
             var businessList = [];
             for(var i = 0; i < baseData.length; i ++ ){
            	 var business = new Object();
            	 if(i == 0){
            		 businessDataVo.id = baseData[i].value;
            	 } else if(i == 1){
            		 businessDataVo.month = baseData[i].value;
            	 } else if(i == (baseData.length - 1)){
            		 businessDataVo.remarks = baseData[i].value;
            	 } else if(i%2 == 0) {
            		 business.num = baseData[i].value;
            	 } else {
            		 business.num = baseData[i-1].value;
            		 business.id = baseData[i].value;
            		 businessList.push(business);
            	 }
             }
             businessDataVo.businessList = businessList;
             
             var data = {
            	 "id":businessDataVo.id,
            	 "month":businessDataVo.month,
            	 "type":"2",
            	 "remarks":businessDataVo.remarks,
            	 "businessList":businessList
             	}
             
             $.ajax({
                 type: "POST",
                 url:"busi/busiData/addOrUpdateBusinessData",
                 contentType: "application/json",
                 data:JSON.stringify(data),
                 async: false,
                 error: function(request) {
                     layer.alert("与服务器连接失败/(ㄒoㄒ)/~~");
                 },
                 success: function(data) {
                     if(data.state=='fail'){
                         layer.alert(data.mesg);
                     }
                     if(data.state=='success'){
                    	 layer.alert("提交成功！", function(){
                    		 parent.layer.closeAll();
                    		 tableReload();
						});
                     }
                 }
             });
             return false;//防止表单提交后跳转
         });
         
         //监听保存
         form.on('submit(savesubmitfilter)', function(data) {

             //为了防止form中的id值被重置后置空,将编辑的id存放在label中
             $("#editid").val($("#editlabelid").html() );
             $("#editlabelid").html("");
             var baseData = $('#addeditformid').serializeArray();
             var businessDataVo = {};
             var businessList = [];
             for(var i = 0; i < baseData.length; i ++ ){
            	 var business = new Object();
            	 if(i == 0){
            		 businessDataVo.id = baseData[i].value;
            	 } else if(i == 1){
            		 businessDataVo.month = baseData[i].value;
            	 } else if(i == (baseData.length - 1)){
            		 businessDataVo.remarks = baseData[i].value;
            	 } else if(i%2 == 0) {
            		 business.num = baseData[i].value;
            	 } else {
            		 business.num = baseData[i-1].value;
            		 business.id = baseData[i].value;
            		 businessList.push(business);
            	 }
             }
             businessDataVo.businessList = businessList;
             
             var data = {
            	 "id":businessDataVo.id,
            	 "month":businessDataVo.month,
            	 "type":"1",
            	 "remarks":businessDataVo.remarks,
            	 "businessList":businessList
             	}
             
             $.ajax({
                 type: "POST",
                 url:"busi/busiData/addOrUpdateBusinessData",
                 contentType: "application/json",
                 data:JSON.stringify(data),
                 async: false,
                 error: function(request) {
                     layer.alert("与服务器连接失败/(ㄒoㄒ)/~~");
                 },
                 success: function(data) {
                     if(data.state=='fail'){
                         layer.alert(data.mesg);
                     }
                     if(data.state=='success'){
                    	 layer.alert("提交成功！", function(){
                    		 parent.layer.closeAll();
                    		 tableReload();
						});
                     }
                 }
             });
             return false;//防止表单提交后跳转
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
		content: 'busi/busiData/toBusinessDataAdd?type=2&id='+data ,
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


 function pageInit() {
	 //初始化表格
	 layui.use('table', function(){
		  tableid = layui.table.render({
		    elem: '#businessDataTable'
		    ,url:'busi/busiData/businessDataList'
		    ,method:'post'
		    ,cols: [[
		    	 {type:'checkbox'}
		    	,{field:'month',  title: '月份', templet: '#usernameTpl',width:'7%'}
		    	,{field:'dept',  title: '所属部门',width:'7%'}
		    	,{field:'totalPrice',  title: '总金额',width:'10%'}
		    	,{field:'deptExamine',  title: '部门审批状态',width:'10%'}
		    	,{field:'deptExamineDate',  title: '部门审批时间',width:'13%'}
		    	,{field:'leaderExamine',  title: '财务审批状态',width:'10%'}
		    	,{field:'leaderExamineDate',  title: '财务审批时间',width:'13%'}
		    	,{field:'submitDate',  title: '提交审批时间',width:'13%'}
		    	,{field:'createUser',  title: '创建者',width:'7%'}
		    	,{field:'createTime',  title: '创建时间',width:'13%'}
		    ]]
		    ,page: true
		    ,skin: 'row'
		    ,size: 'sm'
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
	
	 $("#add").click(function () {
		 mask = layer.load(1, {shade: [0.8, '#393D49']});
		 layerid = layer.open({
			title: "业务数据录入",
			type: 2, 
			skin: 'layui-layer-molv',
			area: ['80%', '90%'],
			move: true,
			shadeClose: false,
			content: 'busi/busiData/toBusinessDataAdd?type=1&id=' ,
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
	 });
	 
	 /*$("#businessDataAdd").click(function () {
		//为了防止form中的id值被重置后置空,将编辑的id存放在label中
         $("#editid").val($("#editlabelid").html() );
         $("#editlabelid").html("");
         var baseData = $('#addeditformid').serializeArray();
         var businessDataVo = {};
         var businessList = [];
         for(var i = 0; i < baseData.length; i ++ ){
        	 var business = new Object();
        	 if(i == 0){
        		 businessDataVo.id = baseData[i].value;
        	 } else if(i == 1){
        		 businessDataVo.month = baseData[i].value;
        	 } else if(i == (baseData.length - 1)){
        		 businessDataVo.remarks = baseData[i].value;
        	 } else if(i%2 == 0) {
        		 business.num = baseData[i].value;
        	 } else {
        		 business.num = baseData[i-1].value;
        		 business.id = baseData[i].value;
        		 businessList.push(business);
        	 }
         }
         businessDataVo.businessList = businessList;
         
         var data = {
        	 "id":businessDataVo.id,
        	 "month":businessDataVo.month,
        	 "type":"1",
        	 "remarks":businessDataVo.remarks,
        	 "businessList":businessList
         	}
         
         $.ajax({
             type: "POST",
             url:"busi/busiData/addOrUpdateBusinessData",
             contentType: "application/json",
             data:JSON.stringify(data),
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
                		 parent.layer.closeAll();
                		 layer.close(layerid);
                		 tableReload();
					});
                 }
             }
         });
         return false;//防止表单提交后跳转
	 });*/
	 
	 $("#delete").click(function () {
	    	var selectData = layui.table.checkStatus('businessDataTable');
	    	var orderNosList = new Array();
	     	if(selectData.data.length > 0){
	     		for (var i = 0; i < selectData.data.length; i++) {
					orderNosList[i] = selectData.data[i].id;
				}
	     		layer.open({
	                content: '确定删除所选择的数据?',
	                btn: ['确定', '取消'],//定义两个按钮，是和否
	                yes: function(index, layero){//点击是时候的回调
	                    //do something
	                    layer.close(index); //如果设定了yes回调，需进行手工关闭
	                    //请求后台，执行删除操作
	                    $.ajax({
	                        type: "POST",
	                        url:"busi/busiData/deleteBusinessDatas",
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
	                            		parent.layer.closeAll();
	                           		 	$("#busiDataSearch").click();
	                            	});
	                            }
	                        }
	                    });
	                }
	            });
	     	} else {
	           layer.alert("请选择要删除的记录");
	     	}
	  });
 }
 