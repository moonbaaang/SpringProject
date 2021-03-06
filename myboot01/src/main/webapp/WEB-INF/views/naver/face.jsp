<%@page import="java.math.BigDecimal"%>
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
<h1>닮은 연예인 찾아주기</h1>
<!--  JSONObject  / JSONArray 라이브러리 다운로드 -->
<%-- 
<%
	String faceResult = (String)request.getAttribute("faceResult");
	out.println(faceResult + "<br>");

	String faceInfo[] = faceResult.split("\"faces\"");
	out.println(faceInfo[1] + "<br>");
	
	String celeInfo[] = faceInfo[1].split("\"celebrity\"");
	out.println(celeInfo[celeInfo.length-1]+ "<br>");

	String one = celeInfo[celeInfo.length-1];
	
	int valueIndex = one.indexOf("\"value\":");
	int valueLength = "\"value\":".length();
	int confiIndex = one.indexOf("\"confidence\":");
	int confiLength = "\"confidence\":".length();
	
	out.println(celeInfo[celeInfo.length-1].substring(valueIndex + valueLength, confiIndex-1)+"<br>");
	out.println(celeInfo[celeInfo.length-1].substring(confiIndex + confiLength, confiIndex + confiLength + 8)+"<br>");
%><br>
--%>
<% 
	
	String faceResult = (String)request.getAttribute("faceResult");
	// {"info":{"size":{"width":264,"height":200},"faceCount":1},"faces":[{"celebrity":{"value":"송혜교","confidence":0.527008}}]} 닮은 연예인 이름 = 송혜교, 닮은 정도 = 53%
	JSONObject obj = new JSONObject(faceResult);
	//Object imsi = obj.get("faces"); // faces : [celebrity : {value:.. confidence:.. }, {} ]
	JSONArray faces = (JSONArray)obj.get("faces");
	boolean find = false;
	for(Object cele : faces){
		JSONObject celebrity = (JSONObject)((JSONObject)cele).get("celebrity");
		find = true;
		//String value = (String)celebrity.get("value");
		BigDecimal confidence = (BigDecimal)celebrity.get("confidence");
		
		out.println("닮은 연예인 이름 = " + celebrity.get("value") + ", 닮은 정도 = "
		+ Math.round(confidence.doubleValue()*100) + "%<br>");
	}
	
	String image = request.getParameter("image");
	if (find == false){
		out.println("닮은 연예인을 찾을 수 없습니다.<br>");
	}
%>


<img src="/faceimages/<%=image%>">
</body>
</html>