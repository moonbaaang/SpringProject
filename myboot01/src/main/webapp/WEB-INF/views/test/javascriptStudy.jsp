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
$(document).ready(function(){

});
</script>
<script type="text/javascript">
let n = "name";
let a = 'age';

const user = {
		[n]: "Mike",
		[a]: 30,
		[1+4]:5,
};
console.log(user)
for(var i in user){
	console.log(i);
}

function makeObj(key, val){
	return{
		[key]: val,	
	}
}

const obj = makeObj("성별", "male");
const user2 = Object.assign({}, user)
user2.name = 'Jake'
console.log(user2)

const keyresult = Object.keys(user);
console.log(keyresult)
const valresult = Object.values(user);
console.log(valresult)
const entriesresult = Object.entries(user);
console.log(entriesresult)

let arr = [
	['mon', '월'],
	['tue', '화'],
]
const fromEntriesresult = Object.fromEntries(arr);
console.log(fromEntriesresult)
</script>
</head>
<body>

</body>
</html>