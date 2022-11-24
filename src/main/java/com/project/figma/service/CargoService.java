package com.project.figma.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.project.figma.entities.Cargo;
import com.project.figma.entities.dto.CargoDTO;
import com.project.figma.entities.dto.CargoDtoALL;
import com.project.figma.entities.dto.CargoDtoPOST;
import com.project.figma.repository.CargoRepository;
import com.project.figma.repository.SetorRepository;

@Service
public class CargoService {

	@Autowired
	private CargoRepository repository;
	@Autowired
	private SetorRepository setorRepository;

	public CargoDTO find(Integer id) {
		Optional<Cargo> obj = repository.findById(id);
		Cargo cargo = obj.orElseThrow( () -> new com.project.figma.service.exceptions.ObjectNotFoundException("ERROR: Sistema não conseguiu encontrar o ID!"));
		return new CargoDTO(cargo);
	}

	public List<CargoDtoALL> findAll() {
		List<Cargo> obj = repository.findAll();
		return obj.stream().map(CargoDtoALL::new).collect(Collectors.toList());
	}

	public Cargo insert(CargoDtoPOST obj) {
		var setor = setorRepository.findById(obj.getSetorId());
		Cargo cargo = new Cargo();
		cargo.setName(obj.getName())
		.setSetor(setor.get());
		repository.save(cargo);
		return cargo;
	}

	public void delete(Integer id) {
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new com.project.figma.service.exceptions.DataIntegrityViolationException("ERROR: Não foi possivel excluir esse dado.");
		}
	}
	
	public Cargo Update(CargoDTO obj) {
		var objPass = repository.findById(obj.getId());
		updateData(objPass, obj);
		return repository.save(objPass.get());
	}

	// =========================================================================
	// 							FUNÇÂO / METODOS
	// =========================================================================
	
	public void updateData( Optional<Cargo> objPass, CargoDTO newObj) {
		objPass.get().setName(newObj.getName());
	}
	
}
