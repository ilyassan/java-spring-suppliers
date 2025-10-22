package com.ilyassan.service;

import com.ilyassan.models.Supplier;

import java.util.List;
import java.util.Optional;

public interface SupplierService {

    Supplier saveSupplier(Supplier supplier);

    List<Supplier> getAllSuppliers();

    List<Supplier> getAllSuppliersSortedByName();

    Optional<Supplier> getSupplierById(Long id);

    Optional<Supplier> getSupplierByIce(String ice);

    Optional<Supplier> getSupplierByEmail(String email);

    List<Supplier> getSuppliersByCity(String city);

    List<Supplier> searchSuppliersByCompany(String company);

    Supplier updateSupplier(Long id, Supplier supplier);

    void deleteSupplier(Long id);

    long countSuppliers();

    boolean existsById(Long id);
}
