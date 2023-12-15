package com.kv.pribizz.data.remote

import com.kv.pribizz.model.BaseResponseModel
import com.kv.pribizz.model.response.*
import com.kv.pribizz.utils.Constants
import com.kv.pribizz.utils.Utils.getUniqueDeviceId
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST(Constants.SIGNUP)
    suspend fun signup(
        @Field("name") name: String,
        @Field("mobile") mobile: String,
        @Field("password") password: String,
        @Field("referral_code") referral_code: String,
        @Field("device_token") device_token: String,
        @Field("device_id") device_id: String = getUniqueDeviceId(),
        @Field("device_type") device_type: String = "A"
    ): Response<SignupResponseModel>

    @FormUrlEncoded
    @POST(Constants.LOGIN)
    suspend fun login(
        @FieldMap params: HashMap<String, String>,
    ): Response<LoginResponseModel>


    @GET(Constants.GET_NETWORK)
    suspend fun getNetwork(
        @QueryMap params: HashMap<String, String>? = null,
    ): Response<NetworkResponseModel>

    @GET(Constants.GET_VERSION)
    suspend fun getVersion(): Response<AppVersionResponseModel>

    @GET(Constants.GET_CONTACT_DETAILS)
    suspend fun getContactDetails(): Response<ContactDetailsResponseModel>

    @GET(Constants.LOGOUT)
    suspend fun logout(): Response<BaseResponseModel>

    @GET(Constants.GET_USER_BALANCE)
    suspend fun getUserBalance(): Response<UserBalanceResponseModel>

    @GET(Constants.GET_PROFILE)
    suspend fun getProfile(): Response<UserDetailResponseModel>

    @GET(Constants.GET_NOTIFICATIONS)
    suspend fun getNotificationList(
        @Query("page_no") page_no: Int
    ): Response<NotificationListResponseModel>

    @GET(Constants.GET_PLANS)
    suspend fun getPlanList(
    ): Response<PlanResponseModel>

    @GET(Constants.GET_INVESTMENT_HISTORY)
    suspend fun getInvestmentHistory(): Response<InvestmentHistoryResponseModel>

    @GET(Constants.GET_RETURN_HISTORY)
    suspend fun getEarning(
        @QueryMap params: HashMap<String, String>? = null,
    ): Response<ReturnHistoryResponseModel>

    @Multipart
    @POST(Constants.UPDATE_PROFILE)
    suspend fun updateProfile(
        @Part("name") name: RequestBody,
        @Part("mobile") mobile: RequestBody,
        @Part profile_image: MultipartBody.Part?,
    ): Response<UserDetailResponseModel>

    @Multipart
    @POST(Constants.UPDATE_Upi)
    suspend fun updateUpi(
        @Part("upi_id") upi_id: RequestBody,
    ): Response<UserDetailResponseModel>

    @Multipart
    @POST(Constants.UPDATE_Bank)
    suspend fun updateBank(
        @Part("account_name") account_name: RequestBody,
        @Part("bank_name") bank_name: RequestBody,
        @Part("account_no") account_no: RequestBody,
        @Part("ifsc_code") ifsc_code: RequestBody,
    ): Response<CommonResponseModel>

    @Multipart
    @POST(Constants.UPDATE_Pan)
    suspend fun updatePan(
        @Part("pan_number") pan_number: RequestBody,
        @Part("pan_name") pan_name: RequestBody,
        @Part("dob") dob: RequestBody,
    ): Response<CommonResponseModel>

    @GET(Constants.GET_MARQUEE_TEXT)
    suspend fun getMarqueeText(): Response<MarqueeResponseModel>

    //@Body parameters cannot be used with form or multi-part encoding
    //@Part,@PartMap use with only @Multipart
    //@Field, @FieldMap use with only @FormUrlEncoded
}