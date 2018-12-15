package model.db;

import model.domain.categories.Category;
import model.domain.categories.MainCategory;
import model.domain.categories.SubCategory;

import java.util.ArrayList;

public interface CategoryDB {

    ArrayList<Category> addCategory(Category category);

    ArrayList<MainCategory> getMainCategories();

    ArrayList<Category> getCategories();

    ArrayList<SubCategory> getSubCategories();
}
