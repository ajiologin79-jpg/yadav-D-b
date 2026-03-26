package com.demo.controller;

import com.demo.entity.AdvancePayment;
import com.demo.entity.Attendance;
import com.demo.entity.Employee;
import com.demo.repository.AdvanceRepository;
import com.demo.repository.AttendanceRepository;
import com.demo.repository.EmployeeRepository;
import com.demo.service.PayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class PayrollController {

    @Autowired
    private EmployeeRepository employeeRepo;
    @Autowired private AttendanceRepository attendanceRepo;
    @Autowired private AdvanceRepository advanceRepo;
    @Autowired private PayrollService payrollService;

    // EMPLOYEE
    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        return employeeRepo.findAll();
    }

    @PostMapping("/employee")
    public Employee addEmployee(@RequestBody Employee emp) {
        return employeeRepo.save(emp);
    }

    // ATTENDANCE
    @PostMapping("/attendance")
    public Attendance markAttendance(@RequestBody Attendance att) {

        Optional<Attendance> existing =
                attendanceRepo.findByEmployeeIdAndDate(att.getEmployeeId(), att.getDate());

        if (existing.isPresent()) {
            Attendance old = existing.get();
            old.setStatus(att.getStatus());
            return attendanceRepo.save(old);
        }

        return attendanceRepo.save(att);
    }

    // ADVANCE
    @PostMapping("/advance")
    public AdvancePayment addAdvance(@RequestBody AdvancePayment adv) {
        return advanceRepo.save(adv);
    }

    // SALARY
    @GetMapping("/salary")
    public double calculateSalary(
            @RequestParam Long empId,
            @RequestParam String startDate,
            @RequestParam String endDate) {

        return payrollService.calculateSalary(
                empId,
                LocalDate.parse(startDate),
                LocalDate.parse(endDate)
        );
    }

    @GetMapping("/advance/{empId}")
    public List<AdvancePayment> getAdvances(@PathVariable Long empId) {
        return advanceRepo.findByEmployeeId(empId);
    }

    @DeleteMapping("/employee/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeRepo.deleteById(id);
    }

    @GetMapping("/attendance/{empId}")
    public List<Attendance> getAttendance(@PathVariable Long empId) {
        return attendanceRepo.findByEmployeeId(empId);
    }

    @PutMapping("/employee/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee emp) {
        emp.setId(id);
        return employeeRepo.save(emp);
    }
}
