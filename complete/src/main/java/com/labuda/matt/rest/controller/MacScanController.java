package com.labuda.matt.rest.controller;

import com.labuda.matt.iface.ICanLog;
import com.labuda.matt.rest.service.macscan.DatabaseReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.boot.autoconfigure.batch.JobExecutionEvent;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by mateu on 04/04/2018.
 */
@RestController
public class MacScanController implements ICanLog{

    @Autowired
    private DatabaseReadService databaseReadService;

    @RequestMapping("/macdaily/{date}")
    public Map<Timestamp, String[]> daily(@PathVariable(value="date") Date date) {
        _log().debug("serving daily data for date {}",date);
       return databaseReadService.getDailyData(date);
    }

    @RequestMapping("/macnow")
    public String[] now() {
        _log().debug("serving current users");
        return databaseReadService.getCurrentUsers();
    }


    /*custom binder for default date*/
    @InitBinder
    public void initBinder(WebDataBinder binder) throws Exception {
        final DateFormat df = new SimpleDateFormat("yyyyMMdd");
        final CustomDateEditor dateEditor = new CustomDateEditor(df, true) {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                if ("today".equals(text)) {
                    setValue(new Date());
                } else {
                    super.setAsText(text);
                }
            }
        };
        binder.registerCustomEditor(Date.class, dateEditor);
    }
}
