package com.project.figma.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.figma.entities.Trabalhador;

public interface TrabalhadorRepository extends JpaRepository<Trabalhador,Integer>{

	Optional<Trabalhador> findByEmail(String email);

}
