package com.project.figma.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.figma.entities.Cargo;
import com.project.figma.entities.Setor;
import com.project.figma.entities.dto.CargoDtoPOST;
import com.project.figma.repository.CargoRepository;

@Service
public class CargoService {

	@Autowired
	private CargoRepository repository;
	@Autowired
	private SetorService setorService;

	public Cargo find(Integer id) {
		Optional<Cargo> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("ERROR: ID não cadastrado no sistema.", null));
	}

	public List<Cargo> findAll() {
		List<Cargo> obj = repository.findAll();
		return obj;
	}

	public Cargo insert(Cargo obj) {
		obj.setId(null);
		repository.save(obj);
		return obj;
	}

	public void delete(Integer id) {
		repository.deleteById(id);
	}

	// =========================================================================
	// 							FUNÇÂO / METODOS
	// =========================================================================
	
	public Cargo fromDto(CargoDtoPOST obj) {
		Setor s1 = setorService.find(obj.getSetorId());
		Cargo c1 = new Cargo(null, obj.getName(), s1);
		s1.getCargos().add(c1);
		return c1;
	}
}
