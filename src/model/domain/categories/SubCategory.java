package model.domain.categories;

public class SubCategory extends Category {
    MainCategory mainCategory;

    public SubCategory(String name, String description, MainCategory mainCategory) {
        super(name, description);
        setMainCategory(mainCategory);
        setName(mainCategory.getName() + ": " + getName());
    }

    private void setMainCategory(MainCategory mainCategory){
        if (mainCategory == null){
            throw new IllegalArgumentException("Maincategory must not be empty to create a subcategory");
        }
        this.mainCategory = mainCategory;
    }
}
