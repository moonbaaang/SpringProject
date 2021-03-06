<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/jquery-3.2.1.min.js"></script>
<script>
$(document).ready(function() {
//입장 버튼 클릭시 채팅창 보이게
//퇴장 버튼 클릭시 채팅창 안보이게
$("#enter").on('click', function(){
	$("#chatbox").css("display","block");
	send();
});// 입장

$("#exit").on('click', function(){
	$("#chatbox").css("display","none");
	$("#messageWindow").text("");
});//퇴장
});//ready end

function send(){
	//질문 읽어오자
	var inputMessage = $("#inputMessage").val();
	if(inputMessage != ""){// 입장
		$("#messageWindow").append("<div><span style=background-color:yellow;margin:10px;float:right;border-radius:5px 5px 5px 5px>나 : " + inputMessage + "</span></div><br><br>");	
	}
	$.ajax({
		 url : "/chatbot",
		 type : "post",
		 data : {"message" : inputMessage}, //입장시 질문 X > 컨트롤러는 웰컴메시지 출력해줌
		 dataType : 'json',
		 success : function(server_response){ //웰컴 or 실패 or 정상 답변
			 //$("#messageWindow").text(JSON.stringify(server_response));
			var bubbles = server_response.bubbles;
			for(var b in bubbles){
				if(bubbles[b].type == 'text'){ // 텍스트를 음성 변환, 텍스트 답변 처리 시작
					$("#messageWindow").append
					("<div><span style=background-color:#ffffff;>chatbot : " + bubbles[b].data.description + "</span></div><br>");
				$.ajax({ //텍스트 음성으로 변환 응답
					url: '/chatbotvoice',
					type: 'get',
					data: {"text": bubbles[b].data.description,"speaker":"mijin"},
					success: function(mp3){ //경로는 전달되지 않음
						$("#voice").attr('src', "/faceimages/"+mp3);
						var audio = $("#voice").get(0);
						audio.play();
					}//success end
				})
					if(bubbles[b].data.url != null){
						$("#messageWindow").append
						("<a href='" + bubbles[b].data.url + "'>" + bubbles[b].data.url + "</a><br>");
					}// url 있으면 if end
				} // 텍스트 답변 처리 종료
				//이미지나 멀티링크 답변
				else if(bubbles[b].type == 'template'){//이미지 버튼 멀티링크....
					//이미지-이미지로 출력
					if(bubbles[b].data.cover.type == 'image'){
						$("#messageWindow").append
						("<img src='" + bubbles[b].data.cover.data.imageUrl + "'><br>");
					}
					//멀티링크답변 - 텍스트로 출력
					else if(bubbles[b].data.cover.type == 'text'){
						$("#messageWindow").append
						("<div><span style=background-color:#ffffff;>chatbot : " 
						+ bubbles[b].data.cover.data.description + "</span></div><br>");
					}
					//공통 - contentTable 배열 변수 포함. 링크이름 버튼이름 링크url 버튼url
					for(var ct in bubbles[b].data.contentTable){
						var  ctdata = bubbles[b].data.contentTable[ct];//링크 1개나 버튼 1개
						for(var ctdataindex in ctdata){
							var linkurl = ctdata[ctdataindex].data.data.action.data.url;
							var linktitle = ctdata[ctdataindex].data.title;
						}
						$("#messageWindow").append
						("<a href='" + linkurl + "'>" + linktitle + "</a><br>");
					}//공통 for end
				}//else if end
			}//for end
		 }
	});//ajax end
	$("#inputMessage").val("");
	
	//jquery 객체를 자바스크립트 객체 변환
	var obj = $("#messageWindow").get(0);
	// 자동 스크롤 아래로.
	//alert("obj.scrollTop=" + obj.scrollTop +"\nobj.scrollHeight="+obj.scrollHeight)
	obj.scrollTop = obj.scrollHeight;
	
}//send end

