<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>用户管理表格</title>

<link href="${basePath!}/static/css/comm.css" type="text/css" media="screen" rel="stylesheet"/>
<link href="${basePath!}/static/css/global.css" type="text/css" media="screen" rel="stylesheet"/>
<link href="${basePath!}/static/plugins/font-awesome/css/font-awesome.min.css" type="text/css" media="screen" rel="stylesheet"/>
<link href="${basePath!}/static/layui/css/layui.css" type="text/css" media="screen" rel="stylesheet"/>
<link rel="stylesheet" href="${basePath!}/static/css/admin.css" type="text/css"   media="all">

<!-- jquery插件包-必要 -->
<!-- 这个是所有jquery插件的基础，首先第一个引入 -->
<script src="${basePath!}/static/js/jquery.min.js" type="text/javascript"></script>
<script src="${basePath!}/static/jqgrid/js/jquery.jqGrid.min.js" type="text/javascript"></script>


<script src="${basePath!}/static/layui/layui.js" type="text/javascript"></script>
<script src="${basePath!}/static/js/dept/dept.js" type="text/javascript"></script>
<base href="${basePath!}/">

</head>
<body>
  <div class="layui-fluid" style="overflow-y: scroll">
    <div class="layui-card">
      <div class="layui-form layui-card-header layuiadmin-card-header-auto">
        <div class="layui-form-item">
          <div class="layui-inline">
            <label class="layui-form-label">部门编号</label>
            <div class="layui-input-inline">
              <input type="text" id="deptId"  placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
          </div>
          <div class="layui-inline">
            <label class="layui-form-label">部门名称</label>
            <div class="layui-input-inline">
              <input type="text" id="deptName" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
          </div>
          <div class="layui-inline">
            <button class="layui-btn layui-btn-sm"   data-type="reload" id="deptSearch">查询</button>
            <button class="layui-btn layui-btn-sm" type="reset"   id="reset">重置</button>
          </div>
        </div>
      </div>

      <div class="layui-card-body">
        <div style="padding-bottom: 10px;">
          <button class="layui-btn layui-btn-sm" id="addBtn">新增</button>
          <button class="layui-btn layui-btn-sm" id="delBtn">删除</button> 
        </div>
      </div>
        <table id="deptTable" class="layui-hide"></table> 
    </div>
  </div>
  
  <div id="addDept" class="layui-fluid" style="margin: 15px;">
    <form class="layui-form" action="" id="addEditFrom">
        <label hidden="true" id="editlabelid"></label>
        <input id="editid" name="id" value="" hidden/>
        <div class="layui-form-item">
            <label class="layui-form-label">部门编号</label>
            <div class="layui-input-block">
                <input type="text" id="addDeptId" name="deptId" lay-verify="required" placeholder="请输入部门编号" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">部门名称</label>
            <div class="layui-input-block">
            	<input type="text" id="addDeptName" name="deptName" lay-verify="required"  placeholder="请输入部门名称" class="layui-input">
        	</div>
        </div>
        
        <div class="layui-form-item">
            <label class="layui-form-label">部门负责人</label>
            <div class="layui-input-block">
            	<select id="deptAct" name="deptAct">
				</select>
        	</div>
        </div>
        
        <div class="layui-form-item">
            <label class="layui-form-label">分管院领导</label>
            <div class="layui-input-block">
            	<select id="deptLeader" name="deptLeader">
				</select>
        	</div>
        </div>
        

        <div class="layui-form-item">
            <label class="layui-form-label">备注</label>
            <div class="layui-input-block">
			   <textarea id="remarks" name ="remarks" placeholder="请输入内容" class="layui-textarea"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" id="addDeptbtn" >保存</button>
                <button id="reset" type="reset" class="layui-btn layui-btn-primary  layui-layer-close ">取消</button>
            </div>
        </div>
    </form>
</div>
  
</body>
</html>