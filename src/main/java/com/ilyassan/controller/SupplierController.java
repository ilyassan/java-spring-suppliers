package com.ilyassan.controller;

import com.ilyassan.models.Supplier;
import com.ilyassan.service.SupplierService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    private SupplierService supplierService;

    public void setSupplierService(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping
    public ResponseEntity<List<Supplier>> getAllSuppliers(
            @RequestParam(required = false) String sort) {

        List<Supplier> suppliers;

        if ("name".equalsIgnoreCase(sort)) {
            suppliers = supplierService.getAllSuppliersSortedByName();
        } else {
            suppliers = supplierService.getAllSuppliers();
        }

        return ResponseEntity.ok(suppliers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Supplier> getSupplierById(@PathVariable Long id) {
        Optional<Supplier> supplier = supplierService.getSupplierById(id);

        return supplier
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search/ice/{ice}")
    public ResponseEntity<Supplier> getSupplierByIce(@PathVariable String ice) {
        Optional<Supplier> supplier = supplierService.getSupplierByIce(ice);

        return supplier
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search/city/{city}")
    public ResponseEntity<List<Supplier>> getSuppliersByCity(@PathVariable String city) {
        List<Supplier> suppliers = supplierService.getSuppliersByCity(city);
        return ResponseEntity.ok(suppliers);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Supplier>> searchSuppliersByCompany(
            @RequestParam String company) {
        List<Supplier> suppliers = supplierService.searchSuppliersByCompany(company);
        return ResponseEntity.ok(suppliers);
    }

    @PostMapping
    public ResponseEntity<Supplier> createSupplier(@RequestBody Supplier supplier) {
        try {
            Supplier savedSupplier = supplierService.saveSupplier(supplier);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedSupplier);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Supplier> updateSupplier(
            @PathVariable Long id,
            @RequestBody Supplier supplier) {
        try {
            Supplier updatedSupplier = supplierService.updateSupplier(id, supplier);
            return ResponseEntity.ok(updatedSupplier);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Long id) {
        try {
            supplierService.deleteSupplier(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countSuppliers() {
        long count = supplierService.countSuppliers();
        return ResponseEntity.ok(count);
    }
}
