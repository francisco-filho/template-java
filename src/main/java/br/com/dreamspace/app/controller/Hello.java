package br.com.dreamspace.app.controller;

import br.com.dreamspace.app.dao.hello.CustomerDao;
import br.com.dreamspace.app.dao.hello.CustomerRepository;
import br.com.dreamspace.app.entity.hello.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Francisco on 14/03/2015.
 */
@Controller
public class Hello {

    @Autowired
    CustomerDao dao;
    @Autowired
    CustomerRepository repository;

    @RequestMapping("/hello/index")
    public String index(@RequestParam(value = "name", defaultValue = "World", required = false) String name,
                        Model model) {

        model.addAttribute("name", name);

        Customer c = new Customer(name);

        //System.out.println(repository.save(c));
        dao.saveCustomer(c);

        return "hello/index";
    }
}
