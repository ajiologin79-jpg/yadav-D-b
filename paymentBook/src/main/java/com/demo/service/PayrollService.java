package com.demo.service;

import com.demo.entity.AdvancePayment;
import com.demo.entity.Attendance;
import com.demo.entity.Employee;
import com.demo.repository.AdvanceRepository;
import com.demo.repository.AttendanceRepository;
import com.demo.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PayrollService {

    @Autowired
    private EmployeeRepository employeeRepo;

    @Autowired
    private AttendanceRepository attendanceRepo;

    @Autowired
    private AdvanceRepository advanceRepo;

    public double calculateSalary(Long empId, LocalDate start, LocalDate end) {

        Employee emp = employeeRepo.findById(empId).orElseThrow();

        List<Attendance> records =
                attendanceRepo.findByEmployeeIdAndDateBetween(empId, start, end);

        double total = 0;

        for (Attendance a : records) {

            if ("FULL".equals(a.getStatus())) {
                total += emp.getPerDaySalary();
            }
            else if ("HALF".equals(a.getStatus())) {
                total += emp.getPerDaySalary() / 2;
            }
        }

        double advance = advanceRepo
                .findByEmployeeIdAndDateBetween(empId, start, end)
                .stream()
                .mapToDouble(AdvancePayment::getAmount)
                .sum();

        return total - advance;
    }
}
