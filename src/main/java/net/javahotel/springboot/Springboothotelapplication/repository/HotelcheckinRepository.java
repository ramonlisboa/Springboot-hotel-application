package net.javahotel.springboot.Springboothotelapplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.javahotel.springboot.Springboothotelapplication.model.Hotelcheckin;

@Repository
public interface HotelcheckinRepository extends JpaRepository<Hotelcheckin, Long>{

	List<Hotelcheckin> findByIeStatusAndOutDateIsNull(Character status);
	List<Hotelcheckin> findByIeStatusAndOutDateNotNull(Character status);
}
