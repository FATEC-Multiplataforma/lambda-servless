package io.github.mrspock182.stock.repository;

import io.github.mrspock182.stock.repository.orm.ProductOrm;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepositoryWithMySQL extends JpaRepository<ProductOrm, String> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT p FROM product p WHERE p.id = :id")
    Optional<ProductOrm> findByIdForUpdate(@Param("id") String id);
}
