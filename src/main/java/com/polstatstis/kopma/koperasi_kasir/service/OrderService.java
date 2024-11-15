package com.polstatstis.kopma.koperasi_kasir.service;

import com.polstatstis.kopma.koperasi_kasir.dto.OrderDto;
import com.polstatstis.kopma.koperasi_kasir.dto.OrderItemDto;
import com.polstatstis.kopma.koperasi_kasir.entity.Order;
import com.polstatstis.kopma.koperasi_kasir.entity.OrderItem;
import com.polstatstis.kopma.koperasi_kasir.entity.Product;
import com.polstatstis.kopma.koperasi_kasir.exception.CustomException;
import com.polstatstis.kopma.koperasi_kasir.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Order saveOrder(OrderDto orderDto) {
        Order order = new Order(); // Instansiasi Order di awal
        double totalPrice = 0;
        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemDto itemDto : orderDto.getItems()) {
            Product product = productService.getProductByNameOrId(itemDto.getProductName());
            if (product == null) {
                throw new CustomException("Produk tidak ditemukan: " + itemDto.getProductName());
            }
            if (product.getStock() < itemDto.getQuantity()) {
                throw new CustomException("Stok produk tidak mencukupi: " + product.getName());
            }
            OrderItem item = new OrderItem();
            item.setProduct(product);
            item.setQuantity(itemDto.getQuantity());
            item.setName(itemDto.getProductName());
            item.setOrder(order); // Mengatur Order di sini

            orderItems.add(item);
            totalPrice += product.getPrice() * itemDto.getQuantity();

            // Update stock
            productService.updateStock(product.getId(), itemDto.getQuantity());
        }

        if (orderDto.getPayment() < totalPrice) {
            throw new CustomException("Payment tidak boleh kurand dari total price. Total price : " + totalPrice);
        }



        order.setItems(orderItems); // Menambahkan items ke Order
        order.setTotalPrice(totalPrice);
        order.setPayment(orderDto.getPayment());
        order.setChangeAmount(orderDto.getPayment() - totalPrice);

        return orderRepository.save(order);
    }

}
