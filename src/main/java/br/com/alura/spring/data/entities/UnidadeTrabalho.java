package br.com.alura.spring.data.entities;

import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "unidade_trabalho")
public class UnidadeTrabalho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;
    private String descricao;
    private String endereco;
    @ManyToMany(mappedBy = "unidadeTrabalhos", fetch = FetchType.EAGER)
    private List<Funcionario> funcionarios;

    public UnidadeTrabalho() {
    }

    public UnidadeTrabalho(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public UnidadeTrabalho(String descricao, String endereco) {
        this.descricao = descricao;
        this.endereco = endereco;
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    @Override
    public String toString() {
        return "UnidadeTrabalho{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", endereco='" + endereco + '\'' +
                ", funcionarios=" + funcionarios +
                '}';
    }
}
