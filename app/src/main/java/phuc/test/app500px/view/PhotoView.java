package phuc.test.app500px.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import phuc.test.app500px.R;
import phuc.test.app500px.model.Photo;

public class PhotoView extends RelativeLayout {
    private final Picasso picasso;
    @BindView(R.id.photo_view)
    uk.co.senab.photoview.PhotoView ivPhoto;
    @BindView(R.id.tv_author)
    TextView tvAuthor;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    public PhotoView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.layout_photo, this, true);
        ButterKnife.bind(this);
        picasso = Picasso.with(context);
    }

    public void setPhoto(Photo photo) {
        picasso.load(photo.getPhotoUrl()).into(ivPhoto);
        tvTitle.setText(photo.getTitle());
        tvAuthor.setText(photo.getAuthor());
    }
}
