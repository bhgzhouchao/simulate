/* $(function() {
	layui.use('table', function(){
		  var table = layui.table;
		  table.render({
		    elem: '#deptTable'
		    ,url:'admin/dept/deptList'
		    ,method:'post'
		    ,cols: [[
		    	 {type:'checkbox'}
		    	,{field:'id',  title: '编号'}
		    	,{field:'deptId',  title: '部门编号'}
		    	,{field:'deptName',  title: '部门名称'}
		    	,{field:'deptAct',  title: '部门负责人'}
		    	,{field:'deptLeader',  title: '分管院领导'}
		    	,{field:'remarks',  title: '备注'}
		    ]]
		    ,page: true
		    ,skin: 'row'
		    ,size: 'sm'
		    ,even: true
			,limit:10  //默认20条数据一页
			,limits:[10,20,30,50]  //数据分页条
		  	,done: function(res, curr, count){
		  		$("[data-field='id']").css('display','none');
			}
		  });
		});
	 	
		
	    //查询
		$('#deptSearch').on('click', function(){
			var table = layui.table
			table.reload('deptTable', {
		        page: {
		          curr: 1 //重新从第 1 页开始
		        }
		        ,where: {
		        	deptId  : $('#deptId').val(),
		        	deptName  : $('#deptName').val()
		        }
		      });
		  });
		
		//重置
		$('#reset').on('click', function(){
			$("#deptId").val("");
		    $('#deptName').val("")
		});
		
		
		//清空表单数据
		function clearContent(){
			$("#remarks").val("");
		    $('#addDeptName').val("");
		    $('#addDeptId').val("");
		    $('#editid').val("");
		    $("#deptAct").empty();
		    $("#deptLeader").empty();
		}
		
		//添加按钮点击事件
		 $("#addBtn").click(function () {
		 	clearContent();
		 	$.ajax({
                type: "POST",
                url:"admin/dept/getUserList",
                async: true,
                error: function(request) {
                    layer.alert("与服务器连接失败/(ㄒoㄒ)/~~");
                },
                success: function(data) {
                    if(data.state=='fail'){
                        layer.alert(data.mesg);
                    }
                    if(data.state=='success'){
                        //向表单填充数据
                        for (var i = 0; i < data.userList.length; i++) {
                            $("#deptAct").append("<option  value=" + data.userList[i].id + ">" + data.userList[i].userName + "</option>");
                            $("#deptLeader").append("<option  value=" + data.userList[i].id + ">" + data.userList[i].userName + "</option>");
                        }
                        layer.open({//开启表单弹层
	           		         skin: 'layui-layer-molv',
	        		         area:'60%',
	        		         type: 1,
	        		         title:'新建部门',
	        		         content: $('#addDept')
                        });
                        return;
                    }
                }
            });
		 });
	 	$("#addDeptbtn").click(function () {
		 	$.ajax({
		         type: "post",
		         url:"admin/dept/addOrUpdateDept",
		         data:$('#addEditFrom').serialize(),
		         async: false,
		         error: function(request) {
		             layer.alert("与服务器连接失败/(ㄒoㄒ)/~~");
		         },
		         success: function(data) {
		             if(data.state=='fail'){
		                 layer.alert(data.mesg);
		                 return;
		             }
		             if(data.state=='success'){
		             	layer.alert(data.mesg);
		             	return;
		             }
		         }
		     });
		});

 });
 */


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
             userName: function(value) {
                 if(value.length < 5) {
                     return '用户名至少得5个字符';
                 }
             },
             password: [/(.+){6,12}$/, '密码必须6到12位'],
             content: function(value) {
                 layedit.sync(editIndex);
             }
         });

         //监听提交
         form.on('submit(addeditsubmitfilter)', function(data) {

             //为了防止form中的id值被重置后置空,将编辑的id存放在label中
             $("#editid").val($("#editlabelid").html() );
             $("#editlabelid").html("");

             $.ajax({
                 type: "POST",
                 url:"admin/dept/addOrUpdateDept",
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
     
    /* layui.use('table', function(){
    	 var table = layui.table; 
    	 table.on('tool(deptFilter)', function(obj){
		    if(obj.event === 'setSign'){
		    	var data = obj.data;
		    	alert(data);
		    }
    	 });
     });*/
 });
 
 
 function toEdit(data){
	 $("#deptLeader").empty();
  	 $("#deptAct").empty();
     $("#reset").click();
	 $.ajax({
         type: "POST",
         url:"admin/dept/selectDeptById",
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
                 $("#editlabelid").html(data.dept.id);//临时存放id，当提交时再去除赋值给input
                 $("#editid").val(data.dept.id);
                 $("#addDeptId").val(data.dept.deptId);
                 $("#addDeptName").val(data.dept.deptName);
                 $("#remarks").val(data.dept.remarks);
                 
                 for (var i = 0; i < data.userList.length; i++) {
                	 var item = data.userList[i];
                     $("#deptLeader").append("<option  value=" + item.id + ">" + item.userName + "</option>");
                     $("#deptAct").append("<option  value=" + item.id + ">" + item.userName + "</option>");
                 }
                 
                 $("#deptLeader").val(data.dept.deptLeader);
                 $("#deptAct").val(data.dept.deptAct);

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
 
 
//查询表格
 function tableReload(){
	 tableid.reload({
         page: {
           curr: 1 //重新从第 1 页开始
         }
         ,where: {
         	deptId  : $('#deptId').val(),
         	deptName  : $('#deptName').val()
         }
      });
 }

 var tableid;

 function pageInit() {
	 
	 layui.use('table', function(){
		  tableid = layui.table.render({
		    elem: '#deptTable'
		    ,url:'admin/dept/deptList'
		    ,method:'post'
		    ,cols: [[
		    	 {type:'checkbox'}
		    	,{field:'deptId',  title: '部门编号', templet: '#usernameTpl',width:100}
		    	,{field:'deptName',  title: '部门名称'}
		    	,{field:'deptAct',  title: '部门负责人'}
		    	,{field:'deptLeader',  title: '分管院领导'}
		    	,{field:'remarks',  title: '备注',width:200}
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
	        	deptId  : $('#deptId').val(),
	        	deptName  : $('#deptName').val()
	        }
	      });
	  });
	 

     //添加按钮点击事件
     $("#add").click(function () {
     	$("#deptLeader").empty();
     	$("#deptAct").empty();
        $("#reset").click();
        $.ajax({
             type: "POST",
             url:"admin/dept/getUserList",
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
                	 //向表单填充数据
                     for (var i = 0; i < data.userList.length; i++) {
                    	 var item = data.userList[i];
                         $("#deptLeader").append("<option  value=" + item.id + ">" + item.userName + "</option>");
                         $("#deptAct").append("<option  value=" + item.id + ">" + item.userName + "</option>");
                     }
                     layerid=layer.open({//开启表单弹层
                         skin: 'layui-layer-molv',
                         area:'60%',
                         type: 1,
                         title:'新建用户',
                         content: $('#addeditformdivid') //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
                     });

                 }
             }
         });
     });
     	
   //重置
	$('#reset1').on('click', function(){
		$("#deptId").val("");
	    $('#deptName').val("")
	});
     
     

     $("#delete").click(function () {
    	var selectData = layui.table.checkStatus('deptTable');
    	var orderNosList = new Array();
     	if(selectData.data.length > 0){
     		for (var i = 0; i < selectData.data.length; i++) {
				orderNosList[i] = selectData.data[i].id;
			}
     		layer.open({
                content: '确定删除所选择的部门数据?',
                btn: ['确定', '取消'],//定义两个按钮，是和否
                yes: function(index, layero){//点击是时候的回调
                    //do something
                    layer.close(index); //如果设定了yes回调，需进行手工关闭
                    //请求后台，执行删除操作
                    $.ajax({
                        type: "POST",
                        url:"admin/dept/deleteDepts",
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




     $("#edit").click(function () {
     	$("#reset").click();
     	$("#dept").empty();
         var id = jQuery("#list2").jqGrid('getGridParam', 'selrow');//jqgrid逻辑id，不是业务表单的主键字段id,这里要注意
         if (id) {
             var ret = jQuery("#list2").jqGrid('getRowData', id);//通过jqgrid的逻辑id获取该行数据，通过数据对象ret来获取表单主键字段ret.id

             //请求后台，获取该记录的详细记录，并填充进表单
             $.ajax({
                 type: "POST",
                 url:"admin/user/selectUserById",
                 data:{id:ret.id},
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
                         $("#editlabelid").html(ret.id);//临时存放id，当提交时再去除赋值给input
                         $("#userName").val(data.tuser.userName);
                         $("#password").val(data.tuser.password);
                         $("#trueName").val(data.tuser.trueName);
                         $("#bz").val(data.tuser.bz);
                         
                         for (var i = 0; i < data.deptList.length; i++) {
                             var item = data.deptList[i];
                             $("#dept").append("<option  value=" + item.deptName + ">" + item.deptName + "</option>");
                         }
                         
                         $("select option[value='"+data.tuser.dept+"']").attr("selected", "selected");

                         //开启编辑表单所在的弹层。注意编辑和新建的表单是一套模板
                         layerid=layer.open({
                             skin: 'layui-layer-molv',
                             area:'60%',
                             type: 1,
                             title:'编辑用户',
                             content: $('#addeditformdivid') //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
                         });

                     }
                 }
             });


         } else {
             layer.alert("请选择要编辑的记录");
         }


     });


 }
 