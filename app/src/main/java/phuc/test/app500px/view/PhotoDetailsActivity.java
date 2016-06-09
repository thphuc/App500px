package phuc.test.app500px.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import phuc.test.app500px.R;
import phuc.test.app500px.utils.DeviceManager;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * @author phuc.tran
 */
public class PhotoDetailsActivity extends AppCompatActivity implements PhotoDetailsView {
    public static final String PHOTO_URL = "photoUrl";
    public static final String AUTHOR = "author";
    public static final String TITLE = "title";
    public static final String PHOTO_WIDTH = "photoWidth";
    public static final String PHOTO_HEIGHT = "photoHeight";

    @BindView(R.id.frl_main)
    FrameLayout frlMain;

    @BindView(R.id.iv_photo)
    ImageView ivPhoto;

    @BindView(R.id.tv_photo_author)
    BariolTextView tvAuthor;

    @BindView(R.id.tv_photo_title)
    BariolTextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_details);

        // Binding activity for view injection
        ButterKnife.bind(this);

        // Hide action bar
        getSupportActionBar().hide();

        renderPhotoInfo();
    }

    /**
     * Render photo info
     */
    private void renderPhotoInfo() {
        /**
         * Load photo and info
         */
        Intent intent = getIntent();
        if (intent != null) {
            /**
             * Get photo info
             */
            String photoUrl = intent.getStringExtra(PHOTO_URL);
            String author = intent.getStringExtra(AUTHOR);
            String title = intent.getStringExtra(TITLE);
            int width = intent.getIntExtra(PHOTO_WIDTH, 0);
            int height = intent.getIntExtra(PHOTO_HEIGHT, 0);

            /**
             * Calculate new width and new height
             */
            int newWidth = DeviceManager.getScreenWidth(this);
            float scale = (newWidth * 1.0f) / width;
            int newHeight = (int) (height * scale);

            // Add zoom feature to photo
            new PhotoViewAttacher(ivPhoto);
            // OK. Load photo
            Picasso.with(this)
                    .load(photoUrl)
                    .resize(newWidth, newHeight)
                    .into(ivPhoto, new Callback() {
                        @Override
                        public void onSuccess() {
                            /**
                             * Show title and author after photo loaded
                             */
                            tvTitle.setText(title);
                            tvAuthor.setText(author);
                        }

                        @Override
                        public void onError() {

                        }
                    });
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        overridePendingTransition(0, R.anim.push_bottom_out);
    }
}
