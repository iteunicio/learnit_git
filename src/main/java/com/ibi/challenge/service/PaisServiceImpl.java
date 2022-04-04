package com.ibi.challenge.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ibi.challenge.data.domain.Pais;
import com.ibi.challenge.data.payload.request.PaisRequest;
import com.ibi.challenge.data.repository.PaisRepository;
import com.ibi.challenge.exception.ResourceExistsException;
import com.ibi.challenge.exception.ResourceNFException;

@Service
public class PaisServiceImpl implements PaisService {

	@Autowired
	PaisRepository rep;
	
	@Override
	public Pais create(PaisRequest pr) {
		if (rep.findByNome(pr.getNome()).isPresent())
			throw new ResourceExistsException("Pais", "nome", pr.getNome());
		Pais pais = new Pais();
		pais.setNome(pr.getNome());
		pais.setRegiao(pr.getRegiao());
		pais.setSubRegiao(pr.getSubRegiao());
		pais.setArea(pr.getArea());
		rep.save(pais);
		
		return pais;
	}

	@Override
	public Page<Pais> list(Pageable pageable) {
		return rep.findAll(pageable);
	}
	

	@Override
	public Pais update(Long id, PaisRequest pr) {
		verifyById(id);
		
		Pais pais = rep.getById(id);
		pais.setNome(pr.getNome());
		pais.setRegiao(pr.getRegiao());
		pais.setSubRegiao(pr.getSubRegiao());
		pais.setArea(pr.getArea());
		rep.save(pais);
		
		return pais;
	}

	@Override
	public Pais delete(Long id) {
		verifyById(id);
		Pais pais = rep.getById(id);
		rep.deleteById(id);
		
		return pais;
	}

	@Override
	public Pais getById(Long id) {
		verifyById(id);
		
		return rep.getById(id);
	}

	@Override
	public Pais getByNome(String nome) {
		Pais pais = rep.findByNome(nome).orElseThrow(
			() -> new ResourceNFException("País", "nome", nome));
		return pais;
	}

	@Override
	public Pais getByArea(double area) {
		Pais pais = rep.findByArea(area).orElseThrow(
			() -> new ResourceNFException("País", "área", area));
		return pais;
	}

	@Override
	public Page<Pais> listByRegiao(String regiao, Pageable pageable) {
		return rep.findByRegiao(regiao, pageable);
	}

	@Override
	public Page<Pais> listBySubRegiao(String subRegiao, Pageable pageable) {
		return rep.findBySubRegiao(subRegiao, pageable);
	}
	
	private void verifyById(Long id) {
		Optional<Pais> pais = rep.findById(id);
		if (!pais.isPresent())
			throw new ResourceNFException("Pais", "id", id);
	}
}
