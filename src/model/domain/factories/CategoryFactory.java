package model.domain.factories;

import model.domain.categories.Category;
import model.domain.categories.MainCategory;
import model.domain.categories.SubCategory;

public class CategoryFactory {
    public static Category createCategory(String name, String description, MainCategory mainCategory){
        if (mainCategory == null){
            return new MainCategory(name, description);
        } else {
            return new SubCategory(name, description, mainCategory);
        }
    }
}
