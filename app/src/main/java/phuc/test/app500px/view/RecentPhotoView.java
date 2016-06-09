package phuc.test.app500px.view;

import java.util.List;

import phuc.test.app500px.model.Photo;

/**
 * @author phuc.tran
 */
public interface RecentPhotoView {
    void setScrolledToBottom(boolean scrolledToBottom);

    void addAllPhotos(List<Photo> photoList);

    void setRecyclerAdapterDataset(List<Photo> photoList);

    void showLoading(boolean isShown);
}
