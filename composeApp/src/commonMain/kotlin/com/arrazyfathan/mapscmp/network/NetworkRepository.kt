package com.arrazyfathan.mapscmp.network

import com.arrazyfathan.mapscmp.network.response.ReqresResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class NetworkRepository {

    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }

        install(Logging) {
            level = LogLevel.ALL
        }
    }

    suspend fun getUsers(): ReqresResponse {
        return httpClient.get("https://reqres.in/api/users?page=1").body<ReqresResponse>()
    }
}