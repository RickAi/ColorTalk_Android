package navyblue.top.colortalk.rest.clients;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import navyblue.top.colortalk.app.Constants;
import navyblue.top.colortalk.rest.services.ColorTalkService;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by CIR on 16/1/16.
 */
public class ColorTalkClient {

    final ColorTalkService mColorTalkService;

    final static Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .serializeNulls()
            .create();


    public ColorTalkClient() {
        OkHttpClient httpClient = new OkHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_COLORTALK)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(httpClient)
                .build();

        mColorTalkService = retrofit.create(ColorTalkService.class);
    }

    public ColorTalkService getColorTalkService(){
        return mColorTalkService;
    }
}
