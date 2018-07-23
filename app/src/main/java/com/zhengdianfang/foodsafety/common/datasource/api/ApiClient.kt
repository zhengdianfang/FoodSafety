package com.zhengdianfang.foodsafety.common.datasource.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiClient {
    companion object {
        val CLIENT: Retrofit by lazy {
            Retrofit.Builder()
                    .baseUrl("http://4f2bb68a-915a-493e-8fa1-d9e89195faec.mock.pstmn.io")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
    }

//    internal class LiveDataCallFactory : CallAdapter.Factory() {
//        companion object {
//            fun create(): LiveDataCallFactory {
//                return LiveDataCallFactory()
//            }
//        }
//        override fun get(returnType: Type?, annotations: Array<out Annotation>?, retrofit: Retrofit?): CallAdapter<*, *>? {
//            if (returnType != LiveData::class.java) {
//                return null
//            }
//            val responseType = getCallResponseType(returnType)
//            return object : CallAdapter<Any, LiveData<Response<*>>> {
//                override fun adapt(call: Call<Any>?): LiveData<Response<*>> {
//                    val liveDataResponse = MutableLiveData<Response<*>>()
//                    val response = call?.execute()
//                    if (response != null) {
//                        if(response.isSuccessful) {
//                            liveDataResponse.postValue(Response.success(response.body()))
//                        }
//                        liveDataResponse.postValue(Response.error(response.code(), response.errorBody()))
//                    }
//                    return liveDataResponse
//                }
//
//                override fun responseType(): Type {
//                    return responseType
//                }
//
//            }
//        }
//
//        private fun getCallResponseType(returnType: Type): Type {
//            if (returnType !is ParameterizedType) {
//                throw IllegalArgumentException(
//                        "Call return type must be parameterized as Call<Foo> or Call<? extends Foo>")
//            }
//            return getParameterUpperBound(0, returnType)
//        }
//
//    }
//
//    internal class LiveDataResponseBodyConverterFactory private constructor() : Converter.Factory() {
//        companion object {
//
//            fun create(): LiveDataResponseBodyConverterFactory {
//                return LiveDataResponseBodyConverterFactory()
//            }
//
//        }
//
//        override fun responseBodyConverter(type: Type, annotations: Array<Annotation>,
//                                           retrofit: Retrofit): Converter<ResponseBody, *> {
//            if (type is ParameterizedType) {
//                var parameterizedType = type
//                if (parameterizedType.rawType === Response::class.java) {
//                    val subType = parameterizedType.actualTypeArguments[0]
//                    if (subType is ParameterizedType) {
//                        parameterizedType = parameterizedType.actualTypeArguments[0] as ParameterizedType
//                    }
//                }
//
//                if (parameterizedType.rawType === LiveData::class.java) {
//                    val realType = parameterizedType.actualTypeArguments[0]
//                    return retrofit.nextResponseBodyConverter(this, realType, annotations)
//                }
//            }
//            return retrofit.nextResponseBodyConverter(this, type, annotations)
//        }
//
//    }
}