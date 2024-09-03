package com.db.app.qna;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@AutoConfigureMockMvc
public class QnaControllerTest {

	//private WebApplicationContext ctx;
	@Autowired
	private MockMvc mockMvc;
	
//	@Test
//	void test() {
//		//this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
//	}
	//jsp가 없어도 콘솔에 정보가 나옴
	//파라미터 여러개 보낼때도 param을여러개 쓰면 됨
	//MultiValueMap을 만들고 맵에 담아서 보내도 됨 
	//일반 map은 사용이 불가
	@Test
	void getList() throws Exception{
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("page","1");
		map.add("kind","k1");
		map.add("search","2");
		
		//Map<String, String> map2 = new HashMap<>();
		
		mockMvc.perform(
					get("/qna/list")
					.params(map)
//					.param("page","1")
//					.param("kind", "k1")
//					.param("search", "2")
				)
			.andDo(print())
		;
		
	}
	@Test
	public void getDetailTest()throws Exception {
		mockMvc.perform(
//				get("/qna/detail")
				get("/qna/list?page=1")
			//	.param("boardNum","113")
				)
				.andExpect(status().isOk())
				//print 때문에 콘솔에 전체 정보가 출력됨
				.andDo(print());

	}
//	/isOk() 는 200만
	@Test
	public void add()throws Exception{
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("boardNum","0");
		map.add("boardWriter","gg");
		map.add("boardTitle","gg");
		map.add("boardContents","gg");
		
		mockMvc.perform(
				post("/qna/add")
				.params(map)
				)
				.andExpect(status().is3xxRedirection())
				.andDo(print())		
		;
		
	}
}
