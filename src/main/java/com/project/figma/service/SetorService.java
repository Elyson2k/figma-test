package com.project.figma.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.figma.entities.Setor;
import com.project.figma.entities.dto.SetorDTO;
import com.project.figma.entities.dto.SetorDtoPOST;
import com.project.figma.repository.SetorRepository;

@Service
public class SetorService {
	
	@Autowired
	private SetorRepository repository;
	
	public SetorDTO find(Integer id) {
		Optional<Setor> obj = repository.findById(id);
		Setor newObj = obj.orElseThrow( () -> new com.project.figma.service.exceptions.ObjectNotFoundException("ERROR: ID não cadastrado no sistema.") );
		return new SetorDTO(newObj);
	}
	
	public List<SetorDTO> findAll(){
		List<Setor> obj = repository.findAll();
		return obj.stream().map(SetorDTO::new).collect(Collectors.toList());
	}
	
	public void delete(Integer id) {
		try {
			repository.deleteById(id);
		} catch (RuntimeException e) {
			throw new com.project.figma.service.exceptions.DataIntegrityViolationException("ERROR: Não foi possivel excluir esse dado.");
		}
	}
	
	public Setor inserSetor(SetorDtoPOST obj) {
		Setor newObj = new Setor();
		newObj.setName(obj.getName());
		repository.save(newObj);
		return newObj;
	}
	
	public Setor update(SetorDTO obj) {
		var newObj = repository.findById(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj.get());
	}
	
	// =========================================================================
	//                         FUNÇÂO / METODOS
	// =========================================================================
	
	public void updateData(Optional<Setor> obj, SetorDTO objPass) {
		obj.get().setName(objPass.getName());
	}
	
}
