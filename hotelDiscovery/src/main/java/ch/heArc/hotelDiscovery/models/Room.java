package ch.heArc.hotelDiscovery.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="room")
public class Room {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column
    private Integer idRoom;
    
    @Column
    private Integer idHotel;
    
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

	public Integer getIdHotel() {
		return idHotel;
	}

	public void setIdHotel(Integer idHotel) {
		this.idHotel = idHotel;
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
}
