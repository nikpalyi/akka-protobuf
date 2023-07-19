import akka.actor.Actor

class EventActor extends Actor {
  def receive: Receive = {
    case eventMessage: EventMessage =>
      // Process the eventMessage as needed
      println(s"Processing event: ${eventMessage.eventName}, EventId: ${eventMessage.eventId}, Priority: ${eventMessage.eventPriority}")
  }
}
