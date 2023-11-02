package ru.cool.amlapp.limitinfo.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.cool.amlapp.limitinfo.api.LimitInfoService;
import ru.cool.amlapp.limitinfo.models.LimitInfo;

import java.util.List;

@RestController
public class LimitController {

    @Autowired
    private LimitInfoService limitInfoService;

    @GetMapping("/limits")
    public List<LimitInfo> getLimits() {
        return limitInfoService.getAllLimits();
    }

    @PostMapping("/limits")
    public void  saveLimit(@Valid @RequestBody LimitInfo limitInfo) {
        limitInfoService.saveLimit(limitInfo);
    }

    @PutMapping("/limits/{id}")
    public LimitInfo updateLimit (@PathVariable long id, @Valid @RequestBody LimitInfo limitInfo) {
       return  limitInfoService.updateLimit(id, limitInfo);
    }

    @DeleteMapping("/limits/{id}")
    public void updateLimit (@PathVariable long id) {
        limitInfoService.deleteLimit(id);
    }
}
