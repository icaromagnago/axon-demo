package br.com.alphacoders.axon.axondemo.coreapi.exceptions;

public class OrderNotFoundException extends Exception {
  public OrderNotFoundException() {
    super("Order Not Found");
  }
}
