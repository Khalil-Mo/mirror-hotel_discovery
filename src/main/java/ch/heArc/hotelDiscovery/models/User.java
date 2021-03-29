package ch.heArc.hotelDiscovery.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;




@Entity(name = "Users")
@Table(name="user")
public class User implements UserDetails {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column
    private Integer idUser;
    
    @Column
    private String email;
    
    @Column
    private String name;
    
    @Column
    private String surname;
    
    @Column
    private String password;
    
    @Column
    private Boolean isManager;
    
    public Integer getIdUser() {
		return idUser;
	}

	public Boolean getIsManager() {
		return isManager;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	@Column
    private Boolean isAdmin;

    public User() {
        
    }

    public Integer getId() {
        return this.idUser;
    }
    
    public String getEmail() {
        return this.email;
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setIsManager(Boolean isManager) {
		this.isManager = isManager;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		final SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("admin");
		List<SimpleGrantedAuthority> l = new ArrayList<SimpleGrantedAuthority>();
		if (isAdmin)
			l.add(new SimpleGrantedAuthority("admin"));
		if (isManager)
			l.add(new SimpleGrantedAuthority("manager"));
		
		l.add(new SimpleGrantedAuthority("connected"));
		
		return l;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
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

	@Override
	public boolean isEnabled() {
		return true;
	}
}
