<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<% String result = (String)request.getAttribute("objectdetectionResult"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#result").text('<%=result%>');
    var json = JSON.parse('<%=result%>');// js 문자열형태--json 형태 변환
    $('#imagecanvas').click(function(){
    		$("#count").text("탐지객체수 = " + json.predictions[0].num_detections +"개");
        	$("#names").text("이름들 = " + json.predictions[0].detection_names);
        	$("#confidence").text(" 확률 = ");
        	for(var i = 0; i < json.predictions[0].num_detections; i++ ){
        		$("#confidence").append
        		(parseInt(parseFloat(json.predictions[0].detection_scores[i]) * 100) + "% , ");

        	    $('#imagecanvas').click(function(){
        	    	$("#count").text("");
        	    	$("#names").text("");
        	    	$("#confidence").text("");
        	    });	
    	}
    });
    

    
	// 캔버스에 이미지 로드( canvas 태그 + canvas 자바스크립트 라이브러리)
	var imagecanvas = document.getElementById("imagecanvas");//htmlobject타입
	var context = imagecanvas.getContext("2d");
	
	context.fillStyle="red";
	context.font = "15px batang";
	context.strokeStyle="green";
	context.lineWidth=3
	
	//이미지 로드
	var image = new Image();
	image.src = '/faceimages/<%=request.getParameter("image")%>';
	image.onload = function(){
		context.drawImage(image, 0, 0, image.width, image.height);
		var names = json.predictions[0].detection_names;
		var confidence = json.predictions[0].detection_scores;
		var boxes = json.predictions[0].detection_boxes;
		for(var i = 0; i < names.length;i++){
			if(confidence[i] >= 0.5){
			var y1 = boxes[i][0] * image.height; //세로시작지점
			var x1 = boxes[i][1] * image.width;//가로시작지점
			var y2 = boxes[i][2] * image.height; //세로종료지점
			var x2 = boxes[i][3] * image.width;//가로종료지점	
			//이름 : 99% 출력
			context.fillText
			(names[i]+":"+parseInt(confidence[i]*100)+"%" , x1+10, y1+10);
			
			//사각형 그려서 출력
			context.strokeRect(x1,y1, x2-x1, y2-y1);
		   }//if end
		}//for end
		
	}
	//var imagecanvas = $("#imagecanvas");//jquery객체타입
	//var context = imagecanvas.getContext("2d");

    
});
</script>
<%-- <script>
window.onload = function(){
	var result = document.getElementById("result");	
	var count = document.getElementById("count");
	var names = document.getElementById("names");
	var confidence = document.getElementById("confidence");
	result.innerHTML += '<%=result%>'
//서비스나 컨트롤러 구현한다면 - org.json.JSONObject, org.json.JSONArray
//jsp   구현한다면 -  1 자바  json 파싱 - org.json.JSONObject, org.json.JSONArray
                   2. 자바스크립트 json  파싱 -  JSON.parse  
    var json = JSON.parse('<%=result%>');// js 문자열형태--json 형태 변환
    count.innerHTML = "탐지객체수 = " + json.predictions[0].num_detections +"개";
    names.innerHTML = "객체이름 :  " + json.predictions[0].detection_names ;
    confidence.innerHTML = "확률 " ;
    for(var i = 0; i < json.predictions[0].detection_scores.length; i++){
    	confidence.innerHTML += 
    	parseInt(parseFloat(json.predictions[0].detection_scores[i]) * 100) +"% ,";
    	
    }
    
	// id="count" 태그에 탐지객체수 = xx개  출력
	// id="names" 태그에 객체이름 :  "cat"" , ... 출력
	// id="confidence" 태그에  확률 99% 변경, , , ...  출력
	//  '0.997798' --->실수로 변경  * 100--> 99.7798--> 정수 변환 --> "%"
	//Not a Number
}//window onload end


</script> --%>
</head>
<body>

<div id="result"></div>
<div id="count"></div>
<div id="names"></div>
<div id="confidence"></div>
<canvas id="imagecanvas" width=1000 height=1000 style="border : 2px solid red"></canvas>
</body>
</html>












<!-- 	var image = new Image();
	image.src = '/faceimages/<%=request.getParameter("image")%>';
	image.onload = function(){
		context.drawImage(image, 0, 0, image.width, image.height);
		context.fillStyle="red";
		context.font="10px batang";
		context.strokeStyle = "green";
		context.lineWidth = 2;
		
		var names = json.predictions[0].detection_names;
		var confidence = json.predictions[0].detection_scores;
		var boxes = json.predictions[0].detection_boxes;
		
		for(var  i = 0; i < names.length; i++){
			if(confidence[i] >= 0.9){
			var x1 = boxes[i][0]*image.width;
			var y1 = boxes[i][1]*image.height;
			var x2 = boxes[i][2]*image.width;
			var y2 = boxes[i][3]*image.height;
			context.fillText
			(names[i]+":"+ parseInt(parseFloat(confidence[i]) * 100)+"%", 
			x1+10, y1+10);
			context.strokeRect(x1, y1, x2-x1, y2-y1);
			}//if 
		}//for
		
		
	} -->