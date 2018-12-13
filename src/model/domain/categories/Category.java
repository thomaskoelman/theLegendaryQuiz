package model.domain.categories;

import java.io.Serializable;

public abstract class Category implements Serializable {
    private String name, description;
    private static final long serialVersionUID = 1L;

    protected Category(String name, String description){
        setName(name);
        setDescription(description);
    }

    protected void setName(String name){
        if (name == null || name.trim().isEmpty()){
             throw new IllegalArgumentException("Name must have a value");
        }
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    protected void setDescription(String description){
        if (description == null || description.trim().isEmpty()){
            throw new IllegalArgumentException("Description must have a value");
        }
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }

    //necessary to display categories properly in comboboxes
    @Override
    public String toString(){
        return getName();
    }
}
