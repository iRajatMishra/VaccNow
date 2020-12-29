package com.vaccnow.api;

import com.vaccnow.Service.CustomerService;
import com.vaccnow.entity.Customer;
import com.vaccnow.enums.PaymentMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerResource {
    @Autowired
    CustomerService customerService;

    @GetMapping("/schedulevaccination")
    public ResponseEntity<String> schedulevaccination(@RequestParam String branchName
            , @RequestParam PaymentMethod paymentMethod, @RequestParam String email) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(customerService.scheduleTimeSlot(email, paymentMethod, branchName));
    }

    @GetMapping("/getappliedvaccinationsoverperiod")
    public ResponseEntity<List<Customer>> getAppliedVaccinationsOverPeriod(@RequestParam String fromDate,
                                                                           @RequestParam String toDate, @RequestParam String fromTime, @RequestParam String toTime) throws ParseException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(customerService.getAllAppliedWithVaccinationsOverAPeriod(fromDate, toDate,
                        fromTime, toTime));
    }

}
