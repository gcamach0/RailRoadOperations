package com.rroperations.controllers

import com.example.railRoadOperations.models.ClassificationTrack
import com.example.railRoadOperations.models.Train
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

@MicronautTest
class TrainController {

    @Inject
    @field:Client("/")
    lateinit var client : HttpClient

    private val trains: ArrayList<Train> = arrayListOf(
            Train("Box 1", "Houston", "UPS"),
            Train("Box 2", "LA", "UPS"),
            Train("Box 3", "Houston", "FedEx"),
            Train("Box 4", "Chicago", "Old Dominion"),
            Train("Box 5", "Chicago", "UPS")
    )

    private val classificationTracks: ArrayList<ClassificationTrack> = arrayListOf(
            ClassificationTrack(1,"Box 1", "Houston", "UPS"),
            ClassificationTrack(2, "Box 3", "Houston", "FedEx"),
            ClassificationTrack(3, "Box 5", "Chicago", "UPS"),
            ClassificationTrack(4, "Box 4", "Chicago", "Old Dominion"),
            ClassificationTrack(5, "Box 2", "LA", "UPS")
    )

    @Test
    fun testHello() {
        val request: HttpRequest<Any> = HttpRequest.POST("trains/railroadoperations", trains)
        val body = client.toBlocking().retrieve(request)
        assertNotNull(body)
        assertEquals(classificationTracks, body)
    }
}