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
import com.autobots.automanager.entidades.Telefone;
import com.autobots.automanager.modelo.TelefoneAtualizador;
import com.autobots.automanager.modelo.ClienteSelecionador;
import com.autobots.automanager.repositorios.ClienteRepositorio;
import com.autobots.automanager.repositorios.TelefoneRepositorio;

@RestController
@RequestMapping("/telefone")
public class TelefoneControle {
	@Autowired
	private TelefoneRepositorio repositorioTelefone;
	@Autowired
	private ClienteRepositorio repositorioCliente;
	@Autowired
	private ClienteSelecionador selecionador;


	
	@GetMapping("/cliente/{id}")
	public List<Telefone> obterTelefone(@PathVariable long id) {
		List<Cliente> clientes = repositorioCliente.findAll();
		return selecionador.selecionar(clientes, id).getTelefones();
	}

	@GetMapping("/todos")
	public List<Telefone> obterTelefones() {
		List<Telefone> Telefones = repositorioTelefone.findAll();
		return Telefones;
	}

	@PostMapping("/cadastrar")
	public void cadastrarTelefone(@RequestBody Telefone Telefone) {
		repositorioTelefone.save(Telefone);
	}

	@PutMapping("/atualizar")
	public void atualizarTelefone(@RequestBody Telefone atualizacao) {
		Telefone Telefone = repositorioTelefone.getById(atualizacao.getId());
		TelefoneAtualizador atualizador = new TelefoneAtualizador();
		atualizador.atualizar(Telefone, atualizacao);
		repositorioTelefone.save(Telefone);
	}

	@DeleteMapping("/excluir")
	public void excluirTelefone(@RequestBody Telefone exclusao) {
		Telefone Telefone = repositorioTelefone.getById(exclusao.getId());
		repositorioTelefone.delete(Telefone);
	}
}
