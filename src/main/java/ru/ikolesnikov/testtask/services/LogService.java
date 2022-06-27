package ru.ikolesnikov.testtask.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ikolesnikov.testtask.model.Banner;
import ru.ikolesnikov.testtask.model.Log;
import ru.ikolesnikov.testtask.repository.BannerRepository;
import ru.ikolesnikov.testtask.repository.LogRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class LogService {
    private final BannerRepository bannerRepository;
    private final LogRepository logRepository;
    private final HttpServletRequest servletRequest;

    public String getBanner(String request) {
        String remoteAddr = servletRequest.getRemoteAddr();
        String userAgent = servletRequest.getHeader("User-Agent");
        List<Long> bannerIdStopList = logRepository
                .findAllByIpAddressAndUserAgentAndDateAfter(remoteAddr, userAgent, dayAgo())
                .stream().map(req -> req.getBanner().getBannerId()).collect(Collectors.toList());
        bannerIdStopList.add(0L);
        Optional<Banner> banner = bannerRepository
                .findFirstByBannerIdNotInAndCategory_RequestAndDeletedOrderByPriceDesc(
                        bannerIdStopList, request, false);

        if (banner.isEmpty())
            return null;

        Log log = new Log();
        log.setDate(new Date());
        log.setIpAddress(remoteAddr);
        log.setUserAgent(userAgent);
        log.setBanner(banner.get());
        logRepository.save(log);
        return banner.get().getContent();
    }

    private Date dayAgo() {
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DATE, -1);
        return new Date(calendar.getTimeInMillis());
    }
}
