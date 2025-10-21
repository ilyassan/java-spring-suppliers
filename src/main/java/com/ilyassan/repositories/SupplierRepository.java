package com.ilyassan.repositories;

import com.ilyassan.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    Optional<Supplier> findByIce(String ice);

    Optional<Supplier> findByEmail(String email);

    List<Supplier> findByCity(String city);

    List<Supplier> findByCompanyContainingIgnoreCase(String company);

    List<Supplier> findByEmailEndingWith(String domain);

    List<Supplier> findAllByOrderByCompanyAsc();

    long countByCity(String city);

    boolean existsByIce(String ice);
}
