package model.db;

import model.domain.categories.Category;
import model.domain.categories.MainCategory;
import model.domain.categories.SubCategory;
import readAndWriteJAR.FindPath;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;

public class CategoryTXT implements CategoryDB{
    private static CategoryTXT uniqueInstance;
    ArrayList<Category> categories;
    File file;
    private FindPath fp;
    private Path categoriesPath;

    private CategoryTXT(){
        fp = new FindPath();
        categoriesPath = fp.addToPath("txt_files", "categories.txt");
        //System.out.format("\npropsPath toString: %s%n", categoriesPath.toString() + "\n");
        //this.file = new File("txt_files/categories.txt");
        this.categories = getCategoriesFromTXT();
    }

    public synchronized static CategoryTXT getInstance(){
        if (uniqueInstance == null){
            uniqueInstance = new CategoryTXT();
        }
        return uniqueInstance;
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

    @Override
    public ArrayList<Category> updateCategory(Category category, String id) {
        for (Category categoryFromList: getCategories()){
            if (categoryFromList.getName().equals(id)){
                int index = getCategories().indexOf(categoryFromList);
                getCategories().set(index, category);
            }
        }
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
        try {
        FileInputStream fis = new FileInputStream(categoriesPath.toString());
        if (fis.available() == 0){
        //File file = getFile();
       // try {
            //if (file.length() == 0){
                return new ArrayList<>();
            }
            //FileInputStream fis = new FileInputStream(categoriesPath.toString());
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
            FileOutputStream fos = new FileOutputStream(categoriesPath.toString());
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

    @Override
    public ArrayList<SubCategory> getSubCategories() {
        ArrayList<SubCategory> subCategories = new ArrayList<>();
        for (Category category: getCategories()){
            if (category instanceof SubCategory){
                SubCategory subCategory = (SubCategory) category;
                subCategories.add(subCategory);
            }
        }
        return subCategories;
    }



    private File getFile(){
        return this.file;
    }
}
