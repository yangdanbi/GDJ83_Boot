package com.db.app.WebClient;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.db.app.comments.PostVO;


import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.agent.VirtualMachine.ForHotSpot.Connection.Response;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@SpringBootTest
@Slf4j
class ClientTest {
	@Test
	void WebClientListTest() {
			WebClient webClient = WebClient.builder()
												.baseUrl("https://jsonplaceholder.typicode.com/")
												.build();
												
			//여러개 가져오기
			Flux<PostVO> res =	webClient.get()
										 .uri("posts/1")
										 //응답에 관련됨
										 .retrieve()
										 .bodyToFlux(PostVO.class)
										 ;
		
			res.subscribe(v->{
				PostVO p =v;
				log.info("V : {}",v);
				
				
			});
			
			//log.info("WebCilentResult : {}", postVO);
		}
	
	
	
	//@Test
	void WebClientTest() {
		WebClient webClient = WebClient.builder()
											.baseUrl("https://jsonplaceholder.typicode.com/")
											.build();
											
		
		Mono<PostVO> res =	webClient.get()
									 .uri("posts/1")
									 //응답에 관련됨
									 .retrieve()
									 .bodyToMono(PostVO.class)
									 ;
		
		PostVO postVO = res.block();
		
		log.info("WebCilentResult : {}", postVO);
	}

	//@Test
	void test1() {
		//RestTemplate
		RestTemplate restTemplate = new RestTemplate();
		
		//parameter 생성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("postId","1");
		
		//요청 객체 생성
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String,String>>(params, null);
		
		//요청 전송 응답 처리
		//ResponseEntity<PostVO []>
		
		List<PostVO> res = restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts/1",List.class);
		//List<PostVO []> result = res.getBody();
		
		log.info("result : {} ",res.size());
		
	}

}
