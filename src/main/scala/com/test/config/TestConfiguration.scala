package com.test.config

object TestConfiguration {

  val host: String = System.getProperty("host", "localhost")
  val port: String = System.getProperty("port", "8085")

  val targetRps: Int = Integer.getInteger("targetRps", 20)
  val rampUpSeconds: Int = Integer.getInteger("rampUpSeconds", 180) // 3 mins
  val sustainLoadSeconds: Int = Integer.getInteger("sustainLoadSeconds", 600) // 10 mins

  val resourcesFolder: String = System.getProperty("resourcesFolder", "data")


}
