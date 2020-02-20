package net.javahotel.springboot.Springboothotelapplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.javahotel.springboot.Springboothotelapplication.model.Hotelguest;

@Repository
public interface HotelguestRepository extends JpaRepository<Hotelguest, Long>{
	
	
	  List<Hotelguest> findByNameContaining(String name);
	  List<Hotelguest> findByDocumentContaining(String document);
	  List<Hotelguest> findByPhoneContaining(String phone);
	  
	 

}
