<%@page import="java.math.BigDecimal"%>
<%@page import="org.json.JSONArray"%>
<%@ page import="org.json.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%	String image = request.getParameter("image"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
window.onload = function() {
	var bodynames = ["코", "목", "오른쪽 어깨", "오른쪽 팔굼치", "오른쪽 손목", "왼쪽 어깨", "왼쪽 팔굼치", "왼쪽 손목", "오른쪽 엉덩이", "오른쪽 무릎","오른쪽 발목", "왼쪽 엉덩이", "왼쪽 무릎", "왼쪽 발목", "오른쪽 눈", "왼쪽 눈", "오른쪽 귀", "왼쪽 귀", "오른쪽 귀"];
	var samplecanvas = document.getElementById("samplecanvas");
	var resultdiv = document.getElementById("resultdiv");
	var samplecontext = samplecanvas.getContext("2d");
	
	samplecontext.fillStyle = "red";
	samplecontext.lineWidth = 5;
	samplecontext.strokeStyle = "green";
	
	var image = new Image();
	image.src = "/faceimages/<%=image%>";
	image.onload = function() {
		samplecontext.drawImage(image, 0, 0, image.width, image.height);
<%
		String poseResult = (String)request.getAttribute("poseResult");
%>
		var json = JSON.parse('<%=poseResult%>');
		for(var i in json.predictions) {
			for(var ii in json.predictions[i]) {
				var body = json.predictions[i][ii];
				resultdiv.innerHTML += ii + " 신체 부위 : x = " + body.x + " y = " + body.y + "<br>";
				samplecontext.fillRect(body.x * image.width, body.y * image.height, 5, 5);
				
				// 전체 신체 부위 이름 출력
				// samplecontext.fillText(bodynames[ii], body.x * image.width + 5, body.y * image.height + 5);
				
				// 왼쪽 손목, 오른쪽 손목만 이름 출력
				if(bodynames[ii].indexOf("오른쪽 손목") >= 0 || bodynames[ii].indexOf("왼쪽 손목") >= 0 ) {
					samplecontext.fillText(bodynames[ii], body.x * image.width + 5, body.y * image.height + 5);
				}
				if(bodynames[ii].indexOf("오른쪽 손목") >= 0 ) {
					var rightx = body.x * image.width;
					var righty = body.y * image.height;
				}
				if(bodynames[ii].indexOf("왼쪽 손목") >= 0 ) {
					var leftx = body.x * image.width;
					var lefty = body.y * image.height;
				}
				// 양쪽 손목 서로 선 연결
				samplecontext.beginPath();
				samplecontext.moveTo(leftx, lefty);
				samplecontext.lineTo(rightx, righty);
				samplecontext.closePath();
				samplecontext.stroke();
				
			}
		}
	} // image onload end
} // window onload end
</script>
</head>
<body>
<%=request.getAttribute("poseResult") %>
<div id="resultdiv"></div>
<h1> 이미지 전체 캔버스 </h1>
<canvas id="samplecanvas" width=900 height=900 style="border : solid 2px pink"></canvas>
</body>
</html>