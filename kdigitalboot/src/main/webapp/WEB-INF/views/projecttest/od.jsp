<%@page import="ch.qos.logback.core.recovery.ResilientSyslogOutputStream"%>
<%@page import="java.math.BigDecimal"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String image = (String)request.getAttribute("image");
	String result = (String)request.getAttribute("odResult");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	var json = JSON.parse('<%=result%>')
	
	var imagecanvas = document.getElementById("imagecanvas")
	var context = imagecanvas.getContext("2d")
	context.fillStyle = 'red'
	context.font = '15px batang'
	context.strokeStyle = 'green'
	context.lineWidth = 3
	
	var image = new Image()
	image.src = "/projecttest/<%=image%>"
	image.onload = function(){
		context.drawImage(image, 10, 10, image.width, image.height)
		var names = json.predictions[0].detection_names
		var confidence = json.predictions[0].detection_scores
		var boxes = json.predictions[0].detection_boxes
		
		for(var i=0; i<names.length; i++){
			var y1 = boxes[i][0]*image.height
			var x1 = boxes[i][1]*image.width
			var y2 = boxes[i][2]*image.height
			var x2 = boxes[i][3]*image.width
			
			if(!(names[i]=="person")){
				context.fillText(names[i]+":"+parseInt(confidence[i]*100)+"%", x1+10, y1+10)
				context.strokeRect(x1+10, y1+10, x2-x1, y2-y1)
				$("#names").append("<a href='https://search.shopping.naver.com/search/all?query="+names[i]+"&cat_id=&frm=NVSHATC'>#"+names[i]+"</a>")
				
			}
		}
	}
	
}) //function end
</script>
</head>
<body>
<canvas id="imagecanvas" width=500 height=500 style="border: 2px solid pink"></canvas>
<div id="names"></div>
</body>
</html>