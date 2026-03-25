package com.demo.repository;

import com.demo.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    List<Attendance> findByEmployeeIdAndDateBetween(Long empId, LocalDate start, LocalDate end);

    Optional<Attendance> findByEmployeeIdAndDate(Long empId, LocalDate date);
    List<Attendance> findByEmployeeId(Long empId);
}
