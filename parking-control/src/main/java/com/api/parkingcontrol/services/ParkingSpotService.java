package com.api.parkingcontrol.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.parkingcontrol.models.ParkingSpotModel;
import com.api.parkingcontrol.repositories.ParkingSpotRepository;

@Service
public class ParkingSpotService {

//	@Autowired
//	ParkingSpotRepository parkingSpotRepositoy;
	
	final ParkingSpotRepository parkingSpotRepositoy;
	
	
	public ParkingSpotService(ParkingSpotRepository parkingSpotRepositoy) {
		this.parkingSpotRepositoy = parkingSpotRepositoy;
	}

	@Transactional
	public Object save(ParkingSpotModel parkingSpotModel) {
		
		return parkingSpotRepositoy.save(parkingSpotModel);
	}

	public boolean existsByLicencePlateCar(String licencePlateCar) {	 
		return parkingSpotRepositoy.existsByLicencePlateCar(licencePlateCar);
	}

	public boolean existsByParkingSpotNumber(String parkingSpotNumber) {
		 
		return parkingSpotRepositoy.existsByParkingSpotNumber(parkingSpotNumber);
	}

/*	public Page<ParkingSpotModel> finAll(Pageable pageable) {
		
		return parkingSpotRepositoy.findAll(pageable);
	}
*/
	public List<ParkingSpotModel> finAll() {
		
		return parkingSpotRepositoy.findAll();
	}

	public Optional<ParkingSpotModel> findById(Long id) {
		
		return parkingSpotRepositoy.findById(id);
	}

	public void delete(ParkingSpotModel parkingSpotModel) {
		parkingSpotRepositoy.delete(parkingSpotModel);
		return;
	}

}
