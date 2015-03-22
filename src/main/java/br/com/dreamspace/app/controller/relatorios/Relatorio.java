package br.com.dreamspace.app.controller.relatorios;

import br.com.dreamspace.app.entity.relatorios.Params;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Francisco on 21/03/2015.
 */
@Controller
public class Relatorio {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping("/relatorios/")
    public String index(Model model) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String gerevs = mapper.writeValueAsString(listGerevs());

        System.out.println(gerevs);
        model.addAttribute("gerevs", gerevs);
        return "relatorios/index";
    }

    @RequestMapping("/relatorios/get-columns") @ResponseBody
    public Object columns(Model model){
        List<Map<String,Object>> columns = jdbcTemplate.queryForList("SELECT * FROM relatorios.columns_vi");
        List<Map<String,Object>> groups = jdbcTemplate.queryForList("SELECT * FROM relatorios.groups_vi");

        Map<String, Object> result = new HashMap<>();
        result.put("columns", columns);
        result.put("groups", groups);

        return result;
    }

    @RequestMapping("/relatorios/get-data") @ResponseBody
    public Object getData(@RequestBody Params params){

        Map<String,Object> queryMap = jdbcTemplate.queryForMap("SELECT query FROM relatorios.relatorios WHERE id=?", 1);
        String query = (String)queryMap.get("query");

        String dependencias = String.format("(SELECT * FROM dependencias.prefixos WHERE %s)",
                processParams(params.getParam()));
                //processParam(params.getParam().get(0)));
        query = query.replace("$dependencia", dependencias);

        System.out.println(query);
        List<Map<String,Object>> data = jdbcTemplate.queryForList(query);

        return data;
    }

    public List<Map<String,Object>> listGerevs(){
        String query = "select dbh_diretoria, dbh_super, dbh_gerev, dbh_prefixo, dbh_nome\n" +
                "from dependencias.prefixos\n" +
                "where dbh_tipo_dep='4'";

        return jdbcTemplate.queryForList(query);
    }

    public String processParams(List<Map<String, String>> params) {

        boolean first = true;
        String where = "";

        for (Map<String, String> param : params) {
            if (!first){
                where = where + " OR ";
            }

            where += processParam(param);
            first = false;
        }

        return where;
    }

    public String processParam(Map<String,String> param){
        String q = "(dbh_tipo_dep IN(%s) AND %s=%s)";

        return String.format(q,
                getVisao(param.get("visao")),
                getFiltro(param.get("filtro")),
                param.get("value"));
    }

    public String getVisao(String visao){
        switch (visao){
            case "super":
                return "'3'";
            case "gerev":
                return "'4'";
            case "agencia":
                return "'13','15','35'";
        }

        return "13,15,35";
    }


    public String getFiltro(String filtro){
        switch (filtro){
            case "diretoria":
                return "dbh_diretoria";
            case "super":
                return "dbh_super";
            case "gerev":
                return "dbh_gerev";
            case "agencia":
                return "dbh_prefixo";
        }

        return "dbh_prefixo";
    }




}
