package br.com.dreamspace.app.dao.hello;

import br.com.dreamspace.app.entity.hello.Customer;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Francisco on 14/03/2015.
 */
public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
