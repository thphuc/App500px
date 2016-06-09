package phuc.test.app500px.presenter;

import java.util.List;

import phuc.test.app500px.api.APIManager;
import phuc.test.app500px.api.DataManager;
import phuc.test.app500px.model.Photo;
import phuc.test.app500px.view.RecentPhotoView;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author phuc.tran
 */
public class RecentPhotosPresenter {
    private RecentPhotoView recentPhotoView;
    private DataManager dataManager;

    public RecentPhotosPresenter(RecentPhotoView recentPhotoView, int categoryId) {
        this.recentPhotoView = recentPhotoView;

        /**
         * Create retrofit and delegate to data manager
         */
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIManager.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIManager apiManager = retrofit.create(APIManager.class);
        dataManager = new DataManager(apiManager, categoryId);
    }

    /**
     * Load more photo when user scrolls to bottom of the list
     */
    public void loadMore() {
        dataManager.loadMore();
    }

    public void subscribe() {
        dataManager.subscribeNewChanges()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(photos -> {
                    recentPhotoView.setScrolledToBottom(false);
                    recentPhotoView.addAllPhotos(photos);

                    // Hide progress bar
                    recentPhotoView.showLoading(false);
                }, Throwable::printStackTrace);
    }

    public void loadPhotos() {
        recentPhotoView.setRecyclerAdapterDataset(dataManager.getPhotos());
    }

    public List<Photo> getPhotos() {
        return dataManager.getPhotos();
    }
}
