<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <%=request.getAttribute("poseResult") %> --%>
<% String image = (String)request.getParameter("image"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="jquery-3.2.1.min.js"></script>

<script>

/*
1. id="samplecanvas"에 image 변수 저장된 파일 그리기
2. 1번 이미지에서 얼굴 x, y, width, height 값 가져오기
3. 1번 이미지에서 2번 영역 얼굴만 복사하여 id=targetcanvas로 붙여넣기
*/
window.onload = function(){
	var bodynames = ["코","목","오른쪽어깨","오른쪽팔굼치","오른쪽손목","왼쪽어깨","왼쪽팔굼치","왼쪽손목","오른쪽엉덩이","오른쪽무릎",
		"오른쪽발목","왼쪽엉덩이","왼쪽무릎","왼쪽발목","오른쪽눈","왼쪽눈","오른쪽귀","왼쪽귀"];
	var resultdiv = document.getElementById("result")
	var samplecanvas = document.getElementById("samplecanvas");
	var samplecontext = samplecanvas.getContext("2d");
	
	samplecontext.fillStyle ="red";
	samplecontext.linewidth = 5;
	samplecontext.strokeStyle = "green";
	samplecontext.font = "10px batang";
	
	var image = new Image();
	image.src = "/faceimages/<%=image%>";
	image.onload = function(){
		samplecontext.drawImage(image, 0, 0, image.width, image.height);
	
	<%	 		
		String poseResult = (String)request.getAttribute("poseResult");	
	%>
		var json = JSON.parse('<%=poseResult%>');
		
		for(var i in json.predictions){
			for(var j in json.predictions[i]){
				var body = json.predictions[i][j]; // 18개 객체 "0":{score=xx, x= ,y= }... , "17":{} / [사람][신체부위]
				resultdiv.innerHTML += j + " 신체부위 : x = "+body.x+" y = "+body.y +"<br>";
				samplecontext.fillRect(body.x * image.width, body.y * image.height, 5, 5); //strokeRect, 
				
				//전체 신체부위 이름 출력
				//samplecontext.fillText(bodynames[j], body.x * image.width+5, body.y * image.height+5) 
				
				//1.왼쪽 오른쪽 손목만 이름 출력
				if(bodynames[j].indexOf("오른쪽손목")>=0  
					|| bodynames[j].indexOf("왼쪽손목")>=0 ){ //bodynames[j] == "오른쪽손목"도 가능
					samplecontext.fillText(bodynames[j], body.x * image.width+5, body.y * image.height+5)
				}
				//2. 왼쪽 오른쪽 손목 선 연결
				if(bodynames[j].indexOf("오른쪽손목")>=0){
					var rightx = body.x * image.width;
					var righty = body.y * image.height;
				}
				if(bodynames[j].indexOf("왼쪽손목")>=0){
					var leftx = body.x * image.width;
					var lefty = body.y * image.height;
				}
				
				samplecontext.beginPath();
				samplecontext.moveTo(leftx, lefty);//시작점
				samplecontext.lineTo(rightx, righty);//종료점까지 선
				samplecontext.closePath();
				samplecontext.stroke();
				//samplecontext.arc(x,y, 반지름, 2파이, true);
			
			}	//inner for		
		}// outer for
	
	} // image.onload end
} //window.onload end
</script>
</head>
<body>
<h1> 신체 인식 서비스 </h1>
<div id=result></div>
<h1>이미지 전체 캔버스</h1>
<canvas id="samplecanvas" width=900 height=900 style="border : solid 2px pink"></canvas>
</body>
</html>