package com.example.news;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;


@RestController
@RequestMapping("/api/news")
public class NewsApiController {

	final NewsDAO dao;
	@Autowired
	public NewsApiController(NewsDAO dao) {
		this.dao = dao;
	}

	//뉴스 등록
	@PostMapping
	public String addNews(@RequestBody News news) {
		try {
			dao.addNews(news);
		} catch (Exception e) {
			e.printStackTrace();
			return "NEWS API: 뉴스 등록 실패";
		}
		return "NEWS API: 뉴스 등록됨";
	}
	
	//뉴스 삭제 - API 형식 구현에서는 파일 부분은 빠져 있음.
	@DeleteMapping("{aid}")
	public String delNews(@PathVariable("aid") int aid) {
		try {
			dao.delNews(aid);
		} catch (Exception e) {
			e.printStackTrace();
			return "NEWS API: 뉴스 삭제 실패! - " + aid; 
		}
		return "NEWS API: 뉴스 삭제됨! - " + aid;
	}
	
	//뉴스 목록
	@GetMapping
	public List<News> getNewList() {
		List<News> newsList = null;
		
		try {
			newsList = dao.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newsList;
	}

	//뉴스 상세 정보
	@GetMapping("{aid}")
	public News getNews(@PathVariable("aid") int aid) {
		News news = null;
		
		try {
			news = dao.getNews(aid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return news;
	}
}
