package org.mifos.client.core.office

import org.mifos.client.core.ApiResponseFlow
import de.jensklingenberg.ktorfit.http.GET

interface OfficeService {

    @GET("offices")
    fun getAllOffices(): ApiResponseFlow<List<Office>>
}
