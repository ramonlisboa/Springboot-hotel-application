package net.javahotel.springboot.Springboothotelapplication.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Entity
@Table(name = "hotelguest")
public class Hotelguest implements Serializable {

private static final long serialVersionUID = 98244570097L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private long id;
	
    @Column(name = "name", nullable = false)
	private String name;    
    
    @Column(name = "document", nullable = false)
	private String document;    
    
    @Column(name = "phone", nullable = true)
	private String phone;
	
//    @OneToMany(mappedBy = "hotelGuest")
//    @JsonIgnore
//    private Set<Hotelcheckin> hotelCheckins = new HashSet<>();
    
    @OneToMany(mappedBy = "hotelguest")
    @JsonIgnore
    private List<Hotelcheckin> hotelCheckins;
	
	public Hotelguest() {
		
	}
	
	public Hotelguest(long id, String name, String document, String phone, Hotelcheckin hotelCheckin) {
		this.id = id;    
		this.name = name;
		this.document = document;
		this.phone = phone;
		
		
//		  this.hotelCheckins = Stream.of(hotelCheckin).collect(Collectors.toSet());
//		  this.hotelCheckins.forEach(x -> x.setHotelguest(this));
		 
	}
	

        public long getId() {
        return id;
    }
	
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getDocument() {
        return document;
    }
    
    public void setDocument(String document) {
        this.document = document;
    }

    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    

	public List<Hotelcheckin> getHotelCheckins() {
		return hotelCheckins;
	}

	public void setHotelCheckins(List<Hotelcheckin> hotelCheckins) {
		this.hotelCheckins = hotelCheckins;
	}

	@Override
    public String toString() {
        return "Hostelguest [id=" + id + ", name=" + name + ", document=" + document + ", phone=" + phone
       + "]";
    }
}
