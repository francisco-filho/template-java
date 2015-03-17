package br.com.dreamspace.app.controller;

import br.com.dreamspace.app.dao.hello.CustomerDao;
import br.com.dreamspace.app.dao.hello.CustomerRepository;
import br.com.dreamspace.app.dao.topicos.TopicosRepository;
import br.com.dreamspace.app.entity.hello.Customer;
import br.com.dreamspace.app.entity.topicos.Topico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Francisco on 14/03/2015.
 */
@Controller
public class Hello {

    @Autowired
    CustomerDao dao;
    @Autowired
    CustomerRepository repository;
    @Autowired
    TopicosRepository topicos;

    @RequestMapping("/hello/index")
    public String index(@RequestParam(value = "name", defaultValue = "World", required = false) String name,
                        Model model) {

        model.addAttribute("name", name);

        Customer c = new Customer(name);
        System.out.println("Hot");

        for (Topico t : topicos.findAll()) {
            System.out.println(t);
        }

        dao.saveCustomer(c);

        return "hello/index";
    }

    @RequestMapping("/hello/get-topicos/") @ResponseBody
    public Object getTopicos(){
        return topicos.findOne(1L);
    }

    @RequestMapping("/hello/save-topicos/") @ResponseBody
    public Object saveTopicos(@RequestBody Topico data) {
        System.out.println(data);
        return topicos.save(data);
    }
}
