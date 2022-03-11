package com.demo.restapi.repository;

import com.demo.restapi.model.Media;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {
    Page<Media> findByReceiptId(Long receiptId, Pageable pageable);
}
