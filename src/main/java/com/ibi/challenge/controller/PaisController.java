package com.ibi.challenge.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ibi.challenge.data.domain.Pais;
import com.ibi.challenge.data.payload.request.PaisRequest;
import com.ibi.challenge.data.payload.response.MessageResponse;
import com.ibi.challenge.service.PaisService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/paises")
@ApiResponses(value = {
	@io.swagger.annotations.ApiResponse(code = 200, 
		message = "A solicitação foi bem-sucedida."),
	@io.swagger.annotations.ApiResponse(code = 201, 
		message = "A solicitação foi bem-sucedida e " + 
			"um novo recurso foi criado como resultado."),
	@io.swagger.annotations.ApiResponse(code = 400, 
		message = "O servidor não conseguiu entender a " + 
			"solicitação devido à sintaxe inválida."), 
	@io.swagger.annotations.ApiResponse(code = 401, 
		message = "Devido a restrições de segurança, "+ 
			"sua solicitação de acesso não pode ser autorizada."),
	@io.swagger.annotations.ApiResponse(code = 403, 
		message = "O cliente não tem direitos de acesso ao conteúdo."),
	@io.swagger.annotations.ApiResponse(code = 403, 
		message = "O servidor não consegue encontrar o recurso solicitado."),
	@io.swagger.annotations.ApiResponse(code = 405, 
		message = "O método de solicitação é conhecido pelo servidor, " + 
			"mas não é suportado pelo recurso de destino."),
	@io.swagger.annotations.ApiResponse(code = 406, 
		message = "O servidor não encontra nenhum conteúdo que esteja " + 
			"de acordo com os critérios fornecidos pelo agente do usuário."),
	@io.swagger.annotations.ApiResponse(code = 500, 
		message = "O servidor encontrou uma situação que não sabe como lidar.") 
	}
)
public class PaisController {

	@Autowired
	private PaisService service;
	
	@PostMapping
	@ApiOperation(value = "Adiciona novo país.", response = Pais.class, 
		responseContainer = "Message")
	public ResponseEntity<MessageResponse> adicionar( @Valid
			@RequestBody PaisRequest pr, Pageable pageable) {
		Pais pais = service.create(pr);
		MessageResponse msg = new MessageResponse(String.format(
			"País (%s) adicionado com sucesso.", pais.getNome()), 
				linkSelf(pais.getId(), pageable).getHref(), 
				linkGeral(pageable).getHref());
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ServletUriComponentsBuilder
			.fromCurrentRequest().path("/{id}")
			.buildAndExpand(pais.getId())
			.toUri());
		
		return new ResponseEntity<>(msg, headers, HttpStatus.CREATED);
	}
	
	@GetMapping
	@ApiOperation(value = "Lista todos países.", response = Pais.class, 
		responseContainer = "List")
	public ResponseEntity<Page<Pais>> paises(Pageable pageable) {
		Page<Pais> paises = service.list(pageable);
		
		paises.forEach(pais -> {
			pais.add(linkSelf(pais.getId(), pageable));
			pais.add(linkRegiao(pais.getRegiao(), pageable));			
			pais.add(linkSubRegiao(pais.getSubRegiao(), pageable));
		});
		
		return ResponseEntity.ok(paises);
	}
	
	@GetMapping("/regiao/{regiao}")
	@ApiOperation(value = "Lista todos países de uma determinada região/continente.", 
		response = Pais.class, responseContainer = "List")
	public ResponseEntity<Page<Pais>> paises(
			@PathVariable("regiao") String regiao, Pageable pageable) {
		Page<Pais> paises = service.listByRegiao(regiao, pageable);
		
		paises.forEach(pais -> {
			pais.add(linkSelf(pais.getId(), pageable));			
			pais.add(linkGeral(pageable));			
			pais.add(linkSubRegiao(pais.getSubRegiao(), pageable));
		});
		
		return ResponseEntity.ok(paises);
	}
	
	@GetMapping("/sub-regiao/{subRegiao}")
	@ApiOperation(value = "Lista todos países de uma determinada subregião.", 
		response = Pais.class, responseContainer = "List")
	public ResponseEntity<Page<Pais>> paisesSub(
			@PathVariable("subRegiao") String subRegiao, Pageable pageable) {
		Page<Pais> paises = service.listBySubRegiao(subRegiao, pageable);
		
		paises.forEach(pais -> {
			pais.add(linkSelf(pais.getId(), pageable));
			pais.add(linkGeral(pageable));			
			pais.add(linkRegiao(pais.getRegiao(), pageable));
		});
		
		return ResponseEntity.ok(paises);
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Retorna um país pelo id.", response = Pais.class, 
		responseContainer = "Pais")
	public ResponseEntity<Pais> pais(@PathVariable("id") Long id, Pageable pageable) {
		Pais pais = service.getById(id);		
		return ResponseEntity.ok(linkGeral(pais, pageable).add(linkSelf(pais.getId(), pageable)));
	}
	
	@GetMapping("/nome/{nome}")
	@ApiOperation(value = "Retorna um país pelo nome.", response = Pais.class, 
		responseContainer = "Pais")
	public ResponseEntity<Pais> pais(@PathVariable("nome") String nome, Pageable pageable) {
		Pais pais = service.getByNome(nome);
		return ResponseEntity.ok(linkGeral(pais, pageable).add(linkSelf(pais.getId(), pageable)));
	}
	
	@GetMapping("/area/{area}")
	@ApiOperation(value = "Retorna um país pela área (em km²).", response = Pais.class, 
		responseContainer = "Pais")
	public ResponseEntity<Pais> pais(@PathVariable("area") double area, Pageable pageable) {
		Pais pais = service.getByArea(area);		
		return ResponseEntity.ok(linkGeral(pais, pageable).add(linkSelf(pais.getId(), pageable)));
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "Actualiza um país pelo id.", response = Pais.class, 
		responseContainer = "Msg")
	public ResponseEntity<MessageResponse> actualizar(@PathVariable("id") 
			Long id, @Valid @RequestBody PaisRequest pr, Pageable pageable) {
		Pais pais = service.update(id, pr);
		
		return ResponseEntity.ok(new MessageResponse(String.format(
			"Dados de %s actualizados com sucesso.", pais.getNome()), 
				linkSelf(pais.getId(), pageable).getHref(), 
				linkGeral(pageable).getHref()));
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Exclui um país pelo id.", response = Pais.class, 
		responseContainer = "Msg")
	public ResponseEntity<?> excluir(@PathVariable("id") Long id, Pageable pageable) {
		Pais pais = service.delete(id);		
		return ResponseEntity.ok(new MessageResponse(String.format(
			"%s excluído com sucesso.", pais.getNome()), 
				"[Excluído]", 
				linkGeral(pageable).getHref()));
	}
	
	private Pais linkGeral(Pais pais, Pageable pageable) {
		 return pais.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
			getClass()).paises(pageable)).withRel("Todos Países"));
	}
	
	private Link linkGeral(Pageable pageable) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
				.methodOn(getClass()).paises(pageable)).withSelfRel();
	}
	
	private Link linkSelf(Long id, Pageable pageable) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
				.methodOn(getClass()).pais(id , pageable)).withSelfRel();
	}
	
	private Link linkRegiao(String regiao, Pageable pageable) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
			getClass()).paises(regiao, pageable)).withRel("Região: " + regiao);
	}
	
	private Link linkSubRegiao(String subRegiao, Pageable pageable) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
			getClass()).paisesSub(subRegiao, pageable)).withRel("Sub-Região: " + subRegiao);
	}
}
