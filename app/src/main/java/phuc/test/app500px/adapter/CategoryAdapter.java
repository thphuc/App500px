package phuc.test.app500px.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import phuc.test.app500px.R;
import phuc.test.app500px.model.Category;
import phuc.test.app500px.view.BariolTextView;
import phuc.test.app500px.view.CategoryView;

/**
 * @author phuc.tran
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    // Adapter interact with category view through this view
    private CategoryView categoryView;
    private Category[] categories;

    /**
     * Provide a suitable constructor (depends on the kind of dataset)
     */
    public CategoryAdapter(CategoryView categoryView, Category[] categories) {
        this.categoryView = categoryView;
        this.categories = categories;
    }

    /**
     * Create new views (invoked by the layout manager)
     */
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_category, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    /**
     * Replace the contents of a view (invoked by the layout manager)
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvCategoryName.setText(categories[position].getName().toUpperCase());

        /**
         * Click on a category -> Open recent photos page
         */
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryView.openRecentPhotosPage(categories[position]);
            }
        });
    }

    /**
     * Return the size of your dataset (invoked by the layout manager)
     */
    @Override
    public int getItemCount() {
        if (categories != null) {
            return categories.length;
        }
        return 0;
    }

    /**
     * View holder to optimize memory when rendering items in list
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvCategoryName;

        public ViewHolder(View itemView) {
            super(itemView);

            tvCategoryName = (BariolTextView) itemView.findViewById(R.id.tv_category_name);
        }
    }
}
