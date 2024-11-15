package com.polstatstis.kopma.koperasi_kasir.repository;

import com.polstatstis.kopma.koperasi_kasir.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
