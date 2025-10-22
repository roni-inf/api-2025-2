package br.com.serratec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import br.com.serratec.entity.Funcionario;
import br.com.serratec.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository repository;

	public List<Funcionario> listar() {
		return repository.findAll();
	}

	public Page<Funcionario> listarPorPagina(Pageable pageable) {
		
		return repository.findAll(pageable);
	}
}
