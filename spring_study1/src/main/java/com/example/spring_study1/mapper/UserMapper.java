package com.example.spring_study1.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.spring_study1.entity.User;

@Mapper
//자동으로 @Component기능 비슷하게 스프링 컨테이너에 등록이 됨(인터페이스)
//자바언어와 sql언어를 통역해주는 역할을 함
public interface UserMapper {
	//CRUD의 CREATE에 해당하는 기능중 하나
	@Insert("INSERT INTO users (username, password, writer, role) "
			+ "VALUES( #{username}, #{password}, #{writer}, #{role})")
	void insertUser(User user);
	//void -> 우리가 데이터베이스에서 백엔드영역(스프링프레임워크)으로 데이터를
	//가져오는 게 없기 때문에 void로 가져오는게 없다고 작성한다.
	//CRUD의 READ에 해당하는 기능중 하나
	@Select("SELECT username, password, writer, role FROM users WHERE username=#{username}")
	User findByUsername(String username);
	
	@Select("SELECT writer FROM users WHERE username=#{username}")
	String findWriter(String username);
	//CRUD의 UPDATE에 해당하는 기능중 하나
	//@Update()
	//CRUD의 DELETE에 해당하는 기능중 하나
	//@Delete()
}
