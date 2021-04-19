package ch.heArc.hotelDiscovery.models;

import java.sql.Date;

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
@Table(name="reservation")
public class Reservation {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column
    private Integer idReservation;
    	
    /*@Column
    private Integer idUser;*/
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUser")
    private User user;
    
    
    /*@Column
    private Integer idRoom;*/
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idRoom")
    private Room room;
    



	@Column
    private Date dateStart;
    
    @Column
    private Date dateEnd;
    

    public Reservation() {
        
    }


	public Integer getIdReservation() {
		return idReservation;
	}


	public void setIdReservation(Integer idReservation) {
		this.idReservation = idReservation;
	}


	public Date getDateStart() {
		return dateStart;
	}


	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}


	public Date getDateEnd() {
		return dateEnd;
	}


	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}
	
    public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Room getRoom() {
		return room;
	}


	public void setRoom(Room room) {
		this.room = room;
	}
    
    


}
