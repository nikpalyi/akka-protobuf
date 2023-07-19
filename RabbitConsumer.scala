import com.rabbitmq.client._
import java.nio.charset.StandardCharsets

class RabbitMQConsumer(channel: Channel) extends DefaultConsumer(channel) {
  override def handleDelivery(consumerTag: String, envelope: Envelope, properties: AMQP.BasicProperties, body: Array[Byte]): Unit = {
    val message = EventMessage.parseFrom(body)
    // Process the received message or send it to Akka Stream event actor
    // For simplicity, let's print the received message
    println(s"Received event: ${message.eventName}, EventId: ${message.eventId}, Priority: ${message.eventPriority}")
  }
}
