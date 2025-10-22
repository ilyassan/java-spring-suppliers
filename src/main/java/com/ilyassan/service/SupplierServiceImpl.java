package com.ilyassan.service;

import com.ilyassan.models.Supplier;
import com.ilyassan.repositories.SupplierRepository;

import java.util.List;
import java.util.Optional;

public class SupplierServiceImpl implements SupplierService {

    private SupplierRepository supplierRepository;

    public void setSupplierRepository(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public Supplier saveSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    @Override
    public List<Supplier> getAllSuppliersSortedByName() {
        return supplierRepository.findAllByOrderByCompanyAsc();
    }

    @Override
    public Optional<Supplier> getSupplierById(Long id) {
        return supplierRepository.findById(id);
    }

    @Override
    public Optional<Supplier> getSupplierByIce(String ice) {
        return supplierRepository.findByIce(ice);
    }

    @Override
    public Optional<Supplier> getSupplierByEmail(String email) {
        return supplierRepository.findByEmail(email);
    }

    @Override
    public List<Supplier> getSuppliersByCity(String city) {
        return supplierRepository.findByCity(city);
    }

    @Override
    public List<Supplier> searchSuppliersByCompany(String company) {
        return supplierRepository.findByCompanyContainingIgnoreCase(company);
    }

    @Override
    public Supplier updateSupplier(Long id, Supplier supplier) {
        Optional<Supplier> existingSupplier = supplierRepository.findById(id);

        if (existingSupplier.isPresent()) {
            Supplier supplierToUpdate = existingSupplier.get();

            supplierToUpdate.setCompany(supplier.getCompany());
            supplierToUpdate.setAddress(supplier.getAddress());
            supplierToUpdate.setContact(supplier.getContact());
            supplierToUpdate.setEmail(supplier.getEmail());
            supplierToUpdate.setPhone(supplier.getPhone());
            supplierToUpdate.setCity(supplier.getCity());
            supplierToUpdate.setIce(supplier.getIce());

            return supplierRepository.save(supplierToUpdate);
        } else {
            throw new RuntimeException("Supplier with ID " + id + " not found");
        }
    }

    @Override
    public void deleteSupplier(Long id) {
        if (!supplierRepository.existsById(id)) {
            throw new RuntimeException("Supplier with ID " + id + " not found");
        }
        supplierRepository.deleteById(id);
    }

    @Override
    public long countSuppliers() {
        return supplierRepository.count();
    }

    @Override
    public boolean existsById(Long id) {
        return supplierRepository.existsById(id);
    }
}
