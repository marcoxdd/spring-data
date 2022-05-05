package br.com.alura.spring.data.entities;

import javax.persistence.*;

@Entity
@Table(name = "cargos")
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Cargo{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
