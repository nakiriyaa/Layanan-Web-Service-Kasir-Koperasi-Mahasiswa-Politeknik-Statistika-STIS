package com.polstatstis.kopma.koperasi_kasir.repository;

import com.polstatstis.kopma.koperasi_kasir.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

//@RepositoryRestResource(collectionResourceRel = "products", path = "products")
//pakai ini kalau mau gak ribet bikin mapping (post, get, dkk)

public interface ProductRepository extends JpaRepository<Product, Long> {
    //    findProductByNameOrID
    Optional<Product> findByName(String name);
}
