package com.soul.crm.presentation.sign_in

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soul.crm.data.base.BaseNetworkResult
import com.soul.crm.data.base.CheckResult
import com.soul.crm.data.remote.models.request.sign_up.SignUpRequest
import com.soul.crm.data.remote.models.response.sign_up.SignUpResponse
import com.soul.crm.domain.sign_up.use_case.*
import com.soul.crm.domain.sign_up.validates.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val validateName: ValidateName = ValidateName(),
    private val validateGender: ValidateGender = ValidateGender(),
    private val validateLastName: ValidateLastName = ValidateLastName(),
    private val validatePhoneNumber: ValidatePhoneNumber = ValidatePhoneNumber(),
    private val validatePassword: ValidatePassword = ValidatePassword()
) : ViewModel() {
    private val _sigUp = MutableSharedFlow<BaseNetworkResult<SignUpResponse>>()
    val signUp = _sigUp.asSharedFlow()

    private val _name = Channel<CheckResult>()
    val name = _name.receiveAsFlow()

    private val _lastname = Channel<CheckResult>()
    val lastname = _lastname.receiveAsFlow()

    private val _phoneNumber = Channel<CheckResult>()
    val phoneNumber = _phoneNumber.receiveAsFlow()

    private val _gender = Channel<CheckResult>()
    val gender = _gender.receiveAsFlow()

    private val _password = Channel<CheckResult>()
    val password = _password.receiveAsFlow()

    fun signUp(
        name: String,
        lastname: String,
        phoneNumber: String,
        password: String,
        image: String? = null,
        gender: String
    ) {
        viewModelScope.launch {
            if (validateName.validateName(name).check &&
                validateLastName.validateLastname(lastname).check &&
                !validateGender.validateGender(gender).check &&
                validatePhoneNumber.validatePhoneNumber(phoneNumber).check &&
                validatePassword.validatePassword(password).check
            ) {
                Log.d("OOO", "signUp: Check")
                signUpUseCase.signUp(
                    SignUpRequest(
                        first_name = name,
                        last_name = lastname,
                        gender = gender,
                        image = image,
                        phone_number = phoneNumber,
                        password = password
                    )
                ).onEach { result ->
                    when (result) {
                        is BaseNetworkResult.Error -> {
                            _sigUp.emit(BaseNetworkResult.Error(result.message))
                        }
                        is BaseNetworkResult.Loading -> {
                            _sigUp.emit(BaseNetworkResult.Loading(result.isLoading))
                        }
                        is BaseNetworkResult.Success -> {
                            result.data?.let {
                                _sigUp.emit(BaseNetworkResult.Success(it))
                            }
                        }
                    }
                }.launchIn(viewModelScope)
            } else {
                validateLastName.validateLastname(lastname).let { _lastname.send(it) }
                gender?.let { validateGender.validateGender(it).let {t-> _gender.send(t) } }
                validateName.validateName(name).let { _name.send(it) }
                validatePhoneNumber.validatePhoneNumber(phoneNumber).let { _phoneNumber.send(it) }
                validatePassword.validatePassword(password).let { _password.send(it) }
            }
        }
    }

    fun validatePhoneNumber(phoneNumber: String){
        viewModelScope.launch{
            validatePhoneNumber.validatePhoneNumber(phoneNumber).let { _phoneNumber.send(it) }
        }
    }


    fun validatePassword(password: String){
        viewModelScope.launch{
            validatePassword.validatePassword(password).let { _password.send(it) }
        }
    }


    fun validateName(name: String){
        viewModelScope.launch{
            validateName.validateName(name).let { _name.send(it) }
        }
    }


    fun validateLastname(lastname: String){
        viewModelScope.launch{
            validateLastName.validateLastname(lastname).let { _lastname.send(it) }
        }
    }
}