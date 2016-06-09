package phuc.test.app500px.view;

import phuc.test.app500px.model.Category;

/**
 * @author phuc.tran
 */
public interface CategoryView {

    /**
     * Rendering list of category to recycler view
     */
    void renderCategoryList();

    /**
     * Open recent photos page by category id
     *
     * @param category The category
     */
    void openRecentPhotosPage(Category category);
}
