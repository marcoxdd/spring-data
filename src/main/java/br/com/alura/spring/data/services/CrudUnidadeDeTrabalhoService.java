package br.com.alura.spring.data.services;

import br.com.alura.spring.data.entities.UnidadeTrabalho;
import br.com.alura.spring.data.repository.UnidadeDeTrabalhoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

@Service
public class CrudUnidadeDeTrabalhoService {
    UnidadeDeTrabalhoRepository repository;

    public CrudUnidadeDeTrabalhoService(UnidadeDeTrabalhoRepository repository) {this.repository = repository;}

    public void inicial(Scanner scan){
        System.out.println("Escolha a opção desejada");
        System.out.println("1 - Cadastrar");
        System.out.println("2 - Atualizar");
        System.out.println("3 - Vizualizar");
        System.out.println("4 - Apagar");
        System.out.println("0 - Sair");

        Integer acao = scan.nextInt();

        switch (acao){
            case 1:
                cadastrar(scan);
                break;
            case 2:
                atualizar(scan);
                break;
            case 3:
                visualizar();
                break;
            case 4:
                remover(scan);
            case 0:
                break;
        };

    }

    private void cadastrar(Scanner scan){
        System.out.println("Informes os dados da Unidade:");
        System.out.println("Descrição");
        scan.nextLine();
        String descricao = scan.nextLine();
        System.out.println("Endereço:");
        String endereco = scan.nextLine();
        UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho(descricao, endereco);
        System.out.println("Unidade Salva com sucesso");
        repository.save(unidadeTrabalho);
    }

    private void visualizar(){
        Iterable<UnidadeTrabalho> unidadeTrabalhoList = repository.findAll();
        unidadeTrabalhoList.forEach(System.out::println);
    }

    private void atualizar(Scanner scan){
        System.out.println("Digite o id do metodo que deseja atualizar: ");
        visualizar();
        Long id = scan.nextLong();
        Optional<UnidadeTrabalho> unidadeTrabalhoAtualizar = repository.findById(id);
        if (unidadeTrabalhoAtualizar.isPresent()) {
            System.out.println("Informes os dados da Unidade:");
            System.out.println("Descrição");
            scan.nextLine();
            unidadeTrabalhoAtualizar.get().setDescricao(scan.nextLine());
            System.out.println("Endereço:");
            unidadeTrabalhoAtualizar.get().setEndereco(scan.nextLine());
            repository.save(unidadeTrabalhoAtualizar.get());
            System.out.println("Unidade Salva com sucesso");
        }
        else System.out.println("Id invalido");
    }

    private void remover(Scanner scan){
        visualizar();
        System.out.println("Digite o ID da unidade que deve ser removida");
        Long id = scan.nextLong();
        repository.deleteById(id);
        System.out.println("Removido com sucesso");
    }

}
