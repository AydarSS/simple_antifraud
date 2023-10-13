package ru.cool.amlapp.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cool.amlapp.api.LimitInfoService;
import ru.cool.amlapp.models.LimitInfo;
import ru.cool.amlapp.repositiries.LimitInfoRepository;

import java.util.List;
@Service
public class LimitInfoServiceImpl implements LimitInfoService {

    @Autowired
    private LimitInfoRepository limitInfoRepository;

    @Override
    public List<LimitInfo> getAllLimits() {
        return limitInfoRepository.findAll();
    }
}
