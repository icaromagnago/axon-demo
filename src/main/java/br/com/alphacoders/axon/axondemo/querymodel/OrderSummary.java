package br.com.alphacoders.axon.axondemo.querymodel;

import java.util.HashMap;
import java.util.Map;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

//@Entity
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Getter
public class OrderSummary {
  
  //@Id
  private String id;

 // @Enumerated(EnumType.STRING)
  private OrderStatus orderStatus;

  //@ElementCollection(fetch = FetchType.EAGER)
  //@MapKeyColumn(name="product_id")
  //@Column(name="count")
  private Map<String, Integer> products;

  public OrderSummary(String orderId) {
    this.id = orderId;
    this.orderStatus = OrderStatus.CREATED;
    this.products = new HashMap<>();
  }

  public void setOrderConfirmed() {
    this.orderStatus = OrderStatus.CONFIRMED;
  }

  public void setOrderShipped() {
    this.orderStatus = OrderStatus.SHIPPED;
  }

  public void addProduct(String productId) {
    this.products.putIfAbsent(productId, 1);
  }

  public void incrementProduct(String productId) {
    this.products.computeIfPresent(productId, (id, count) -> ++count);
  }

  public void decrementProduct(String productId) {
    this.products.computeIfPresent(productId, (id, count) -> --count);
  }

  public void removeProduct(String productId) {
    this.products.remove(productId);
  }

}
