package br.com.stant.stant_android_occurrences.services

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiDataSource  {

    companion object {

        fun <S> createService(serviceClass: Class<S>): S {

            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val httpClient = OkHttpClient.Builder()
                .readTimeout(15, TimeUnit.SECONDS)

            httpClient.addInterceptor(loggingInterceptor)

            val retrofit = Retrofit.Builder()
                .baseUrl("http://10.0.0.100:8080/api/rest/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .client(httpClient.build())
                .build()

            return retrofit.create(serviceClass)
        }

    }


}
