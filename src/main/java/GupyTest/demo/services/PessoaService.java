package GupyTest.demo.services;

import GupyTest.demo.models.entities.Endereco;
import GupyTest.demo.models.entities.Pessoa;

import java.util.Optional;

public class PessoaService {
    public Pessoa setEnderecoToPrincipal(Pessoa pessoa, int index) {
        pessoa.getEndereco().forEach(endereco -> endereco.setIfPrincipal(false));
        pessoa.getEndereco().get(index - 1).setIfPrincipal(true);
        return pessoa;
    }

    public Pessoa getPessoaById(Optional<Pessoa> pessoa) {
        return pessoa.get();
    }

    public Pessoa addEnderecoToPessoa(Pessoa pessoa, Endereco endereco) {
        pessoa.addEndereco(endereco);
        if (pessoa.getEndereco().size() == 1) {
            pessoa.getEndereco().get(0).setIfPrincipal(true);
        }
        return pessoa;
    }

    public Pessoa updatePessoa(Pessoa newPessoa, Pessoa oldPessoa){
        newPessoa.setNome(oldPessoa.getNome());
        newPessoa.setData_nascimento(oldPessoa.getData_nascimento());
        return newPessoa;
    }
}