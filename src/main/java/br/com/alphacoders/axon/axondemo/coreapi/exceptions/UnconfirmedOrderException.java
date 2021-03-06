package br.com.alphacoders.axon.axondemo.coreapi.exceptions;

public class UnconfirmedOrderException extends IllegalStateException {
  
  public UnconfirmedOrderException() {
    super("Cannot ship an order which has not been confirmed yet.");
  }
}
