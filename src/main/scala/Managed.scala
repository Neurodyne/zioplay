package hello

import zio.{ App, ZIO, ZManaged }
import zio.console.{ getStrLn, putStrLn, Console }

object Managed extends App {

  def run(args: List[String]) =
    myAppLogic.fold(_ => 1, _ => 0)

  val zManagedResource: ZManaged[Console, Nothing, Unit] =
    ZManaged.make(putStrLn("acquiring"))(_ => putStrLn("releasing"))
  val zUsedResource: ZIO[Console, Nothing, Unit] = zManagedResource.use { _ =>
    putStrLn("running")
  }

  val myAppLogic = zUsedResource

}
