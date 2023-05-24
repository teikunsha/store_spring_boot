$(document).ready(function() {
	getUserSession();
});

$("#logout").click(function() {
	$.ajax({
		url: '/users/logout',
		type: 'GET',
		success: function (data) {
			alert("登出成功")
			location.href = "index.html"
		},
		error: function (xhr) {
			console.log("登出失敗");
		}
	});
})

function add_to_cart_login() {
	$(".btn-add-to-cart-login").click(function () {
		alert("請先登入");
		location.href = "login.html";
	})
}

function getUserSession() {
	$.ajax({
		url: '/users/getUserSession',
		dataType:'text',
		type: 'POST',
		success: function (data) {
			if(data == "OK") {
				$("#login").remove();
				$("#logout").css("display", "block");
			}else{
				$("#btn-add-to-cart").remove();
				$(".btn_add").append('<button id="btn-add-to-cart-login" type="button" class="btn-add-to-cart-login">加入購物車</button>');
				add_to_cart_login();
			}
		},
		error: function (xhr) {
			console.log("請求失敗")
		}
	});
}