<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>用户管理表格</title>

<link href="${basePath!}/static/css/comm.css" type="text/css" media="screen" rel="stylesheet"/>
<link href="${basePath!}/static/css/admin.css" type="text/css" media="screen" rel="stylesheet"/>

<!-- jqGrid组件基础样式包-必要 -->
<link href="${basePath!}/static/jqgrid/css/ui.jqgrid.css" type="text/css" media="screen" rel="stylesheet"/>
<link href="${basePath!}/static/jqgrid/css/jquery-ui.css" type="text/css" media="screen" rel="stylesheet"/>
<link href="${basePath!}/static/css/global.css" type="text/css" media="screen" rel="stylesheet"/>
<link href="${basePath!}/static/plugins/font-awesome/css/font-awesome.min.css" type="text/css" media="screen" rel="stylesheet"/>
<link href="${basePath!}/static/layui/css/layui.css" type="text/css" media="screen" rel="stylesheet"/>

<!-- jquery插件包-必要 -->
<!-- 这个是所有jquery插件的基础，首先第一个引入 -->
<script src="${basePath!}/static/js/jquery.min.js" type="text/javascript"></script>
<script src="${basePath!}/static/jqgrid/js/jquery.jqGrid.min.js" type="text/javascript"></script>
<!-- jqGrid插件的多语言包-非必要 -->
<!-- 在jqgrid/js/i18n下还有其他的多语言包，可以尝试更换看效果 -->
<script src="${basePath!}/static/jqgrid/js/i18n/grid.locale-cn.js" type="text/javascript"></script>
<script src="${basePath!}/static/jqgrid/js/jquery-ui.js" type="text/javascript"></script>
<script src="${basePath!}/static/layui/layui.js" type="text/javascript"></script>

<script src="${basePath!}/static/js/quota/quota.js" type="text/javascript"></script>
<base href="${basePath!}/">

</head>
<body >
 <div class="layui-fluid" style ="overflow-y: scroll">
    <div class="layui-card">
      <div class="layui-form layui-card-header layuiadmin-card-header-auto">
        <div class="layui-form-item">
	      	<div class="layui-inline">
	      	 	<label class="layui-form-label">年份</label>
	       		<div class="layui-input-block">
	         		<input type="text" id="yearSearch" class="layui-input">
	      		</div>
	      	</div> 
      		<div class="layui-inline">
        		<button class="layui-btn layui-btn-sm"  id="deptSearch">查询</button>
        		<!-- <button class="layui-btn layui-btn-sm"   id="reset1">重置</button> -->
      		</div>
	    	<div class="layui-card-body">
	        	<div style="padding-bottom: 10px;">
		          <button class="layui-btn layui-btn-sm" id="add">新增</button>
		          <button class="layui-btn layui-btn-sm" id="delete">删除</button> 
	        	</div>
	       </div> 
       	</div>
  	  </div>
 	 <table id="deptTable" class="layui-hide"   lay-filter="deptFilter" style ="overflow-y: scroll"></table> 
   </div>
</div> 
  
<div id="addeditformdivid" hidden="" class="layui-fluid" style="margin: 15px;">
    <form class="layui-form" action="" id="addeditformid">
       <label hidden="true" id="editlabelid"></label>
        <input id="editid" name="id" value="" hidden/>
        <div class="layui-form-item">
            <label class="layui-form-label" >年份</label>
            <div class="layui-input-block" >
            	<input type="text" id="year" name="year" class="layui-input" required lay-verify="required">
        	</div>
        </div>
       
        <div class="layui-form-item">
            <label class="layui-form-label">成本项目</label>
            <div class="layui-input-block">
            	<select id="subject" name="subject">
            	
				</select>
        	</div>
        </div>
        
         <div class="layui-form-item">
            <label class="layui-form-label">金额</label>
            <div class="layui-input-block">
            	<input type="text" id="subjectValue" name="subjectValue"  placeholder="请输入成本项目金额" required lay-verify="required" class="layui-input" maxlength="9">
        	</div>
        </div>
        
        <div class="layui-form-item">
            <label class="layui-form-label">备注</label>
            <div class="layui-input-block">
			   <textarea id="remarks" name ="remarks" placeholder="请输入内容" class="layui-textarea" maxlength="200"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="addeditsubmitfilter">保存</button>
                <button id="reset" type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>
 <script type="text/html" id="usernameTpl">
  <a href="javascript:void(0)" onclick="toEdit({{ d.id }})" class="layui-table-link" target="_blank">{{ d.subject }}</a>
</script>
</body>
</html>