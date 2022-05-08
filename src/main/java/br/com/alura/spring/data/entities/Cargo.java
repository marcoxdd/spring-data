package br.com.alura.spring.data.entities;

import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cargos")
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;
    private String descricao;
    @OneToMany(mappedBy = "cargo")
    private List<Funcionario> funcionario;

    public Cargo(Long id, String descricao, List<Funcionario> funcionario) {
        this.id = id;
        this.descricao = descricao;
        this.funcionario = funcionario;
    }

    public Cargo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Cargo [id=" + id + ", descricao=" + descricao + "]";
    }

}