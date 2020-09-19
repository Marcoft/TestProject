package project.test.testproject;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ProgressBar;
import android.widget.TextView;

import project.test.testproject.Model.retrofit.ApiHolder;

public class ProgressBarAnimation extends Animation {

    private Context context;
    private ProgressBar progressBar;
    private TextView textView;
    private float  from;
    private float to;
    private ApiHolder apiHolder;

    Presenter presenter;

    public ProgressBarAnimation(Context context, ProgressBar progressBar, TextView textView, float from, float to,ApiHolder apiHolder) {
        this.context = context;
        this.progressBar = progressBar;
        this.textView = textView;
        this.from = from;
        this.to = to;
        this.apiHolder = apiHolder;
    }

    int i = 0;
    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        float value = from + (to - from) * interpolatedTime;
        progressBar.setProgress((int)value);
        textView.setText((int)value + " %");

        if(value == to && value == 100){
            i++;
            if(i == 2){
                presenter = new Presenter(context, apiHolder);
                i = 0;
                presenter.firstEnter();
            }
        }
    }


}
