package com.example.demo.model;

public class IssueDTO {

    private FieldDTO fields;

    private String id;

    private String key;


    public IssueDTO(FieldDTO fields,String id, String key){
        this.fields = fields;
        this.key = key;
        this.id = id;
    }


    public void setKey(String key) {
        this.key = key;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFields(FieldDTO fields) {
        this.fields = fields;
    }

    public FieldDTO getFields() {
        return fields;
    }

    public String getKey() {
        return key;
    }

    public String getId() {
        return id;
    }
}
