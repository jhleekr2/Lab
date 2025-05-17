package com.example.news;

import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/news")
public class NewsWebController {
	final NewsDAO dao;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	//설정 파일로부터 저장 경로 참조(현대 웹 개발의 경우는 파일 자체를 배포 폴더안에 넣지 않는다)
	//대신 설정 파일을 통해 파일을 외부에 저장하고(이때 배포시마다 환경에 맞게 저장 경로 설정 파일을 변경한다)
	//URL과 실제 파일 위치를 연결(매핑)하는 식으로 파일을 관리한다.
	@Value("${news.imgdir}")
	String fdir;
	
	@Autowired
	public NewsWebController(NewsDAO dao) {
		this.dao=dao;
	}
	
	@PostMapping("/add")
	public String addNews(@ModelAttribute News news, Model m,
						  @RequestParam("file") MultipartFile file) {

		try {
			//저장 폴더 객체 생성
			File uploaddir = new File(fdir);
			
			//저장할 폴더가 없으면 생성한다
			if(!uploaddir.exists()) {
				uploaddir.mkdirs(); //상위 폴더까지 모두 생성
				// mkdir()은 부모 없으면 실패, mkdirs()는 부모까지 생성
			}
			
			//저장 파일 객체 생성
			File dest = new File(fdir+"/"+file.getOriginalFilename());
			
			//파일 저장
			file.transferTo(dest);
			
			//News 객체에 파일 이름 저장
			news.setImg("/img/"+dest.getName());
			//저장 경로와는 달리 DB에는 /img/filename 이런 형태로 저장되도록 설정
			dao.addNews(news);

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("뉴스 추가 과정에서 문제 발생!");
			m.addAttribute("error", "뉴스가 정상적으로 등록되지 않았습니다.");
		}
		return "redirect:/news/list";
	}

	@GetMapping("/delete/{aid}")
	public String deleteNews(@PathVariable int aid, Model m) {
		try {
			News n = dao.getNews(aid); // 파일 삭제 위해 뉴스 다시 조회

			//DB에서 조회하여 기존에 업로드한 파일 이름을 찾음
			String uploadPath = n.getImg();

			String storedFileName = null;
			if(uploadPath.startsWith("/img/")) {
				storedFileName = uploadPath.substring("/img".length());
			} else {
				logger.info("이미지 파일 형식이 올바르지 않습니다");
			}

			File file = new File(fdir+"/"+storedFileName);
			if(file.exists()) { // 파일이 존재하면 삭제한다.
				try {
					file.delete();
				} catch (Exception e) {
					logger.info("이미지 파일이 없습니다");
				}
			}
			dao.delNews(aid);
		} catch (Exception e){
			e.printStackTrace();
			logger.info("뉴스 삭제 과정에서 문제 발생!");
			m.addAttribute("error", "뉴스가 정상적으로 삭제되지 않았습니다.");
		}
		return "redirect:/news/list";
	}

	@GetMapping("/list")
	public String listNews(Model m) {
		List<News> list;
		try {
			list = dao.getAll();
			m.addAttribute("newslist", list);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("뉴스 목록 생성 과정에서 문제 발생!");
			m.addAttribute("error", "뉴스 목록이 정상적으로 처리되지 않았습니다.");
		}
		return "news/newsList";
	}

	@GetMapping("/{aid}")
	public String getNews(@PathVariable int aid, Model m) {
		try {
			News n = dao.getNews(aid);
			m.addAttribute("news", n);
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("뉴스를 가져오는 과정에서 문제 발생!");
			m.addAttribute("error", "뉴스를 정상적으로 가져오지 못했습니다.");
		}
		
		return "news/newsView";
	}
}
