package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Department implements Serializable {

    private static final long serialVersion = 1L;
    private Integer id;
    private String nome;

    public Department() {
    }

    public Department(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Department that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getNome(), that.getNome());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome());
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
