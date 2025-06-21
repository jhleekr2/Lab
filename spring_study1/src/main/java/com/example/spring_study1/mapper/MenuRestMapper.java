package com.example.spring_study1.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.spring_study1.entity.Menu;

//@Component와 비슷 => @ComponentScan
//@Mapper => @MapperScan => 자동으로 스프링컨테이너에 등록
@Mapper
public interface MenuRestMapper {

	//CRUD의 일부분;
	//첫번째 게시글 -> idx:1
	//두번째 게시글 -> idx:2
	//세번째 게시글 -> idx:3
	//내림차순 정렬때문에 세번째 게시글이 먼저 보일 것이다.
	//포트폴리오나 현업에서 select * from menu;와 같이 *을 쓰지 말것
	//-> 쓰면 안좋은 코드로 인식된다고 함
	@Select("SELECT idx,memID,title,content,writer,indate,count FROM menu ORDER BY idx DESC")
	public List<Menu> getList();
	
	@Insert("INSERT INTO menu(memID,title,content,writer,indate) VALUES (#{memID}, #{title}, #{content}, #{writer}, #{indate})")
	public void boardInsert(Menu menu);
	
	@Select("SELECT idx,memID,title,content,writer,indate,count FROM menu WHERE idx=#{idx}")
	public Menu boardContent(int idx);
	
	@Delete("DELETE FROM menu where idx=#{idx}")
	public void boardDelete(int idx);
	
	@Update("UPDATE menu SET title=#{title}, content=#{content}, writer=#{writer} WHERE idx=#{idx}")
	public void boardUpdate(Menu menu);
	
	@Update("UPDATE menu SET count=count+1 WHERE idx=#{idx}")
	public void boardCount(int idx);
}
