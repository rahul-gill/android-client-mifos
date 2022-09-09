package org.mifos.client.android.app.clients.create

import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skydoves.sandwich.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import org.mifos.client.android.R
import org.mifos.client.android.data.ApiDefaults
import org.mifos.client.android.data.api_services.client.ClientService
import org.mifos.client.android.data.api_services.client.ClientTemplate
import org.mifos.client.android.data.api_services.client.CreateClientPayload
import org.mifos.client.android.data.api_services.staff.Staff
import org.mifos.client.android.data.api_services.staff.StaffService
import org.mifos.client.android.ui.ViewModelString
import org.mifos.client.android.ui.components.SingleChoiceFieldState
import org.mifos.client.android.ui.components.countries
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import javax.inject.Inject

sealed class CreateClientEffects{
    data class ErrorMessage(val message: ViewModelString): CreateClientEffects()
    object Success: CreateClientEffects()
}


@HiltViewModel
class CreateClientViewModel @Inject constructor(
    private val clientService: ClientService,
    private val staffService: StaffService
) : ViewModel(){
    val userImageUri = mutableStateOf<Uri?>(null)
    val firstName = mutableStateOf("")
    val middleName = mutableStateOf("")
    val lastName = mutableStateOf("")
    val externalId = mutableStateOf("")
    val mobileNumber = mutableStateOf("")
    val mobileCountry = mutableStateOf(countries.first())
    val dateOfBirth = mutableStateOf(LocalDate.now().minus(18, ChronoUnit.YEARS))

    val clientTemplateErrorState = mutableStateOf(false)
    val clientTemplateLoadingState = mutableStateOf(true)

    val selectedGender = mutableStateOf(-1)
    val genderOptions = mutableStateOf<List<ClientTemplate.GenderOption>>(listOf())

    val selectedClientType = mutableStateOf(-1)
    val clientTypeOptions = mutableStateOf<List<ClientTemplate.ClientTypeOption>>(listOf())

    val selectedClientClassification = mutableStateOf(-1)
    val clientClassificationOptions = mutableStateOf<List<ClientTemplate.ClientClassificationOption>>(listOf())

    val selectedOffice = mutableStateOf(-1)
    val officeOptions = mutableStateOf<List<ClientTemplate.OfficeOption>>(listOf())

    val selectedStaff = mutableStateOf(-1)
    val staffOptions = mutableStateOf<List<Staff>>(listOf())
    val staffOptionsState = mutableStateOf(SingleChoiceFieldState.NotApplicableYet)

    val isClientActive = mutableStateOf(false)
    val activationDate = mutableStateOf(LocalDate.now())

    private val effectsChannel =  Channel<CreateClientEffects>(Channel.UNLIMITED)
    val effectsFlow
        get() = effectsChannel.receiveAsFlow()

    init {
        loadClientTemplate()
    }

    fun loadClientTemplate(){
        clientTemplateLoadingState.value = true
        clientTemplateErrorState.value = false
        viewModelScope.launch {
            clientService.getClientTemplate()
                .onFailure {
                    clientTemplateErrorState.value = true
                    clientTemplateLoadingState.value = false
                }
                .onSuccess {
                    genderOptions.value = data.genderOptions
                    clientTypeOptions.value = data.clientTypeOptions
                    clientClassificationOptions.value = data.clientClassificationOptions
                    officeOptions.value = data.officeOptions
                    staffOptions.value = data.staffOptions
                    if(genderOptions.value.isNotEmpty())
                        selectedGender.value = -1
                    if(clientTypeOptions.value.isNotEmpty())
                        selectedClientType.value = -1
                    if(clientClassificationOptions.value.isNotEmpty())
                        selectedClientClassification.value = -1
                    if(officeOptions.value.isNotEmpty())
                        selectedOffice.value = -1
                    if(staffOptions.value.isNotEmpty())
                        selectedStaff.value = -1
                    clientTemplateLoadingState.value = false
                }
        }
    }

    fun loadStaffsForOffice(officeId: Int){
        staffOptionsState.value = SingleChoiceFieldState.Loading
        selectedStaff.value = -1
        viewModelScope.launch {
            staffService.getStaffForOffice(officeId)
                .onSuccess {
                    staffOptions.value = data
                    staffOptionsState.value =
                        if(data.isEmpty()) SingleChoiceFieldState.NotApplicableYet
                        else SingleChoiceFieldState.OK
                }
                .onFailure {
                    clientTemplateErrorState.value = true
                    staffOptionsState.value = SingleChoiceFieldState.NotApplicableYet
                }
        }
    }

    fun onSubmit(){
        val errorMessage = when{
            firstName.value.isBlank() -> ViewModelString(R.string.field_cant_be_empty, R.string.first_name)
            lastName.value.isBlank() -> ViewModelString(R.string.field_cant_be_empty, R.string.last_name)
            selectedOffice.value == -1 -> ViewModelString(R.string.field_cant_be_empty, R.string.office)
            dateOfBirth.value.isAfter(LocalDate.now()) -> ViewModelString(R.string.invalid_field, R.string.date_of_birth)
            activationDate.value.isAfter(LocalDate.now()) -> ViewModelString(R.string.invalid_field, R.string.activation_date)
            else -> null
        }
        if(errorMessage != null){
            viewModelScope.launch {
                effectsChannel.send(CreateClientEffects.ErrorMessage(errorMessage))
            }
            return
        }

        viewModelScope.launch {
            clientService.createClient(
                clientPayload = CreateClientPayload(
                    firstname = firstName.value,
                    middlename = middleName.value.ifBlank { null },
                    lastname = lastName.value,
                    officeId = officeOptions.value[selectedOffice.value].id,
                    dateOfBirth = DateTimeFormatter.ofPattern(ApiDefaults.API_DATE_FORMAT).format(dateOfBirth.value),
                    mobileNo = "+${mobileCountry.value.code}${mobileNumber.value}",
                    externalId = externalId.value.ifBlank { null },
                    staffId =
                        if(selectedStaff.value == -1) null
                        else staffOptions.value[selectedStaff.value].id.toString(),
                    genderId =
                        if(selectedGender.value == -1) null
                        else genderOptions.value[selectedGender.value].id.toString(),
                    clientTypeId =
                        if(selectedClientType.value == -1) null
                        else clientTypeOptions.value[selectedClientType.value].id.toString(),
                    clientClassificationId =
                        if(selectedClientClassification.value == -1) null
                        else clientClassificationOptions.value[selectedClientClassification.value].id.toString(),
                    submittedOnDate = DateTimeFormatter.ofPattern(ApiDefaults.API_DATE_FORMAT).format(LocalDate.now()),
                    active = isClientActive.value,
                    activationDate =
                        if(isClientActive.value) DateTimeFormatter.ofPattern(ApiDefaults.API_DATE_FORMAT).format(activationDate.value)
                        else null,
                    dateFormat = ApiDefaults.API_DATE_FORMAT,
                    locale = ApiDefaults.API_LOCALE
                )
            )
            .suspendOnSuccess {
                effectsChannel.send(CreateClientEffects.Success)
            }
            .suspendOnFailure {
                effectsChannel.send(CreateClientEffects.ErrorMessage(ViewModelString(R.string.error_occured_message_default)))
            }
        }
    }

}