package br.com.alphacoders.axon.axondemo.commandmodel;

import java.util.UUID;

import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.alphacoders.axon.axondemo.coreapi.commands.CreateOrderCommand;
import br.com.alphacoders.axon.axondemo.coreapi.commands.ShipOrderCommand;
import br.com.alphacoders.axon.axondemo.coreapi.events.OrderConfirmedEvent;
import br.com.alphacoders.axon.axondemo.coreapi.events.OrderCreatedEvent;
import br.com.alphacoders.axon.axondemo.coreapi.events.OrderShippedEvent;
import br.com.alphacoders.axon.axondemo.coreapi.exceptions.UnconfirmedOrderException;

public class OrderAggregateUnitTest {

  private static final String ORDER_ID = UUID.randomUUID().toString();
  
  private FixtureConfiguration<OrderAggregate> fixture;

  @BeforeEach
  void setup() {
    fixture = new AggregateTestFixture<>(OrderAggregate.class);
  }
  
  @Test
  void giveNoPriorActivity_whenCreateOrderCommand_thenShouldPublishOrderCreatedEvent() {
    fixture.givenNoPriorActivity()
      .when(new CreateOrderCommand(ORDER_ID))
      .expectEvents(new OrderCreatedEvent(ORDER_ID));
  }

  @Test
  void givenOrderCreatedEvent_whenShipOrderCommand_thenShouldThrowUnconfirmedOrderException() {
    fixture.given(new OrderCreatedEvent(ORDER_ID))
      .when(new ShipOrderCommand(ORDER_ID))
      .expectException(UnconfirmedOrderException.class);
  }

  @Test
  void givenOrderCreatedEventAndOrderConfirmedEvent_whenShipOrderCommand_thenShouldPublishOrderShippedEvent() {
    fixture.given(new OrderCreatedEvent(ORDER_ID), new OrderConfirmedEvent(ORDER_ID))
      .when(new ShipOrderCommand(ORDER_ID))
      .expectEvents(new OrderShippedEvent(ORDER_ID));
  }
}
