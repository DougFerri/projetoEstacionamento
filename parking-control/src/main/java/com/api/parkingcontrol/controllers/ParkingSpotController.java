package com.api.parkingcontrol.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.api.parkingcontrol.dto.ParkingSpotDto;
import com.api.parkingcontrol.models.ParkingSpotModel;
import com.api.parkingcontrol.services.ParkingSpotService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/parking-spot")
public class ParkingSpotController {

	@Autowired
	ParkingSpotService parkingSpotService;
	
/*	@RequestMapping(value = "/")
    public String index() {
        return "index.html";
    }  
*/
	
	@RequestMapping("/")
	   public ModelAndView index(){
	   return new ModelAndView("index");
	}
	
//	@PostMapping(value = "/save")
	@RequestMapping(value = "/save", method =  RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> saveParkingSpot(@RequestBody @Valid ParkingSpotDto parkingSpotDto){
		
		Long getId = parkingSpotDto.getId();
		
		if(getId != null) {
			return updateOneParkingSpot(parkingSpotDto.getId(), parkingSpotDto);
		}else {
 			if(parkingSpotService.existsByLicencePlateCar(parkingSpotDto.getLicencePlateCar())) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: A placa informada já está em uso!");
			}
	
			if(parkingSpotService.existsByParkingSpotNumber(parkingSpotDto.getParkingSpotNumber())) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: A vaga informada já está em uso!");
			}
 	
			var parkingSpotModel = new ParkingSpotModel();
			BeanUtils.copyProperties(parkingSpotDto, parkingSpotModel); // converter o Dto em model de forma simples
			parkingSpotModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
			return ResponseEntity.status(HttpStatus.CREATED).body(parkingSpotService.save(parkingSpotModel));
		}
	}
	
/*	@GetMapping("/get")
	public ResponseEntity<Page<ParkingSpotModel>> getAllParkingSpots(@PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable){
		return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.finAll(pageable));
	}
*/
	
	@GetMapping("/get")
	public ResponseEntity<List<ParkingSpotModel>> getAllParkingSpots(){
		return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.finAll());
	}

	@GetMapping("/getOne")
	@ResponseBody
	public ResponseEntity<Object> getOneParkingSpot(@RequestParam(value = "rfc", required =true) Long id){
		
		Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findById(id);
		
		if(!parkingSpotModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaga não encontrada");
		}
		return ResponseEntity.status(HttpStatus.OK).body(parkingSpotModelOptional.get());
	}
	
	@DeleteMapping("/del")
	@ResponseBody
	public ResponseEntity<Object> deleteOneParkingSpot(@RequestParam Long id){
		
		Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findById(id);
		
		if(!parkingSpotModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaga não encontrada");
		}
		parkingSpotService.delete(parkingSpotModelOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Vaga deletada");
	}

	@PutMapping("/put/{id}")
	public ResponseEntity<Object> updateOneParkingSpot(@PathVariable(value = "id") Long id,
													   @RequestBody @Valid ParkingSpotDto parkingSpotDto){
		
		Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findById(id);
		
		if(!parkingSpotModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found");
		}
		
		var parkingSpotModel = new ParkingSpotModel();
		BeanUtils.copyProperties(parkingSpotDto, parkingSpotModel); // converter o Dto em model de forma simples
		parkingSpotModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
		parkingSpotModel.setId(parkingSpotModelOptional.get().getId());
			
		parkingSpotService.save(parkingSpotModelOptional.get());
		
		return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.save(parkingSpotModel));
	}

}














