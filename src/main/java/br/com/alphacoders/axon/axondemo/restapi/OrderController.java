package br.com.alphacoders.axon.axondemo.restapi;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alphacoders.axon.axondemo.coreapi.commands.AddProductCommand;
import br.com.alphacoders.axon.axondemo.coreapi.commands.ConfirmOrderCommand;
import br.com.alphacoders.axon.axondemo.coreapi.commands.CreateOrderCommand;
import br.com.alphacoders.axon.axondemo.coreapi.commands.DecrementProductCountCommand;
import br.com.alphacoders.axon.axondemo.coreapi.commands.IncrementProductCountCommand;
import br.com.alphacoders.axon.axondemo.coreapi.commands.ShipOrderCommand;
import br.com.alphacoders.axon.axondemo.coreapi.queries.FindAllOrders;
import br.com.alphacoders.axon.axondemo.querymodel.OrderSummary;

@RestController
@RequestMapping("/orders")
public class OrderController {

  private final CommandGateway commandGateway;
  private final QueryGateway queryGateway;

  public OrderController(CommandGateway commandGateway, QueryGateway queryGateway) {
    this.commandGateway = commandGateway;
    this.queryGateway = queryGateway;
  }

  @PostMapping
  public CompletableFuture<Void> createOrder() {
    return commandGateway.send(new CreateOrderCommand(UUID.randomUUID().toString()));
  }

  @PutMapping("/{orderId}/confirm")
  public CompletableFuture<Void> confirmOrder(@PathVariable String orderId) {
    return commandGateway.send(new ConfirmOrderCommand(orderId));
  }

  @PutMapping("/{orderId}/ship")
  public CompletableFuture<Void> shipOrder(@PathVariable String orderId) {
    return commandGateway.send(new ShipOrderCommand(orderId));
  }

  @GetMapping
  public CompletableFuture<List<OrderSummary>> findAllOrders() {
    return queryGateway.query(new FindAllOrders(), ResponseTypes.multipleInstancesOf(OrderSummary.class));
  }

  @PostMapping("/{orderId}/products/{productId}")
  public CompletableFuture<Void> addProduct(@PathVariable String orderId, @PathVariable String productId) {

    return commandGateway.send(new AddProductCommand(orderId, productId));
  }

  @PostMapping("/{orderId}/products/{productId}/increment")
  public CompletableFuture<Void> incrementProduct(@PathVariable String orderId, @PathVariable String productId) {

    return commandGateway.send(new IncrementProductCountCommand(orderId, productId));
  }

  @PostMapping("/{orderId}/products/{productId}/decrement")
  public CompletableFuture<Void> decrementProduct(@PathVariable("orderId") String orderId, @PathVariable("productId") String productId) {
      return commandGateway.send(new DecrementProductCountCommand(orderId, productId));
  }
  
}
