package com.fatec.scel;

import java.util.List;
import java.util.stream.LongStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fatec.scel.model.Livro;
import com.fatec.scel.model.LivroRepository;

@SpringBootApplication
public class ScelApplication {
	Logger logger = LogManager.getLogger(ScelApplication.class);
	@Autowired
	LivroRepository repository;
	public static void main(String[] args) {
		SpringApplication.run(ScelApplication.class, args);
	}
	@Autowired
	public void inicializa() {
		Livro livro = new Livro("1112", "Engenharia de Software", "Pressman");
		repository.save(livro);
		Livro umLivro = repository.findByIsbn("1112");
		logger.info(">>>>>> inclui 1 registro na inicializacao =>  " + umLivro.toString());
		
	}
}
