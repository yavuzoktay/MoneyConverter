package com.yavuzoktay.moneyconverter.remote;

import com.yavuzoktay.moneyconverter.model.Coin;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Yavuz on 6.12.2017.
 */

public interface CoinService {

    @GET("data/price")
    Call<Coin> calculateValue(@Query("fsym") String from,@Query("tsyms") String to);


}
