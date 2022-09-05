package com.alfayedoficial.coreNetwork.interactor

import com.alfayedoficial.coreModel.apiResponse.asteroidImgesResponse.AsteroidImagesResponse
import com.alfayedoficial.coreModel.apiResponse.asteroidResponses.AsteroidResponse
import com.alfayedoficial.coreModel.dataState.DataState
import com.alfayedoficial.coreModel.dataState.Failure
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException


/**
 * Created by ( Eng Ali Al Fayed)
 * Class do : Interactor Network Call
 * Date 6/12/2021 - 3:08 PM
 */
object NetworkCall {

  private fun getHandelLocateMsg(code: Int, lang : String):String{
    return when(code){
      0 ->{
        if (lang == "ar"){
          "فقد الاتصال بالسيرفر نتيجة قطع الاتصال بالانترنت , الرجاء المعاودة مرة أخري"
        }else{
          "The connection to the server has been lost due to a disconnection from the Internet, please try again"
        }
      }
      400 ->{
        if (lang == "ar"){
          "يتعذر على السيرفر معالجة الطلب أو لا يقوم بذلك بسبب شيء يُنظر إليه على أنه خطأ من قِبل العميل"
        }else{
          "The server cannot or will not process the request due to something that is perceived to be a client error"
        }
      }
      401 ->{
        if (lang == "ar"){
          "لم يتم إكمال طلب العميل لأنه يفتقر إلى بيانات اعتماد مصادقة صالحة للمورد المطلوب."
        }else{
          "The client request has not been completed because it lacks valid authentication credentials for the requested resource."
        }
      }
      403 ->{
        if (lang == "ar"){
          "السيرفر يفهم الطلب لكنه يرفض الإذن به."
        }else{
          "That the server understands the request but refuses to authorize it."
        }
      }
      404 ->{
        if (lang == "ar"){
          "لا يمكن للسيرفر العثور على المورد المطلوب"
        }else{
          "The server cannot find the requested resource"
        }
      }
      405 ->{
        if (lang == "ar"){
          "السيرفر لا يعرف طريقةالطلب"
        }else{
          "That the server knows the request method"
        }
      }
      422 ->{
        if (lang == "ar"){
          "لا يفهم السيرفر نوع المحتوى لكيان الطلب ، وصيغة كيان الطلب غير صحيحة"
        }else{
          "That the server understands the content type of the request entity, and the syntax of the request entity is correct"
        }
      }
      500 ->{
        if (lang == "ar"){
          "واجه السيرفر حالة غير متوقعة منعته من تلبية الطلب."
        }else{
          "The server encountered an unexpected condition that prevented it from fulfilling the request."
        }
      }
      else ->{
        if (lang == "ar"){
          "حدث خطأ غير متوقع عند الاتصال بالسيرفر"
        }else{
          "An unexpected error occurred when connecting to the server"
        }
      }
    }
  }


  fun handleAsteroidListApi( lang : String,execute: suspend () -> Response<AsteroidResponse>) = flow {
    emit(DataState.IsLoading(status = true))
    try {
      val response = execute()
      val body = response.body()
      if (response.isSuccessful && body != null) {
        emit(DataState.ApiSuccess(data = body, statusCode =200 , success = response.isSuccessful ))
      } else {
        val type = object : TypeToken<AsteroidResponse>() {}.type
        val error = Gson().fromJson<AsteroidResponse>(response.errorBody()?.charStream(), type)
        when(response.code()){
          400 -> emit(DataState.ApiError(Failure.ServerError.BadRequest(code = 400 , message = getHandelLocateMsg(400 , lang) ,errorBody = response.errorBody() )))
          401 -> emit(DataState.ApiError(Failure.ServerError.Unauthorized(code = 401 , message = getHandelLocateMsg(401 , lang),errorBody = response.errorBody())))
          403 -> emit(DataState.ApiError(Failure.ServerError.Forbidden(code = 403 , message = getHandelLocateMsg(403 , lang),errorBody = response.errorBody())))
          404 -> emit(DataState.ApiError(Failure.ServerError.NotFound(code = 404 , message = getHandelLocateMsg(404 , lang),errorBody = response.errorBody())))
          405 -> emit(DataState.ApiError(Failure.ServerError.MethodNotAllowed(code = 405 , message = getHandelLocateMsg(405 , lang),errorBody = response.errorBody())))
          422 -> emit(DataState.ApiError(Failure.ServerError.UnProcessableEntity(code = 422 , message = getHandelLocateMsg(422 , lang),errorBody = response.errorBody())))
          500 -> emit(DataState.ApiError(Failure.ServerError.InternalServerError(code = 500 , message = getHandelLocateMsg(500 , lang),errorBody= response.errorBody())))
          else -> emit(DataState.ApiError(Failure.ServerError.Unknown(code = 1 , message = getHandelLocateMsg(1 , lang),errorBody = response.errorBody())))
        }
      }
    }catch (e: SocketTimeoutException) {
      e.printStackTrace()
      emit(DataState.ApiError(Failure.NetworkConnection(code = 0 , message = getHandelLocateMsg(0 , lang))))
    } catch (e: ConnectException) {
      e.printStackTrace()
      emit(DataState.ApiError(Failure.NetworkConnection(code = 0 , message = getHandelLocateMsg(0 , lang))))
    } catch (e: UnknownHostException) {
      e.printStackTrace()
      emit(DataState.ApiError(Failure.NetworkConnection(code = 0 , message = getHandelLocateMsg(0 , lang))))
    } catch (e : HttpException){
      e.printStackTrace()
      when(e.code()){
        400 -> emit(DataState.ApiError(Failure.ServerError.BadRequest(code = 400 , message = getHandelLocateMsg(400 , lang))))
        401 -> emit(DataState.ApiError(Failure.ServerError.Unauthorized(code = 401 , message = getHandelLocateMsg(401 , lang))))
        403 -> emit(DataState.ApiError(Failure.ServerError.Forbidden(code = 403 , message = getHandelLocateMsg(403 , lang))))
        404 -> emit(DataState.ApiError(Failure.ServerError.NotFound(code = 404 , message = getHandelLocateMsg(404 , lang))))
        405 -> emit(DataState.ApiError(Failure.ServerError.MethodNotAllowed(code = 405 , message = getHandelLocateMsg(405 , lang))))
        422 -> emit(DataState.ApiError(Failure.ServerError.UnProcessableEntity(code = 422 , message = getHandelLocateMsg(422 , lang))))
        500 -> emit(DataState.ApiError(Failure.ServerError.InternalServerError(code = 500 , message = getHandelLocateMsg(500 , lang))))
        else -> emit(DataState.ApiError(Failure.ServerError.Unknown(code = 1 , message = getHandelLocateMsg(1 , lang))))
      }
    }catch (e : Throwable){
      emit(DataState.ApiError(Failure.ThrowableFailure(message = e.message)))
    }
  }

