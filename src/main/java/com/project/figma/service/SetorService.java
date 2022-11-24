package com.project.figma.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.figma.entities.model.Setor;
import com.project.figma.entities.dto.SetorDtoPOST;
import com.project.figma.repository.SetorRepository;

@Service
public class SetorService {
	
	@Autowired
	private SetorRepository repository;
	
	public Setor find(Integer id) {
		Optional<Setor> obj = repository.findById(id);
		return obj.orElseThrow( () -> new ObjectNotFoundException("ERROR: ID não cadastrado no sistema.", null) );
	}
	
	public List<Setor> findAll(){
		List<Setor> obj = repository.findAll();
		return obj;
	}
	
	public void delete(Integer id) {
		repository.deleteById(id);
	}
	
	public Setor inserSetor(Setor obj) {
		obj.setId(null);
		return repository.save(obj);	
	}
	
	
	// =========================================================================
	//                         FUNÇÂO / METODOS
	// =========================================================================
	
	public Setor fromDto(SetorDtoPOST obj) {
		Setor s1 = new Setor(null, obj.getName());
		return s1;
	}
	
}
