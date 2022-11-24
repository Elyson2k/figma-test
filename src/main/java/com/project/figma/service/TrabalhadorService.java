package com.project.figma.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.figma.entities.model.Cargo;
import com.project.figma.entities.model.Trabalhador;
import com.project.figma.entities.dto.TrabalhadorDtoPOST;
import com.project.figma.repository.TrabalhadorRepository;

@Service
public class TrabalhadorService {

	private final TrabalhadorRepository repository;
	private final CargoService cargoService;

	public TrabalhadorService(TrabalhadorRepository repository, CargoService cargoService) {
		this.repository = repository;
		this.cargoService = cargoService;
	}

	public Trabalhador find(Integer id) {
		Optional<Trabalhador> obj = repository.findById(id);
		return obj.orElseThrow( () -> new ObjectNotFoundException("ERROR: ID não cadastrado no sistema.", null) );
	}
	
	public List<Trabalhador> findAll(){
		var trabalhadores = repository.findAll();
		return trabalhadores;
	}
	
	public Trabalhador insert(TrabalhadorDtoPOST novoTrabalhador) {
		var cargo = cargoService.find(novoTrabalhador.getCargoId());
		Trabalhador trabalhador = new Trabalhador();
		trabalhador.setId(null)
				.setCargo(cargo)
				.setSetor(cargo.getSetor())
				.setCpf(novoTrabalhador.getCpf())
				.setEmail(novoTrabalhador.getEmail())
				.setName(novoTrabalhador.getName());
		return repository.save(trabalhador);
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
