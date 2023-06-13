package com.supplier.supplier.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supplier.supplier.entities.Supplier;

public interface SupplierRepository extends JpaRepository <Supplier, Integer>{
    
}
