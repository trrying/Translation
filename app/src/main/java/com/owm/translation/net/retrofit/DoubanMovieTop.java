package com.owm.translation.net.retrofit;

import com.owm.translation.model.MovieResult;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 豆瓣接口
 * Created by ouweiming on 2016/11/3.
 */

public interface DoubanMovieTop {


    @GET("v2/movie/top250")
    Observable<MovieResult> getTopMovie(@Query("start") int start, @Query("count") int count);

}
