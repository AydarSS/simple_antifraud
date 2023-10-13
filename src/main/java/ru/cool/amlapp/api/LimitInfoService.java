package ru.cool.amlapp.api;

import org.springframework.stereotype.Service;
import ru.cool.amlapp.models.LimitInfo;

import java.util.List;


public interface LimitInfoService {

    List<LimitInfo> getAllLimits();
}
