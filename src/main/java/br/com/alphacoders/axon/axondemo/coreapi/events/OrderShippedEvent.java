package br.com.alphacoders.axon.axondemo.coreapi.events;

import lombok.Value;

@Value
public class OrderShippedEvent {
  private final String orderId;
}
