package phuc.test.app500px.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import phuc.test.app500px.R;
import phuc.test.app500px.presenter.SplashPresenter;

public class SplashActivity extends AppCompatActivity implements SplashView {

    private SplashPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        // Make it full-screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        // Use presenter to create category list
        this.presenter = new SplashPresenter(this);
        this.presenter.generateCategoryList();
    }

    @Override
    public void openCategoryListPage() {
        // Create and open category page
        Intent intent = new Intent(this, CategoryActivity.class);
        startActivity(intent);

        // Add push effect when transferring pages
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

        // Close splash page
        finish();
    }

    @Override
    public void showCreateCategoryListError() {
        // Show a notification about creating category error
        Snackbar.make(findViewById(R.id.rtl_main), R.string.msg_create_category_error,
                Snackbar.LENGTH_INDEFINITE)
                .setAction(getString(R.string.try_again), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Try to create category list again
                        presenter.generateCategoryList();
                    }
                }).show();
    }
}
