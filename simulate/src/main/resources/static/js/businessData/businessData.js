var layerid;//当前弹层id;这个id可以定义多个，主要的目的是为了在回调函数关闭弹层时使用的

$(function () {
     //页面加载完成之后执行
     pageInit();

     /*layui.use(['layer','form','layedit','laydate'], function(){
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
     });*/
 });
 
 
 
 var tableid;

 function pageInit() {
	 //初始化表格
	 layui.use('table', function(){
		  tableid = layui.table.render({
		    elem: '#businessDataTable'
		    ,url:'busi/busiData/businessDataList'
		    ,method:'post'
		    ,cols: [[
		    	 {type:'checkbox'}
		    	,{field:'month',  title: '月份', templet: '#usernameTpl'}
		    	,{field:'dept',  title: '所属部门'}
		    	,{field:'totalPrice',  title: '总金额'}
		    	,{field:'deptExamine',  title: '部门审批'}
		    	,{field:'deptExamineDate',  title: '部门审批时间'}
		    	,{field:'leaderExamine',  title: '分管领导审批'}
		    	,{field:'leaderExamineDate',  title: '分管领导审批时间'}
		    	,{field:'submitDate',  title: '提交审批时间'}
		    	,{field:'createUser',  title: '创建者'}
		    	,{field:'createTime',  title: '创建时间'}
		    	,{field:'remarks',  title: '备注',width:100}
		    ]]
		    ,page: true
		    ,skin: 'row'
		    ,size: 'sm'
		    ,even: true
			,limit:10  //默认20条数据一页
			,limits:[10,20,30,50]  //数据分页条
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
});
	 
	 
	 layui.use('laydate', function(){
		 var laydate = layui.laydate;
		 laydate.render({
			 elem: '#month'
			 ,type: 'month'
		 });
	});
	 
 }
 