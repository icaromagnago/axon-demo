package br.com.alphacoders.axon.axondemo.querymodel;

import java.util.List;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import br.com.alphacoders.axon.axondemo.coreapi.events.OrderConfirmedEvent;
import br.com.alphacoders.axon.axondemo.coreapi.events.OrderCreatedEvent;
import br.com.alphacoders.axon.axondemo.coreapi.events.OrderShippedEvent;
import br.com.alphacoders.axon.axondemo.coreapi.events.ProductAddedEvent;
import br.com.alphacoders.axon.axondemo.coreapi.events.ProductCountDecrementedEvent;
import br.com.alphacoders.axon.axondemo.coreapi.events.ProductCountIncrementedEvent;
import br.com.alphacoders.axon.axondemo.coreapi.events.ProductRemovedEvent;
import br.com.alphacoders.axon.axondemo.coreapi.exceptions.OrderNotFoundException;
import br.com.alphacoders.axon.axondemo.coreapi.queries.FindAllOrders;

@Component
public class OrderSummaryProjection {
  
//  private OrderSummaryRepository orderSummaryRepository;
//
//  public OrderSummaryProjection(OrderSummaryRepository orderSummaryRepository) {
//    this.orderSummaryRepository = orderSummaryRepository;
//  }

//  @EventHandler
//  public void on(OrderCreatedEvent event) {
//    orderSummaryRepository.save(new OrderSummary(event.getOrderId()));
//  }
//
//  @EventHandler
//  public void on(OrderConfirmedEvent event) throws OrderNotFoundException {
//    OrderSummary orderSummary = orderSummaryRepository
//      .findById(event.getOrderId())
//      .orElseThrow(() -> new OrderNotFoundException());
//
//    orderSummary.setOrderConfirmed();
//  }
//
//  @EventHandler
//  public void on(OrderShippedEvent event) throws OrderNotFoundException {
//    OrderSummary orderSummary = orderSummaryRepository
//      .findById(event.getOrderId())
//      .orElseThrow(() -> new OrderNotFoundException());
//
//      orderSummary.setOrderShipped();
//  }
//
//  @EventHandler
//  public void on(ProductAddedEvent event) throws OrderNotFoundException {
//    OrderSummary orderSummary = orderSummaryRepository
//      .findById(event.getOrderId())
//      .orElseThrow(() -> new OrderNotFoundException());
//
//      orderSummary.addProduct(event.getProductId());
//  }
//
//  @EventHandler
//  public void on(ProductCountIncrementedEvent event) throws OrderNotFoundException {
//    OrderSummary orderSummary = orderSummaryRepository
//      .findById(event.getOrderId())
//      .orElseThrow(() -> new OrderNotFoundException());
//
//      orderSummary.incrementProduct(event.getProductId());
//  }
//
//  
//  @EventHandler
//  public void on(ProductCountDecrementedEvent event) throws OrderNotFoundException {
//    OrderSummary orderSummary = orderSummaryRepository
//      .findById(event.getOrderId())
//      .orElseThrow(() -> new OrderNotFoundException());
//
//      orderSummary.decrementProduct(event.getProductId());
//  }
//
//  
//  @EventHandler
//  public void on(ProductRemovedEvent event) throws OrderNotFoundException {
//    OrderSummary orderSummary = orderSummaryRepository
//      .findById(event.getOrderId())
//      .orElseThrow(() -> new OrderNotFoundException());
//
//      orderSummary.removeProduct(event.getProductId());
//  }
//
//  @QueryHandler
//  public List<OrderSummary> handle(FindAllOrders findAllOrders) {
//    return orderSummaryRepository.findAll();
//  }
}
