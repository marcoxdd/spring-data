package br.com.alura.spring.data.services;

import br.com.alura.spring.data.entities.Cargo;
import br.com.alura.spring.data.entities.Funcionario;
import br.com.alura.spring.data.entities.UnidadeTrabalho;
import br.com.alura.spring.data.repository.CargoRepository;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.repository.UnidadeDeTrabalhoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class CrudFuncionarioService {

    FuncionarioRepository repositoryFuncionario;
    UnidadeDeTrabalhoRepository repositoryUnidadeDeTRabalho;
    CargoRepository repositoryCargo;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public CrudFuncionarioService(FuncionarioRepository repositoryFuncionario, UnidadeDeTrabalhoRepository repositoryUnidadeDeTRabalho, CargoRepository repositoryCargo) {
        this.repositoryFuncionario = repositoryFuncionario;
        this.repositoryUnidadeDeTRabalho = repositoryUnidadeDeTRabalho;
        this.repositoryCargo = repositoryCargo;
    }

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
        }

    }

    private void visualizar() {
        Iterable<Funcionario> funcionarios = repositoryFuncionario.findAll();
        funcionarios.forEach(System.out::println);
    }


    private void atualizar(Scanner scan) {
        visualizar();
        Optional<Cargo> cargo;
        System.out.println("Digite o id");
        Long id = scan.nextLong();

        System.out.println("Digite o nome");
        scan.nextLine();
        String nome = scan.nextLine();

        System.out.println("Digite o cpf");
        String cpf = scan.nextLine();

        System.out.println("Digite o salario");
        Double salario = scan.nextDouble();

        System.out.println("Digite a data de contratação");
        scan.nextLine();
        String dataContratacao = scan.nextLine();

        visualizarCargos();
        System.out.println("Digite o cargoId");
        Long cargoId = scan.nextLong();

        Funcionario funcionario = new Funcionario();
        funcionario.setId(id);
        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setSalario(salario);
        funcionario.setDataContratacao(LocalDate.parse(dataContratacao, formatter));
        while (true){
            cargo = repositoryCargo.findById(cargoId);
            if (cargo.isPresent()){
                funcionario.setCargo(cargo.get());
                break;
            }else{
                System.out.println("Cargo invalido digite id correto");
                visualizarCargos();
                cargoId = scan.nextLong();
            }
        }
        repositoryFuncionario.save(funcionario);
        System.out.println("Alterado");
    }

    private void visualizarCargos(){
        List<Cargo> cargos = (List<Cargo>) repositoryCargo.findAll();
        cargos.forEach(System.out::println);
    }

    private void remover(Scanner scan) {
        visualizar();
        System.out.println("Id");
        Long id = scan.nextLong();
        repositoryFuncionario.deleteById(id);
        System.out.println("Deletado");
    }

    private void cadastrar(Scanner scan) {
        System.out.println("Digite o nome");
        scan.nextLine();
        String nome = scan.nextLine();;

        System.out.println("Digite o cpf");
        String cpf = scan.nextLine();;

        System.out.println("Digite o salario");
        Double salario = scan.nextDouble();

        System.out.println("Digite a data de contratação");
        scan.nextLine();
        String dataContratacao = scan.nextLine();

        visualizarCargos();
        System.out.println("Digite o cargoId");
        Long cargoId = scan.nextLong();

        List<UnidadeTrabalho> unidades = unidade(scan);

        Funcionario funcionario = new Funcionario();
        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setSalario(salario);
        funcionario.setDataContratacao(LocalDate.parse(dataContratacao, formatter));
        Optional<Cargo> cargo;
        while (true){
            cargo = repositoryCargo.findById(cargoId);
            if (cargo.isPresent()){
                funcionario.setCargo(cargo.get());
                break;
            }else{
                System.out.println("Cargo invalido digite id correto");
                visualizarCargos();
                cargoId = scan.nextLong();
            }
        }
        funcionario.setUnidadeTrabalhos(unidades);

        repositoryFuncionario.save(funcionario);
        System.out.println("Salvo Com sucesso");
    }



    private List<UnidadeTrabalho> unidade(Scanner scan) {
        Boolean isTrue = true;
        List<UnidadeTrabalho> unidades = new ArrayList<>();
        List<UnidadeTrabalho> unidadesExistentes = (List<UnidadeTrabalho>) repositoryUnidadeDeTRabalho.findAll();
        unidadesExistentes.forEach(System.out::println);

        while (isTrue) {
            System.out.println("Digite o unidadeId (Para sair digite 0)");
            Long unidadeId = scan.nextLong();

            if (unidadeId != 0) {
                Optional<UnidadeTrabalho> unidade = repositoryUnidadeDeTRabalho.findById(unidadeId);
                unidade.ifPresent(unidades::add);
                if (unidade.isEmpty()) System.out.println("Id invalido");

            } else {
                isTrue = false;
            }
        }
        return unidades;
    }
}
