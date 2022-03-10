package com.demo.restapi.repository;

import com.demo.restapi.model.Bill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long>{
    Page<Bill> findByCreatedBy(Long userId, Pageable pageable);
}
