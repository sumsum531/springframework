package com.mycompany.webapp.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.webapp.dto.Ch07Board;
import com.mycompany.webapp.dto.Ch07City;
import com.mycompany.webapp.dto.Ch07Cloth;
import com.mycompany.webapp.dto.Ch07Member;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/ch07")
@Log4j2
public class Ch07Controller {
	private static final Logger logger = LoggerFactory.getLogger(Ch07Controller.class);
	private static int count = 0;
	
	@RequestMapping("/content")
	public String content(HttpServletRequest request) {
		log.info("실행");
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = sdf.format(date);
		
		request.setAttribute("strDate", strDate);
		
		return "ch07/content";
	}
	
	@RequestMapping("/requestScopeSave")
	public String requestScopeSave(HttpServletRequest request) {
		//Request 범위에 객체 저장
		request.setAttribute("requestScopeValue", "홍길동");
		
		//멤버 객체 생성후 request 범위에 객체 저장
		Ch07Member member = new Ch07Member();
		member.setName("홍길동");
		member.setAge(25);
		member.setJob("프로그래머");
		Ch07City city = new Ch07City();
		city.setName("서울");
		member.setCity(city);
		request.setAttribute("member", member);
		
		return "ch07/content";
	}
	
	@RequestMapping("/sessionScopeSave")
	public String sessionScopeSave(HttpSession session) {
		//Session 범위에 객체 저장
		session.setAttribute("sessionScopeValue", "감자바");
		
		//멤버 객체 생성후 request 범위에 객체 저장
		Ch07Member member = new Ch07Member();
		member.setName("말하는감자");
		member.setAge(27);
		member.setJob("개발자");
		Ch07City city = new Ch07City();
		city.setName("고양시");
		member.setCity(city);
		session.setAttribute("member2", member);
		
		return "ch07/content";
	}
	
	@RequestMapping("/applicationScopeSave")
	public String applicationScopeSave(HttpServletRequest request) {
		//application 범위에 객체 저장
		//ServletContext에 저장하면 application 객체에 저장하는 것이다!
		ServletContext application = request.getServletContext();
		application.setAttribute("applicationScopeValue", "한여름");
		
		//객체 생성 후 session 범위에 객체 저장
		Integer counter = ++count;
		application.setAttribute("counter", counter);
		
		return "ch07/content";
	}
	
	@GetMapping("/useJstl1")
	public String useJstl1(HttpServletRequest request) {
		String[] languages = {"Java", "JavaScript", "SpringFramework", "Vue"};
		request.setAttribute("langs", languages);
		return "ch07/useJstl1";
	}
	
	@GetMapping("/useJstl2")
	public String useJstl2(HttpServletRequest request) {
		List<Ch07Board> list = new ArrayList<>();
		for(int i=1; i<=5; i++) {
			Ch07Board board = new Ch07Board(i, "제목"+i, "내용"+i, "글쓴이"+i , new Date());
			list.add(board);
		}
		request.setAttribute("boardList", list);
		return "ch07/useJstl2";
	}
	
	@GetMapping("/modelAndViewReturn")
	public ModelAndView modelAndViewReturn() {
		Ch07Board board = new Ch07Board(1, "제목1", "내용1", "글쓴이1" , new Date());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("board", board);
		modelAndView.setViewName("ch07/boardDetail1");
		
		return modelAndView;
	}
	
	//얘를 많이 사용한다!(데이터 전달시!)
	@GetMapping("/modelArgument")
	public String modelArgument(Model model) {
		Ch07Board board = new Ch07Board(2, "제목2", "내용2", "글쓴이2" , new Date());
		
		model.addAttribute("board", board);
		
		return "ch07/boardDetail1";
	}
	
	@GetMapping("/modelAttribute")
	public String modelAttribute(
			@ModelAttribute("kind") String kind, 
			@ModelAttribute("sex") String sex) {
		return "ch07/clothInfo";
	}
	
	//위의 코드와 같음!
	/*
	@GetMapping("/modelAttribute")
	public String modelAttribute(String kind, String sex, Model model) {
		model.addAttribute("kind", kind);
		model.addAttribute("sex", sex);
		return "ch07/clothInfo";
	}
	/*
	@GetMapping("/commandObject")
	public String commandObject(Ch07Cloth cloth) { //String kind 이렇게 개별적으로 받은 애들은 전달되지 않아 앞에 @어노테이션을 붙여야 하지만, DTO는 전달된다!
		return "ch07/clothInfo";
	}
	*/
	
	
	//String kind 이렇게 개별적으로 받은 애들은 전달되지 않아 앞에 @어노테이션을 붙여야 하지만, DTO는 전달된다!
	@GetMapping("/commandObject")
	public String commandObject(@ModelAttribute("cloth") Ch07Cloth cloth) {
		return "ch07/clothInfo";
	}
	
	//요청 매핑 메소드가 실행될 때 마다 먼저 실행된다!
	@ModelAttribute("commonData")
	public Ch07Board getCommonData() { //얘가 리턴하는 값이 commonData라는 키 이름으로 request에 저장됨!
		log.info("실행");
		Ch07Board board = new Ch07Board(3, "제목3", "내용3", "글쓴이3", new Date());
		return board;
	}
}
