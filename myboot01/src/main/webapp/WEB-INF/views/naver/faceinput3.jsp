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
<h1> 얼굴 인식 후 색상 반전하기 </h1>
<% 

String[] filelist = (String[])request.getAttribute("filelist");
for(String file : filelist){
%>	<a href="/face3?image=<%=file %>"> <%=file %>(얼굴인식후 색상반전)</a> <img src="/faceimages/<%=file%>", width=100px, height=100px> <br>
<% 
}
%>

</body>
</html>