function enterkey(){
	//  엔터키 입력(a - 97  0 - 48 엔터키 - 13)하면 send  함수 동일 효과
	if(window.event.keyCode == 13){
		send();
	}
}
</script>
</head>
<body>
<button id="enter"> 입장 </button> &nbsp;&nbsp; <button id="exit"> 퇴장 </button>
<div id="chatbox" style="display:none;">
	<div id="messageWindow" style="background-color:#abcdef;width:500px;height:600px;overflow:scroll;"></div>
	<input type=text id="inputMessage" style="width:400px" onkeyup="enterkey()"> 
	<input type=submit value="send" onclick="send()">
	<button id="rec_start">녹음</button>&nbsp;<button id="rec_stop">정지</button>
	<audio id="voice" src=""></audio> <!-- controls="controls" 를 주면 시각화 -->
</div>
<script>
//녹음 정지 기능 구현
		const record = document.getElementById("rec_start")
        const stop = document.getElementById("rec_stop")
        //const soundClips = document.getElementById("sound-clips")
		//<div><audio><a></div> 화면에 보여줄 필요 없음
        
        if (navigator.mediaDevices) {
            console.log('getUserMedia supported.')
            const constraints = {
                audio: true
            }
            let chunks = []
            navigator.mediaDevices.getUserMedia(constraints)
                .then(stream => {
                    const mediaRecorder = new MediaRecorder(stream) // 녹음 api
                    record.onclick = () => { // 녹음버튼 클릭시
                        mediaRecorder.start() // 음성녹음 시작
                        console.log(mediaRecorder.state)
                        console.log("recorder started")
                        record.style.background = "red"
                        record.style.color = "black"
                    }
                    stop.onclick = () => {//정지 버튼 클릭시에
                        mediaRecorder.stop() //녹음 정지시켜라
                        console.log(mediaRecorder.state)
                        console.log("recorder stopped")
                        record.style.background = ""
                        record.style.color = ""
                    }
					//녹음 정지시킨 상태가 되면 실행하라
                    mediaRecorder.onstop = e => {
                        console.log("data available after MediaRecorder.stop() called.")
                        //저장파일명
                        const clipName = "mictest"+new Date().getTime().toString().substring(10);
                        //chunks에 저장된 녹음 데이터를 audio 양식으로 만들어라
                        const blob = new Blob(chunks, {
                            'type': 'audio/mp3'
                        })
                        chunks = []
                      
 						//파일 저장
 						//a.href=audioURL;
 						//a.download = clipName; //저장파일명
 						//a.innerHTML = 'MP3로 저장';
 						
 						// 내 스프링서버로 녹음한 데이터 업로드
 						//js FormData 객체 - <form ... <input type=file name=file  / form 태그 쓴거와 같은 효과
 						var formData = new FormData();
 						formData.append('file', blob, clipName+".mp3");
 						$.ajax({
 							url: '/mp3upload',
 							type: 'post',
 							data: formData,
 							processData: false, //string값이 아니다라는 정보
 							contentType: false, //파일전송시 부가적으로 필요함
 							success: function(response){ // 스프링 서버에 업로드 완료
 								//alert(response);
 								$.ajax({
 									url:'/chatbotspeech',
 									type: 'get',
 									data: {"mp3file": clipName+".mp3", "lang":"Kor"},
 									success: function(responsetext){
 										//alert(responsetext)
 										//String 타입 전달
 										//JSON.parse(responsetext); //json형태가된다
 										//JSON.stringify({}); //string형태가 된다.
 										var json = JSON.parse(responsetext);
 										$("#inputMessage").val(json.text);
 										
 										
 										//$("#inputMessage").val(responsetext); 
 									},
 								}) //inner ajax
 								
 							} 
 						});	//jquery 표현
 						
 						
                    }//mediaRecorder.onstop

                    //녹음 시작시킨 상태가 되면 chunks에 녹음 데이터를 저장하라 
                    mediaRecorder.ondataavailable = e => {
                        chunks.push(e.data)
                    }
                })
                .catch(err => {
                    console.log('The following error occurred: ' + err)
                })
        }

</script>
</body>
</html>