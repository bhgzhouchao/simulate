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
                 url:"busi/busiData/addOrUpdateBusinessData",
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
		    	,{field:'month',  title: '月份', templet: '#usernameTpl',width:80}
		    	,{field:'dept',  title: '所属部门'}
		    	,{field:'totalPrice',  title: '总金额',width:80}
		    	,{field:'deptExamine',  title: '部门审批'}
		    	,{field:'deptExamineDate',  title: '部门审批时间',width:120}
		    	,{field:'leaderExamine',  title: '分管领导审批',width:120}
		    	,{field:'leaderExamineDate',  title: '分管领导审批时间',width:140}
		    	,{field:'submitDate',  title: '提交审批时间',width:120}
		    	,{field:'createUser',  title: '创建者'}
		    	,{field:'createTime',  title: '创建时间'}
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
	
	function tableReload(){
		tableid.reload({
	        page: {
	          curr: 1 //重新从第 1 页开始
	        }
	        ,where: {
	        	month  : $('#month').val()
	        }
	      });
	}
	 
	//初始化日期控件
	 layui.use('laydate', function(){
		 var laydate = layui.laydate;
		 laydate.render({
			 elem: '#month'
			 ,type: 'month'
		 });
		 laydate.render({
			 elem: '#addMonth'
			 ,type: 'month'
		 });
	});
	
	/*//添加按钮点击事件
     $("#add").click(function () {
        $.ajax({
             type: "POST",
             url:"busi/busiData/toBusinessDataAdd",
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
                     for (var i = 0; i < data.businessList.length; i++) {
                     }
                     layerid=layer.open({//开启表单弹层
                         skin: 'layui-layer-molv',
                         area:['80%','90%'],
                         type: 1,
                         title:'业务数据录入',
                         content: $('#addeditformdivid') //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
                     });

                 }
             }
         });
     });*/
	 $("#add").click(function () {
		 mask = layer.load(1, {shade: [0.8, '#393D49']});
		 layer.open({
			title: "业务数据录入",
			type: 2, 
			skin: 'layui-layer-molv',
			area: ['80%', '90%'],
			move: true,
			shadeClose: false,
			content: 'busi/busiData/toBusinessDataAdd' ,
			success: function(layero, index){
				//关闭弹出层遮罩
				layer.close(mask);
			},
			cancel: function(index, layero){ 
				layer.confirm('确定退出当前页面？', {
					skin: 'layer-ext-myskin',
					btn:['确定', '取消']}, 
				function(){
					layer.closeAll()
				})
				return false; 
			},
			end:function(){
				// 加载数据
				tableReload();
			}
		}); 
	 });
 }
 