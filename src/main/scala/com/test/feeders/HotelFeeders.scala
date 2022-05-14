package com.test.feeders

import com.test.config.TestConfiguration.{resourcesFolder}
import io.gatling.core.Predef._
import io.gatling.core.feeder.{BatchableFeederBuilder, FeederBuilderBase}
import io.gatling.jdbc.Predef._


object HotelFeeders {

  def getHotelId(): BatchableFeederBuilder[String] = {
    csv(s"$resourcesFolder/MOCK_DATA.csv").circular
  }

}
