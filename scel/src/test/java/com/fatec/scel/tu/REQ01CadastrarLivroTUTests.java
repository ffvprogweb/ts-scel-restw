package com.fatec.scel.tu;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fatec.scel.controller.LivroController;
import com.fatec.scel.model.Livro;
import com.fatec.scel.model.LivroRepository;
import com.fatec.scel.servico.LivroServicoI;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;

//@WebMvcTest
@SpringBootTest
class REQ01CadastrarLivroTUTests {
	@Autowired
	private LivroController livroController;
	@Autowired
	private LivroRepository livroRepository;
	@MockBean
	private LivroServicoI livroService;
	@BeforeEach // usa beforeeach nao precisa ser estatico
	public void setup() {
//		//otimiza o contexto de teste sob somente o LivroController
		RestAssuredMockMvc.standaloneSetup(this.livroController);
//		livroRepository.deleteAll();
//		Livro umLivro = new Livro("1111", "Teste de Software", "Delamaro");
//		livroRepository.save(umLivro);
//		umLivro = new Livro("2222", "Engenharia de Software", "Pressman");
//		livroRepository.save(umLivro);
//		List<Livro> livros = livroRepository.findAll();
//		ArrayList<Livro> lista = new ArrayList<Livro>();
//		livros.forEach(livro -> lista.add(livro));
//		lista.forEach(liv-> System.out.println("livros cadastrados nesta sessao =>" + liv.toString()));
	}

	@Test
	void dado_que_existe_um_livro_cadastrado_quando_consultar_retorna_sucesso() {
		Livro umLivro = new Livro("3333","Desenvolvimento de Software","Pilone");
		when(this.livroService.consultaPorId(2L)).thenReturn(new ResponseEntity<Livro> (umLivro,HttpStatus.OK));
		given().accept(ContentType.JSON) //content type do header
		.when().get("/api/v1/livro/{id}",5L)
		.then().statusCode(HttpStatus.OK.value());
	}
	@Test
	void ct_02_dado_que_existe_um_livro_cadastrado_quando_consultar_retorna_sucesso() {
		
		when(this.livroService.consultaPorId(5L)).thenReturn(new ResponseEntity<> (null,HttpStatus.NOT_FOUND));
		given().accept(ContentType.JSON) //content type do header
		.when().get("/api/v1/livro/{id}",5L)
		.then().statusCode(HttpStatus.NOT_FOUND.value());
	}

}
