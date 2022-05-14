package com.test

import com.test.config.TestConfiguration
import com.test.simulations.CapacitySimulation
import io.gatling.app.Gatling
import io.gatling.core.config.GatlingPropertiesBuilder

object GatlingRunner {

  def main(args: Array[String]) {
    val config = TestConfiguration
    val simClass = classOf[CapacitySimulation].getName
    val props = new GatlingPropertiesBuilder
    props.binariesDirectory("./target/scala-2.11/classes")
    props.simulationClass(simClass)
    Gatling.fromMap(props.build)
  }


}
