package phuc.test.app500px.presenter;

import phuc.test.app500px.model.CategoryList;
import phuc.test.app500px.view.CategoryView;

/**
 * @author phuc.tran
 */
public class CategoryPresenter {

    private CategoryView categoryView;

    public CategoryPresenter(CategoryView categoryView) {
        this.categoryView = categoryView;
    }

    /**
     * Get category list
     */
    public void getCategoryList() {
        CategoryList.generateCategories();

        // OK. Render this list please
        categoryView.renderCategoryList();
    }
}
