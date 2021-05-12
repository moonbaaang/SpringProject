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
<% 

String[] filelist = (String[])request.getAttribute("filelist");
for(String file : filelist){
	if(file.contains("font")){ // 파일 이름에 font가 붙은 것만
%>	<a href="/ocr?image=<%=file %>"> <%=file %></a> <img src="/faceimages/<%=file%>", width=100px, height=100px> <br>
<% 
	}
}
%>

</body>
</html>