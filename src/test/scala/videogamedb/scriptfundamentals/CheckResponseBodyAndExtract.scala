package videogamedb.scriptfundamentals

import io.gatling.http.Predef._
import io.gatling.core.Predef._

import scala.concurrent.duration.DurationInt
class CheckResponseBodyAndExtract extends Simulation {

  val httpProtocol = http.baseUrl("https://videogamedb.uk/api")
    .acceptHeader("application/json")

  val scn = scenario("Check response code")

    .exec(http("Get first videogame")
      .get("/videogame/1")
      .check(status.in(200, 210))
      .check(jsonPath("$.name").is("Resident Evil 4")))
    .pause(2)

  setUp(
    scn.inject(atOnceUsers(1))
      .protocols(httpProtocol)
  )
}
