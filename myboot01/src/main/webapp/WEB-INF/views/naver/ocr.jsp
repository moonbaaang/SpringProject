<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%>
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
<h1></h1>
<script>
window.onload = function(){
	
	var div = document.getElementById("ocrResult");

<%
	String image = request.getParameter("image");
	String ocrResult = (String)request.getAttribute("ocrResult");
	JSONObject obj = new JSONObject(ocrResult);
	JSONArray images = (JSONArray)obj.get("images");
	JSONObject oneimage = (JSONObject)images.get(0);
	JSONArray fields = (JSONArray)oneimage.get("fields");

	for(int i=0; i<fields.length(); i++){
		JSONObject onefield = (JSONObject)fields.get(i);
		String inferText = (String)onefield.get("inferText");
		Boolean lineBreak = (Boolean)onefield.get("lineBreak");
		if(!lineBreak){
%>		div.innerHTML += "<%= inferText%> ";
<%
		} else {
			out.println();
%>			div.innerHTML += "<%= inferText%> "+"<br>";
<%	 		
		}
	}
%>
}
</script>

<img src="/faceimages/<%=image%>"><br>
<div id=ocrResult>
	<!--  inferText 출력하되, lineBreak true 이면 줄바꿈 출력-->
</div>
</body>
</html>