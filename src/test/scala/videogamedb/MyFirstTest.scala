package videogamedb

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class MyFirstTest extends Simulation {

  // 1. HTTP CONFIGURATION
  val httpProtocol = http.baseUrl("https://catfact.ninja")
    .acceptHeader("application/json")


  // 2. SCENARIO CONFIGURATION
  val scn = scenario("Get list of facts and a fact")
    .exec(http("GET ALL FACTS")
    .get("/facts"))
    .exec(http("GET one FACT")
    .get("/fact"))

  // 3. LOAD SCENARIO
  setUp(
    scn.inject(atOnceUsers(1))
      .protocols(httpProtocol)
  )

}
