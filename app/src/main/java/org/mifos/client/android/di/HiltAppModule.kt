package org.mifos.client.android.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.mifos.client.android.data.api_services.auth.AuthService
import org.mifos.client.android.data.api_services.centers.CentersService
import org.mifos.client.android.data.api_services.client.ClientService
import org.mifos.client.android.data.api_services.groups.GroupService
import org.mifos.client.android.data.api_services.search.SearchService
import org.mifos.client.android.data.api_services.staff.StaffService
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

    @Singleton
    @Provides
    fun searchService(retrofit: Retrofit): SearchService = retrofit.create(SearchService::class.java)

    @Singleton
    @Provides
    fun clientService(retrofit: Retrofit): ClientService = retrofit.create(ClientService::class.java)

    @Singleton
    @Provides
    fun groupService(retrofit: Retrofit): GroupService = retrofit.create(GroupService::class.java)

    @Singleton
    @Provides
    fun centerService(retrofit: Retrofit): CentersService = retrofit.create(CentersService::class.java)

    @Singleton
    @Provides
    fun staffService(retrofit: Retrofit): StaffService = retrofit.create(StaffService::class.java)


}