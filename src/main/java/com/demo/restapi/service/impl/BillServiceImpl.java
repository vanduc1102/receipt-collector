package com.demo.restapi.service.impl;

import com.demo.restapi.exception.RestapiException;
import com.demo.restapi.exception.ResourceNotFoundException;
import com.demo.restapi.model.Bill;
import com.demo.restapi.model.Media;
import com.demo.restapi.model.User;
import com.demo.restapi.payload.BillResponse;
import com.demo.restapi.payload.ApiResponse;
import com.demo.restapi.payload.PagedResponse;
import com.demo.restapi.payload.request.BillRequest;
import com.demo.restapi.repository.BillRepository;
import com.demo.restapi.repository.UserRepository;
import com.demo.restapi.security.UserPrincipal;
import com.demo.restapi.service.BillService;
import com.demo.restapi.utils.AppUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.demo.restapi.utils.AppConstants.ID;

@Service
public class BillServiceImpl implements BillService{

    private static final String CREATED_AT = "createdAt";

    private static final String BILL_STR = "Bill";

    private static final String YOU_DON_T_HAVE_PERMISSION_TO_MAKE_THIS_OPERATION = "You don't have permission to make this operation";

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PagedResponse<BillResponse> getAllBills(int page, int size) {
        AppUtils.validatePageNumberAndSize(page, size);
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, CREATED_AT);
        Page<Bill> bills = billRepository.findAll(pageable);
        if (bills.getNumberOfElements() == 0) {
            return new PagedResponse<>(Collections.emptyList(), bills.getNumber(), bills.getSize(), bills.getTotalElements(),
                    bills.getTotalPages(), bills.isLast());
        }

        List<BillResponse> billResponses = Arrays.asList(modelMapper.map(bills.getContent(), BillResponse[].class));

        return new PagedResponse<>(billResponses, bills.getNumber(), bills.getSize(), bills.getTotalElements(), bills.getTotalPages(),
                bills.isLast());
    }

    @Override
    public ResponseEntity<Bill> addBill(BillRequest billRequest, UserPrincipal currentUser) {
        User user = userRepository.getUser(currentUser);

        Bill bill = new Bill();

        modelMapper.map(billRequest, bill);

        bill.setUser(user);
        Bill newBill = billRepository.save(bill);
        return new ResponseEntity<>(newBill, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Bill> getBill(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<BillResponse> updateBill(Long id, BillRequest newBill, UserPrincipal currentUser) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse> deleteBill(Long id, UserPrincipal currentUser) {
        return null;
    }

    @Override
    public PagedResponse<Bill> getUserBills(String username, int page, int size) {
        return null;
    }
}