  fun handleAsteroidImageApi( lang : String,execute: suspend () -> Response<AsteroidImagesResponse>) = flow {
    emit(DataState.IsLoading(status = true))
    try {
      val response = execute()
      val body = response.body()
      if (response.isSuccessful && body != null) {
        emit(DataState.ApiSuccess(data = body, statusCode =200 , success = response.isSuccessful ))
      } else {
        val type = object : TypeToken<AsteroidImagesResponse>() {}.type
        val error = Gson().fromJson<AsteroidImagesResponse>(response.errorBody()?.charStream(), type)
        when(response.code()){
          400 -> emit(DataState.ApiError(Failure.ServerError.BadRequest(code = 400 , message = getHandelLocateMsg(400 , lang) ,errorBody = response.errorBody() )))
          401 -> emit(DataState.ApiError(Failure.ServerError.Unauthorized(code = 401 , message = getHandelLocateMsg(401 , lang),errorBody = response.errorBody())))
          403 -> emit(DataState.ApiError(Failure.ServerError.Forbidden(code = 403 , message = getHandelLocateMsg(403 , lang),errorBody = response.errorBody())))
          404 -> emit(DataState.ApiError(Failure.ServerError.NotFound(code = 404 , message = getHandelLocateMsg(404 , lang),errorBody = response.errorBody())))
          405 -> emit(DataState.ApiError(Failure.ServerError.MethodNotAllowed(code = 405 , message = getHandelLocateMsg(405 , lang),errorBody = response.errorBody())))
          422 -> emit(DataState.ApiError(Failure.ServerError.UnProcessableEntity(code = 422 , message = getHandelLocateMsg(422 , lang),errorBody = response.errorBody())))
          500 -> emit(DataState.ApiError(Failure.ServerError.InternalServerError(code = 500 , message = getHandelLocateMsg(500 , lang),errorBody= response.errorBody())))
          else -> emit(DataState.ApiError(Failure.ServerError.Unknown(code = 1 , message = getHandelLocateMsg(1 , lang),errorBody = response.errorBody())))
        }
      }
    }catch (e: SocketTimeoutException) {
      e.printStackTrace()
      emit(DataState.ApiError(Failure.NetworkConnection(code = 0 , message = getHandelLocateMsg(0 , lang))))
    } catch (e: ConnectException) {
      e.printStackTrace()
      emit(DataState.ApiError(Failure.NetworkConnection(code = 0 , message = getHandelLocateMsg(0 , lang))))
    } catch (e: UnknownHostException) {
      e.printStackTrace()
      emit(DataState.ApiError(Failure.NetworkConnection(code = 0 , message = getHandelLocateMsg(0 , lang))))
    } catch (e : HttpException){
      e.printStackTrace()
      when(e.code()){
        400 -> emit(DataState.ApiError(Failure.ServerError.BadRequest(code = 400 , message = getHandelLocateMsg(400 , lang))))
        401 -> emit(DataState.ApiError(Failure.ServerError.Unauthorized(code = 401 , message = getHandelLocateMsg(401 , lang))))
        403 -> emit(DataState.ApiError(Failure.ServerError.Forbidden(code = 403 , message = getHandelLocateMsg(403 , lang))))
        404 -> emit(DataState.ApiError(Failure.ServerError.NotFound(code = 404 , message = getHandelLocateMsg(404 , lang))))
        405 -> emit(DataState.ApiError(Failure.ServerError.MethodNotAllowed(code = 405 , message = getHandelLocateMsg(405 , lang))))
        422 -> emit(DataState.ApiError(Failure.ServerError.UnProcessableEntity(code = 422 , message = getHandelLocateMsg(422 , lang))))
        500 -> emit(DataState.ApiError(Failure.ServerError.InternalServerError(code = 500 , message = getHandelLocateMsg(500 , lang))))
        else -> emit(DataState.ApiError(Failure.ServerError.Unknown(code = 1 , message = getHandelLocateMsg(1 , lang))))
      }
    }catch (e : Throwable){
      emit(DataState.ApiError(Failure.ThrowableFailure(message = e.message)))
    }
  }


}