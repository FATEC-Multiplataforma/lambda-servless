package io.github.mrspock182.stock.repository;

import io.github.mrspock182.stock.repository.orm.OrdersOrm;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepositoryWithMySQL extends JpaRepository<OrdersOrm, String> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT o FROM orders o WHERE o.id = :id")
    Optional<OrdersOrm> findByIdForUpdate(@Param("id") String id);
}