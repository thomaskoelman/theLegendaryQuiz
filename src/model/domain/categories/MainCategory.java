package model.domain.categories;

public class MainCategory extends Category {

    public MainCategory(String name, String description) {
        super(name, description);
    }

    @Override
    public String toString(){
        return getName();
    }
}
