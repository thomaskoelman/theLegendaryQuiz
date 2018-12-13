package model.db;

import model.domain.categories.Category;
import model.domain.categories.MainCategory;

import java.io.*;
import java.util.ArrayList;

public class CategoryTXT implements CategoryDB{
    ArrayList<Category> categories;
    File file;

    public CategoryTXT(){
        this.file = new File("files/categories.txt");
        this.categories = getCategoriesFromTXT();
    }

    //final method that adds the category you created in the categoryCreator
    @Override
    public ArrayList<Category> addCategory(Category category){
        if (category == null){
            throw new IllegalArgumentException("you cannot add a non-existent category");
        }
        getCategories().add(category);
        saveCategories();
        return getCategories();
    }

    //We extract the main categories from the list to display in the combobox of the categoryCreator
    @Override
    public ArrayList<MainCategory> getMainCategories() {
        ArrayList<MainCategory> mainCategories = new ArrayList<>();
        for (Category category: getCategories()){
            if (category instanceof MainCategory){
                MainCategory mainCategory = (MainCategory)category;
                mainCategories.add(mainCategory);
            }
        }
        return mainCategories;
    }


    //fetch categories from the txt file
    private ArrayList<Category> getCategoriesFromTXT(){
        File file = getFile();
        try {
            if (file.length() == 0){
                return new ArrayList<>();
            }
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Category> categories = (ArrayList<Category>) ois.readObject();
            ois.close();
            return categories;
        }catch (Exception e){
            throw new DbException(e.getMessage(), e);
        }
    }

    //overwrite the txt file with the current state of the arraylist
    private void saveCategories(){
        try {
            FileOutputStream fos = new FileOutputStream(getFile());
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(getCategories());
            oos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //getters

    public ArrayList<Category> getCategories(){
        return this.categories;
    }

    private File getFile(){
        return this.file;
    }
}
