package com.labuda.matt.rest.controller;

import com.labuda.matt.iface.ICanLog;
import com.labuda.matt.rest.service.schedule.ScheduleService;
import com.labuda.matt.schedule.JobSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

/**
 * Created by mateu on 06/04/2018.
 */


@RestController
public class ScheduleController implements ICanLog {

    @Autowired
    private ScheduleService scheduleService;

    @RequestMapping("/refreshJournal")
    public Set<JobSchedule.ScheduleEntry> refreshJournal() {
        _log().debug("Refreshing Journal");
        return scheduleService.refreshSchedules();
    }

    @RequestMapping("/checkJournal")
    public Set<JobSchedule.ScheduleEntry> checkJournal() {
        _log().debug("Checking Journal");
        return scheduleService.checkJournal();
    }

    @RequestMapping("/clearJournal")
    public Set<JobSchedule.ScheduleEntry> clearJournal() {
        _log().debug("Clearing Journal");
        return scheduleService.clearJournal();
    }

    @RequestMapping("/scheduleJob")
    public void scheduleJob(@RequestParam() Map<String,String> requestParams) {
        _log().debug("adding to schedule");
        String jobName = requestParams.get("jobName");
        String jobParams = requestParams.get("jobParams");
        String cronExpression = requestParams.get("cronExpression");
        scheduleService.scheduleJob(jobName,jobParams,cronExpression);
    }
}