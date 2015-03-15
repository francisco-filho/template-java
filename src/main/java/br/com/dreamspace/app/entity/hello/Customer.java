package br.com.dreamspace.app.entity.hello;

import javax.persistence.*;

/**
 * Created by Francisco on 14/03/2015.
 */
@Entity
@Table(name = "customer", schema = "hello")
public class Customer {

    @Id
    @SequenceGenerator(name ="customer_seq", sequenceName = "hello.customer_id_seq", schema = "hello")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
    private long id;

    private String name;

    protected Customer(){}

    public Customer(String name) {
        this.setName(name);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }
}
