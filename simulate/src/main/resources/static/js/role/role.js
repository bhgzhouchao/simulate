$(function () {
    //页面加载完成之后执行
    pageInit();
    var zTreeObj1;
    var zTreeObj2;
    var layerid;//当前弹层id;这个id可以定义多个，主要的目的是为了在回调函数关闭弹层时使用的
    layui.use(['layer','form','layedit','laydate'], function(){
        var layer = layui.layer,
        layedit = layui.layedit,
        laydate = layui.laydate,
        form = layui.form;

        //创建一个编辑器
        var editIndex = layedit.build('LAY_demo_editor');
        //自定义验证规则
        form.verify({
            name: function(value) {
                if(value.trim().length == 0) {
                    return '角色名称不能为空！';
                }
            },
            content: function(value) {
                layedit.sync(editIndex);
            }
        });

        //监听提交
        form.on('submit(addeditsubmitfilter)', function(data) {

            //为了防止form中的id值被重置后置空,将编辑的id存放在label中
            //$("#editid").val($("#editlabelid").html() );
            //$("#editlabelid").html("");

            $.ajax({
                type: "POST",
                url:"admin/role/addupdaterole",
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
                   		 	$("#deptSearch").click();
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
                   		 	$("#deptSearch").click();
                    	});
                    }
                }
            });
            return false;//防止跳转
        });
        
        $('#menuPermission').on('click', function () {
        	var selectData = layui.table.checkStatus('deptTable');
        	var orderNosList = new Array();
         	if(selectData.data.length == 0){
         		 layer.alert("请选择要编辑的记录！");
         		 return false;
         	}
        	
         	if(selectData.data.length > 1){
        		 layer.alert("不能选择多条数据！");
        		 return false;
        	}
        	var setting = {
        	        check:{
        	            enable: true,
        	            chkStyle: "checkbox",
        	            chkboxType :{ "Y" : "p", "N" : "s" },
        	            nocheckInherit: true,
        	            chkDisabledInherit: true
        	        }
        	    };
        	    // zTree 的数据属性，深入使用请参考 API 文档（zTreeNode 节点数据详解）

        	    var zNodes = [
        	        {name:"test1", open:true, children:[
        	            {name:"test1_1"}, {name:"test1_2"}]},
        	        {name:"test2", open:true, children:[
        	            {name:"test2_1"}, {name:"test2_2"}]}
        	    ];

        	    $.ajax({
        	        type: "POST",
        	        //url:'admin/role/loadCheckMenuInfo?parentId=1&roleId='+selectData.id,
        	        url:'admin/role/loadCheckMenuInfo',
        	        async: false,
        	        dataType: 'json',
        	        data:{parentId:'1',roleId:selectData.data[0].id},
        	        timeout: 1000,
        	        cache: false,
        	        error: function(request) {
        	            layer.alert("与服务器连接失败/(ㄒoㄒ)/~~");
        	        },
        	        success: function(data) {
        	            zNodes=data;

        	            zTreeObj1 = $.fn.zTree.init($("#treeDemo"), setting, zNodes);

        	            layerid=layer.open({//开启表单弹层
        	                skin: 'layui-layer-molv',
        	                type: 1,
        	                title:'设置权限',
        	                content: $('#setpermisdiv') //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
        	            });

        	        }
        	    });
        });
        
        //设置菜单
		$('#savesetpermis').on('click', function () {
	        /*var id = jQuery("#list2").jqGrid('getGridParam', 'selrow');//jqgrid逻辑id，不是业务表单的主键字段id,这里要注意
	        var ret = jQuery("#list2").jqGrid('getRowData', id);//通过jqgrid的逻辑id获取该行数据，通过数据对象ret来获取表单主键字段ret.id
*/	       
			var selectData = layui.table.checkStatus('deptTable');
        	var orderNosList = new Array();
         	if(selectData.data.length == 0){
         		 layer.alert("请选择要编辑的记录！");
         		 return false;
         	}
        	
         	if(selectData.data.length > 1){
        		 layer.alert("不能选择多条数据！");
        		 return false;
        	}
			var nodes = zTreeObj1.getCheckedNodes(true);
	        var menuArrIds=[];
	        for(var i=0;i<nodes.length;i++){
	            //alert(menuIds);
	            menuArrIds.push(nodes[i].id);
	        }
	        var menuIds=menuArrIds.join(",");
	        $.ajax({
	            type: "POST",
	            url:"admin/role/saveMenuSet",
	            data:{menuIds:menuIds,roleId:selectData.data[0].id},
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
	
	    });
		
	    $('#closesetpermis').on('click', function () {
	        layer.close(layerid);
	    });
    });
});   


var tableid;  

function toEdit(data){
    $("#reset").click();
	$.ajax({
        type: "POST",
        url:"admin/role/selectRoleById",
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
                $("#editlabelid").html(data.trole.id);//临时存放id，当提交时再去除赋值给input
                $("#name").val(data.trole.name);
                $("#bz").val(data.trole.bz);
                $("#editid").val(data.trole.id);
                //开启编辑表单所在的弹层。注意编辑和新建的表单是一套模板
                layerid=layer.open({
                    skin: 'layui-layer-molv',
                    area:'60%',
                    type: 1,
                    title:'编辑角色',
                    content: $('#addeditformdivid') //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
                });
            }
        }
    });
}


