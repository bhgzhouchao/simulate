 var layerid;//当前弹层id;这个id可以定义多个，主要的目的是为了在回调函数关闭弹层时使用的
 var tableid;
 $(function () {
     //页面加载完成之后执行
     pageInit();

     layui.use(['layer','form','layedit','laydate'], function(){
         var layer = layui.layer,
         layedit = layui.layedit,
         laydate = layui.laydate,
         $ = layui.$,
         form = layui.form;

         //自定义验证规则
         form.verify({
             //price: [/[1-9]\d{0,5}\.\d$|^[1-9]\d{0,5}\.\d{1}$|^[1-9]\d{0,5}\.\d{2}$|^[1-9]\d{0,5}$|^[0]\.\d{1}$|^[0]\.\d{2}$/, '单价只能是数字，且整数部分长度不超过6位']
         });

         //监听提交
         form.on('submit(addeditsubmitfilter)', function(data) {
        	 
        	 var price  =$("#price").val();
        	 var pattern = /^[1-9]\d{0,4}\.\d$|^[1-9]\d{0,4}\.\d{1}$|^[1-9]\d{0,5}\.\d{2}$|^[1-9]\d{0,5}$|^[0]\.\d{1}$|^[0]\.\d{2}$/;
             if (!pattern.test(price)) {
                 layer.alert("单价只能是数字，且整数部分长度不超过6位");
                 return false;
             }
        	 
             //为了防止form中的id值被重置后置空,将编辑的id存放在label中
             $("#editid").val($("#editlabelid").html() );
             $("#editlabelid").html("");

             $.ajax({
                 type: "POST",
                 url:"admin/business/addOrUpdateBusi",
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
 
 
 /**
  * 单价输入框输入限制
  */
 function clearPrice(obj){ 
 	//清除“数字”和“.”以外的字符  
     obj.value = obj.value.replace(/[^\d.]/g,"");  
     //只保留第一个. 清除多余的  
     obj.value = obj.value.replace(/\.{2,}/g,"."); 
     obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$","."); 
     //数字和点，且以点结尾的 eg:123. 替换为123.0
     obj.value = obj.value.replace(/(\d+)\.$/g,"$1.0"); 
     //有小数点且首位是0的数字 0123.
     obj.value = obj.value.replace(/^[0](\d+)\.(\d)/g,"$1.$2"); 
     //只能输入一位小数 
     obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d{2}).*$/,'$1$2.$3');
     //以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的数字
     if(obj.value.indexOf(".")< 0 && obj.value !=""){
         obj.value= parseFloat((obj.value)).toFixed(1); 
     } 
 } 
 
 
 function toEdit(data){
	 $("#addDeptId").empty();
  	 $("#businessType").empty();
     $("#reset").click();
	 $.ajax({
         type: "POST",
         url:"admin/business/selectBusiById",
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
                 $("#editlabelid").html(data.business.id);//临时存放id，当提交时再去除赋值给input
                 $("#editid").val(data.business.id);
                 $("#addBusinessName").val(data.business.businessName);
                 $("#addBusinessId").val(data.business.businessId);
                 $("#remarks").val(data.business.remarks);
                 $("#price").val(data.business.price);
                 
                 for (var i = 0; i < data.busiTypeList.length; i++) {
                	 var item = data.busiTypeList[i];
                     $("#businessType").append("<option  value=" + item.id + ">" + item.businessTypeName + "</option>");
                 }
                 for (var i = 0; i < data.deptList.length; i++) {
                	 var item = data.deptList[i];
                     $("#addDeptId").append("<option  value=" + item.id + ">" + item.deptName + "</option>");
                 }
                 
                 $("#addDeptId").val(data.business.deptId);
                 $("#businessType").val(data.business.businessType);

                 //开启编辑表单所在的弹层。注意编辑和新建的表单是一套模板
                 layerid=layer.open({
                     skin: 'layui-layer-molv',
                     area:'60%',
                     type: 1,
                     title:'编辑业务',
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


 function pageInit() {
	 
	 layui.use('table', function(){
		  tableid = layui.table.render({
		    elem: '#deptTable'
		    ,url:'admin/business/busiList'
		    ,method:'post'
		    ,cols: [[
		    	 {type:'checkbox'}
		    	,{field:'businessId',  title: '业务编号'}
		    	,{field:'businessName',  title: '业务名称', templet: '#usernameTpl'}
		    	,{field:'deptId',  title: '所属部门'}
		    	,{field:'businessType',  title: '业务分类'}
		    	,{field:'price',  title: '单价'}
		    	,{field:'remarks',  title: '备注',width:200}
		    ]]
		    ,page: true
		    ,skin: 'row'
		    ,size: 'sm'
		    ,even: true
		    ,height : 'full-150'
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
	         	businessName  : $('#businessName').val()
	         }
	       });
	   });
	
	 
	//单价输入框焦点失去事件
	$("#price").blur(function(){
		clearPrice(this)
	})
	 

     //添加按钮点击事件
     $("#add").click(function () {
     	$("#addDeptId").empty();
     	$("#businessType").empty();
        $("#reset").click();
        $.ajax({
             type: "POST",
             url:"admin/business/getBusiTypeList",
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
                	 //向表单业务分类填充数据
                     for (var i = 0; i < data.busiTypeList.length; i++) {
                    	 var item = data.busiTypeList[i];
                         $("#businessType").append("<option  value=" + item.id + ">" + item.businessTypeName + "</option>");
                     }
                     //向表单所属部门填充数据
                     for (var i = 0; i < data.deptList.length; i++) {
                    	 var item = data.deptList[i];
                         $("#addDeptId").append("<option  value=" + item.id + ">" + item.deptName + "</option>");
                     }
                     layerid=layer.open({//开启表单弹层
                         skin: 'layui-layer-molv',
                         area:'60%',
                         type: 1,
                         title:'新建业务',
                         content: $('#addeditformdivid') //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
                     });

                 }
             }
         });
     });
     	
   //重置
	$('#reset1').on('click', function(){
		$("#deptId").val("abc");
	    $('#businessName').val("");
	});
    
     

     $("#delete").click(function () {
    	var selectData = layui.table.checkStatus('deptTable');
    	var orderNosList = new Array();
     	if(selectData.data.length > 0){
     		for (var i = 0; i < selectData.data.length; i++) {
				orderNosList[i] = selectData.data[i].id;
			}
     		layer.open({
                content: '确定删除所选择的业务?',
                btn: ['确定', '取消'],//定义两个按钮，是和否
                yes: function(index, layero){//点击是时候的回调
                    //do something
                    layer.close(index); //如果设定了yes回调，需进行手工关闭
                    //请求后台，执行删除操作
                    $.ajax({
                        type: "POST",
                        url:"admin/business/deleteBusis",
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
 }
 