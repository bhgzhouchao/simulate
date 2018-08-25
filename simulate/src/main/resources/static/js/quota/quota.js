var layerid;//当前弹层id;这个id可以定义多个，主要的目的是为了在回调函数关闭弹层时使用的

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

         //监听提交
         form.on('submit(addeditsubmitfilter)', function(data) {

             //为了防止form中的id值被重置后置空,将编辑的id存放在label中
             $("#editid").val($("#editlabelid").html() );
             $("#editlabelid").html("");

             $.ajax({
                 type: "POST",
                 url:"busi/quota/addOrUpdateQuota",
                 data:$('#addeditformid').serialize(),
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
                    		 layer.closeAll();
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
	 $("#subject").empty();
 	 $("#reset").click();
	 $.ajax({
        type: "POST",
        url:"busi/quota/selectQuotaById",
        data:{id:data},
        async: false,
        error: function(request) {
            layer.alert("与服务器连接失败/(ㄒoㄒ)/~~");
        },
        success: function(data) {
            if(data.state=='fail'){
                layer.alert(data.mesg);
            }
            if(data.state=='success'){
            	//向表单填充数据
                $("#editlabelid").html(data.quota.id);//临时存放id，当提交时再去除赋值给input
                $("#editid").val(data.quota.id);
                $("#year").val(data.quota.year);
                $("#subjectValue").val(data.quota.subjectValue);
                $("#remarks").val(data.quota.remarks);
                
                for (var i = 0; i < data.subjectList.length; i++) {
               	 var item = data.subjectList[i];
                    $("#subject").append("<option  value=" + item.id + ">" + item.subjectName+ "</option>");
                }
                $("#subejct").val(data.quota.subject);

                //开启编辑表单所在的弹层。注意编辑和新建的表单是一套模板
                layerid=layer.open({
                    skin: 'layui-layer-molv',
                    area:'60%',
                    type: 1,
                    title:'编辑部门',
                    content: $('#addeditformdivid') //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
                });

            }
        }
    });
}


function tableReload(){
	tableid.reload({
        page: {
          curr: 1 //重新从第 1 页开始
        }
        ,where: {
        	year  : $('#yearSearch').val()
        }
     });
}


 
 
 var tableid;

 function pageInit() {
	 //初始化表格
	 layui.use('table', function(){
		  tableid = layui.table.render({
		    elem: '#deptTable'
		    ,url:'busi/quota/quotaList'
		    ,method:'post'
		    ,cols: [[
		    	 {type:'checkbox'}
		    	,{field:'year',  title: '年份'}
		    	,{field:'subject',  title: '成本项目', templet: '#usernameTpl'}
		    	,{field:'subjectValue',  title: '金额',}
		    	,{field:'remarks',  title: '创建时间'}
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
	$('#deptSearch').on('click', function(){
		
		tableid.reload({
	        page: {
	          curr: 1 //重新从第 1 页开始
	        }
	        ,where: {
	        	year  : $('#yearSearch').val()
	        }
	      });
	  }); 
	
	 
	//初始化日期控件
	 layui.use('laydate', function(){
		 var laydate = layui.laydate;
		 laydate.render({
			 elem: '#yearSearch'
			 ,type: 'year'
		 });
		 laydate.render({
			 elem: '#year'
			 ,type: 'year'
		 });
	});
	
	//添加按钮点击事件
     $("#add").click(function () {
    	$("#subject").empty();
        $("#reset").click();
        $.ajax({
             type: "POST",
             url:"busi/quota/getSubjectList",
             data:"",
             async: false,
             error: function(request) {
                 layer.alert("与服务器连接失败/(ㄒoㄒ)/~~");
             },
             success: function(data) {
                 if(data.state=='fail'){
                     layer.alert(data.mesg);
                     return false;
                 }
                 if(data.state=='success'){
                	 //向表单填充数据
                     for (var i = 0; i < data.subjectList.length; i++) {
                    	 var item = data.subjectList[i];
                         $("#subject").append("<option  value=" + item.id + ">" + item.subjectName+ "</option>");
                     }
                     layerid=layer.open({//开启表单弹层
                         skin: 'layui-layer-molv',
                         area:['60%'],
                         type: 1,
                         title:'成本项目金额',
                         content: $('#addeditformdivid') //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
                     });

                 }
             }
         });
     });
     
     $("#delete").click(function () {
     	var selectData = layui.table.checkStatus('deptTable');
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
                         url:"busi/quota/deleteQuotas",
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
                            		layer.closeAll();
                            		$("#deptSearch").click();
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
 