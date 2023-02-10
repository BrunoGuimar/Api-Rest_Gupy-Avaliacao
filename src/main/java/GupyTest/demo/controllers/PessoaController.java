package GupyTest.demo.controllers;

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
        Pessoa pessoa = repository.findById(id).get();
        return pessoa.getEndereco();
    }

    @GetMapping("/endereco/{pessoaId}/{enderecoIndex}")
    public List<Endereco> setEnderecoPrincipal(@PathVariable int pessoaId, @PathVariable int enderecoIndex){
        Pessoa pessoa = repository.findById(pessoaId).get();
        pessoa.getEndereco().stream().forEach(endereco -> endereco.setIfPrincipal(false));
        pessoa.getEndereco().get(enderecoIndex-1).setIfPrincipal(true);
        repository.save(pessoa);
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
        Pessoa pessoa = repository.findById(id).get();
        pessoa.addEndereco(endereco);
        if(pessoa.getEndereco().size() == 1){
            pessoa.getEndereco().get(0).setIfPrincipal(true);
        }
        return repository.save(pessoa);
    }
    //POST METHODS
    //PUT METHODS
    @PutMapping(consumes = "application/json", produces = "application/json")
    public Pessoa updatePessoa(@RequestBody Pessoa pessoa){
        return repository.save(pessoa);
    }
    //DELETE METHOD "Não foi requisitado mas como é uma API-Restfull"
    @DeleteMapping("/{id}")
    public void deletePessoa(@PathVariable int id){
        repository.deleteById(id);
    }
    //DELETE METHOD
}
