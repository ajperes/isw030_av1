package com.autobots.automanager.controles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.entidades.Documento;
import com.autobots.automanager.modelo.DocumentoAtualizador;
import com.autobots.automanager.modelo.ClienteSelecionador;
import com.autobots.automanager.repositorios.ClienteRepositorio;
import com.autobots.automanager.repositorios.DocumentoRepositorio;

@RestController
@RequestMapping("/documento")
public class DocumentoControle {
	@Autowired
	private DocumentoRepositorio repositorioDocumento;
	@Autowired
	private ClienteRepositorio repositorioCliente;
	@Autowired
	private ClienteSelecionador selecionador;
	@Value("${autobots.roll.out}")
    private String autobots;

	
	@GetMapping("/cliente/{id}")
	public List<Documento> obterDocumento(@PathVariable long id) {
		List<Cliente> clientes = repositorioCliente.findAll();
		return selecionador.selecionar(clientes, id).getDocumentos();
	}

	@GetMapping("/todos")
	public List<Documento> obterDocumentos() {
		List<Documento> Documentos = repositorioDocumento.findAll();
		return Documentos;
	}

	@GetMapping("/autobots")
	public String obterPrime() {
		return autobots;
	}

	@PostMapping("/cadastrar")
	public void cadastrarDocumento(@RequestBody Documento Documento) {
		repositorioDocumento.save(Documento);
	}

	@PutMapping("/atualizar")
	public void atualizarDocumento(@RequestBody Documento atualizacao) {
		Documento Documento = repositorioDocumento.getById(atualizacao.getId());
		DocumentoAtualizador atualizador = new DocumentoAtualizador();
		atualizador.atualizar(Documento, atualizacao);
		repositorioDocumento.save(Documento);
	}

	@DeleteMapping("/excluir")
	public void excluirDocumento(@RequestBody Documento exclusao) {
		Documento Documento = repositorioDocumento.getById(exclusao.getId());
		repositorioDocumento.delete(Documento);
	}
}
