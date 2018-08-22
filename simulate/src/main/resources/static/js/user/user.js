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
                url:"admin/user/addupdateuser",
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
            return false;
        });

        //监听提交
        form.on('submit(editroleformsubmit)', function(data) {
            //为了防止form中的id值被重置后置空,将编辑的id存放在label中
            $("#editroleid").val($("#editrolelabelid").html() );
            $("#editrolelabelid").html("");

            $.ajax({
                type: "POST",
                url:"admin/user/saveRoleSet",
                data:$('#editroleformid').serialize(),// 你的formid
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
            return false;
        });
    });
});


function toEdit(data){
	$("#dept").empty();
    $("#reset").click();
	$.ajax({
        type: "POST",
        url:"admin/user/selectUserById",
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
            	$("#editlabelid").html(data.tuser.id);//临时存放id，当提交时再去除赋值给input
                $("#userName").val(data.tuser.userName);
                $("#password").val(data.tuser.password);
                $("#trueName").val(data.tuser.trueName);
                $("#bz").val(data.tuser.bz);
                
                for (var i = 0; i < data.deptList.length; i++) {
                    var item = data.deptList[i];
                    $("#dept").append("<option  value=" + item.id + ">" + item.deptName + "</option>");
                }
                
                $("select option[value='"+data.tuser.dept+"']").attr("selected", "selected");

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
        	userName  : $('#searchUserName').val()
        }
     });
}


