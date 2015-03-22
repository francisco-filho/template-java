package br.com.dreamspace.app.entity.relatorios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Francisco on 22/03/2015.
 */
public class Params {

    private List<Map<String,String>> param = new ArrayList<>();

    private Map<String,String> custom = new HashMap<>();

    public List<Map<String, String>> getParam() {
        return param;
    }

    public void setParam(List<Map<String, String>> param) {
        this.param = param;
    }

    public Map<String, String> getCustom() {
        return custom;
    }

    public void setCustom(Map<String, String> custom) {
        this.custom = custom;
    }

    public Params() {}

    @Override
    public String toString() {
        return "Params{" +
                "param=" + param +
                ", custom=" + custom +
                '}';
    }
}
