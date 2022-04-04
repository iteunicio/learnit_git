package com.ibi.challenge.data.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibi.challenge.data.domain.Pais;

@Repository
public interface PaisRepository extends JpaRepository<Pais, Long> {

	public Optional<Pais> findByNome(String nome);
	
	public Page<Pais> findByRegiao(String regiao, Pageable pageable);
	
	public Page<Pais> findBySubRegiao(String subRegiao, Pageable pageable);
	
	public Optional<Pais> findByArea(double area);
}

