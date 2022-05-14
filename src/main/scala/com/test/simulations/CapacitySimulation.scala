package com.test.simulations

import ch.qos.logback.classic.{Level, LoggerContext}
import com.test.config.TestConfiguration._
import com.test.scenarios.{HotelScenarios}
import io.gatling.core.Predef._
import io.gatling.core.structure.{PopulationBuilder, ScenarioBuilder}
import io.gatling.http.Predef._
import org.slf4j.LoggerFactory

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

class CapacitySimulation extends Simulation {

  //FIXME: move this to config file
  val context: LoggerContext = LoggerFactory.getILoggerFactory.asInstanceOf[LoggerContext]
  context.getLogger("io.gatling.http").setLevel(Level.WARN)
  context.getLogger("io.gatling.core").setLevel(Level.WARN)

  private val baseHttpProtocol = http
    .baseUrl(s"http://$host:$port")
    .shareConnections

  // Read Operation RPS
  private val getHotelRps: Double = targetRps * 40.0/ 100
  private val listHotelsRps: Double = targetRps * 60.0/ 100


  setUp(
    HotelScenarios.getHotel().rampUpTo(getHotelRps),
    HotelScenarios.listHotels().rampUpTo(listHotelsRps),
  ).throttle(reachRps(targetRps) in(rampUpSeconds), holdFor(sustainLoadSeconds))

  implicit private class ScenarioToPopulation(scenario: ScenarioBuilder) {
    def rampUpTo(targetRps: Double): PopulationBuilder = {
      scenario.inject(
        rampUsersPerSec(1) to (targetRps) during (rampUpSeconds seconds),
        constantUsersPerSec(targetRps) during (sustainLoadSeconds seconds)
      ).protocols(baseHttpProtocol)
    }
  }

}
