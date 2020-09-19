package project.test.testproject.Model.retrofit;

import com.google.gson.annotations.SerializedName;

public class ResponseBody {

    @SerializedName("result")
    private boolean opened;

    public ResponseBody(boolean opened) {
        this.opened = opened;
    }

    public boolean isOpened() {
        return opened;
    }
}
