<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色管理表格</title>

<link href="${basePath!}/static/css/comm.css" type="text/css" media="screen" rel="stylesheet"/>
<link href="${basePath!}/static/css/admin.css" type="text/css" media="screen" rel="stylesheet"/>

<!-- jqGrid组件基础样式包-必要 -->
<link href="${basePath!}/static/jqgrid/css/ui.jqgrid.css" type="text/css" media="screen" rel="stylesheet"/>
<link href="${basePath!}/static/jqgrid/css/jquery-ui.css" type="text/css" media="screen" rel="stylesheet"/>
<link href="${basePath!}/static/css/global.css" type="text/css" media="screen" rel="stylesheet"/>
<link href="${basePath!}/static/plugins/font-awesome/css/font-awesome.min.css" type="text/css" media="screen" rel="stylesheet"/>
<link href="${basePath!}/static/layui/css/layui.css" type="text/css" media="screen" rel="stylesheet"/>
<link href="${basePath!}/static/css/ztree/metroStyle/metroStyle.css" type="text/css" media="screen" rel="stylesheet"/>
<link href="${basePath!}/static/css/ztree/demo.css" type="text/css" media="screen" rel="stylesheet"/>

<!-- jquery插件包-必要 -->
<!-- 这个是所有jquery插件的基础，首先第一个引入 -->
<script src="${basePath!}/static/js/jquery.min.js" type="text/javascript"></script>
<script src="${basePath!}/static/jqgrid/js/jquery.jqGrid.min.js" type="text/javascript"></script>
<!-- jqGrid插件的多语言包-非必要 -->
<!-- 在jqgrid/js/i18n下还有其他的多语言包，可以尝试更换看效果 -->
<script src="${basePath!}/static/jqgrid/js/i18n/grid.locale-cn.js" type="text/javascript"></script>
<script src="${basePath!}/static/jqgrid/js/jquery-ui.js" type="text/javascript"></script>
<script src="${basePath!}/static/layui/layui.js" type="text/javascript"></script>
<script src="${basePath!}/static/js/ztree/jquery.ztree.all.js" type="text/javascript"></script> 

<script src="${basePath!}/static/js/role/role.js" type="text/javascript"></script>
<base href="${basePath!}/">

</head>
<body>
<!-- 
<div class="layui-btn-group">
    <button class="layui-btn" id="add">增加</button>
    <button class="layui-btn" id="edit">编辑</button>
    <button class="layui-btn" id="delete">删除</button>
</div>

<table id="list2"></table>
<div id="pager2"></div> -->

 <div class="layui-fluid" style ="overflow-y: scroll">
    <div class="layui-card">
      <div class="layui-form layui-card-header layuiadmin-card-header-auto">
        <div class="layui-form-item">
	      	<div class="layui-inline">
	      	 <label class="layui-form-label">角色名称</label>
	       		<div class="layui-input-block">
	         		<input type="text" id="roleNameSreach"  placeholder="请输入" autocomplete="off" class="layui-input">
	      		</div>
	      	</div> 
      		<div class="layui-inline">
        		<button class="layui-btn layui-btn-sm"  id="deptSearch">查询</button>
        		<button class="layui-btn layui-btn-sm"   id="reset1">重置</button>
      		</div>
	    	<div class="layui-card-body">
	        	<div style="padding-bottom: 10px;">
		          <button class="layui-btn layui-btn-sm" id="add">新增</button>
		          <button class="layui-btn layui-btn-sm" id="menuPermission">设置菜单权限</button> 
		          <button class="layui-btn layui-btn-sm" id="delete">删除</button> 
	        	</div>
	       </div> 
       	</div>
  	  </div>
 	 <table id="deptTable" class="layui-hide"   lay-filter="deptFilter" style ="overflow-y: scroll"></table> 
   </div>
</div> 


<#--↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓add↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓-->
<#--带有 class="layui-fluid" 的容器中，那么宽度将不会固定，而是 100% 适应-->
<div id="addeditformdivid" hidden="" class="layui-fluid" style="margin: 15px;">
    <form class="layui-form" action="" id="addeditformid">
        <label hidden="true" id="editlabelid"></label>
        <input id="editid" name="id" value="" hidden/>
        <div class="layui-form-item">
            <label class="layui-form-label">角色名称</label>
            <div class="layui-input-block">
                <input type="text" id="name" name="name" lay-verify="name" autocomplete="off" placeholder="请输入角色名称" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">备注</label>
            <div class="layui-input-block">
                <input type="text" id="bz" name="bz" autocomplete="off" placeholder="请输入备注信息" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="addeditsubmitfilter">立即提交</button>
                <button id="reset" type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>

    </form>
</div>
<#--↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑add↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑-->



<#--菜单权限设置弹窗-->
<div  id="setpermisdiv" hidden="" class="layui-fluid" >
    <ul id="treeDemo" class="ztree"></ul>
    <button class="layui-btn" id="savesetpermis">保存权限设置</button>
    <button class="layui-btn" id="closesetpermis">关闭</button>
    <script type="text/javascript">
    </script>
</div>






<!--&lt;#&ndash;字段权限设置弹窗&ndash;&gt;
<div  id="setcolumnspermisdiv" hidden="" class="layui-fluid" >
    <ul id="columnstreeDemo" class="ztree"></ul>
    <button class="layui-btn" id="savesetcolumnspermis">保存字段权限设置</button>
    <button class="layui-btn" id="closesetcolumnspermis">关闭</button>
    <script type="text/javascript">
        $('#savesetcolumnspermis').on('click', function () {

            var id = jQuery("#list2").jqGrid('getGridParam', 'selrow');//jqgrid逻辑id，不是业务表单的主键字段id,这里要注意
            var ret = jQuery("#list2").jqGrid('getRowData', id);//通过jqgrid的逻辑id获取该行数据，通过数据对象ret来获取表单主键字段ret.id


            var nodes = zTreeObj2.getCheckedNodes(true);
            var permisArrIds=[];
            for(var i=0;i<nodes.length;i++){
                permisArrIds.push(nodes[i].id);
            }
            var permisIds=permisArrIds.join(",");
            alert(permisIds);

            $.ajax({
                type: "POST",
                url:"admin/role/saveColumnsSet",
                data:{permisIds:permisIds,roleId:ret.id},
                async: false,
                    error: function(request) {
                        layer.alert("与服务器连接失败/(ㄒoㄒ)/~~");
                    },
                    success: function(data) {
                    if(data.state=='fail'){
                        layer.alert(data.mesg);
                    }
                    if(data.state=='success'){
                        layer.open({
                            skin: 'layui-layer-molv',
                            type:1,
                            area:"10%",
                            content:data.mesg,
                            shadeClose:true,
                            end: function(){
                                layer.close(layerid);
                                jQuery("#list2").jqGrid().trigger("reloadGrid");//重新加载数据
                            }
                        });

                    }
                }
            });
        });
        $('#closesetcolumnspermis').on('click', function () {
            layer.close(layerid);
        });
    </script>
</div>-->

<script type="text/html" id="usernameTpl">
  <a href="javascript:void(0)" style="text-decoration:none" onclick="toEdit({{ d.id }})" class="layui-table-link" target="_blank">{{ d.name }}</a>
</script>
</body>
</html>



