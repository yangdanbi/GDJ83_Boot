package com.db.app.members;

import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberUserService extends DefaultOAuth2UserService implements UserDetailsService {
	@Autowired
	private MemberMapper memberMapper;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberVO memberVO = new MemberVO();
		memberVO.setUsername(username);
		try {
			memberVO=memberMapper.detail(memberVO);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return memberVO;
	}
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		//log.error("Token : {} " , userRequest.getAccessToken());
		ClientRegistration registration= userRequest.getClientRegistration();
		
		String sns = registration.getRegistrationId();
		
		OAuth2User auth2User=super.loadUser(userRequest);
		
		if(sns.equals("kakao")) {
			auth2User = this.useKaKao(userRequest);
		}
		if(sns.equals("naver")) {
			
			
		}
		//유저정보가 아닌 개발자의 key
		//log.error("ClientId : {} " , registration.getClientId());
		return auth2User;
	}
	private OAuth2User useKaKao(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException{
		OAuth2User auth2User = super.loadUser(userRequest); 
		log.error("====================================================================");
		log.error("ID : {}", auth2User.getName());
		log.error("Attribute : {}", auth2User.getAttributes());
		log.error("Authorities : {}", auth2User.getAuthorities());
		log.error("username : {}", auth2User.getName());
		
		Map<String, Object> attributes = auth2User.getAttributes();
		Map<String, Object> properties = (Map<String, Object>)attributes.get("properties");
		
		log.error("properties : {}", properties);
		MemberVO memberVO= new MemberVO();
		
		memberVO.setAccessToken(userRequest.getAccessToken().getTokenValue());
		memberVO.setSns(userRequest.getClientRegistration().getRegistrationId());
		memberVO.setAttributes(attributes);
		
		memberVO.setUsername(auth2User.getName());
		memberVO.setName(properties.get("nickname").toString());
		
		List<RoleVO> list = new ArrayList<>();
		RoleVO roleVO = new RoleVO();
		roleVO.setRoleName("ROLE_USER");
		list.add(roleVO);
		memberVO.setVos(list);
		return memberVO;
	} 
	
}
