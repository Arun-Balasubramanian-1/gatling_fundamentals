package videogamedb.scriptfundamentals

import io.gatling.http.Predef._
import io.gatling.core.Predef._

import scala.concurrent.duration.DurationInt
class CheckResponseCode extends Simulation {

  val httpProtocol = http.baseUrl("https://catfact.ninja")
    .acceptHeader("application/json")

  val scn = scenario("Check response code")
    .exec(http("Get all facts - 1")
      .get("/facts")
      .check(status.is(200)))
    .pause(2)

    .exec(http("Get one fact - 1")
      .get("/fact")
      .check(status.in(200, 210)))
    .pause(3, 10)

    .exec(http("Get all facts - 2")
      .get("/facts")
      .check(status.not(500), status.not(400)))
    .pause(2000.milliseconds)

    .exec(http("Get one fact - 2")
      .get("/fact"))

  setUp(
    scn.inject(atOnceUsers(1))
      .protocols(httpProtocol)
  )
}