//重置表格
function tableReload(){
	 tableid.reload({
        page: {
          curr: 1 //重新从第 1 页开始
        }
        ,where: {
        	name  : $('#roleNameSreach').val()
        }
     });
}

function pageInit() {
    /*//创建jqGrid组件
    jQuery("#list2").jqGrid({
        caption: "角色管理",//表格的标题名字
        mtype: "post",//向后台请求数据的ajax的类型。可选post,get
        url: 'admin/role/list',
        //url : 'static/jqgrid/data/JSONData.json',//组件创建完成之后请求数据的url
        //styleUI: 'Bootstrap',
        datatype: "json",//请求数据返回的类型。可选json,xml,txt
        emptyrecords: "当前无记录",
        colNames: ['ID', '角色名称', '备注', '权限菜单设置'],//jqGrid的列显示名字
        colModel: [  //这里会根据index去解析jsonReader中root对象的属性，填充cell
            {name: 'id', index: 'id', width: 100, sortable: true, search: false},
            {name: 'name', index: 'name', width: 220, sortable: false,search: true,
                //被该列搜索时的搜索条件有哪些
                searchoptions: {sopt: ['eq']}
                
                 //如果使用自定义按钮点击事件的方式进行记录增删改操作的话下面的配置可以去掉
                editable: true,
                editoptions: {size: "20", maxlength: "30"}//当执行修改和新增的操作时，会显示输入框，输入框的配置
            },
            {name: 'bz', index: 'bz', width: 400, sortable: false, search: false},
            {name: 'setpermis', index: 'setpermis', align: "center", width: 300, sortable: false, search: false}
        ],

        //在gridComplete事件中添加：
        gridComplete: function () {
            var ids = jQuery("#list2").jqGrid('getDataIDs');
            for (var i = 0; i < ids.length; i++) {
                //alert(ids[i]);
                var id = ids[i];
                // var editBtn = "<a href='javascript:void(0)' style='color:#f60' onclick='' >设置权限</a>";
                var editBtn= '<button onclick="setrolelayer('+ids[i]+')" class="layui-btn layui-btn-radius layui-btn-xs" style="width:inherit;height: 85%">'+
                        '<i class="layui-icon">&#xe642;</i>设置菜单权限'+
                        '</button>';

                jQuery("#list2").jqGrid('setRowData', ids[i], { setpermis: editBtn });
            }
        },

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
        rownumbers: true // 显示行号
    });
    创建jqGrid的操作按钮容器
    可以控制界面上增删改查的按钮是否显示
    jQuery("#list2").jqGrid('navGrid', '#pager2', {
        //设置为false需要自己重新重新该方法
            edit: false,
            add: false,
            del: false,
            search: true,
        });
    }*/
	 //初始化表格
	 layui.use('table', function(){
		  tableid = layui.table.render({
		    elem: '#deptTable'
		    ,url:'admin/role/list'
		    ,method:'post'
		    ,cols: [[
		    	 {type:'checkbox'}
		    	,{field:'id',  title: 'ID',width:80}
		    	,{field:'name',  title: '角色名称', templet: '#usernameTpl'}
		    	,{field:'bz',  title: '备注'}
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
	        	name  : $('#roleNameSreach').val()
	        }
	      });
	  });
	 
	//重置
	$('#reset1').on('click', function(){
		$("#roleNameSreach").val("");
	});
	 
    //添加按钮点击事件
	$("#add").click(function () {
	    $("#reset").click();//重置表单(新建时在进入表单前要重置一下表单的内容，不然表单打开后会显示上一次的表单的内容。这里调用表单中重置按钮的点击方法来重置)
	    layerid=layer.open({//开启表单弹层
	        skin: 'layui-layer-molv',
	        area:'60%',
	        type: 1,
	        title:'新建角色',
	        content: $('#addeditformdivid') 
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
                content: '确定删除所选择的角色?',
                btn: ['确定', '取消'],//定义两个按钮，是和否
                yes: function(index, layero){//点击是时候的回调
                    //do something
                    layer.close(index); //如果设定了yes回调，需进行手工关闭
                    //请求后台，执行删除操作
                    $.ajax({
                        type: "POST",
                        url:"admin/role/deleterole",
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
	    var id = jQuery("#list2").jqGrid('getGridParam', 'selrow');//jqgrid逻辑id，不是业务表单的主键字段id,这里要注意
	    if (id) {
	        var ret = jQuery("#list2").jqGrid('getRowData', id);//通过jqgrid的逻辑id获取该行数据，通过数据对象ret来获取表单主键字段ret.id
	        //请求后台，获取该记录的详细记录，并填充进表单
	        $.ajax({
	            type: "POST",
	            url:"admin/role/selectRoleById",
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
	                    $("#name").val(data.trole.name);
	                    $("#bz").val(data.trole.bz);
	
	                    //开启编辑表单所在的弹层。注意编辑和新建的表单是一套模板
	                    layerid=layer.open({
	                        skin: 'layui-layer-molv',
	                        area:'60%',
	                        type: 1,
	                        title:'编辑角色',
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

function setrolelayer(id) {
    // zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
    

}

