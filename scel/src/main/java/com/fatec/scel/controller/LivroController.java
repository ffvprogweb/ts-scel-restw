package com.fatec.scel.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.scel.model.Livro;
import com.fatec.scel.servico.LivroServico;

@RestController
@RequestMapping("/api")
public class LivroController {
	@Autowired
	LivroServico servico;
	
	Logger logger = LogManager.getLogger(LivroController.class);
	@PostMapping("/v1/livro")
	public ResponseEntity<Object> create (@RequestBody @Valid Livro livro, BindingResult result){
		if(result.hasErrors()) {
			logger.info(">>>>>> 1. controller chamou servico save - erro detectado no bean");
			return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
		}else {
			logger.info(">>>>>> 1. controller chamou servico save sem erro no bean validation");
			return servico.save(livro);
		}
	}
	@GetMapping("/v1/livros")
	public ResponseEntity<List<Livro>> consultaTodos(){
		return servico.consultaTodos();
		
	}
	@GetMapping("/v1/livro/{id}")
	public ResponseEntity<Livro> findById(@PathVariable long id) {
		logger.info(">>>>>> 1. controller chamou servico consulta por id => " + id );
		return servico.consultaPorId(id);
	}
	@GetMapping("/v1/livros/{isbn}")
	public ResponseEntity<Livro> findByIsbn(@PathVariable String isbn) {
		logger.info(">>>>>> 1. controller chamou servico consulta por isbn => " + isbn );
		return servico.consultaPorIsbn(isbn);
	}
}
