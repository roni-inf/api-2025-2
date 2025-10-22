package br.com.serratec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.entity.Funcionario;
import br.com.serratec.service.FuncionarioService;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

	@Autowired
	private FuncionarioService service;

	@GetMapping
	public List<Funcionario> listar() {
		return service.listar();
	}

	@GetMapping("/paginacao")
	public Page<Funcionario> listar(@PageableDefault(
			page = 0, size = 10, sort = "dataNascimento", 
			direction = Direction.DESC)  Pageable pageable) {
		return service.listarPorPagina(pageable);
	}
}
