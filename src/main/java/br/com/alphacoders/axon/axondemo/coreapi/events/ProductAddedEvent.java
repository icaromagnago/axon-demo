package br.com.alphacoders.axon.axondemo.coreapi.events;

import lombok.Value;

@Value
public class ProductAddedEvent {
  private final String orderId;
  private final String productId;
}
