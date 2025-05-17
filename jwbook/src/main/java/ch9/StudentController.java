package ch9;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

@WebServlet("/studentControl")
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	StudentDAO dao;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		dao = new StudentDAO();
	}
	
	// 국비에서는 일일히 doGet, doPost 오버라이딩 하는 구조로 배웠지만, 여기서는 좀 다른 구조로 호출한다.
	// 여러 요청을 하나의 컨트롤러 서블릿에서 처리하기 위해 기본 생성 서블릿 구조를 굳이 사용하지 않고,
	// service 메서드를 오버라이딩하여 컨트롤러 기본 구조를 구현하고 각가의 요청은 action 파라미터를
	// 비교해 메서드를 호출한 다음 뷰로 이동하는 구조를 사용한다.
	// 이때 싱글톤 패턴이 사용된다.
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8"); //한글 깨짐 문제 해결
		String action = req.getParameter("action");
		String view = "";
		
		if(req.getParameter("action") == null) {
			getServletContext().getRequestDispatcher("/studentControl?action=list").forward(req, resp);
		} else {
			switch(action) {
			case "list": view = list(req, resp); break;
			case "insert": view = insert(req, resp); break;
			}
			getServletContext().getRequestDispatcher("/ch09/"+view).forward(req, resp);
		}
	}

	private String list(HttpServletRequest req, HttpServletResponse resp) {
		req.setAttribute("students", dao.getAll());
		return "studentInfo.jsp";
	}

	private String insert(HttpServletRequest req, HttpServletResponse resp) {
		Student s = new Student();
		try {
			BeanUtils.populate(s, req.getParameterMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
		dao.insert(s);
		return list(req, resp);
	}
}
