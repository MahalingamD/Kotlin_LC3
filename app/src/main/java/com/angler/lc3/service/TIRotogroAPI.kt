package com.angler.lc3.service


import com.angler.lc3.BuildConfig
import com.angler.lc3.model.Data
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

/**
 * This class represents the Countries API, all endpoints can stay here.
 *
 * @author Mahalingam
 * @date 30/01/19.
 */
interface TIRotogroAPI {

    @get:GET(BuildConfig.Main_Base_url + "GetlanguageList")
    val allLanguage: Call<Data>


    @GET(BuildConfig.Main_Base_url + "GetAppUpdate")
    fun getAppUpdate(@Query("version_name") version_name: String): Call<Data>

    @GET(BuildConfig.Main_Base_url + "ValidateUser")
    fun getOTP(@Query("Mob_Number") aMobile_number: String): Call<Data>

    @GET(BuildConfig.Main_Base_url + "ValidateOtp")
    fun validateOTP(@QueryMap params: Map<String, String>): Call<Data>

    @GET(BuildConfig.Main_Base_url + "GetUpdateProduct")
    fun updateProduct(@Query("LastUpdate") aLastUpdateTime: String): Call<Data>


}
