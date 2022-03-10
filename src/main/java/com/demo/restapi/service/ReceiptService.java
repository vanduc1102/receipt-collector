package com.demo.restapi.service;

import com.demo.restapi.model.Bill;
import com.demo.restapi.payload.ReceiptResponse;
import com.demo.restapi.payload.ApiResponse;
import com.demo.restapi.payload.PagedResponse;
import com.demo.restapi.payload.request.BillRequest;
import com.demo.restapi.security.UserPrincipal;
import org.springframework.http.ResponseEntity;

public interface ReceiptService {
    PagedResponse<ReceiptResponse> getAllBills(int page, int size);
    ResponseEntity<Bill> addBill(BillRequest billRequest, UserPrincipal currentUser);
    ResponseEntity<Bill> getBill(Long id);
    ResponseEntity<ReceiptResponse> updateBill(Long id, BillRequest newBill, UserPrincipal currentUser);
    ResponseEntity<ApiResponse> deleteBill(Long id, UserPrincipal currentUser);
    PagedResponse<Bill> getUserBills(String username, int page, int size);
}
