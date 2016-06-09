package phuc.test.app500px.api;

import phuc.test.app500px.model.PhotoList;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author phuc.tran
 */
public interface APIManager {
    String BASE_URL = "https://api.500px.com/v1/";
    String CONSUMER_KEY = "SagIwwMEuSNUOvOkkB22gVYFF8SC2ouKNlEZgcCz";
    // The image size id (Pick it here: https://github.com/500px/api-documentation/blob/master/basics/formats_and_terms.md#image-urls-and-image-sizes)
    int IMAGE_SIZE = 440;

    @GET("photos?feature=fresh_today&&image_size=" + IMAGE_SIZE + "&consumer_key=" + CONSUMER_KEY)
    Observable<PhotoList> getPhotos(@Query("page") int page, @Query("category") int categoryId);
}
