package net.javahotel.springboot.Springboothotelapplication.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.javahotel.springboot.Springboothotelapplication.exception.ResourceNotFoundException;
import net.javahotel.springboot.Springboothotelapplication.model.Hotelcheckin;
import net.javahotel.springboot.Springboothotelapplication.model.Hotelguest;
import net.javahotel.springboot.Springboothotelapplication.repository.HotelcheckinRepository;
import net.javahotel.springboot.Springboothotelapplication.repository.HotelguestRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController 
@RequestMapping("/api/v1")
public class HotelcheckinController {
	
	@Autowired
    private HotelcheckinRepository hotelcheckinRepository;
	@Autowired
	private HotelguestRepository hotelguestRepository;

    @GetMapping("/hotelcheckins")
    public List<Hotelcheckin> getAllHotelcheckin() {
        return hotelcheckinRepository.findAll();
    }

    @GetMapping("/hotelcheckins/{id}")
    public ResponseEntity<Hotelcheckin> getHotelcheckinById(@PathVariable(value = "id") Long hotelcheckinId)
        throws ResourceNotFoundException {
    	Hotelcheckin hotelcheckin = hotelcheckinRepository.findById(hotelcheckinId)
          .orElseThrow(() -> new ResourceNotFoundException("Hotel guest not found for this id : " + hotelcheckinId));
        return ResponseEntity.ok().body(hotelcheckin);
    }

    @GetMapping("/hotelcheckins/status/{status}")
    public List<Hotelcheckin> getHotelcheckinByStatus(@PathVariable(value = "status") Character hotelcheckinStatus) {
    	List<Hotelcheckin> hotelcheckin;
    	if('A' == hotelcheckinStatus) {
    		hotelcheckin = hotelcheckinRepository.findByIeStatusAndOutDateIsNull(hotelcheckinStatus);
    	} else {
    		hotelcheckin = hotelcheckinRepository.findByIeStatusAndOutDateNotNull(hotelcheckinStatus);
    	}
        return hotelcheckin;
    }

    @GetMapping("/hotelcheckins/update/{guestId}")
    public List<Hotelcheckin> getHotelcheckinByStatus(@PathVariable(value = "guestId") Long guestId) 
    		 throws ResourceNotFoundException {
    	return hotelguestRepository.findById(guestId).map(guest -> {
    		System.out.println("guest: " + guest);
        	
        	Hotelcheckin hCheckin = new Hotelcheckin();
        	hCheckin.setHotelguest(guest);
        	hCheckin.setEntryDate(new Date());
        	hCheckin.setIeStatus('A');
        	hCheckin.setVlTotal(135.00);
        	
        	hotelcheckinRepository.save(hCheckin);
        	
        	return hotelcheckinRepository.findAll();
    	}).orElseThrow(() -> new ResourceNotFoundException("HotelGuest " + guestId + " not found"));
    	
    	
    }
    
//    @PostMapping("/hotelcheckins/{hotelGuestId}/save")
//    public Hotelcheckin createHotelcheckin(@PathVariable (value = "hotelGuestId") Long hotelGuestId,
//    		@Valid @RequestBody Hotelcheckin hotelcheckin) throws ResourceNotFoundException  {
//    	
//        return hotelguestRepository.findById(hotelGuestId).map(guest -> {
//        	hotelcheckin.setHotelguest(guest);
//        	return hotelcheckinRepository.save(hotelcheckin);
//        }).orElseThrow(() -> new ResourceNotFoundException("HotelGuest " + hotelGuestId + " not found"));
//    }
    
    @RequestMapping(value = "/hotelcheckins",
    	    method = RequestMethod.POST,
    	    produces = MediaType.APPLICATION_JSON_VALUE)
    public Hotelcheckin createHotelcheckin(@Valid @RequestBody Hotelcheckin hotelcheckin)  
   		 throws ResourceNotFoundException {
    	return hotelguestRepository.findById(hotelcheckin.getHotelguest().getId()).map(guest -> {
    		System.out.println("guest: " + guest);
    		System.out.println("checkin: " + hotelcheckin.getEntryDate());
        	
    		hotelcheckin.setHotelguest(guest);
        	hotelcheckinRepository.save(hotelcheckin);
        	
        	return hotelcheckinRepository.save(hotelcheckin);
    	}).orElseThrow(() -> new ResourceNotFoundException("HotelGuest " + hotelcheckin.getHotelguest().getId() + " not found"));
    	
    	
    }

    @PutMapping("/hotelcheckins/{id}")
    public ResponseEntity<Hotelcheckin> updateHotelcheckin(@PathVariable(value = "id") Long hotelcheckinId,
         @Valid @RequestBody Hotelcheckin hotelcheckinDetails) throws ResourceNotFoundException {
        Hotelcheckin hotelcheckin = hotelcheckinRepository.findById(hotelcheckinId)
        .orElseThrow(() -> new ResourceNotFoundException("Hotelcheckin not found for this id : " + hotelcheckinId));

        hotelcheckin.setEntryDate(hotelcheckinDetails.getEntryDate());
        hotelcheckin.setOutDate(hotelcheckinDetails.getOutDate());
        hotelcheckin.setIeVehicle(hotelcheckinDetails.isIeVehicle());
        hotelcheckin.setIeStatus(hotelcheckinDetails.getIeStatus());
        hotelcheckin.setVlTotal(hotelcheckinDetails.getVlTotal());
        final Hotelcheckin updatedHotelcheckin = hotelcheckinRepository.save(hotelcheckin);
        return ResponseEntity.ok(updatedHotelcheckin);
    }
    
    @PutMapping("/hotelcheckins/close/{id}")
    public ResponseEntity<Hotelcheckin> closeHotelcheckin(@PathVariable(value = "id") Long hotelcheckinId,
         @Valid @RequestBody Hotelcheckin hotelcheckinDetails) throws ResourceNotFoundException {
        Hotelcheckin hotelcheckin = hotelcheckinRepository.findById(hotelcheckinId)
        .orElseThrow(() -> new ResourceNotFoundException("Hotelcheckin not found for this id : " + hotelcheckinId));

        hotelcheckin.setOutDate(hotelcheckinDetails.getOutDate());
        hotelcheckin.setIeStatus(hotelcheckinDetails.getIeStatus());
        hotelcheckin.setVlTotal(hotelcheckinDetails.getVlTotal());
        final Hotelcheckin updatedHotelcheckin = hotelcheckinRepository.save(hotelcheckin);
        return ResponseEntity.ok(updatedHotelcheckin);
    }

    @DeleteMapping("/hotelcheckins/{id}")
    public Map<String, Boolean> deleteHotelcheckin(@PathVariable(value = "id") Long hotelcheckinId)
         throws ResourceNotFoundException {
        Hotelcheckin hotelcheckin = hotelcheckinRepository.findById(hotelcheckinId)
       .orElseThrow(() -> new ResourceNotFoundException("Hotelcheckin not found for this id :: " + hotelcheckinId));

        hotelcheckinRepository.delete(hotelcheckin);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
