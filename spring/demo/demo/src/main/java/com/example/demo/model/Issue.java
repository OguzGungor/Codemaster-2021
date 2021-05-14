package com.example.demo.model;

public class Issue {

    private Field fields;

    private String id;

    private String key;


    Issue(Field fields,String id, String key){
        this.fields = fields;
        this.key = key;
        this.fields = fields;
    }


    public void setKey(String key) {
        this.key = key;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFields(Field fields) {
        this.fields = fields;
    }

    public Field getFields() {
        return fields;
    }

    public String getKey() {
        return key;
    }

    public String getId() {
        return id;
    }
}
