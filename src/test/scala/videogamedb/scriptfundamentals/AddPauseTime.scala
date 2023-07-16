package videogamedb.scriptfundamentals

import io.gatling.http.Predef._
import io.gatling.core.Predef._

import scala.concurrent.duration.DurationInt

class AddPauseTime extends Simulation {

  val httpProtocol = http.baseUrl("https://catfact.ninja")
    .acceptHeader("application/json")

  val scn = scenario("Get Facts")
    .exec(http("Get All facts")
      .get("/facts"))
    .pause(2)

    .exec(http("Get one fact")
      .get("/fact"))
    .pause(3, 10)

    .exec(http("Get all facts")
      .get("/facts"))
    .pause(2000.milliseconds)

    .exec(http("Get one fact")
      .get("/fact"))

  setUp(
    scn.inject(atOnceUsers(1))
    .protocols(httpProtocol)
  )
}
