package com.test.scenarios

import com.test.feeders.HotelFeeders
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._

object HotelScenarios {

  def listHotels(): ScenarioBuilder = {
    scenario("listHotels 200")
      .exec(http("listHotels200")
        .get("/hotels")
        .check(status is 200)
      )
  }

  def getHotel(): ScenarioBuilder = {
    val feeder = HotelFeeders.getHotelId()
    scenario("getHotel 200")
      .feed(feeder)
      .exec(http("getHotel200")
        .get("/hotels/${id}")
        .check(status is 200))
  }

}
