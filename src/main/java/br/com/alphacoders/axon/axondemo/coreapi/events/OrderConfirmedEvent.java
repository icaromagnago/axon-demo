package br.com.alphacoders.axon.axondemo.coreapi.events;

import lombok.Value;

@Value
public class OrderConfirmedEvent {
  private final String orderId;
}
