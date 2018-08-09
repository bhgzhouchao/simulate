 $(function() {
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
		 	
		 	for (var i = 0; i < 5; i++) {
                $("#deptAct").append("<option  value=110 > aa </option>");
                //$("#deptLeader").append("<option  value=" + item.id + ">" + item.userName + "</option>");
            }
		 	
		 	layerid=layer.open({//开启表单弹层
  		         skin: 'layui-layer-molv',
		         area:'60%',
		         type: 1,
		         title:'新建部门',
		         content: $('#addDept')
           });
		 	
		 	/*$.ajax({
                type: "POST",
                url:"admin/dept/getUserList",
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
                        for (var i = 0; i < data.userList.length; i++) {
                            var item = data.userList[i];
                            $("#deptAct").append("<option  value=" + item.id + ">" + item.userName + "</option>");
                            //$("#deptLeader").append("<option  value=" + item.id + ">" + item.userName + "</option>");
                        }
                        $("#remarks").val("aa");
                        $("#deptAct").val();
                        layerid=layer.open({//开启表单弹层
	           		         skin: 'layui-layer-molv',
	        		         area:'60%',
	        		         type: 1,
	        		         title:'新建部门',
	        		         content: $('#addDept')
                        });
                    }
                }
            });*/
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
		             }
		             if(data.state=='success'){
		             	layer.alert(data.mesg);
		             }
		         }
		     });
		});
		
		
 });
 