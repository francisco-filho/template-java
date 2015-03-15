package br.com.dreamspace.app.dao.hello;

import br.com.dreamspace.app.entity.hello.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;

/**
 * Created by Francisco on 14/03/2015.
 */
@Repository
public class CustomerDao {

    @Autowired
    EntityManager em;

    @Transactional
    public Customer saveCustomer(Customer c){
        em.persist(c);
        System.out.println(c);

        return c;
    }

}
