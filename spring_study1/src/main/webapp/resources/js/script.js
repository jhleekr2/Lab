//DOM 객체 연결(html혹은 jsp파일안에 있는 태그들.
//즉 객체들을 자바스크립트와 연결시키는 과정
const container = document.getElementById("container");
const menuAdmin = document.getElementById("menuAdmin");
const menuList = document.getElementById("menuList");

//CSRF 토큰과 헤더이름 가져오기 - 이때 CSRF 토큰은 index.jsp 앞부분에 정의되어 있으며 백단에서 넘어온 것이 근원이다.
const csrfToken = document.querySelector("meta[name='_csrf']").getAttribute('content');
const csrfHeader = document.querySelector("meta[name='_csrf_header']").getAttribute('content');

//데이터를 조회할때 사용할 기능을 정의해야 함
function fetchMenus() {
	fetch("/menu/all").then(response => response.json())
	.then(menus => {
		menuList.innerHTML = '';//기존메뉴목록을 초기화
		menus.forEach(menu =>{
			//각 메뉴 아이템을 생성해서 리스트에 추가
			const menuItem = document.createElement('div');
			menuItem.className='menu-item';
			menuItem.innerHTML=`
			<a href="#" class="menu-link" style="text-decoration:none;color:black;">
				<h3>${menu.title}</h3>
				<p>${menu.content}</p>
				<small>작성자:${menu.writer},작성일:${menu.indate},조회수:${menu.count}</small>
			</a>
			<br>
			<br>
			`
			//게시글을 메인페이지에서 하나씩 클릭할때
			menuItem.querySelector(".menu-link").addEventListener('click',(event)=>{
				event.preventDefault();
				console.log(`event:${event}`);
				//EL JSTL 쓸때 반드시 tab키 위의 따옴표로 둘러싸자(ES6)
				incrementCount(menu.idx).then(()=>window.location.href=`/noticeCheckPage?idx=${menu.idx}`)
				//JavaScript에서 변수를 문자열 리터럴 안에 삽입하려면 **템플릿 리터럴(Template literals)**을 사용해야 하는데, 템플릿 리터럴은 일반적인 작은따옴표(')나 큰따옴표(")가 아닌 **백틱(``)**으로 감싸야 합니다.
			});
			menuList.appendChild(menuItem);
		})
	})
	//백엔드단에서 프론트단 데이터 가져온다
}

function incrementCount(idx){
	return fetch(`/menu/count/${idx}`,{
		method:'PUT',
		headers:{
			[csrfHeader]:csrfToken
		}
	}).then(response=>{
		if(!response.ok){
			console.log("데이터가 프론트서버에서 백엔드서버로 넘어가는데 실패.");
		}
	}).catch(error=>{
		console.error(`Error:${error}`);
	})
}

//메인페이지가 열리면 자동실행됨
window.addEventListener('load',fetchMenus);

//fetchMenus();