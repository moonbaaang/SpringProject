<%@page import="java.math.BigDecimal"%>
<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%String image = (String)request.getParameter("image"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="jquery-3.2.1.min.js"></script>
<script>
window.onload = function(){
	var samplecanvas = document.getElementById("samplecanvas");
	var samplecontext = samplecanvas.getContext("2d");
	var image = new Image();
	image.src = "/faceimages/<%=image%>";
	image.onload = function(){
		samplecontext.drawImage(image, 0, 0, image.width, image.height);
		
	<%
		String faceResult2 = (String)request.getAttribute("faceResult2");
	
		JSONObject obj = new JSONObject(faceResult2);
		JSONArray faces = (JSONArray)obj.get("faces");
		
		for(Object one : faces){
			JSONObject roi = (JSONObject)((JSONObject)one).get("roi");
			int x = (int)roi.get("x");
			int y = (int)roi.get("y");
			int width = (int)roi.get("width");
			int height = (int)roi.get("height");
			
	%>		
		var x = <%=x %>;
		var y = <%=y %>;
		var width = <%=width %>;
		var height = <%=height %>;
		
		var faceimage = samplecontext.getImageData(x, y, width, height);
		
		// 색상 반전 - rgb/i - 이미지 구성 기본 단위 - 점(pixel) - 1개 (구성성분 , rgbi)
		var data = faceimage.data;
		var numpixels = data.length;
		for(var i = 0; i<numpixels; i=i+4){
			data[i] = 255 - data[i]; //r 반전처리
			data[i+1] = 255 - data[i+1]; //g 반전처리
			data[i+2] = 255 - data[i+2]; //b 반전처리
			
		} //색상 반전 종료
		
		samplecontext.putImageData(faceimage, x, y);
		
		var targetcanvas = document.getElementById("targetcanvas");
		var targetcontext = targetcanvas.getContext("2d");
		targetcontext.putImageData(faceimage, 50, 50);
	<%	
		} //for end
	%>
	
	} //onload end
} // function end
</script>
</head>
<body>
<h1> 얼굴 인식 서비스 </h1>
<h1>이미지 전체 캔버스</h1>
<canvas id="samplecanvas" width=500 height=500 style="border : solid 2px pink"></canvas>
<h1>얼굴만 캔버스</h1>
<canvas id="targetcanvas" width=300 height=300 style="border : solid 2px pink"></canvas>

</body>
</html>