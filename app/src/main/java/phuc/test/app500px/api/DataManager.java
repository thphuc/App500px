package phuc.test.app500px.api;

import java.util.ArrayList;
import java.util.List;

import phuc.test.app500px.model.Photo;
import phuc.test.app500px.utils.SmartLog;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;
import rx.subscriptions.CompositeSubscription;

public class DataManager {
    private final PublishSubject<List<Photo>> subject = PublishSubject.create();
    private final CompositeSubscription subscriptions = new CompositeSubscription();
    private final APIManager apiManager;
    private int page = 1;
    private boolean isDownloading;
    private List<Photo> photos = new ArrayList<>();
    private int categoryId;

    public DataManager(APIManager apiManager) {
        this.apiManager = apiManager;
    }

    public DataManager(APIManager apiManager, int categoryId) {
        this.apiManager = apiManager;
        this.categoryId = categoryId;
        download();
    }

    private void updatePhotos(List<Photo> newPhotos) {
        isDownloading = false;
        this.photos.addAll(newPhotos);
        subject.onNext(newPhotos);
    }

    private void download() {
        isDownloading = true;
        SmartLog.error(DataManager.class, "category = " + categoryId);
        subscriptions.add(apiManager.getPhotos(page, categoryId)
                .doOnError(Throwable::printStackTrace)
                .retry()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(photoList -> updatePhotos(photoList.getPhotos())));
    }

    public void loadMore() {
        page++;
        download();
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public Observable<List<Photo>> subscribeNewChanges() {
        return subject.asObservable();
    }
}
