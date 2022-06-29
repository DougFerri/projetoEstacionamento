package com.api.parkingcontrol.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * @author dougf
 *
 */
@Entity
@Table(name = "TB_PARKING_SPOT")
@SequenceGenerator(name = "seq_usuario", sequenceName =  "seq_usuario", allocationSize = 1, initialValue = 1)
public class ParkingSpotModel implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuario")
	private Long id;
	
	@Column(nullable = false, unique = true, length = 10)
	private String parkingSpotNumber;
	
	@Column(nullable = false, unique = true, length = 7)	
	private String licencePlateCar;
	
	@Column(nullable = false, length = 70)	
	private String brandCar;
	
	@Column(nullable = false, length = 70)	
	private String modelCar;
	
	@Column(nullable = false)	
	private String colorCar;
	
	@Column(nullable = false, length = 70)	
	private LocalDateTime registrationDate;

	@Column(nullable = false, length = 130)	
	private String responsibleName;

	@Column(nullable = false, length = 30)	
	private String apartment;

	@Column(nullable = false, length = 30)	
	private String block;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getParkingSpotNumber() {
		return parkingSpotNumber;
	}

	public void setParkingSpotNumber(String parkingSpotNumber) {
		this.parkingSpotNumber = parkingSpotNumber;
	}

	public String getLicencePlateCar() {
		return licencePlateCar;
	}

	public void setLicencePlateCar(String licencePlateCar) {
		this.licencePlateCar = licencePlateCar;
	}

	public String getBrandCar() {
		return brandCar;
	}

	public void setBrandCar(String brandCar) {
		this.brandCar = brandCar;
	}

	public String getModelCar() {
		return modelCar;
	}

	public void setModelCar(String modelCar) {
		this.modelCar = modelCar;
	}

	public String getColorCar() {
		return colorCar;
	}

	public void setColorCar(String colorCar) {
		this.colorCar = colorCar;
	}

	public LocalDateTime getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(LocalDateTime registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getResponsibleName() {
		return responsibleName;
	}

	public void setResponsibleName(String responsibleName) {
		this.responsibleName = responsibleName;
	}

	public String getApartment() {
		return apartment;
	}

	public void setApartment(String apartment) {
		this.apartment = apartment;
	}

	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block;
	}

	
}
