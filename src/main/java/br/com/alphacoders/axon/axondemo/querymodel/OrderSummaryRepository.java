package br.com.alphacoders.axon.axondemo.querymodel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderSummaryRepository extends JpaRepository<OrderSummary, String> {
  
}
