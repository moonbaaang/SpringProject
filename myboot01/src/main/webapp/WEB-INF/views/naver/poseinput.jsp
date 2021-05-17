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
<h1> Pose estimate </h1>
<% 

String[] filelist = (String[])request.getAttribute("filelist");
for(String file : filelist){ //*.mp3, *.txt제외
	//"." 특수문자 분리
	String file_split[] = file.split("[.]"); //문자열 패턴 (자바 = a.b > 시작문자 a, 끝날때 b, 사이 한글자는 어떠한 문자든 상관없음) 다음과같이 해야 . 이라는 기호로 분리하라는 뜻
	String ext = file_split[file_split.length-1];
	if( !(ext.equals("mp3")||ext.equals("txt"))){
%>	<a href="/pose?image=<%=file %>"> <%=file %></a> <img src="/faceimages/<%=file%>", width=100px, height=100px> <br>
<% 
	}
}
%>

</body>
</html>