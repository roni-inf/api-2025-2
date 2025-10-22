package br.com.serratec.dto;

import br.com.serratec.entity.Endereco;

public record EnderecoResponseDTO(String cep, String logradouro, String bairro, String localidade, String uf) {

	
}
