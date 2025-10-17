package br.com.serratec.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.serratec.dto.UsuarioRequestDTO;
import br.com.serratec.dto.UsuarioResponseDTO;
import br.com.serratec.entity.Usuario;
import br.com.serratec.exception.UsuarioException;
import br.com.serratec.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private BCryptPasswordEncoder encoder;

	public List<UsuarioResponseDTO> listar() {
		List<UsuarioResponseDTO> usuariosDTO = new ArrayList<>();
		for (Usuario usuario : repository.findAll()) {
			usuariosDTO.add(new UsuarioResponseDTO(usuario.getId(), usuario.getNome(), usuario.getEmail()));
		}
		return usuariosDTO;
	}

	public UsuarioResponseDTO inserir(UsuarioRequestDTO usuarioRequestDTO) {
		var user = repository.findByEmail(usuarioRequestDTO.getEmail());
		if (user.isPresent()) {
			throw new UsuarioException("Email já está cadastrado");
		}
		usuarioRequestDTO.setSenha(encoder.encode(usuarioRequestDTO.getSenha()));

		Usuario usuario = new Usuario();
		usuario.setNome(usuarioRequestDTO.getNome());
		usuario.setEmail(usuarioRequestDTO.getEmail());
		usuario.setSenha(usuarioRequestDTO.getSenha());
		usuario = repository.save(usuario);
		return new UsuarioResponseDTO(usuario.getId(), usuario.getNome(), usuario.getEmail());
	}

}
