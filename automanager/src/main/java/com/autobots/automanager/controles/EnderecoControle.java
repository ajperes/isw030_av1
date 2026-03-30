package com.autobots.automanager.controles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.entidades.Endereco;
import com.autobots.automanager.modelo.EnderecoAtualizador;
import com.autobots.automanager.modelo.ClienteSelecionador;
import com.autobots.automanager.repositorios.ClienteRepositorio;
import com.autobots.automanager.repositorios.EnderecoRepositorio;

@RestController
@RequestMapping("/endereco")
public class EnderecoControle {
	@Autowired
	private EnderecoRepositorio repositorioEndereco;
	@Autowired
	private ClienteRepositorio repositorioCliente;
	@Autowired
	private ClienteSelecionador selecionador;


	
	@GetMapping("/cliente/{id}")
	public Endereco obterEndereco(@PathVariable long id) {
		List<Cliente> clientes = repositorioCliente.findAll();
		return selecionador.selecionar(clientes, id).getEndereco();
	}

	@GetMapping("/todos")
	public List<Endereco> obterEnderecos() {
		List<Endereco> Enderecos = repositorioEndereco.findAll();
		return Enderecos;
	}

	@PostMapping("/cadastrar")
	public void cadastrarEndereco(@RequestBody Endereco Endereco) {
		repositorioEndereco.save(Endereco);
	}

	@PutMapping("/atualizar")
	public void atualizarEndereco(@RequestBody Endereco atualizacao) {
		Endereco Endereco = repositorioEndereco.getById(atualizacao.getId());
		EnderecoAtualizador atualizador = new EnderecoAtualizador();
		atualizador.atualizar(Endereco, atualizacao);
		repositorioEndereco.save(Endereco);
	}

	@DeleteMapping("/excluir")
	public void excluirEndereco(@RequestBody Endereco exclusao) {
		Endereco Endereco = repositorioEndereco.getById(exclusao.getId());
		repositorioEndereco.delete(Endereco);
	}
}
