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

    .exec(http("Get list of videogame")
      .get("/videogame")
      .check(status.in(200, 210))
      //Get ID of the second videogame in the list response
      .check(jsonPath("$[1].id").saveAs("id")))
    .pause(2)

    .exec(http("Get first videogame")
      .get("/videogame/#{id}")
      .check(status.in(200, 210))
      .check(jsonPath("$.name").is("Gran Turismo 3")))

  setUp(
    scn.inject(atOnceUsers(1))
      .protocols(httpProtocol)
  )
}
