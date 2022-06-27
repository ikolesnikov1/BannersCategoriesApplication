package ru.ikolesnikov.testtask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ikolesnikov.testtask.model.Log;

import java.util.Date;
import java.util.List;

public interface LogRepository extends JpaRepository<Log, Long> {
    List<Log> findAllByIpAddressAndUserAgentAndDateAfter(String ipAddress, String userAgent, Date date);
}
