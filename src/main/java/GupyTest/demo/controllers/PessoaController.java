package GupyTest.demo.controllers;

import GupyTest.demo.services.PessoaService;
import GupyTest.demo.models.entities.Endereco;
import GupyTest.demo.models.entities.Pessoa;
import GupyTest.demo.models.entities.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    PessoaRepository repository;

    PessoaService pessoaService = new PessoaService();

    //GET METHODS
    @GetMapping
    public Iterable<Pessoa> getPessoas(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Pessoa> getPessoa(@PathVariable int id){
        return repository.findById(id);
    }

    @GetMapping("/endereco/{id}")
    public List<Endereco> getEnderecosFromPessoa(@PathVariable int id){
        Pessoa pessoa = pessoaService.getPessoaById(repository.findById(id));
        return pessoa.getEndereco();
    }
    //GET METHODS
    //POST METHODS

    @PostMapping(consumes = "application/json", produces = "application/json")
    public Pessoa newPessoa(@RequestBody Pessoa pessoa){
        return repository.save(pessoa);
    }

    @PostMapping("/endereco/{id}")
    public Pessoa newEndereco(@RequestBody Endereco endereco, @PathVariable int id){
        Pessoa pessoa = pessoaService.getPessoaById(repository.findById(id));
        pessoaService.addEnderecoToPessoa(pessoa, endereco);
        return repository.save(pessoa);
    }

    //POST METHODS
    //PUT METHODS

    @PutMapping(consumes = "application/json", produces = "application/json")
    public Pessoa updatePessoa(@RequestBody Pessoa pessoa){
        Pessoa newPessoa = pessoaService.getPessoaById(repository.findById(pessoa.getId()));
        pessoaService.updatePessoa(newPessoa, pessoa);
        return repository.save(newPessoa);
    }

    @PutMapping("/endereco/{pessoaId}/{enderecoIndex}")
    public List<Endereco> setEnderecoPrincipal(@PathVariable int pessoaId, @PathVariable int enderecoIndex){
        Pessoa pessoa = pessoaService.getPessoaById(repository.findById(pessoaId));
        pessoaService.setEnderecoToPrincipal(pessoa, enderecoIndex);
        repository.save(pessoa);
        return pessoa.getEndereco();
    }
}