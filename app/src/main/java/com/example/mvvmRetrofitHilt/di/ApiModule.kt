package com.example.mvvmRetrofitHilt.di

import android.content.Context
import com.example.mvvmRetrofitHilt.networkApi.Api
import com.example.mvvmRetrofitHilt.networkApi.NetworkConnectionInterceptor
import com.example.mvvmRetrofitHilt.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun okHttpClient(context: Context) =
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                chain.proceed(chain.request().newBuilder().also {
                    it.addHeader(
                        "Authorization",
                        "Bearer null"
//                        " Bearer ${provideSessionManager(provideSharedPreferences(context)).getString(Constants.TOKEN)}}"
                    )
                }.build())
            }
            .addInterceptor(NetworkConnectionInterceptor(context))
            .retryOnConnectionFailure(true)
            .build()

    @Provides
    @Singleton
    fun retrofitInstance(@ApplicationContext context: Context): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient(context))
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit) : Api = retrofit.create(Api::class.java)
}


//
//@Module
//@InstallIn(SingletonComponent::class)
//object PreferenceModule {
//    @Singleton
//    @Provides
//    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
//        context.getSharedPreferences(
//            Constants.PREFERENCE_NAME, Context.MODE_PRIVATE
//        )
//
//    @Singleton
//    @Provides
//    fun provideSessionManager(preferences: SharedPreferences) =
//        MyPreferenceManager(preferences)
//}