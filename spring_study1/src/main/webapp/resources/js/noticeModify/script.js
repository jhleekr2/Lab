document.getElementById("buttonUpdate").addEventListener("click",function(){
	const idx = document.getElementById("idx").value;
	
	const formData = {
		title:document.getElementById("title").value,
		content:document.getElementById("content").value,
		writer:document.getElementById("writer").value
	};
	
	const csrfToken = document.querySelector("meta[name='_csrf']").getAttribute("content");
	const csrfHeader = document.querySelector("meta[name='_csrf_header']").getAttribute("content");
	
	fetch(`/menu/update/${idx}`,{
		method:"PUT",
		headers:{
			'Content-Type':'application/json',
			[csrfHeader]:csrfToken //CSRF헤더와 토큰을 동적으로 추가
		},
		body:JSON.stringify(formData)
	}).then(response=>{
		if(!response.ok){
			throw new Error("공지사항 수정실패.");
		}
		return response.text();
	}).then(_=>{
		alert("수정이 성공적으로 진행되었습니다.")
		window.location.href="/";
	})
	
})
