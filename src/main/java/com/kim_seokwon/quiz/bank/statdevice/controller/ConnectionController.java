package com.kim_seokwon.quiz.bank.statdevice.controller;

import com.kim_seokwon.quiz.bank.statdevice.service.ConnectionRateStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController(value="/device-stat")
public class ConnectionController {

    private final ConnectionRateStatService connectionRateStatService;

    @Autowired
    public ConnectionController(final ConnectionRateStatService connectionRateStatService) {
        this.connectionRateStatService = connectionRateStatService;
    }

    @GetMapping("/by-year")
    public @ResponseBody
    Map<String, List> getMaxRateDeviceAnnually() {
        List list = connectionRateStatService.getMaxRateAnnually();
        Map<String, List> result = new HashMap<>();
        result.put("devices", list);
        return result;
    }

    @GetMapping("/year/{year}")
    public @ResponseBody
    Map<String, List> getMaxRateDeviceByYear(@PathVariable("year") final String year) {

        try {
            List list = connectionRateStatService.getMaxRateDeviceByYear(Integer.parseInt(year));
            Map<String, List> result = new HashMap<>();
            result.put("result", list);
            return result;
        } catch ( NumberFormatException ex) {
            throw new IllegalArgumentException("Year must be numeric");
        }
    }

    @GetMapping("/device/{device}")
    public @ResponseBody
    Map<String, List> getMaxRateYearByDevice(@PathVariable("device") final String deviceId) {
        List list = connectionRateStatService.getMaxRateYearByDevice(deviceId);
        Map<String, List> result = new HashMap<>();
        result.put("result", list);
        return result;
    }
}
