package GupyTest.demo.controllers;

import GupyTest.demo.models.entities.Endereco;
import GupyTest.demo.models.entities.Pessoa;
import GupyTest.demo.models.entities.repositories.PessoaRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PessoaControllerTest {
    @Autowired
    PessoaRepository repositoryTest;

    @AfterEach
    void tearDown() {
        repositoryTest.deleteAll();
    }

    @Test
    void itShouldTestIfBothEnderecosAreEquals(){
        //given
        Endereco endereco1 = new Endereco(
                "Rua Teste",
                "cep teste",
                123,
                "Cidade teste",
                false
        );
        Endereco endereco2 = new Endereco(
                "Rua Teste",
                "cep teste",
                321,
                "Cidade teste",
                false
        );
        List<Endereco> enderecos = new ArrayList<>();
        enderecos.add(endereco1);
        enderecos.add(endereco2);
        //when
        Pessoa pessoa = new Pessoa("Bruno Guimaraes", "13/11/2000", enderecos);
        repositoryTest.save(pessoa);
        //then
        assertArrayEquals(repositoryTest.findById(pessoa.getId()).get().getEndereco().toArray(), enderecos.toArray());
    }

    @Test
    void itShouldSetLastEnderecoIfPrincipalValueToTrue() {
        //given
        Endereco endereco = new Endereco(
                "Rua Teste",
                "cep teste",
                123,
                "Cidade teste",
                true
        );
        Endereco endereco2 = new Endereco(
                "Rua Teste2",
                "cep teste2",
                321,
                "Cidade teste2",
                false
        );
        Endereco endereco3 = new Endereco(
                "Rua Teste3",
                "cep teste3",
                333,
                "Cidade teste3",
                true
        );
        Pessoa pessoa = new Pessoa();
        //when
        pessoa.addEndereco(endereco);
        pessoa.addEndereco(endereco2);
        pessoa.addEndereco(endereco3);
        pessoa.getEndereco().stream().forEach(data -> data.setIfPrincipal(false));
        pessoa.getEndereco().get(pessoa.getEndereco().size()-1).setIfPrincipal(true);
        //then
        repositoryTest.save(pessoa);
        assertTrue(repositoryTest.findById(pessoa.getId()).get().getEndereco().get(pessoa.getEndereco().size()-1).isIfPrincipal());
    }
}