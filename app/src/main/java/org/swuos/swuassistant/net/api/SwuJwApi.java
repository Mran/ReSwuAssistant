package org.swuos.swuassistant.net.api;


import org.swuos.swuassistant.commmon.Constant;
import org.swuos.swuassistant.utils.Logger;
import org.swuos.swuassistant.net.interfacelmpl.JwGrade;
import org.swuos.swuassistant.net.interfacelmpl.JwGradeDetail;
import org.swuos.swuassistant.net.interfacelmpl.JwJudgement;
import org.swuos.swuassistant.net.interfacelmpl.JwSchedule;
import org.swuos.swuassistant.net.interfacelmpl.LoginIds;
import org.swuos.swuassistant.net.interfacelmpl.LoginIswu;
import org.swuos.swuassistant.net.interfacelmpl.LoginJw;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Authenticator;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.Credentials;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by 张孟尧 on 2016/8/30.
 */
public class SwuJwApi {

    private static LoginIswu loginIswu;
    private static LoginIds sLoginIds;

    private static LoginJw loginJw;
    private static JwSchedule jwSchedule;
    private static JwGrade jwGrade;
    private static JwGradeDetail jwGradeDetail;
    private static JwJudgement jwJudgement;

    private static ScalarsConverterFactory scalarsConverterFactory = ScalarsConverterFactory.create();
    private static GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();
    private static HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();

    private static CookieJar cookieJar = new CookieJar() {
        List<Cookie> cookies = new ArrayList<>();

        @Override
        public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
            if (cookies.size() == 0) {
                cookies.addAll(list);
                return;
            }
            for (Cookie c : list) {

                for (int i = 0; i < cookies.size(); i++) {
                    Cookie cookie = cookies.get(i);
                    if (cookie.name().equals(c.name())) {
                        cookies.remove(i);
                    }
                }
                cookies.add(c);

            }
        }

        @Override
        public List<Cookie> loadForRequest(HttpUrl httpUrl) {
            return cookies != null ? cookies : new ArrayList<Cookie>();
        }
    };


    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .cookieJar(cookieJar)
            .addInterceptor(httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY))
            .authenticator(new Authenticator() {
                @Override
                public Request authenticate(Route route, Response response) throws IOException {
                    String credential = Credentials.basic("opensource", "freedom");
                    Logger.d("okhttp", "认证");
                    Request request = response.request().newBuilder().header("Authorization", credential).build();
                    return request;
                }
            })
            .readTimeout(Constant.TIMEOUT, TimeUnit.MILLISECONDS)
            .build();


    public static LoginIswu loginIswu() {
        if (loginIswu == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://i.swu.edu.cn/")
                    .client(okHttpClient)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .addConverterFactory(gsonConverterFactory)
                    .build();
            loginIswu = retrofit.create(LoginIswu.class);
        }
        return loginIswu;
    }

    public static LoginIds loginIds() {
        if (sLoginIds == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://ids1.swu.edu.cn:81")
                    .client(okHttpClient)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .addConverterFactory(gsonConverterFactory)
                    .build();
            sLoginIds = retrofit.create(LoginIds.class);
        }
        return sLoginIds;
    }

    public static LoginJw loginJw() {

        if (loginJw == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://i.swu.edu.cn/")
                    .client(okHttpClient)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .addConverterFactory(scalarsConverterFactory)
                    .build();
            loginJw = retrofit.create(LoginJw.class);
        }
        return loginJw;
    }
    public static LoginJw loginJw(String cookies) {
        List<Cookie> cookies1 = new ArrayList<>();
        Cookie cookie = Cookie.parse(HttpUrl.parse("http://jw.swu.edu.cn"), cookies);
        cookies1.add(cookie);
        cookieJar.saveFromResponse(HttpUrl.parse("http://jw.swu.edu.cn"), cookies1);

        if (loginJw == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://i.swu.edu.cn/")
                    .client(okHttpClient)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .addConverterFactory(scalarsConverterFactory)
                    .build();
            loginJw = retrofit.create(LoginJw.class);
        }
        return loginJw;
    }

    public static JwJudgement getJwJudgement() {
        if (jwJudgement == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://jw.swu.edu.cn/")
                    .client(okHttpClient)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .addConverterFactory(scalarsConverterFactory)
                    .build();
            jwJudgement = retrofit.create(JwJudgement.class);
        }
        return jwJudgement;
    }
    public static JwSchedule jwSchedule() {
        if (jwSchedule == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://jw.swu.edu.cn")
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .addConverterFactory(scalarsConverterFactory)
                    .client(okHttpClient)
                    .build();

            jwSchedule = retrofit.create(JwSchedule.class);
        }
        return jwSchedule;
    }

    public static JwGrade jwGrade() {
        if (jwGrade == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://jw.swu.edu.cn")
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .addConverterFactory(scalarsConverterFactory)
                    .client(okHttpClient)
                    .build();

            jwGrade = retrofit.create(JwGrade.class);
        }
        return jwGrade;
    }

    public static JwGradeDetail getJwGradeDetail() {
        if (jwGradeDetail == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://jw.swu.edu.cn")
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .addConverterFactory(scalarsConverterFactory)
                    .client(okHttpClient)
                    .build();

            jwGradeDetail = retrofit.create(JwGradeDetail.class);
        }
        return jwGradeDetail;
    }

}
