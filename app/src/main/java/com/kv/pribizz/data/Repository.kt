package com.kv.pribizz.data

import com.kv.pribizz.data.remote.RemoteDataSource
import com.kv.pribizz.model.BaseApiResponse
import com.kv.pribizz.model.BaseResponseModel
import com.kv.pribizz.model.response.*
import com.kv.pribizz.utils.NetworkResult
import com.kv.pribizz.utils.Utils.getUniqueDeviceId
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.File
import javax.inject.Inject

// create test class with name RepositoryTest in app/src/test/java/com/kv/pribizz/data/RepositoryTest.kt
@ActivityRetainedScoped
class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : BaseApiResponse() {

    suspend fun signup(
        name: String,
        mobile: String,
        password: String,
        referral_code: String,
        device_token: String,
    ): Flow<NetworkResult<SignupResponseModel>> {
        return flow {
            emit(safeApiCall {
                remoteDataSource.signup(
                    name,
                    mobile,
                    password,
                    referral_code,
                    device_token,
                )
            })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun login(
        username: String,
        password: String,
        device_token: String,
    ): Flow<NetworkResult<LoginResponseModel>> {
        return flow {
            val params = HashMap<String, String>().apply {
                this["username"] = username
                this["password"] = password
                this["device_token"] = device_token
                this["device_id"] = getUniqueDeviceId()
                this["device_type"] = "A"
                this["loginfrom"] = "M"
            }
            emit(safeApiCall {
                remoteDataSource.login(
                    params
                )
            })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun get_version(
    ): Flow<NetworkResult<AppVersionResponseModel>> {
        return flow {

            emit(safeApiCall {
                remoteDataSource.getVersion(
                )
            })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getNetwork(
        mobile: String,
        page_no: String
    ): Flow<NetworkResult<NetworkResponseModel>> {
        return flow {
            emit(safeApiCall { remoteDataSource.getNetwork(mobile, page_no) })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getContactDetails(): Flow<NetworkResult<ContactDetailsResponseModel>> {
        return flow {
            emit(safeApiCall { remoteDataSource.getContactDetails() })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun logout(): Flow<NetworkResult<BaseResponseModel>> {
        return flow<NetworkResult<BaseResponseModel>> {
            emit(safeApiCall { remoteDataSource.logout() })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getUserBalance(): Flow<NetworkResult<UserBalanceResponseModel>> {
        return flow<NetworkResult<UserBalanceResponseModel>> {
            emit(safeApiCall { remoteDataSource.getUserBalance() })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getProfile(): Flow<NetworkResult<UserDetailResponseModel>> {
        return flow<NetworkResult<UserDetailResponseModel>> {
            emit(safeApiCall { remoteDataSource.getProfile() })
        }.flowOn(Dispatchers.IO)
    }


    suspend fun getNotificationList(page_no: Int): Flow<NetworkResult<NotificationListResponseModel>> {
        return flow {
            emit(safeApiCall { remoteDataSource.getNotificationList(page_no) })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getPlanList(): Flow<NetworkResult<PlanResponseModel>> {
        return flow {
            emit(safeApiCall { remoteDataSource.getPlanList() })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getInvestmentHistory(
        from_date: String,
        to_date: String,
        page_no: String
    ): Flow<NetworkResult<InvestmentHistoryResponseModel>> {
        return flow {
            emit(safeApiCall {
                remoteDataSource.getInvestmentHistory(
                    from_date,
                    to_date,
                    page_no
                )
            })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getEarnigHistory(
        from_date: String,
        to_date: String,
        page_no: String
    ): Flow<NetworkResult<ReturnHistoryResponseModel>> {
        return flow {
            emit(safeApiCall {
                remoteDataSource.getEarnigHistory(
                    from_date,
                    to_date,
                    page_no
                )
            })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun updateUpi(
        upi_id: String,
    ): Flow<NetworkResult<UserDetailResponseModel>> {
        return flow {
            emit(safeApiCall {
                remoteDataSource.updateUpi(
                    upi_id,
                )
            })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun updateBank(
        account_name: String,
        bank_name: String,
        account_no: String,
        ifsc_code: String,
    ): Flow<NetworkResult<CommonResponseModel>> {
        return flow {
            emit(safeApiCall {
                remoteDataSource.updateBank(
                    account_name,
                    bank_name,
                    account_no,
                    ifsc_code,
                )
            })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun updatePan(
        pan_number: String,
        pan_name: String,
        dob: String
    ): Flow<NetworkResult<CommonResponseModel>> {
        return flow {
            emit(safeApiCall {
                remoteDataSource.updatePan(
                    pan_number,
                    pan_name,
                    dob,
                )
            })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun updateProfile(
        name: String,
        mobile: String,
        profile_image: File?
    ): Flow<NetworkResult<UserDetailResponseModel>> {
        return flow {
            emit(safeApiCall {
                remoteDataSource.updateProfile(
                    name,
                    mobile,
                    profile_image
                )
            })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getMarqueeText(): Flow<NetworkResult<MarqueeResponseModel>> {
        return flow {
            emit(safeApiCall { remoteDataSource.getMarqueeText() })
        }.flowOn(Dispatchers.IO)
    }

}