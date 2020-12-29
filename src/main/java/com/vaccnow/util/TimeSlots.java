package com.vaccnow.util;

import com.vaccnow.entity.Slot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TimeSlots {

    public List<Slot> getSlots(String startDate, String endDate) throws ParseException {
        String startTime = "09:00 AM";
        String endTime = "8:00 PM";
        String format = "dd-mm-yyyy hh:mm a";

        SimpleDateFormat sdf = new SimpleDateFormat(format);

        Date startDateObj = sdf.parse(startDate + " " + startTime);
        Date endDateObj = sdf.parse(endDate + " " + endTime);

        long slot = startDateObj.getTime();
        List<Slot> slots = new ArrayList<>();
        while (slot < endDateObj.getTime()) {
            slots.add(Slot.builder().withSlot(new Date(slot)).withIsBooked(false).build());
            slot += 900000;
        }
        return slots;
    }
}