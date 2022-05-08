package br.com.alura.spring.data.services;

import br.com.alura.spring.data.entities.Funcionario;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class CrudRelatorioService {
    FuncionarioRepository funcionarioRepository;

    public CrudRelatorioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public void inicial(Scanner scan){
        System.out.println("Qual relatorio deseja visualizar?");
        System.out.println("1 - Funcionario por nome");

        int acao = scan.nextInt();

        switch (acao){
            case 1:
                funcionarioByName(scan);
        }
    }

    private void funcionarioByName(Scanner scan) {
        System.out.println("Digite o primeiro nome");
        List<Funcionario> funcionarios = funcionarioRepository.findByNomeContaining(scan.next());
        funcionarios.forEach(System.out::println);
    }
}
