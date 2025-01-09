<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://me2.do/5BvBFJ57">
</head>
<body>
	<!-- test.jsp -->
	<h1>WebSocket <small>연결 테스트</small></h1>
	
	<div>
		<button type="button" class="in" id="btnConnect">연결하기</button>
		<button type="button" class="out" id="btnDisConnect">종료하기</button>
	</div>
	<hr>
	
	<div>
		<input type="text" class="long" id="msg">
		<button type="button" id="btnMsg">보내기</button>
	</div>
	
	<div class="message full"></div>
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
		<script>
		const url = 'ws://localhost:8088/testserver.do';
		
		let ws; //웹 소켓 참조 변수
        
		$('#btnConnect').click(function() {
			
			ws = new WebSocket(url);
			
			ws.onopen = function(evt) {
				log('서버와 연결하였습니다.');
			};
			
			ws.onclose = function(evt) {
				log('서버와 연결이 종료되었습니다.');
			};
			
			ws.onmessage = function(evt) {
				log(evt.data);
			};
			
			ws.onerror = function(evt) {
				log('에러가 발생했습니다.' + evt);
			};
		});
		
		$('#btnDisConnect').click(function() {
			ws.close();
			log('서버와 연결 종료를 시도합니다.');
			
		});
		
		function log(msg) {
			$('.message').prepend(`
				<div>[\${new Date().toLocaleTimeString()}] \${msg}</div>		
			`);
		}
		
		$('#btnMsg').click(function() {
			ws.send($('#msg').val());
			log('메시지를 전송했습니다.');
			
			$('#msg').val('');
		});
		
	</script>
</body>
</html>