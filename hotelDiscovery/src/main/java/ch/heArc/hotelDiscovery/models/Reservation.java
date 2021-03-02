package ch.heArc.hotelDiscovery.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="reservation")
public class Reservation {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column
    private Integer idReservation;
    	
    @Column
    private Integer idUser;
    
    @Column
    private Integer idRoom;
    
    @Column
    private Date DateStart;
    
    @Column
    private Date DateEnd;
    

    public Reservation() {
        
    }


	public Integer getIdReservation() {
		return idReservation;
	}


	public void setIdReservation(Integer idReservation) {
		this.idReservation = idReservation;
	}


	public Integer getIdUser() {
		return idUser;
	}


	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}


	public Integer getIdRoom() {
		return idRoom;
	}


	public void setIdRoom(Integer idRoom) {
		this.idRoom = idRoom;
	}


	public Date getDateStart() {
		return DateStart;
	}


	public void setDateStart(Date dateStart) {
		DateStart = dateStart;
	}


	public Date getDateEnd() {
		return DateEnd;
	}


	public void setDateEnd(Date dateEnd) {
		DateEnd = dateEnd;
	}
    
    


}
