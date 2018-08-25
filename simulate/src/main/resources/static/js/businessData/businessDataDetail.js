/*初始化页面方法*/
function init(){
	//获取审批状态
	var deptExamineFlg = $("#deptExamineFlg").val();
	var leaderExamineFlg = $("#leaderExamineFlg").val();
	if(deptExamineFlg == "2" || deptExamineFlg == "3"){
		//禁用按钮
	    $("#submitBtn").attr("disabled", true);
	    $("#businessDataAdd").attr("disabled", true);
	    $("#reset").attr("disabled", true);
	    //禁用输入框
	    $("input[type='text']").each(function () {
	    	　$("#" + this.id).attr("disabled", true);
	    });
	    //禁用多行文本框
	    $("#remarks").attr("disabled",true); 
	}
	
}

$(function () {
     init();
});
 