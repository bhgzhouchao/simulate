<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>指标测算表</title>

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

<script src="${basePath!}/static/js/indexCalculation/indexCalculation.js" type="text/javascript"></script>
<base href="${basePath!}/">
<style type="text/css">
	.spanclsss{text-align:center;display: block;font-weight:bold;font-size:20px;left:60%}
</style>
</head>
<body>
<div class="layui-fluid"  >
    <div class="layui-card">
      <div class="layui-form layui-card-header layuiadmin-card-header-auto">
        <div class="layui-form-item">
	      	<div class="layui-inline">
	      	 <label class="layui-form-label">测算类型</label>
	       		<div class="layui-input-block">
	            	<select id="calculationType" >
	            		<!-- <option value="" >请选择</option> -->
						<option value="season">季度</option>
						<option value="month">月份</option>
					</select>
        		</div>
	      	</div> 
      		<div class="layui-inline">
        		<button class="layui-btn layui-btn-sm"  id="search">查询</button>
      		</div>
       	</div>
  	  </div>
	 <span id="indexTabelSpan" class="spanclsss">电科院2018年内模指标测算表</span>
	 <span style="text-align:right;display: block;font-weight:bold;font-size:16px;">单位:万元</span>
	<table id="indexCalculationTable" class="layui-hide"  lay-filter="deptFilter" ></table>
   </div>
</div> 
</body>
</html>