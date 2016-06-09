package phuc.test.app500px.presenter;

import phuc.test.app500px.model.CategoryList;
import phuc.test.app500px.view.SplashView;

/**
 * This presenter controls all the logic inside Splash activity
 *
 * @author phuc.tran
 */
public class SplashPresenter {

    private SplashView splashView;

    public SplashPresenter(SplashView splashView) {
        this.splashView = splashView;
    }

    /**
     * Generate category list from fixed data on Github
     */
    public void generateCategoryList() {
        // Generate fixed category list
        CategoryList.generateCategories();

        /**
         * Create category list fail -> Show error
         * Create category list successfully -> Open category list page
         */
        if (CategoryList.CATEGORIES == null || CategoryList.CATEGORIES.length == 0) {
            splashView.showCreateCategoryListError();
        } else {
            splashView.openCategoryListPage();
        }
    }
}
