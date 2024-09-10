package com.db.app.members;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.db.app.validate.MemberAddGroup;
import com.db.app.validate.MemberUpdateGroup;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MemberVO implements UserDetails {

	@NotBlank(groups = {MemberAddGroup.class, MemberUpdateGroup.class})
	private String username;
//	@NotBlank
//	@Size(max = 5, min = 1)
	@Pattern(groups= {MemberAddGroup.class},regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*\\\\W)(?=\\\\S+$).{6,12}")
	@NotBlank(groups= {MemberAddGroup.class})
	private String password;
	
	private String passwordCheck;
	
	@NotBlank(groups= {MemberAddGroup.class,MemberUpdateGroup.class})
	private String name;
	@Email(groups= {MemberAddGroup.class,MemberUpdateGroup.class})
	private String email;
	@Past(groups= {MemberAddGroup.class,MemberUpdateGroup.class})
	private Date birth;
	private boolean enabled;
	private List<RoleVO> vos;
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		for(RoleVO roleVO :  vos) {
			GrantedAuthority authority = new SimpleGrantedAuthority(roleVO.getRoleName());
			authorities.add(authority);
			
		}
		
		return authorities;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	public boolean isEnabled() {
		return true;
	}
	
	
}
