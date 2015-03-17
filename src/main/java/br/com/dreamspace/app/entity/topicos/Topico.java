package br.com.dreamspace.app.entity.topicos;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Francisco on 15/03/2015.
 */
@Entity
@Table(name = "topicos", schema = "links")
public class Topico {
    @Id
    @SequenceGenerator(name = "generator", sequenceName = "links.topicos_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
    private Long id;

    private String titulo;

    private String descricao;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="topico_id")
    List<Agrupamento> agrupamentos = new ArrayList<>();

    @Column(name="criado_em")
    private Calendar criadoEm;

    public Topico() {}

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Calendar getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(Calendar criadoEm) {
        this.criadoEm = criadoEm;
    }

    public List<Agrupamento> getAgrupamentos() {
        return agrupamentos;
    }

    public void setAgrupamentos(List<Agrupamento> agrupamentos) {
        this.agrupamentos = agrupamentos;
    }

    @Override
    public String toString() {
        return "Topico{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", criadoEm=" + criadoEm +
                '}';
    }
}
