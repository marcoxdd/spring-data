package br.com.alura.spring.data.services;

import br.com.alura.spring.data.entities.Cargo;
import br.com.alura.spring.data.repository.CargoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class CrudCargoService {
    private final CargoRepository cargoRepository;
    private List<Cargo> cargos = new ArrayList<>();


    public CrudCargoService(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
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
                salvar(scan);
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

    private void salvar(Scanner scan){
        System.out.println("Descrição do Cargo");
        String descricao = scan.next();
        Cargo cargo = new Cargo();
        cargo.setDescricao(descricao);
        cargoRepository.save(cargo);
        System.out.println("Cargo salvo");
    }

    private void visualizar(){
        cargos.addAll((List<Cargo>) cargoRepository.findAll());
        cargos.forEach(System.out::println);
    }

    private void atualizar(Scanner scan){
        try {
            System.out.println("Qual o id do cargo que deseja atualizar?");
            visualizar();
            Long id = scan.nextLong();
            System.out.println("Informe a nova Descrição: ");
            //Maneira um de lidar com null pointer
            getCargos().stream().filter(c -> c.getId() == id).findFirst().orElseThrow(Exception::new).setDescricao(scan.next());
            cargoRepository.saveAll(getCargos());
            System.out.println("Atualizado com Sucesso!!");
        }catch (Exception ex){
            System.out.println("Id invalido!");
        }

    }

    private void remover(Scanner scan){
        System.out.println("Qual o id do cargo que deseja Remover?");
        List<Cargo> cargos = (List<Cargo>) cargoRepository.findAll();
        cargos.forEach(System.out::println);
        Long id = scan.nextLong();
        Optional<Cargo> cargoRemove = cargoRepository.findById(id);
        //Maneira 2 de lidar com null pointer(prefiro essa);
        if (cargoRemove.isPresent()) cargoRepository.delete(cargoRemove.get());
        else System.out.println("Cargo não encontrado");
    }

    public List<Cargo> getCargos() {
        return cargos;
    }

    public void setCargos(List<Cargo> cargos) {
        this.cargos = cargos;
    }
}
