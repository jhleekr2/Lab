document.getElementById("buttonUpdate").addEventListener("click",function(){
	const idx = document.getElementById("idx").value;
	window.location.href=`/noticeModifyPage?idx=${idx}`;
})

document.getElementById("buttonDelete").addEventListener("click",function(){
	const idx = document.getElementById("idx").value;
	
	const csrfToken = document.querySelector("meta[name='_csrf']").getAttribute("content");
	const csrfHeader = document.querySelector("meta[name='_csrf_header']").getAttribute("content");
	
	fetch(`/menu/delete/${idx}`,{
		method:"DELETE",
		headers:{
			[csrfHeader]:csrfToken
		}
	}).then(response=>{
		if(!response.ok){
			throw new Error("백엔드에서 응답이 잘 안됨.");
		}else{
			return response.text();
		}
	}).then(_=>{
		alert("게시글을 성공적으로 삭제하였습니다.");
		window.location.href="/";//메인페이지로 이동
	}).catch(error=>{
		console.log("Error가 발생",error);
	})
		
})