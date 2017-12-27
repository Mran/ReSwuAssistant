package org.swuos.swuassistant.net.interfacelmpl;




import org.swuos.swuassistant.net.jsona.LoginJson;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;


/**
 * Created by 张孟尧 on 2016/8/30.
 */
public interface LoginIds {
    @POST("http://ids1.swu.edu.cn:81/amserver/UI/Login")
    @FormUrlEncoded
    Observable<LoginJson> login(@FieldMap Map<String, String> formData);
}
