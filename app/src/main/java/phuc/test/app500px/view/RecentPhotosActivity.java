package phuc.test.app500px.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import phuc.test.app500px.R;
import phuc.test.app500px.adapter.PhotosAdapter;
import phuc.test.app500px.model.Photo;
import phuc.test.app500px.presenter.RecentPhotosPresenter;

/**
 * @author phuc.tran
 */
public class RecentPhotosActivity extends AppCompatActivity implements RecentPhotoView {
    public static final String KEY_CATEGORY_ID = "categoryId";
    public static final String KEY_CATEGORY_NAME = "categoryName";
    @BindView(R.id.recycler_view_photos)
    RecyclerView recyclerViewPhotos;
    @BindView(R.id.progressbar)
    ProgressBar progressBar;
    private PhotosAdapter recyclerAdapter;
    private boolean isScrolledToBottom;
    private RecentPhotosPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_photos);

        ButterKnife.bind(this);

        setupAdapter();
        setupRecyclerView();

        // Show category name on title bar
        String categoryName = getIntent().getStringExtra(KEY_CATEGORY_NAME);
        getSupportActionBar().setTitle(categoryName.toUpperCase());

        // Get category id
        int categoryId = getIntent().getIntExtra(KEY_CATEGORY_ID, 0);
        presenter = new RecentPhotosPresenter(this, categoryId);
        presenter.subscribe();
        recyclerAdapter.setDataset(presenter.getPhotos());
    }

    public void setupAdapter() {
        // Create adapter and adapt it to the recycler view for rendering
        recyclerAdapter = new PhotosAdapter(this);
        recyclerViewPhotos.setAdapter(recyclerAdapter);
    }

    /**
     * Setup recycler view to improve performance
     */
    private void setupRecyclerView() {
        recyclerViewPhotos.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewPhotos.setLayoutManager(layoutManager);

        /**
         * Set up scroll listener
         */
        recyclerViewPhotos.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();

                if (!isScrolledToBottom) {
                    if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                        isScrolledToBottom = true;
                        presenter.loadMore();
                    }
                }
            }
        });
    }

    @Override
    public void setScrolledToBottom(boolean scrolledToBottom) {
        isScrolledToBottom = scrolledToBottom;
    }

    @Override
    public void addAllPhotos(List<Photo> photoList) {
        recyclerAdapter.addAll(photoList);
    }

    public void setRecyclerAdapterDataset(List<Photo> photoList) {
        recyclerAdapter.addAll(photoList);
    }

    @Override
    public void showLoading(boolean isShown) {
        if (isShown) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}

