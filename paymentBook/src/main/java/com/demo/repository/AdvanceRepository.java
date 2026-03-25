package com.demo.repository;

import com.demo.entity.AdvancePayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AdvanceRepository extends JpaRepository<AdvancePayment, Long> {

    // 🔹 Used in salary calculation
    List<AdvancePayment> findByEmployeeIdAndDateBetween(
            Long empId,
            LocalDate start,
            LocalDate end
    );

    // 🔹 Used to show advance history in UI
    List<AdvancePayment> findByEmployeeId(Long empId);
}