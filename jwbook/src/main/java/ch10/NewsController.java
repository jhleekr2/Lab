package ch10;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;

@WebServlet(urlPatterns = "/news.nhn")
@MultipartConfig(maxFileSize=1024*1024*2)
public class NewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private NewsDAO dao;
	private ServletContext ctx;
	
	private final String START_PAGE = "ch10/newsList.jsp";
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		dao = new NewsDAO();
		ctx = getServletContext();
	}
	
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8"); //한글 인코딩
		String action = req.getParameter("action");
		dao = new NewsDAO();
		
		Method m;
		String view = null;
		
		if(action == null) {
			action = "listNews";
		}
		try {
			m = this.getClass().getMethod(action, HttpServletRequest.class);
			view = (String)m.invoke(this, req);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			ctx.log("요청 action 없음!");
			req.setAttribute("error", "action 파라미터가 잘못되었습니다!");
			view = START_PAGE;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(view.startsWith("redirect:/")) {
			String rview = view.substring("redirect:/".length());
			resp.sendRedirect(rview);
		} else {
			RequestDispatcher dispatcher = req.getRequestDispatcher(view);
			dispatcher.forward(req, resp);
		}
		
	}

	public String addNews(HttpServletRequest req) {
		News n = new News();
		
		Part part;
		try {
			part = req.getPart("file");
			String fileName = getFilename(part);
			if(fileName != null && !fileName.isEmpty()) {
				// 파일을 저장할 실제 경로 계산
				String uploadPath = req.getServletContext().getRealPath("/img/");
				
				// 저장될 폴더가 없으면 생성한다
				File uploadDir = new File(uploadPath);
				if (!uploadDir.exists()) {
					uploadDir.mkdirs(); // 상위 디렉터리까지 모두 생성
					// mkdir()은 부모 없으면 실패, mkdirs()는 부모까지 생성
				}
				
				// 최종 파일 경로
				String filePath = uploadPath + "/" + fileName;
				part.write(filePath);
			}
			BeanUtils.populate(n, req.getParameterMap());
			// 파일 업로드 시 교재와 달리 실제 서블릿 배포 경로를 호출해서 그곳에 파일 저장
			// 국비에서 배운것과 달리 commons.io 의존성 없이도 서블릿 자체 기능만으로 파일
			// 관리가 가능하다는 사실을 알게 되었다.
			n.setImg("/img/"+fileName);
			dao.addNews(n);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("뉴스 추가 과정에서 문제 발생!");
			req.setAttribute("error", "뉴스가 정상적으로 등록되지 않았습니다.");
			return listNews(req);
		}
		return "redirect:/news.nhn?action=listNews";
	}

	public String deleteNews(HttpServletRequest req) {
		int aid = Integer.parseInt(req.getParameter("aid"));
		//실제 업로드한 파일도 같이 삭제해야만 한다.
		//파일 삭제를 위해 쿼리를 다시 조회해야 한다.
		
		try {
			News n = dao.getNews(aid); // 파일 삭제 위해 뉴스 다시 조회
			String uploadPath = req.getServletContext().getRealPath(n.getImg());
			// 디버거를 통해 파일 주소가 올바르게 리턴되는 것을 확인했다.
			File file = new File(uploadPath);
			if(file.exists()) { // 파일이 존재하면 삭제한다.
				try {
					file.delete();
				} catch (Exception e) {
					ctx.log("이미지 파일이 없습니다");
				}
			}
			dao.delNews(aid);
		} catch (Exception e){
			e.printStackTrace();
			ctx.log("뉴스 삭제 과정에서 문제 발생!");
			req.setAttribute("error", "뉴스가 정상적으로 삭제되지 않았습니다.");
			return listNews(req);
		}
		return "redirect:/news.nhn?action=listNews";
	}
	
	public String listNews(HttpServletRequest req) {
		List<News> list;
		try {
			list = dao.getAll();
			req.setAttribute("newslist", list);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("뉴스 목록 생성 과정에서 문제 발생!");
			req.setAttribute("error", "뉴스 목록이 정상적으로 처리되지 않았습니다.");
		}
		return "ch10/newsList.jsp";
	}

	public String getNews(HttpServletRequest req) {
		int aid = Integer.parseInt(req.getParameter("aid"));
		try {
			News n = dao.getNews(aid);
			req.setAttribute("news", n);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("뉴스를 가져오는 과정에서 문제 발생!");
			req.setAttribute("error", "뉴스를 정상적으로 가져오지 못했습니다.");
		}
		
		return "ch10/newsView.jsp";
	}
	
	private String getFilename(Part part) {
		String fileName = null;
		String header = part.getHeader("content-disposition");
		System.out.println("Header =>" + header);
		int start = header.indexOf("filename");
		fileName = header.substring(start+10, header.length()-1);
		ctx.log("파일명:"+fileName);
		return fileName;
	}
	
}
