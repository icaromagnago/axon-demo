package br.com.alphacoders.axon.axondemo.coreapi.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Value;

@Value
public class IncrementProductCountCommand {
  
  @TargetAggregateIdentifier
  private final String orderId;
  private final String productId;
}
