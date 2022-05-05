package br.com.alura.spring.data.services;

import br.com.alura.spring.data.entities.Cargo;
import br.com.alura.spring.data.repository.CargoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class CrudCargoService {
    private final CargoRepository cargoRepository;


    public CrudCargoService(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    public void inicial(Scanner scan, Integer acao){
        if (acao == 1) salvar(scan);
        else if (acao == 2) atualizar(scan);
        else remover(scan);
    }

    private void salvar(Scanner scan){
        System.out.println("Descrição do Cargo");
        String descricao = scan.next();
        Cargo cargo = new Cargo();
        cargo.setDescricao(descricao);
        cargoRepository.save(cargo);
        System.out.println("Cargo salvo");
    }

    private void atualizar(Scanner scan){
        try {
            System.out.println("Qual o id do cargo que deseja atualizar?");
            List<Cargo> cargos = (List<Cargo>) cargoRepository.findAll();
            cargos.forEach(System.out::println);
            Long id = scan.nextLong();
            System.out.println("Informe a nova Descrição: ");
            cargos.stream().filter(c -> c.getId() == id).findFirst().orElseThrow(Exception::new).setDescricao(scan.next());
            cargoRepository.saveAll(cargos);
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
        if (cargoRemove.isPresent()) cargoRepository.delete(cargoRemove.get());
        else System.out.println("Cargo não encontrado");
    }
}
