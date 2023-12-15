package com.kv.pribizz.data.remote

import android.text.TextUtils
import com.kv.pribizz.model.response.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Response
import java.io.File
import javax.inject.Inject


class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun signup(
        name: String,
        mobile: String,
        password: String,
        referral_code: String,
        device_token: String,
    ) =
        apiService.signup(
            name,
            mobile,
            password,
            referral_code,
            device_token,
        )

    suspend fun login(
        params: HashMap<String, String>,
    ) =
        apiService.login(
            params
        )

    suspend fun getVersion() = apiService.getVersion()

    suspend fun updateProfile(
        name: String,
        mobile: String,
        profile_image_file: File?
    ): Response<UserDetailResponseModel> {
        val name: RequestBody = name
            .toRequestBody("text/plain".toMediaTypeOrNull())
        val mobile: RequestBody = mobile
            .toRequestBody("text/plain".toMediaTypeOrNull())

        var profile_image: MultipartBody.Part? = null
        if (profile_image_file != null) {
            val coontentType = "image/" + profile_image_file.extension
            val reqFile: RequestBody =
                profile_image_file.asRequestBody(coontentType.toMediaTypeOrNull())

            profile_image = MultipartBody.Part.createFormData(
                "profile_image", profile_image_file.getName(),
                reqFile
            )
        }

        return apiService.updateProfile(
            name,
            mobile,
            profile_image
        )
    }

    suspend fun updateUpi(
        upi_id: String,
    ): Response<UserDetailResponseModel> {

        val upi_id: RequestBody = upi_id
            .toRequestBody("text/plain".toMediaTypeOrNull())

        return apiService.updateUpi(
            upi_id,
        )
    }

    suspend fun updateBank(
        account_name: String,
        bank_name: String,
        account_no: String,
        ifsc_code: String,
    ): Response<CommonResponseModel> {
        val account_name: RequestBody = account_name
            .toRequestBody("text/plain".toMediaTypeOrNull())
        val bank_name: RequestBody = bank_name
            .toRequestBody("text/plain".toMediaTypeOrNull())
        val account_no: RequestBody = account_no
            .toRequestBody("text/plain".toMediaTypeOrNull())

        val ifsc_code: RequestBody = ifsc_code
            .toRequestBody("text/plain".toMediaTypeOrNull())

        return apiService.updateBank(
            account_name,
            bank_name,
            account_no,
            ifsc_code,
        )
    }

    suspend fun updatePan(
        pan_number: String,
        pan_name: String,
        dob: String,
    ): Response<CommonResponseModel> {
        val pan_number: RequestBody = pan_number
            .toRequestBody("text/plain".toMediaTypeOrNull())
        val pan_name: RequestBody = pan_name
            .toRequestBody("text/plain".toMediaTypeOrNull())
        val dob: RequestBody = dob
            .toRequestBody("text/plain".toMediaTypeOrNull())
        return apiService.updatePan(
            pan_number,
            pan_name,
            dob,
        )
    }

    suspend fun getContactDetails() =
        apiService.getContactDetails()

    suspend fun getInvestmentHistory(
        from_date: String,
        to_date: String,
        page_no: String
    ): Response<InvestmentHistoryResponseModel> {
//
//        val body: RequestBody = MultipartBody.Builder().setType(MultipartBody.FORM)
//            .addFormDataPart("start_date", from_date)
//            .addFormDataPart("end_date", to_date)
//            .addFormDataPart("page_no", page_no)
//            .build()
        return apiService.getInvestmentHistory()
    }

    suspend fun getEarnigHistory(
        from_date: String,
        to_date: String,
        page_no: String
    ): Response<ReturnHistoryResponseModel> {

        val map = HashMap<String, String>()
        if (!TextUtils.isEmpty(from_date)) {
            map.put("start_date", from_date)
        }
        if (!TextUtils.isEmpty(to_date)) {
            map.put("end_date", to_date)
        }
        if (!TextUtils.isEmpty(page_no)) {
            map.put("page_no", page_no)
        }
        return apiService.getEarning(map)
    }

    suspend fun logout() =
        apiService.logout()

    suspend fun getProfile() =
        apiService.getProfile()

    suspend fun getUserBalance() =
        apiService.getUserBalance()


    suspend fun getNotificationList(page_no: Int) =
        apiService.getNotificationList(page_no)

    suspend fun getPlanList() =
        apiService.getPlanList()


    suspend fun getNetwork(mobile: String, page_no: String): Response<NetworkResponseModel> {
        val map = HashMap<String, String>()
        if (!TextUtils.isEmpty(mobile)) {
            map.put("mobile", mobile)
        }
        if (!TextUtils.isEmpty(page_no)) {
            map.put("page_no", page_no)
        }
        return apiService.getNetwork(map)
    }

    suspend fun getMarqueeText() =
        apiService.getMarqueeText()

}


