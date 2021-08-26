package br.com.alphacoders.axon.axondemo.commandmodel;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.EntityId;

import br.com.alphacoders.axon.axondemo.coreapi.commands.DecrementProductCountCommand;
import br.com.alphacoders.axon.axondemo.coreapi.commands.IncrementProductCountCommand;
import br.com.alphacoders.axon.axondemo.coreapi.events.OrderConfirmedEvent;
import br.com.alphacoders.axon.axondemo.coreapi.events.ProductCountDecrementedEvent;
import br.com.alphacoders.axon.axondemo.coreapi.events.ProductCountIncrementedEvent;
import br.com.alphacoders.axon.axondemo.coreapi.events.ProductRemovedEvent;
import br.com.alphacoders.axon.axondemo.coreapi.exceptions.OrderAlreadyConfirmedException;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class OrderLine {
  
  @EntityId
  private final String productId;
  private Integer count;
  private boolean orderConfirmed;

  public OrderLine(String productId) {
    this.productId = productId;
    this.count = 1;
  }

  @CommandHandler
  public void handle(IncrementProductCountCommand command) {
    if(orderConfirmed) {
      throw new OrderAlreadyConfirmedException(command.getOrderId());
    }

    AggregateLifecycle.apply(new ProductCountIncrementedEvent(command.getOrderId(), productId));
  }

  @CommandHandler
  public void handle(DecrementProductCountCommand command) {
    if(orderConfirmed) {
      throw new OrderAlreadyConfirmedException(command.getOrderId());
    }

    if (count <= 1) {
      AggregateLifecycle.apply(new ProductRemovedEvent(command.getOrderId(), productId));
    } else {
      AggregateLifecycle.apply(new ProductCountDecrementedEvent(command.getOrderId(), productId));
    }
  }

  @EventSourcingHandler
  public void on(OrderConfirmedEvent event) {
      this.orderConfirmed = true;
  }

  @EventSourcingHandler
  public void on(ProductCountIncrementedEvent event) {
    this.count++;
  }

  @EventSourcingHandler 
  public void on(ProductCountDecrementedEvent event) {
    this.count--;
  }
}
