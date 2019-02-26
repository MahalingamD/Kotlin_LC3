package com.angler.lc3.service

import com.angler.lc3.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * This class represents RetrofitInstance.
 *
 * @author Mahalingam
 * @date 30/01/19.
 */

class RetrofitInstance {

    private var retrofit: Retrofit? = null


    /**
     * This method creates a new instance of the API interface.
     *
     * @return The API interface
     */
    //Set Timeout for retrofit
    val api: TIRotogroAPI
        get() {


            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BuildConfig.Main_Base_url)
                    .client(requestTimeOut)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }

            return retrofit!!.create(TIRotogroAPI::class.java)
        }

    private val requestTimeOut: OkHttpClient
        get() = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()

}