<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js" integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO" crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>뉴스 관리 앱</title>
</head>
<body>
	<div class="container w-75 mt-5 mx-auto">
	<h2>${news.title }</h2>
	<hr>
	<div class="card w-75 mx-auto">
		<img class="card-img-top" src="${news.img }">
		<div class="card-body">
			<h4 class="card-title">Date: ${news.date }</h4>
			<p class="card-text">Content: ${news.content }</p>
		</div>
	</div>
	<hr>
	<a href="javascript:history.back()" class="btn btn-primary"> << Back </a>
	</div>
</body>
</html>