package com.example.alshaqahaapharmacies;


import java.util.HashMap;
import java.util.Map;

public class Cars {
    private String id;
    private String name;
    private String condition;
    private String description;
    private String branch;
    private String price;

    public Cars(String id, String name, String condition, String description, String branch, String price) {
        this.id = id;
        this.name = name;
        this.condition = condition;
        this.description = description;
        this.branch = branch;
        this.price = price;
    }

    public Cars(String name, String condition, String description, String branch, String price) {
        this.name = name;
        this.condition = condition;
        this.description = description;
        this.branch = branch;
        this.price = price;
    }

    public Cars() {
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("condition", condition);
        map.put("description", description);
        map.put("branch", branch);
        map.put("price", price);
        return map;
    }
}
