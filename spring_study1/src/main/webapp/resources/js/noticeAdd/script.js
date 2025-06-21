document.getElementById("buttonSubmit").addEventListener("click",function(){
	const formData = {
		memID:document.getElementById("memID").value,
		title:document.getElementById("title").value,
		content:document.getElementById("content").value,
		writer:document.getElementById("writer").value,
		
	}
//indate:new Date().toISOString().split("T")[0], 의 의미를 알 필요가 있다.
//index.jsp파일에서 만들 메타 CSRF 태그 두개를 js파일로 가져온다.
const csrfToken = document.querySelector("meta[name='_csrf']").getAttribute("content");
const csrfHeader = document.querySelector("meta[name='_csrf_header']").getAttribute("content");

fetch("/menu/add",{
	method:"POST",
	headers:{
		'Content-Type':'application/json',
		[csrfHeader]:csrfToken //CSRF헤더와 토큰을 동적으로 추가
	},
	body: JSON.stringify(formData)
}).then(response =>{
	if(!response.ok){
		throw new Error("공지사항 작성실패.")
	}
	return response.text(); //백단에서 return 한 게시글 잘 작성됐다는 메시지 받음
}).then(_=>{
	console.log("Success");
	window.location.href="/";
	//localhost:8080 로 페이지가 이동됩니다.
}).catch(error=>{
	console.log("Error가 발생",error);
});
});