function pageInit() {
    /*//创建jqGrid组件
    jQuery("#list2").jqGrid(
            {
                caption: "用户管理",//表格的标题名字
                mtype: "post",//向后台请求数据的ajax的类型。可选post,get
                url: 'admin/user/list',
                //url : 'static/jqgrid/data/JSONData.json',//组件创建完成之后请求数据的url
                //styleUI: 'Bootstrap',
                datatype: "json",//请求数据返回的类型。可选json,xml,txt
                emptyrecords: "当前无记录",
                colNames: ['ID', '用户名','部门', '密码', '真实姓名', '备注', '拥有角色'],//jqGrid的列显示名字
                colModel: [  //这里会根据index去解析jsonReader中root对象的属性，填充cell
                    {name: 'id', index: 'id', width: 100, sortable: true, search: false},
                    {name: 'userName', index: 'userName', width: 180, sortable: false,search: true,
                        //被该列搜索时的搜索条件有哪些
                        searchoptions: {sopt: ['eq']}
                        //如果使用自定义按钮点击事件的方式进行记录增删改操作的话下面的配置可以去掉
                        
                        editable: true,
                        editoptions: {size: "20", maxlength: "30"}//当执行修改和新增的操作时，会显示输入框，输入框的配置
                    },
                    {name: 'dept', index: 'dept', width: 150, sortable: false, search: false},
                    {name: 'password', index: 'password', width: 150, sortable: false, search: false},
                    {name: 'trueName', index: 'trueName', width: 180, sortable: false, search: false},
                    {name: 'bz', index: 'bz', width: 180, sortable: false, search: false},
                    {name: 'roles', index: 'roles', width: 180, sortable: false, search: false}
                ],
                //如果使用自定义按钮点击事件的方式进行记录增删改操作的话下可以去掉
                //editurl: "admin/user/adduser",
                //cellsubmit: "clientArray",
                //cellEdit:true,//启用或者禁用单元格编辑功能
                jsonReader: {
                    root: "datamap",//数据的根节点
                    page: "currpage",//返回数据的当前页
                    total: "totalpages",//总页数
                    records: "totalrecords",//总记录数
                    repeatitems: false,// 如果设为false，则jqGrid在解析json时，会根据name来搜索对应的数据元素（即可以json中元素可以不按顺序）；而所使用的name是来自于colModel中的name设定。
                    id: "id"//主键字段名称
                },
                prmNames: { //如当前查询实体为ware，这些可以在查询对象的superObject中设定
                    page: "page", // 表示请求页码的参数名称
                    rows: "length", // 表示请求行数的参数名称
                    sort: "sidx", // 表示用于排序的列名的参数名称
                    order: "sord", // 表示采用的排序方式的参数名称
                    search: "search", // 表示是否是搜索请求的参数名称(实际上在搜索时会传给后台三个参数：String searchField;//搜索字段String searchString;//搜索值String searchOper;//搜索条件公式)
                    nd: "nd", // 表示已经发送请求的次数的参数名称
                    id: "id", // 表示当在编辑数据模块中发送数据时，使用的id的名称
                    oper: "oper", // operation参数名称
                    editoper: "edit", // 当在edit模式中提交数据时，操作的名称
                    addoper: "add", // 当在add模式中提交数据时，操作的名称
                    deloper: "del", // 当在delete模式中提交数据时，操作的名称
                    subgridid: "id", // 当点击以载入数据到子表时，传递的数据名称
                    npage: null,
                    totalrows: "totalrows" // 表示需从Server得到总共多少行数据的参数名称，参见jqGrid选项中的rowTotal
                },
                rowNum: 10,//一页显示多少条
                rowList: [10, 20, 30],//可供用户选择一页显示多少条
                pager: '#pager2',//表格页脚的占位符(一般是div)的id
                sortname: 'id',//初始化的时候排序的字段
                sortorder: "asc",//排序方式,可选desc,asc
                viewrecords: true,//定义是否要显示总记录数
                hidegrid: false,//启用或者禁用控制表格显示、隐藏的按钮，只有当caption 属性不为空时起效
                height: "100%",
                autowidth: true,//如果为ture时，则当表格在首次被创建时会根据父元素比例重新调整表格宽度。如果父元素宽度改变，为了使表格宽度能够自动调整则需要实现函数：setGridWidth
                shrinkToFit: true,
                rownumbers: true, // 显示行号
     });
	创建jqGrid的操作按钮容器
	 可以控制界面上增删改查的按钮是否显示
    jQuery("#list2").jqGrid('navGrid', '#pager2', {
        //设置为false需要自己重新重新该方法
        edit: false,
        add: false,
        del: false,
        search: true,
    });*/
	
	 layui.use('table', function(){
		  tableid = layui.table.render({
		    elem: '#userTable'
		    ,url:'admin/user/list'
		    ,method:'post'
		    ,cols: [[
		    	 {type:'checkbox'}
		    	,{field:'id',  title: 'ID'}
		    	,{field:'userName',  title: '用户名',templet: '#usernameTpl'}
		    	,{field:'dept',  title: '所属部门'}
		    	,{field:'trueName',  title: '真实姓名'}
		    	,{field:'bz',  title: '备注',width:200}
		    	,{field:'roles',  title: '拥有角色',width:200}
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
	        	userName  : $('#searchUserName').val()
	        }
	      });
	  });
	//重置
	$('#reset1').on('click', function(){
	    $('#searchUserName').val("")
	});
	
    //添加按钮点击事件
    $("#add").click(function () {
    	$("#dept").empty();
        $("#reset").click();
        $.ajax({
            type: "POST",
            url:"admin/user/getDept",
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
                    for (var i = 0; i < data.deptList.length; i++) {
                        var item = data.deptList[i];
                        $("#dept").append("<option  value=" + item.id + ">" + item.deptName + "</option>");
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


    $("#delete").click(function () {
    	var selectData = layui.table.checkStatus('userTable');
    	var orderNosList = new Array();
     	if(selectData.data.length > 0){
     		for (var i = 0; i < selectData.data.length; i++) {
				orderNosList[i] = selectData.data[i].id;
			}
     		layer.open({
                content: '确定删除所选择的用户?',
                btn: ['确定', '取消'],
                yes: function(index, layero){
                    layer.close(index); 
                    $.ajax({
                        type: "POST",
                        url:"admin/user/deleteuser",
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
    	
       /* var id = jQuery("#list2").jqGrid('getGridParam', 'selrow');//jqgrid逻辑id，不是业务表单的主键字段id,这里要注意
        if (id) {
            var ret = jQuery("#list2").jqGrid('getRowData', id);//通过jqgrid的逻辑id获取该行数据，通过数据对象ret来获取表单主键字段ret.id

            layer.open({
                content: '请确定是否真的要删除id为'+ret.id+'的记录?',
                btn: ['yes', 'no'],//定义两个按钮，是和否
                yes: function(index, layero){//点击是时候的回调
                    //do something
                    layer.close(index); //如果设定了yes回调，需进行手工关闭

                    //请求后台，执行删除操作
                    $.ajax({
                        type: "POST",
                        url:"admin/user/deleteuser",
                        data:{id:ret.id},
                        async: false,
                        error: function(request) {
                            layer.alert("与服务器连接失败/(ㄒoㄒ)/~~");
                        },
                        success: function(data) {
                            if(data.state=='fail'){
                                layer.alert(data.mesg);
                            }
                            if(data.state=='success'){
                                //打开成功消息提示
                                layer.open({
                                    skin: 'layui-layer-molv',
                                    type:1,
                                    area:"10%",
                                    content:data.mesg,
                                    shadeClose:true,
                                    end: function(){
                                        layer.close(layerid);//消息提示结束后回调，关闭上一级新建表单所在弹层
                                        jQuery("#list2").jqGrid().trigger("reloadGrid");//jqgrid数据表重新主动加载数据
                                    }
                                });

                            }
                        }
                    });



                }
            });


        } else {
            layer.alert("请选择要删除的记录");
        }*/


    });




    /*$("#edit").click(function () {
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


    });*/




    //编辑用户角色
    $("#editrole").click(function () {
    	var selectData = layui.table.checkStatus('userTable');
    	var orderNosList = new Array();
     	if(selectData.data.length == 0){
     		 layer.alert("请选择要编辑的记录！");
     		 return false;
     	}
    	
     	if(selectData.data.length > 1){
    		 layer.alert("不能选择多条数据！");
    		 return false;
    	}
        //获得当前用户已经拥有的角色集合和未拥有的角色集合，并组装表单的复选按钮
        $.ajax({
            type: "POST",
            url:"admin/user/selectUserById",
            data:{id:selectData.data[0].id},
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
                    $("#editrolelabelid").html(selectData.data[0].id);//临时存放id，当提交时再去除赋值给input
                    var roleList=[];
                    roleList=data.roleList;//该记录已经拥有的记录集合
                    var notinrolelist=[];
                    notinrolelist=data.notinrolelist;//该记录尚未拥有的记录集合

                    var strs="";
                    $.each(roleList, function (n, value) {//n从0开始自增+1；value为每次循环的单个对象
                        strs+='<input type="checkbox" name="role" title="'+value.name+'" value="'+value.id+'"  checked="checked">';
                    });
                    $.each(notinrolelist, function (n, value) {
                        strs+='<input type="checkbox" name="role" title="'+value.name+'"  value="'+value.id+'" >';
                    });
                    $("#checkboxlistid").empty();//每次填充前都要清空所有按钮，重新填充
                    $("#checkboxlistid").append(strs);

                    layui.form.render(); //更新全部

                    layerid=layer.open({
                        skin: 'layui-layer-molv',
                        area:'60%',
                        type: 1,
                        title:'编辑用户角色',
                        content: $('#editroleformdivid') //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
                    });

                }
            }
        });
    });
}