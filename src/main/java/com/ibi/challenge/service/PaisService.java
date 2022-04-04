package com.ibi.challenge.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.ibi.challenge.data.domain.Pais;
import com.ibi.challenge.data.payload.request.PaisRequest;

@Component
public interface PaisService {

	Pais create(PaisRequest pr);
	
	Page<Pais> list(Pageable pageable);
	
	Pais update(Long id, PaisRequest pr);
	
	Pais delete(Long id);
	
	Pais getById(Long id);
	
	Pais getByNome(String nome);
	
	Pais getByArea(double area);
	
	Page<Pais> listByRegiao(String regiao, Pageable pageable);
	
	Page<Pais> listBySubRegiao(String subRegiao, Pageable pageable);
}