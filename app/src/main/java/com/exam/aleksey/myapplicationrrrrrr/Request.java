package com.exam.aleksey.myapplicationrrrrrr;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Aleksey on 09.09.2017.
 */

public interface Request {
    @FormUrlEncoded
    @POST("")
    Call<Object> performPostCall(@FieldMap HashMap<String, String> postDataParams);
}