package com.labuda.matt.rest.service.schedule;

import com.labuda.matt.iface.ICanLog;
import com.labuda.matt.rest.dao.schedule.ScheduleDao;
import com.labuda.matt.schedule.JobSchedule;
import com.labuda.matt.utils.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by mateu on 06/04/2018.
 */
@Service
public class ScheduleService implements ICanLog{

    @Autowired
    ScheduleDao scheduleDao;

    public Set<JobSchedule.ScheduleEntry> refreshSchedules() {
        _log().debug("Refreshing Schedules");
        throw new NotImplementedException();
    }

    public Set<JobSchedule.ScheduleEntry> checkJournal() {
        return scheduleDao.listScheduledJobs();
    }

    public Set<JobSchedule.ScheduleEntry> clearJournal() {
        _log().debug("Clearing Schedules");
        throw new NotImplementedException();
    }

    public void scheduleJob(String jobName, String jobParams, String cronExpression) {
        scheduleDao.scheduleJob(jobName,jobParams,cronExpression);
    }
}
