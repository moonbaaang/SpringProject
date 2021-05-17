<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="jquery-3.2.1.min.js"></script>
<script>
$(document).ready(function(){

});
</script>
</head>
<body>
<script>
var json = JSON.parse('<%=request.getAttribute("speechResult") %>');
var text = json.text;
$(document).ready(function(){
	$("#result").text(text) // .html(태그도 읽어줌) / .append (추가)
});
var textarea = document.getElementById("result");
textarea.innerHTML += text;
</script>

<textarea id=result rows=5 cols=100>
</textarea><br>
<audio src="/faceimages/<%=request.getParameter("image") %>" controls="controls"></audio>
</body>
</html>