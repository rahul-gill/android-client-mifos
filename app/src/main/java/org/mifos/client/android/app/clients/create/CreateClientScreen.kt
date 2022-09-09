package org.mifos.client.android.app.clients.create

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import org.mifos.client.android.R
import org.mifos.client.android.data.api_services.client.ClientTemplate
import org.mifos.client.android.data.api_services.staff.Staff
import org.mifos.client.android.ui.UIDefaults
import org.mifos.client.android.ui.components.*
import org.mifos.client.android.ui.theme.size
import org.mifos.client.android.ui.theme.spacing
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateClientScreen(
    userImageUri: Uri?,
    setUserImageUrl: (Uri?) -> Unit,
    firstName: String,
    setFirstName: (String) -> Unit,
    lastName: String,
    setLastName: (String) -> Unit,
    middleName: String,
    setMiddleName: (String) -> Unit,
    mobileNumber: String,
    mobilePhoneCountry: Country,
    setMobilePhoneCountry: (Country) -> Unit,
    setMobileNumber: (String) -> Unit,
    externalId: String,
    setExternalId: (String) -> Unit,
    dateOfBirth: LocalDate,
    setDateOfBirth: (LocalDate) -> Unit,
    goBack: () -> Unit,
    loadingState: Boolean,
    errorState: Boolean,
    onRetry: () -> Unit,
    genderOptions: List<ClientTemplate.GenderOption>,
    selectedGenderIndex : Int,
    onGenderOptionSelection: (index: Int) -> Unit,
    clientTypeOptions: List<ClientTemplate.ClientTypeOption>,
    selectedClientTypeIndex : Int,
    onClientTypeOptionSelection: (index: Int) -> Unit,
    clientClassificationOptions: List<ClientTemplate.ClientClassificationOption>,
    selectedClientClassificationIndex : Int,
    onClientClassificationSelection: (index: Int) -> Unit,
    officeOptions: List<ClientTemplate.OfficeOption>,
    selectedOfficeIndex : Int,
    onOfficeOptionSelection: (index: Int) -> Unit,
    staffOptions: List<Staff>,
    staffOptionsState: SingleChoiceFieldState,
    onStaffOptionSelection: (index: Int) -> Unit,
    selectedStaffIndex : Int,
    isClientActive: Boolean,
    setIsClientActive: (Boolean) -> Unit,
    activationDate: LocalDate,
    setActivationDate: (LocalDate) -> Unit,
    onSubmit: () -> Unit,
    effectsFlow: Flow<CreateClientEffects>
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = setUserImageUrl
    )
    val dobDatePickerDialogState = rememberMaterialDialogState()
    val activationDatePickerDialogState = rememberMaterialDialogState()
    val focusManager = LocalFocusManager.current
    val snackBarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

    LaunchedEffect(Unit){
        effectsFlow.onEach { effect ->
            when(effect){
                is CreateClientEffects.ErrorMessage -> snackBarHostState.showSnackbar(
                    message = effect.message.resolve(context),
                    actionLabel = context.getString(R.string.ok)
                )
                CreateClientEffects.Success -> {
                    goBack()
                }
                else -> Unit
            }
        }.collect()
    }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            MediumTopAppBar(
                title = { Text(stringResource(id = R.string.create_client)) },
                scrollBehavior = scrollBehavior,
                navigationIcon = {
                    IconButton(onClick = goBack) {
                        Icon(
                            imageVector = Icons.Outlined.ArrowBack,
                            contentDescription = stringResource(R.string.go_back)
                        )
                    }
                }
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        }
    ) { paddingValues ->
        if(loadingState){
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues)){
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }
        }
        else if(errorState){
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues)){
                Column(
                    modifier = Modifier
                        .padding(horizontal = MaterialTheme.spacing.screenHorizontalPadding)
                        .align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = stringResource(id = R.string.error_occured_message_default))
                    LinkText(
                        text = stringResource(id = R.string.retry),
                        onClick = onRetry
                    )
                }
            }
        }
        else Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
                .padding(paddingValues)
                .padding(bottom = MaterialTheme.spacing.listBottomPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.marginBetweenItemsSmall)
        ) {
            val paddingModifier = Modifier.padding(horizontal = MaterialTheme.spacing.screenHorizontalPadding)
            val imageModifier = Modifier
                .size(MaterialTheme.size.imageMedium)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer)
                .clickable { galleryLauncher.launch("image/*") }
            if(userImageUri != null) AsyncImage(
                modifier = imageModifier.then(paddingModifier),
                model = userImageUri,
                contentDescription = stringResource(R.string.profile_picture),
                contentScale = ContentScale.Crop
            )
            else Image(
                modifier = imageModifier.then(paddingModifier),
                imageVector = Icons.Outlined.AccountCircle,
                contentDescription = stringResource(R.string.profile_picture),
                contentScale = ContentScale.Crop
            )

            OutlinedTextField(
                value = firstName,
                onValueChange = setFirstName,
                label = { Text(text = stringResource(R.string.first_name)) },
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .then(paddingModifier)
            )
            OutlinedTextField(
                value = middleName,
                onValueChange = setMiddleName,
                label = { Text(text = stringResource(R.string.middle_name)) },
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .then(paddingModifier)
            )
            OutlinedTextField(
                value = lastName,
                onValueChange = setLastName,
                label = { Text(text = stringResource(R.string.last_name)) },
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .then(paddingModifier)
            )
            OutlinedTextField(
                value = mobileNumber,
                onValueChange = setMobileNumber,
                label = { Text(text = stringResource(R.string.mobile_number)) },
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Phone
                ),
                leadingIcon = {
                    CountryCodePicker(
                        selectedCountry = mobilePhoneCountry,
                        onSelection = setMobilePhoneCountry
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .then(paddingModifier)
            )
            OutlinedTextField(
                value = externalId,
                onValueChange = setExternalId,
                label = { Text(text = stringResource(R.string.external_id)) },
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .then(paddingModifier)
            )
            OutlinedTextField(
                value =  DateTimeFormatter.ofPattern(UIDefaults.dateFormat).format(dateOfBirth),
                onValueChange = {},
                readOnly = true,
                label = { Text(text = stringResource(id = R.string.date_of_birth)) },
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .then(paddingModifier),
                trailingIcon = {
                    IconButton(
                        onClick = { dobDatePickerDialogState.show() }
                    ) {
                        Icon(imageVector = Icons.Default.CalendarToday,
                            contentDescription = stringResource(R.string.pick_date_of_birth)
                        )
                    }
                },
            )
            SingleChoiceField(
                options = genderOptions,
                selectOptionWithIndex = onGenderOptionSelection,
                optionText = {  name },
                selectedOptionIndex = selectedGenderIndex,
                label = { Text(text = stringResource(R.string.gender)) },
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .then(paddingModifier)
            )
            SingleChoiceField(
                options = clientTypeOptions,
                selectOptionWithIndex = onClientTypeOptionSelection,
                optionText = { name },
                selectedOptionIndex = selectedClientTypeIndex,
                label = { Text(text = stringResource(R.string.client_type)) },
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .then(paddingModifier)
            )
            SingleChoiceField(
                options = clientClassificationOptions,
                selectOptionWithIndex = onClientClassificationSelection,
                optionText = {  name },
                selectedOptionIndex = selectedClientClassificationIndex,
                label = { Text(text = stringResource(R.string.client_classification)) },
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .then(paddingModifier)
            )
            SingleChoiceField(
                options = officeOptions,
                selectOptionWithIndex = onOfficeOptionSelection,
                optionText = {  name },
                selectedOptionIndex = selectedOfficeIndex,
                label = { Text(text = stringResource(R.string.office)) },
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .then(paddingModifier)
            )
            SingleChoiceField(
                options = staffOptions,
                selectOptionWithIndex = onStaffOptionSelection,
                selectedOptionIndex = selectedStaffIndex,
                optionText = {  displayName },
                label = { Text(text = stringResource(R.string.staff)) },
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .then(paddingModifier),
                state = staffOptionsState
            )
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .toggleable(
                        value = isClientActive,
                        onValueChange = { setIsClientActive(!isClientActive) },
                        role = Role.Checkbox
                    )
                    .then(paddingModifier),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = isClientActive,
                    onCheckedChange = null
                )
                Text(
                    text = stringResource(R.string.client_active),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = MaterialTheme.spacing.marginBetweenItemsMedium)
                )
            }
            AnimatedVisibility(visible = isClientActive) {
                OutlinedTextField(
                    value = DateTimeFormatter.ofPattern(UIDefaults.dateFormat)
                        .format(activationDate),
                    onValueChange = {},
                    readOnly = true,
                    label = { Text(text = stringResource(R.string.activation_date)) },
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    ),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Text
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .then(paddingModifier),
                    trailingIcon = {
                        IconButton(
                            onClick = { activationDatePickerDialogState.show() }
                        ) {
                            Icon(
                                imageVector = Icons.Default.CalendarToday,
                                contentDescription = stringResource(R.string.pick_date_of_birth)
                            )
                        }
                    },
                )
            }
            Button(
                onClick = { onSubmit() },
                modifier = Modifier
                    .fillMaxWidth()
                    .then(paddingModifier)
            ) {
                Text(text = stringResource(R.string.submit))
            }
        }
    }

    MaterialDialog(
        dialogState = dobDatePickerDialogState,
        buttons = {
            positiveButton(stringResource(id = R.string.ok))
            negativeButton(stringResource(id = R.string.cancel))
        }
    ) {
        datepicker(
            initialDate = dateOfBirth,
            onDateChange = setDateOfBirth
        )
    }
    MaterialDialog(
        dialogState = activationDatePickerDialogState,
        buttons = {
            positiveButton(stringResource(id = R.string.ok))
            negativeButton(stringResource(id = R.string.cancel))
        }
    ) {
        datepicker(
            initialDate = activationDate,
            onDateChange = setActivationDate
        )
    }
}