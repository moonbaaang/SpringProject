<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	li
	{
		background-color:pink;
		margin:auto;
		width:300px;
	}
</style>
<script src="resources/jquery-3.2.1.min.js"></script>
<script>
$(document).ready(function(){

});
</script>
</head>
<body>
<table border="3" >
<tr><th>글번호</th><th>게시글</th><th>조회수</th></tr>
<tr><td>${board.seq }</td><td>${board.contents }</td><td>${board.viewcount }</td></tr>
</table>
</body>
</html>