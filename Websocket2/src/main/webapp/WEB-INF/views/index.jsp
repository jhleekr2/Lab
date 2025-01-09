<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WebSocketTest - chat</title>
</head>
<body>

<h1>웹 소켓 테스트 - 채팅</h1>
<div>
	<div class="group">
		<label>닉네임</label>
		<input type="text" name="name" id="name" class="short">
	</div>
</div>

<div>
	<button type="button" class="in">채팅방 들어가기</button>
</div>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.min.js"></script>

<script type="text/javascript">

$('.in').click(function() {
	let name = $('#name').val();
	
	if ($(event.target).data('name') != null && $(event.target).data('name') != '') {
		name = $(event.target).data('name');
	}
	
	let child = window.open('/socket/chat', 'chat', 'width=405', 'height=510');
	
	child.addEventListener('load', function() {
		//자식창 다 뜨고 나면 발생
		child.connect(name);
	});
	
	//비활성화
	$('.in').css('opacity', .5).prop('disabled', true);
	$('#name').prop('readOnly', true);
});

</script>

</body>
</html>