package ch.heArc.hotelDiscovery.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column
    private Integer idUsers;
    
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
    
    @Column
    private Boolean isAdmin;

    public User() {
        
    }

    public Integer getId() {
        return this.idUsers;
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

	public void setIdUsers(Integer idUsers) {
		this.idUsers = idUsers;
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
}
