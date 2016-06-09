package phuc.test.app500px.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import phuc.test.app500px.R;
import phuc.test.app500px.adapter.CategoryAdapter;
import phuc.test.app500px.model.Category;
import phuc.test.app500px.model.CategoryList;
import phuc.test.app500px.presenter.CategoryPresenter;

/**
 * @author phuc.tran
 */
public class CategoryActivity extends AppCompatActivity implements CategoryView {

    @BindView(R.id.recycler_view_category)
    RecyclerView recyclerViewCategory;
    CategoryAdapter categoryAdapter;

    private CategoryPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        // Binding butter knife to this activity
        ButterKnife.bind(this);

        setupRecyclerView();

        // Set title
        getSupportActionBar().setTitle(getString(R.string.category_list));

        presenter = new CategoryPresenter(this);
        // Get category list
        presenter.getCategoryList();
    }

    /**
     * Setup recycler view to improve performance
     */
    private void setupRecyclerView() {
        recyclerViewCategory.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewCategory.setLayoutManager(layoutManager);
    }

    public void renderCategoryList() {
        // Clear adapter if needed
        if (categoryAdapter != null) {
            categoryAdapter = null;
        }

        // Create adapter and adapt it to the recycler view for rendering
        categoryAdapter = new CategoryAdapter(this, CategoryList.CATEGORIES);
        recyclerViewCategory.setAdapter(categoryAdapter);
    }

    @Override
    public void openRecentPhotosPage(Category category) {
        // Create and open recent photos page
        Intent intent = new Intent(this, RecentPhotosActivity.class);
        intent.putExtra(RecentPhotosActivity.KEY_CATEGORY_ID, category.getId());
        intent.putExtra(RecentPhotosActivity.KEY_CATEGORY_NAME, category.getName());
        startActivity(intent);

        // Add push effect when transferring pages
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }
}
