package GupyTest.demo.controllers;

import GupyTest.demo.models.entities.Endereco;
import GupyTest.demo.models.entities.Pessoa;
import GupyTest.demo.models.entities.repositories.PessoaRepository;
import GupyTest.demo.services.PessoaService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

import static org.mockito.Mockito.verify;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
class PessoaControllerTest {
    @Mock
    PessoaRepository repositoryTest;

    @AfterEach
    void tearDown() {
        repositoryTest.deleteAll();
    }


    @Test
    void CanGetAllPessoas() {
        repositoryTest.findAll();
        verify(repositoryTest).findAll();
    }

    @Test
    void CanAddNewPessoa() {
        Pessoa pessoa = new Pessoa("TEST NOME", "TEST DATE", null);
        repositoryTest.save(pessoa);
        ArgumentCaptor<Pessoa> pessoaArgumentCaptor = ArgumentCaptor.forClass(Pessoa.class);
        verify(repositoryTest).save(pessoaArgumentCaptor.capture());

        Pessoa capturedPessoa = pessoaArgumentCaptor.getValue();
        assertThat(capturedPessoa).isEqualTo(pessoa);

    }

    @Test
    void CanSetEnderecoToPrincipal() {
        PessoaService pessoaService = new PessoaService();
        Endereco enderecoTest = new Endereco(
                "RUA TEST",
                "CEP TEST",
                321,
                "CIDADE TEST",
                false
        );
        List<Endereco> enderecosTest = new ArrayList<>();
        enderecosTest.add(enderecoTest);
        Pessoa pessoa = new Pessoa("TEST NOME", "TEST DATE", enderecosTest);

        pessoaService.setEnderecoToPrincipal(pessoa, 1);

        repositoryTest.save(pessoa);
        ArgumentCaptor<Pessoa> pessoaArgumentCaptor = ArgumentCaptor.forClass(Pessoa.class);
        verify(repositoryTest).save(pessoaArgumentCaptor.capture());
        Pessoa capturedPessoa = pessoaArgumentCaptor.getValue();

        assertThat(capturedPessoa.getEndereco().get(0).isIfPrincipal()).isTrue();
    }
}