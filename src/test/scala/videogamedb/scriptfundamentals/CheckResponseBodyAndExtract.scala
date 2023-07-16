package videogamedb.scriptfundamentals

import io.gatling.http.Predef._
import io.gatling.core.Predef._

import scala.concurrent.duration.DurationInt
class CheckResponseBodyAndExtract extends Simulation {

  val httpProtocol = http.baseUrl(2"https://catfact.ninja")
    .acceptHeader("application/json")

  val scn = scenario("Check response code")

    .exec(http("Get one fact - 1")
      .get("/fact")
      .check(status.in(200, 210))
      .check(jsonPath("$.fact").is("cat says meow")))
    .pause(2)

  setUp(
    scn.inject(atOnceUsers(1))
      .protocols(httpProtocol)
  )
}
