package phuc.test.app500px.app;

import android.app.Application;
import android.graphics.Typeface;

/**
 * @author phuc.tran
 */
public class App500px extends Application {

    // Primary font for the app - We use Bariol Bold font here
    public static Typeface primaryFont;

    @Override
    public void onCreate() {
        super.onCreate();

        createFont();
    }

    /**
     * Create primary font from assets. This font will be used for all text in this sample
     */
    private void createFont() {
        App500px.primaryFont = Typeface.createFromAsset(getAssets(), "font/bariol_bold.otf");
    }
}
