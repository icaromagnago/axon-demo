package br.com.alphacoders.axon.axondemo.coreapi.events;

import lombok.Value;

@Value
public class OrderCreatedEvent {
  
  private final String orderId;
  
}
