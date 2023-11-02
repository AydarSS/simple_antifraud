package ru.cool.amlapp.limitinfo.api;

import ru.cool.amlapp.limitinfo.models.LimitInfo;

import java.util.List;


public interface LimitInfoService {

    List<LimitInfo> getAllLimits();

    void saveLimit(LimitInfo limit);

    LimitInfo updateLimit (long id, LimitInfo limitInfoNew);

    void deleteLimit (long id);
}
