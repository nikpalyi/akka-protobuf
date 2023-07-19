import com.rabbitmq.client.ConnectionFactory
import akka.stream.scaladsl.Source
import akka.actor.{ActorSystem, Props}
import akka.stream.ActorMaterializer

object Main {
  def main(args: Array[String]): Unit = {
    implicit val system: ActorSystem = ActorSystem("EventSystem")
    implicit val materializer: ActorMaterializer = ActorMaterializer()

    val factory = new ConnectionFactory()
    factory.setHost("localhost") // Set your RabbitMQ host address
    val connection = factory.newConnection()
    val channel = connection.createChannel()

    val queueName = "event_queue"
    channel.queueDeclare(queueName, false, false, false, null)

    val eventActor = system.actorOf(Props[EventActor], "EventActor")
    val consumer = new RabbitMQConsumer(channel)
    channel.basicConsume(queueName, true, consumer)

    // Set up an Akka Stream source to emit incoming messages to the EventActor
    val source: Source[EventMessage, _] = ???
    // Implement the source based on your specific requirements, like streaming the messages from RabbitMQ to Akka Stream

    // Process the messages using the EventActor
    source.runForeach { eventMessage =>
      eventActor ! eventMessage
    }
  }
}
