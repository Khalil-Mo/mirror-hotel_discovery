package ch.heArc.hotelDiscovery.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="room")
public class Room {
	
	public Room(Hotel hotel) {
		setHotel(hotel);
	}

	public Room() {
		
	}
	
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column
    private Integer idRoom;
    

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idHotel")
    private Hotel hotel;
    
    @Column
    private String description;
    
    @Column
    private String nbBed;
    
    @Column
    private String nbRoom;
    
    @Column
    private String priceByNight;

	public Integer getIdRoom() {
		return idRoom;
	}

	public void setIdRoom(Integer idRoom) {
		this.idRoom = idRoom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNbBed() {
		return nbBed;
	}

	public void setNbBed(String nbBed) {
		this.nbBed = nbBed;
	}

	public String getNbRoom() {
		return nbRoom;
	}

	public void setNbRoom(String nbRoom) {
		this.nbRoom = nbRoom;
	}

	public String getPriceByNight() {
		return priceByNight;
	}

	public void setPriceByNight(String priceByNight) {
		this.priceByNight = priceByNight;
	}
	
	public Hotel getHotel() {
		return hotel;
	}
	
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	
	public boolean ownedByThisHotel(Hotel hotel) {
		return this.hotel.getIdHotel().equals(hotel.getIdHotel());
	}
}
