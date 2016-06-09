package phuc.test.app500px.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import phuc.test.app500px.app.App500px;

/**
 * This is a custom text view which use primary font (Bariol Bold) for rendering texts
 *
 * @author phuc.tran
 */
public class BariolTextView extends TextView {
    public BariolTextView(Context context) {
        super(context);

        setupFont();
    }

    public BariolTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setupFont();
    }

    public BariolTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setupFont();
    }

    private void setupFont() {
        this.setTypeface(App500px.primaryFont);
    }
}
