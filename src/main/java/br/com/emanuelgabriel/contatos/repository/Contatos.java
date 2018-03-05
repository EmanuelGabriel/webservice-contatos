package br.com.emanuelgabriel.contatos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.emanuelgabriel.contatos.model.Contato;

public interface Contatos extends JpaRepository<Contato, Long> {

}
