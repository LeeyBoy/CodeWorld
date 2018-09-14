$(document).ready(function(){
	//当登陆表单登陆时触发
	$("#login-form").submit(function(){
		encryption();
	});
});

//加密
function encryption(){
	var userName = $("#userName").val();
	var password = $("#password").val();
	//第一次加密密码，第二次加密第一次加密后的密码+小写的用户名
	var encryptionPassword =  MD5(MD5(password)+userName.toLowerCase());
	//重新设置加密后的密码
	$("#password").val(encryptionPassword);
}