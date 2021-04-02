package ru.int24.ownbarbershop.di

import android.content.Context
import android.os.Build
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.int24.ownbarbershop.config.DefConfig
import ru.int24.ownbarbershop.network.ApiYclients
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.inject.Singleton
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager


@Module(includes = [AppModule::class])
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient():OkHttpClient{

        val okHTTP = getOkHttpBuilder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()
        return okHTTP
    }

    private fun getOkHttpBuilder(): OkHttpClient.Builder =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            OkHttpClient().newBuilder()
        } else {
            // Workaround for the error "Caused by: com.android.org.bouncycastle.jce.exception.ExtCertPathValidatorException: Could not validate certificate: Certificate expired at".
            getUnsafeOkHttpClient()
        }

    private fun getUnsafeOkHttpClient(): OkHttpClient.Builder =
                    try {
                        // Create a trust manager that does not validate certificate chains
                        val trustAllCerts: Array<TrustManager> = arrayOf(
                                object : X509TrustManager {
                                    @Throws(CertificateException::class)
                                    override fun checkClientTrusted(chain: Array<X509Certificate?>?,
                                                                    authType: String?) = Unit

                                    @Throws(CertificateException::class)
                                    override fun checkServerTrusted(chain: Array<X509Certificate?>?,
                                                                    authType: String?) = Unit

                                    override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
                                }
                        )
                        // Install the all-trusting trust manager
                        val sslContext: SSLContext = SSLContext.getInstance("SSL")
                        sslContext.init(null, trustAllCerts, SecureRandom())
                        // Create an ssl socket factory with our all-trusting manager
                        val sslSocketFactory: SSLSocketFactory = sslContext.socketFactory
                        val builder = OkHttpClient.Builder()
                        builder.sslSocketFactory(sslSocketFactory,
                                trustAllCerts[0] as X509TrustManager)
                        builder.hostnameVerifier { _, _ -> true }
                        builder
                    } catch (e: Exception) {
                        throw RuntimeException(e)
                    }



    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(DefConfig.url)
                .build()
    }

    @Provides
    @Singleton
    fun provideRemoteApiYclients(retrofit: Retrofit): ApiYclients = retrofit.create(ApiYclients::class.java)

    @Provides
    @Singleton
    fun providePicasso(okHttpClient: OkHttpClient, context: Context): Picasso {
        return Picasso.Builder(context)
                .downloader(OkHttp3Downloader(okHttpClient))
                .build()
    }

}