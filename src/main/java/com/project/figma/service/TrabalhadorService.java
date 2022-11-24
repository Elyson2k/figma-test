package com.project.figma.service;

import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.project.figma.entities.Cargo;
import com.project.figma.entities.Trabalhador;
import com.project.figma.entities.dto.CargoDTO;
import com.project.figma.entities.dto.TrabalhadorDTO;
import com.project.figma.entities.dto.TrabalhadorDtoPOST;
import com.project.figma.repository.TrabalhadorRepository;

@Service
public class TrabalhadorService {
	
	@Autowired
	private TrabalhadorRepository repository;
	@Autowired
	private CargoService cargoService;
	
	public TrabalhadorDTO find(Integer id) {
		Optional<Trabalhador> obj = repository.findById(id);
		var newObj = obj.orElseThrow( () -> new ObjectNotFoundException("ERROR: ID não cadastrado no sistema.", null) );
		return new TrabalhadorDTO(newObj);
	}
	
	public List<TrabalhadorDTO> findAll(){
		List<Trabalhador> list = repository.findAll();
		return list.stream().map(TrabalhadorDTO::new).collect(Collectors.toList());
	}
	
	public Trabalhador insert(TrabalhadorDtoPOST novoTrabalhador) {		
		findEmail(novoTrabalhador);
		var cargo = cargoService.find(novoTrabalhador.getCargoId());
		Cargo cargoEntity = fromDto(cargo);
		Trabalhador trabalhador = new Trabalhador();
		trabalhador.setId(null)
				.setCargo( cargoEntity)
				.setSetor(cargo.getSetor())
				.setCpf(novoTrabalhador.getCpf())
				.setEmail(novoTrabalhador.getEmail())
				.setName(novoTrabalhador.getName());
		return repository.save(trabalhador);		
	}
	
	public Trabalhador update(TrabalhadorDTO obj) {
		findEmail(obj);
		var newObj = repository.findById(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj.get());
	}

	
	public void delete(Integer id) {
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new com.project.figma.service.exceptions.DataIntegrityViolationException("ERROR: Não foi possivel excluir esse dado.");
		}
	}
	
	// =========================================================================
	//                         FUNÇÂO / METODOS
	// =========================================================================
	
	private Cargo fromDto(CargoDTO dto) {
		return new Cargo(dto.getId(), dto.getName(), dto.getSetor());
	}
	
	public void updateData(Optional<Trabalhador> newObj, TrabalhadorDTO obj) {
		newObj.get().setName(obj.getName());
		newObj.get().setEmail(obj.getEmail());
	}
	
	public void findEmail(TrabalhadorDTO obj) {
		Optional<Trabalhador> find = repository.findByEmail(obj.getEmail());
		if(find.isPresent()) {
			throw new com.project.figma.service.exceptions.DataIntegrityViolationException("ERROR: Email ja cadastrado no sistema");
		}
	}
	
	public void findEmail(TrabalhadorDtoPOST obj) {
		Optional<Trabalhador> find = repository.findByEmail(obj.getEmail());
		if(find.isPresent()) {
			throw new com.project.figma.service.exceptions.DataIntegrityViolationException("ERROR: Email ja cadastrado no sistema");
		}
	}
	
	
}
