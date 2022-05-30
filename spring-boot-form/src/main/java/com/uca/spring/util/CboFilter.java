package com.uca.spring.util;

public class CboFilter {

    private String value;

    private String description;
    
     private String group;

    public CboFilter(String value, String description) {
        this.value = value;
        this.description = description;
    }
    
     public CboFilter(String value, String description,String group) {
        this.value = value;
        this.description = description;
         this.group = group;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
    
    

}


