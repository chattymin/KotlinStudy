package com.chattymin.bottomsheetwithwebview

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface NaverMapApi {
    /**   https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode
     * ?query=분당구 불정로 6
     * &X-NCP-APIGW-API-KEY-ID=클라이언트 아이디
     * &X-NCP-APIGW-API-KEY=클라이언트 시크릿
     */
    /**
     * curl -G "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode" \
     * --data-urlencode "query=분당구 불정로 6" \
     * --data-urlencode "coordinate=127.1054328,37.3595963" \
     * -H "X-NCP-APIGW-API-KEY-ID: {애플리케이션 등록 시 발급받은 client id값}" \
     * -H "X-NCP-APIGW-API-KEY: {애플리케이션 등록 시 발급받은 client secret값}" -v
     *
     */
    @Headers("X-NCP-APIGW-API-KEY-ID: bau2wg3fg6 ", "X-NCP-APIGW-API-KEY: NnsZcNODce3Z8U3oQbqpOTGIlxGm8bgsBhxH1DAt ")
    @GET("/map-geocode/v2/geocode")
    fun searchAddress(@Query("query") query: String?): Call<AddressResponse?>?
}