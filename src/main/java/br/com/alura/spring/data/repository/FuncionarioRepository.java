package br.com.alura.spring.data.repository;

import br.com.alura.spring.data.entities.Funcionario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuncionarioRepository extends CrudRepository<Funcionario, Long> {
    List<Funcionario> findByNomeContaining(String nome);
}
