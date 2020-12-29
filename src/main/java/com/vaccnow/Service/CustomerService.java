package com.vaccnow.Service;

import com.vaccnow.Exception.VaccNowException;
import com.vaccnow.Repository.BranchRepository;
import com.vaccnow.Repository.CustomerRepository;
import com.vaccnow.Repository.SlotRepository;
import com.vaccnow.entity.Customer;
import com.vaccnow.entity.Slot;
import com.vaccnow.enums.PaymentMethod;
import com.vaccnow.util.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    BranchRepository branchRepository;

    @Autowired
    SlotRepository slotRepository;

    public String scheduleTimeSlot(String email, PaymentMethod paymentMethod, String branchName) {
        Customer customer = customerRepository.findByEmail(email);
        List<Slot> slots = branchRepository.findByName(branchName)
                .orElseThrow(() -> new VaccNowException("Branch not found", HttpStatus.NOT_FOUND))
                .getSlots();
        boolean flag = false;
        for (Slot slot : slots) {
            if (!slot.isBooked()) {
                slot.setBooked(true);
                customer.setSlot(slot);
                slotRepository.save(slot);
                customerRepository.save(customer);
                Mail mail = new Mail();
                mail.sendMail(customer.getEmail());
                flag = true;
                break;
            }
        }
        if (flag)
            return "Slot booked successfully";
        else
            return "Slot Not Available";
    }

    public List<Customer> getAllAppliedWithVaccinationsOverAPeriod(String fromDate, String toDate, String fromTime, String toTime) throws ParseException {

        List<Customer> customers = customerRepository.findAll();
        String format = "dd-mm-yyyy hh:mm a";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date startDateObj = sdf.parse(fromDate + " " + fromTime);
        Date endDateObj = sdf.parse(toDate + " " + toTime);
        List<Customer> appliedWithVaccination = new ArrayList<>();
        customers.stream().forEach(customer -> {
            if (startDateObj.compareTo(customer.getSlot().getSlot()) * (customer.getSlot().getSlot()).
                    compareTo(endDateObj) > 0) {
                appliedWithVaccination.add(customer);
            }
        });
        return appliedWithVaccination;
    }
}
