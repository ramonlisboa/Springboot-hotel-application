package net.javahotel.springboot.Springboothotelapplication.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javahotel.springboot.Springboothotelapplication.exception.ResourceNotFoundException;
import net.javahotel.springboot.Springboothotelapplication.model.Hotelguest;
import net.javahotel.springboot.Springboothotelapplication.repository.HotelguestRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class HotelguestController {

	@Autowired
	private HotelguestRepository hotelguestRepository;

	@GetMapping("/hotelguests")
	public List<Hotelguest> getAllHotelguest() {
		return hotelguestRepository.findAll();
	}

	@GetMapping("/hotelguests/{id}")
	public ResponseEntity<Hotelguest> getHotelguestById(@PathVariable(value = "id") Long hotelguestId)
			throws ResourceNotFoundException {
		Hotelguest hotelguest = hotelguestRepository.findById(hotelguestId).orElseThrow(
				() -> new ResourceNotFoundException("Hotel guest not found for this id : " + hotelguestId));
		return ResponseEntity.ok().body(hotelguest);
	}

	@GetMapping("/hotelguests/name/{name}")
	public List<Hotelguest> getHotelguestByName(@PathVariable(value = "name") String hotelguestName) {
		List<Hotelguest> hotelguest = hotelguestRepository.findByNameContaining(hotelguestName);
		return hotelguest;
	}

	@GetMapping("/hotelguests/document/{document}")
	public List<Hotelguest> getHotelguestByDocument(@PathVariable(value = "document") String hotelguestDocument) {
		List<Hotelguest> hotelguest = hotelguestRepository.findByDocumentContaining(hotelguestDocument);
		return hotelguest;
	}

	@GetMapping("/hotelguests/phone/{phone}")
	public List<Hotelguest> getHotelguestByPhone(@PathVariable(value = "phone") String hotelguestPhone) {
		List<Hotelguest> hotelguest = hotelguestRepository.findByPhoneContaining(hotelguestPhone);
		return hotelguest;
	}

	@PostMapping("/hotelguests")
	public Hotelguest createHotelguest(@Valid @RequestBody Hotelguest hotelguest) {
		return hotelguestRepository.save(hotelguest);
	}

	@PutMapping("/hotelguests/{id}")
	public ResponseEntity<Hotelguest> updateHotelguest(@PathVariable(value = "id") Long hotelguestId,
			@Valid @RequestBody Hotelguest hotelguestDetails) throws ResourceNotFoundException {
		Hotelguest hotelguest = hotelguestRepository.findById(hotelguestId)
				.orElseThrow(() -> new ResourceNotFoundException("Hotelguest not found for this id : " + hotelguestId));

		hotelguest.setName(hotelguestDetails.getName());
		hotelguest.setDocument(hotelguestDetails.getDocument());
		hotelguest.setPhone(hotelguestDetails.getPhone());
		final Hotelguest updatedHotelguest = hotelguestRepository.save(hotelguest);
		return ResponseEntity.ok(updatedHotelguest);
	}

	@DeleteMapping("/hotelguests/{id}")
	public Map<String, Boolean> deleteHotelguest(@PathVariable(value = "id") Long hotelguestId)
			throws ResourceNotFoundException {
		Hotelguest hotelguest = hotelguestRepository.findById(hotelguestId).orElseThrow(
				() -> new ResourceNotFoundException("Hotelguest not found for this id :: " + hotelguestId));

		hotelguestRepository.delete(hotelguest);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
