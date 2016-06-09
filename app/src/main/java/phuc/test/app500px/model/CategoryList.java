package phuc.test.app500px.model;

/**
 * @author phuc.tran
 */
public class CategoryList {
    // Fixed category ids which are from Github: https://github.com/500px/api-documentation/blob/master/basics/formats_and_terms.md
    private static final int[] CATEGORY_IDS = {
            0, 10, 11, 5, 1, 9, 15, 16, 20, 14,
            2, 24, 23, 3, 8, 12, 18, 4, 7, 19,
            17, 6, 21, 26, 13, 22, 27, 25
    };

    // Fixed category names which are from Github: https://github.com/500px/api-documentation/blob/master/basics/formats_and_terms.md
    private static final String[] CATEGORY_NAMES = {
            "Uncategorized", "Abstract", "Animals", "Black and White", "Celebrities",
            "City and Architecture", "Commercial", "Concert", "Family", "Fashion",
            "Film", "Fine Art", "Food", "Journalism", "Landscapes",
            "Macro", "Nature", "Nude", "People", "Performing Arts",
            "Sport", "Still Life", "Street", "Transportation", "Travel",
            "Underwater", "Urban Exploration", "Wedding"
    };

    public static Category[] CATEGORIES;

    /**
     * Generate category list from fixed ids and names.
     * Those fixed data is from Github
     *
     * @see https://github.com/500px/api-documentation/blob/master/basics/formats_and_terms.md
     */
    public static void generateCategories() {
        // Generate category list only it was not created yet
        if (null == CATEGORIES || CATEGORIES.length == 0) {
            int length = CATEGORY_IDS.length;
            CATEGORIES = new Category[length];
            for (int i = 0; i < length; i++) {
                CATEGORIES[i] = new Category(CATEGORY_IDS[i], CATEGORY_NAMES[i]);
            }
        }
    }
}
