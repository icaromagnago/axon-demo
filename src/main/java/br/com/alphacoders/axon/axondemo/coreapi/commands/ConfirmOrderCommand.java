package br.com.alphacoders.axon.axondemo.coreapi.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Value;

@Value
public class ConfirmOrderCommand {

  @TargetAggregateIdentifier
  private final String orderId;
}
