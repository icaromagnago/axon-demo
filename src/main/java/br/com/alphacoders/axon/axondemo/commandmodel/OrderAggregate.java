package br.com.alphacoders.axon.axondemo.commandmodel;

import java.util.HashMap;
import java.util.Map;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.spring.stereotype.Aggregate;

import br.com.alphacoders.axon.axondemo.coreapi.commands.AddProductCommand;
import br.com.alphacoders.axon.axondemo.coreapi.commands.ConfirmOrderCommand;
import br.com.alphacoders.axon.axondemo.coreapi.commands.CreateOrderCommand;
import br.com.alphacoders.axon.axondemo.coreapi.commands.ShipOrderCommand;
import br.com.alphacoders.axon.axondemo.coreapi.events.OrderConfirmedEvent;
import br.com.alphacoders.axon.axondemo.coreapi.events.OrderCreatedEvent;
import br.com.alphacoders.axon.axondemo.coreapi.events.OrderShippedEvent;
import br.com.alphacoders.axon.axondemo.coreapi.events.ProductAddedEvent;
import br.com.alphacoders.axon.axondemo.coreapi.events.ProductRemovedEvent;
import br.com.alphacoders.axon.axondemo.coreapi.exceptions.DuplicateOrderLineException;
import br.com.alphacoders.axon.axondemo.coreapi.exceptions.OrderAlreadyConfirmedException;
import br.com.alphacoders.axon.axondemo.coreapi.exceptions.UnconfirmedOrderException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Aggregate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderAggregate {

  @AggregateIdentifier
  private String orderId;

  private boolean orderConfirmed;

  @AggregateMember
  private Map<String, OrderLine> orderLines;

  @CommandHandler
  public OrderAggregate(CreateOrderCommand command) {
    AggregateLifecycle.apply(new OrderCreatedEvent(command.getOrderId()));
  }

  @CommandHandler
  public void handle(ConfirmOrderCommand command) {
    if (orderConfirmed) {
      return;
    }

    AggregateLifecycle.apply(new OrderConfirmedEvent(command.getOrderId()));
  }

  @CommandHandler
  public void handle(ShipOrderCommand command) {
    if(!orderConfirmed) {
      throw new UnconfirmedOrderException();
    }

    AggregateLifecycle.apply(new OrderShippedEvent(command.getOrderId()));
  }

  @CommandHandler
  public void handle(AddProductCommand command) {
    if(orderConfirmed) {
      throw new OrderAlreadyConfirmedException(this.orderId);
    }

    if(orderLines.containsKey(command.getProductId())) {
      throw new DuplicateOrderLineException(command.getProductId());
    }

    AggregateLifecycle.apply(new ProductAddedEvent(orderId, command.getProductId()));
  }

  @EventSourcingHandler
  public void on(OrderCreatedEvent event) {
    this.orderId = event.getOrderId();
    this.orderConfirmed = false;
    this.orderLines = new HashMap<>();
  }

  @EventSourcingHandler
  public void on(OrderConfirmedEvent event) {
    this.orderConfirmed = true;
  }

  @EventSourcingHandler
  public void on(ProductAddedEvent event) {
    var productId = event.getProductId();
    this.orderLines.put(productId, new OrderLine(productId));
  }

  @EventSourcingHandler
  public void on(ProductRemovedEvent event) {
      this.orderLines.remove(event.getProductId());
  }
}
