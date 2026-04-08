package com.raka.order.vo;

import com.raka.order.model.Order;

import lombok.Data;

@Data
public class ResponseTemplate {
  Order order;
  Produk produk;

}