package model.db;

import model.domain.categories.Category;
import model.domain.categories.MainCategory;

import java.util.ArrayList;

public interface CategoryDB {

    ArrayList<Category> addCategory(Category category);

    ArrayList<MainCategory> getMainCategories();

    ArrayList<Category> getCategories();
}
