package com.project.figma.service;

import java.util.List;
import java.util.Optional;

import com.project.figma.controller.TrabalhadorController;
import org.hibernate.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.figma.entities.model.Cargo;
import com.project.figma.entities.model.Setor;
import com.project.figma.entities.dto.CargoDtoPOST;
import com.project.figma.repository.CargoRepository;

@Service
public class CargoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CargoService.class);

	private final String CARGO_NOT_FOUND_ERROR = "ERROR: ID não cadastrado no sistema.";

	private final String CLASS_NAME = "Cargo";

	// TODO REMOVER AUTOWIRED
	@Autowired
	private CargoRepository repository;
	@Autowired
	private SetorService setorService;

	public Cargo find(Integer id) {
		Optional<Cargo> obj = repository.findById(id);
		return obj.orElseThrow(() -> {
			LOGGER.error("m=find stage=error code={} description={} ", 404, CARGO_NOT_FOUND_ERROR);
			throw new ObjectNotFoundException(CARGO_NOT_FOUND_ERROR, CLASS_NAME);
		});
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
