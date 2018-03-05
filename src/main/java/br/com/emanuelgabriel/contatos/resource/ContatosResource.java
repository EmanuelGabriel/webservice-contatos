package br.com.emanuelgabriel.contatos.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.emanuelgabriel.contatos.model.Contato;
import br.com.emanuelgabriel.contatos.repository.Contatos;

@RestController
@RequestMapping("/contatos")
public class ContatosResource {
	
	@Autowired
	private Contatos contatos;
	
	
	//Criando o método HTTP: POST - para adicionar os dados na API/RESTFull
	@PostMapping
	public Contato adicionar(@Valid @RequestBody Contato contato) {
		return contatos.save(contato);
	}
	
	
	//Criando o método HTTP: GET - buscar TODOS os dados da API/RESTFull
	@GetMapping
	public List<Contato> listar() {
		return contatos.findAll();
	}
	
	
	//Criando o método HTTP: GET - buscar dados pelo ID de cada informação do banco de dados da API/RESTFull
	@GetMapping("/{id}")
	public ResponseEntity<Contato> buscar(@PathVariable Long id) {
		
		Contato contato = contatos.findOne(id);
		
		if (contato == null) {
			
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(contato);
	}
	
	//Criando o método HTTP: PUT - atualiazar os dados da API/RESTFull
	@PutMapping("/{id}")
	public ResponseEntity<Contato> atualizar(@PathVariable Long id, @Valid @RequestBody Contato contato) {
		
		Contato existente = contatos.findOne(id);
		
		if (existente == null) {
			return ResponseEntity.notFound().build();
			
		}
		
		
		BeanUtils.copyProperties(contato, existente, "id");
		
		existente = contatos.save(existente);
		
		return ResponseEntity.ok(existente);
	
	}

	//Criando o método HTTP: DELETE - deletar dados da API
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		Contato contato = contatos.findOne(id);
		
		if (contato == null) {
			return ResponseEntity.notFound().build();
		}
		
		contatos.delete(contato);
		
		return ResponseEntity.noContent().build();
	}
	
	
	
}











