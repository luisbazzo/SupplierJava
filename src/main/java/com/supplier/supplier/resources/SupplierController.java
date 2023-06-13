package com.supplier.supplier.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.supplier.supplier.entities.Supplier;
import com.supplier.supplier.services.SupplierService;

@RestController
@RequestMapping("suppliers")
@CrossOrigin
public class SupplierController {
    
    @Autowired
    private SupplierService supplierService;

    @GetMapping("{id}")
    public ResponseEntity<Supplier> getSupplier(@PathVariable int id){ 
        Supplier supplier = supplierService.getSupplierById(id);
        return ResponseEntity.ok().body(supplier);
    }

    @GetMapping
    public ResponseEntity<List<Supplier>> getSuppliers(){
        List<Supplier> suppliers = supplierService.getSuppliers();
        return ResponseEntity.ok().body(suppliers);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable int id){ 
        supplierService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> editSupplier(@PathVariable int id, @RequestBody Supplier supplier){
        supplierService.update(id, supplier);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Supplier> saveSupplier(@RequestBody Supplier supplier){
        Supplier newSupplier = supplierService.save(supplier);

        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(supplier.getId())
            .toUri();

        return ResponseEntity.created(location).body(newSupplier);
    }
}
