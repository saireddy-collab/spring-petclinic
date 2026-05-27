package org.springframework.samples.petclinic.a.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.samples.petclinic.a.Product;
import org.springframework.stereotype.Repository;

/**
 * Purpose: Provides data access operations for {@link Product} entities.
 * How to use: Extend JpaRepository to get standard CRUD operations without boilerplate code.
 * This interface is automatically discovered by Spring Data JPA.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}