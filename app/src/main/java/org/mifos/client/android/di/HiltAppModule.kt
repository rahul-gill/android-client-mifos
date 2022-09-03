package org.mifos.client.android.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.mifos.client.android.data.api_services.auth.AuthService
import org.mifos.client.android.data.buildRetrofitClient
import org.mifos.client.android.data.local_prefs.PrefsManager
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltAppModule {

    @Singleton
    @Provides
    fun retrofitClient(prefsManager: PrefsManager): Retrofit = buildRetrofitClient(prefsManager)

    @Singleton
    @Provides
    fun authService(retrofit: Retrofit): AuthService = retrofit.create(AuthService::class.java)

}