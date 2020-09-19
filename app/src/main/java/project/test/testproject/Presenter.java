package project.test.testproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import project.test.testproject.Game.MenuGameActivity;
import project.test.testproject.Model.retrofit.ApiHolder;
import project.test.testproject.Model.retrofit.ResponseBody;
import project.test.testproject.webView.WebViewActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Presenter {

    public Context context;
    ApiHolder apiHolder;

    public Presenter(Context context , ApiHolder apiHolder) {
        this.context = context;
        this.apiHolder = apiHolder;
    }

    boolean isOpening;

    public void getOpened(){

        Call<ResponseBody> call = apiHolder.getPosts();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context, "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                ResponseBody posts = response.body();
                if (posts != null) {
                   isOpening = posts.isOpened();
                    if(isOpening){
                        Intent intent = new Intent(context, WebViewActivity.class);
                        ((Activity)context).finish();
                        context.startActivity(intent);
                    }else {
                        Intent intent2 = new Intent(context, MenuGameActivity.class);
                        ((Activity)context).finish();
                        context.startActivity(intent2);
                    }
                    opened = isOpening;
                    saveOpenedPage();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
            }

        });

    }

    private static final String firstStartApp = "first_start";
    public void firstEnter(){
        SharedPreferences sp = ((Activity)context).getSharedPreferences(firstStartApp, Context.MODE_PRIVATE); // For First Download App
        boolean firstStart = false;
        if (sp != null) {
            firstStart = sp.getBoolean("firstStart", true);
        }

        if(firstStart) {
            sp.edit().putBoolean("firstStart", false).apply();
            getOpened();
        } else {
            loadOpenedPage();
            if(opened){
                Intent intent = new Intent(context, WebViewActivity.class);
                ((Activity)context).finish();
                context.startActivity(intent);
            }else{
                Intent intent2 = new Intent(context, MenuGameActivity.class);
                ((Activity)context).finish();
                context.startActivity(intent2);
            }
        }
    }

    SharedPreferences sPref;
    boolean opened = false;

    private void loadOpenedPage(){
        sPref = ((Activity)context).getPreferences(Context.MODE_PRIVATE);
        opened = sPref.getBoolean("isOpened", false);
    }

    private void saveOpenedPage(){
        sPref = ((Activity)context).getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putBoolean("isOpened", opened);
        ed.apply();
    }

}
