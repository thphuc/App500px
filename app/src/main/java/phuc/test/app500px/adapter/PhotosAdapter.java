package phuc.test.app500px.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import phuc.test.app500px.R;
import phuc.test.app500px.model.Photo;
import phuc.test.app500px.utils.DeviceManager;
import phuc.test.app500px.view.BariolTextView;
import phuc.test.app500px.view.PhotoDetailsActivity;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.PhotoViewHolder> {

    List<Photo> photos = new ArrayList<>();
    private Context context;
    private int photoWidth;
    private int photoHeight;

    public PhotosAdapter(Context context) {
        this.context = context;
        photoWidth = DeviceManager.getScreenWidth(context);
        photoHeight = (photoWidth / 3) * 2;
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_photo, parent, false);
        PhotoViewHolder vh = new PhotoViewHolder(v);

        return vh;
    }

    public void setDataset(List<Photo> photos) {
        this.photos.clear();
        this.photos.addAll(photos);
        notifyDataSetChanged();
    }

    public void addAll(List<Photo> photos) {
        int startPosition = this.photos.size();
        this.photos.addAll(photos);
        notifyItemRangeInserted(startPosition, photos.size());
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder holder, int position) {
        /**
         * Reset title and author
         */
        holder.tvTitle.setText("");
        holder.tvAuthor.setText("");
        /**
         * Load photo using picasso library
         */
        Log.e("URL", photos.get(getLoopedIndex(position)).getPhotoUrl());
        Picasso.with(context)
                .load(photos.get(getLoopedIndex(position)).getPhotoUrl())
                //.placeholder(R.mipmap.ic_launcher)
                .resize(photoWidth, photoHeight)
                .centerCrop()
                .into(holder.imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        /**
                         * Show title and author
                         */
                        holder.tvTitle.setText(photos.get(getLoopedIndex(position)).getTitle());
                        holder.tvAuthor.setText(photos.get(getLoopedIndex(position)).getAuthor());
                    }

                    @Override
                    public void onError() {

                    }
                });

        holder.itemView.setOnClickListener(v -> {
            /**
             * Open photo details page
             */
            Intent intent = new Intent(context, PhotoDetailsActivity.class);
            //int[] screenLocation = new int[2];
            //holder.imageView.getLocationOnScreen(screenLocation);

            intent.putExtra(PhotoDetailsActivity.PHOTO_URL, photos.get(position).getPhotoUrl());
            intent.putExtra(PhotoDetailsActivity.AUTHOR, photos.get(position).getAuthor());
            intent.putExtra(PhotoDetailsActivity.TITLE, photos.get(position).getTitle());
            intent.putExtra(PhotoDetailsActivity.PHOTO_WIDTH, photos.get(position).getWidth());
            intent.putExtra(PhotoDetailsActivity.PHOTO_HEIGHT, photos.get(position).getHeight());

            context.startActivity(intent);

            // Apply animation when transferring pages
            ((Activity) context).overridePendingTransition(R.anim.push_bottom_in, R.anim.push_bottom_out);
        });
    }

    @Override
    public int getItemCount() {
        if (photos != null) {
            Log.e("Test 3", "" + photos.size());
            return photos.size();
        }
        return 0;
    }

    private int getLoopedIndex(int index) {
        return index % photos.size();
    }

    public class PhotoViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public BariolTextView tvAuthor;
        public BariolTextView tvTitle;

        public PhotoViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.iv_photo);
            /**
             * Resize images
             */
            this.imageView.getLayoutParams().width = photoWidth;
            this.imageView.getLayoutParams().height = photoHeight;

            this.tvAuthor = (BariolTextView) itemView.findViewById(R.id.tv_photo_author);
            this.tvTitle = (BariolTextView) itemView.findViewById(R.id.tv_photo_title);
        }
    }
}
