package com.leozhang.portalssm.service;

import com.leozhang.portalssm.entity.Log;
import com.leozhang.portalssm.util.Result;

public interface LogService {
    void insertLog(Log log);

    Result getListForPage(int pno, int psize, String url, String username, String action, String beginTime, String endTime, String sortField, String sortType);
}
