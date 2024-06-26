package com.example.myapplication.apiConnect

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class RepositoryImpl(private val api: Api
) : Repository {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun sendCodeEmail(email: String): Flow<Result<String>> {
        return flow {
            val request = try {
                api.sendCodeEmail(email)
            }
            catch (e: IOException){
                e.printStackTrace()
                emit(Result.Error(message = "IOException " + e.message))
                return@flow
            }
            catch (e: HttpException){
                e.printStackTrace()
                emit(Result.Error(message = "Http "+ e.message))
                return@flow
            }
            catch (e: Exception){
                e.printStackTrace()
                emit(Result.Error(message = "Exception " + e.message))
                return@flow
            }
            emit(Result.Success(request))
        }
    }

}