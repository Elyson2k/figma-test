package com.project.figma;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.project.figma.entities.model.Cargo;
import com.project.figma.entities.model.Setor;
import com.project.figma.repository.CargoRepository;
import com.project.figma.repository.SetorRepository;

@SpringBootApplication
public class FigmaApplication implements CommandLineRunner{

	@Autowired
	private CargoRepository cargoRepository;
	@Autowired
	private SetorRepository setorRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(FigmaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Setor s1 = new Setor(null, "Informatica");
		Setor s2 = new Setor(null, "Eletrica");
		
		Cargo c1 = new Cargo(null, "Eletricista Jr", s2);
		Cargo c2 = new Cargo(null, "Engenheiro de Software", s1);
		
		s1.getCargos().add(c2);
		s2.getCargos().add(c1);
		
		setorRepository.saveAll(Arrays.asList(s1,s2));
		cargoRepository.saveAll(Arrays.asList(c1,c2));
	}

}
