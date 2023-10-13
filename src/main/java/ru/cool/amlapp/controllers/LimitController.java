package ru.cool.amlapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.cool.amlapp.api.LimitInfoService;
import ru.cool.amlapp.models.LimitInfo;

import java.util.List;

@RestController
public class LimitController {

    @Autowired
    private LimitInfoService limitInfoService;

    @GetMapping("/limits")
    public List<LimitInfo> getLimits() {
        return limitInfoService.getAllLimits();
    }
}
