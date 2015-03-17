package br.com.dreamspace.app.entity.topicos;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francisco on 16/03/2015.
 */
@Entity
@Table(name = "agrupamento", schema = "links")
public class Agrupamento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String titulo;

    @OneToMany
    @JoinColumn(name="agrupamento_id")
    List<Item> items = new ArrayList<>();

    public Agrupamento() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Agrupamento{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", items=" + items +
                '}';
    }
}
