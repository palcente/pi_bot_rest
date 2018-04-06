package com.labuda.matt.rest.service.macscan;

import com.labuda.matt.rest.dao.macscan.MacscanDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

/**
 * Created by mateu on 04/04/2018.
 */

@Service
public class DatabaseReadService {

    @Autowired
    private MacscanDao macscanDao;

    public Map<Timestamp, String[]> getDailyData(Date date) {
        return macscanDao.getDailyData(date);
    }

    public String[] getCurrentUsers() {
        return  macscanDao.getCurrentUsers();
    }
}
