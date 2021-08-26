package br.com.alphacoders.axon.axondemo.coreapi.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Value;

@Value
public class ShipOrderCommand {
  
  @TargetAggregateIdentifier
  private final String orderId;
  
}
