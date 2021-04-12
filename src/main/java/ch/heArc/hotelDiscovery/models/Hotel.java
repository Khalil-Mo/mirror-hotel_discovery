package ch.heArc.hotelDiscovery.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="hotel")
public class Hotel {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column
    private Integer idHotel;
    
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUser")
    private User manager;
    
    public void setManager(User user) {
    	this.manager = user;
    }
    
    /*@OneToMany(fetch = FetchType.LAZY, mappedBy="Hotel")//TODO yo ?
    private List<Room> rooms;*/
    
    @Column
    private String name;
    
    @Column
    private String description;
    
    public Integer getIdHotel() {
		return idHotel;
	}

	public void setIdHotel(Integer idHotel) {
		this.idHotel = idHotel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public boolean isTheHotelManager(User user) {
		return manager != null && manager.getId().equals(user.getId());
	}
	
	public User getManager() {
		return manager;
	}

	@Column
    private String city;
    
    @Column
    private String address;
}
