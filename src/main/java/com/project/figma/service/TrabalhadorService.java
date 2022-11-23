package com.project.figma.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.figma.entities.Cargo;
import com.project.figma.entities.Trabalhador;
import com.project.figma.entities.dto.TrabalhadorDtoPOST;
import com.project.figma.repository.TrabalhadorRepository;

@Service
public class TrabalhadorService {
	
	@Autowired
	private TrabalhadorRepository repository;
	@Autowired
	private CargoService cargoService;
	
	public Trabalhador find(Integer id) {
		Optional<Trabalhador> obj = repository.findById(id);
		return obj.orElseThrow( () -> new ObjectNotFoundException("ERROR: ID não cadastrado no sistema.", null) );
	}
	
	public List<Trabalhador> findAll(){
		List<Trabalhador> obj = repository.findAll();
		return obj;
	}
	
	public Trabalhador insert(Trabalhador obj) {
		obj.setId(null);
		return repository.save(obj);
	}
	
	public void delete(Integer id) {
		repository.deleteById(id);
	}
	
	// =========================================================================
	//                         FUNÇÂO / METODOS
	// =========================================================================
	
	public Trabalhador fromDto(TrabalhadorDtoPOST obj) {
		Cargo cargo = cargoService.find(obj.getCargoId());
		Trabalhador trab = new Trabalhador(null, obj.getName(), obj.getEmail(), obj.getCpf(), cargo.getSetor(), cargo);
		return trab;
	}
	
	
}
