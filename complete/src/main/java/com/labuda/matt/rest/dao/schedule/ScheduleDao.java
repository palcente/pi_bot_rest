package com.labuda.matt.rest.dao.schedule;

import com.labuda.matt.iface.ICanLog;
import com.labuda.matt.schedule.JobSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Set;

/**
 * Created by mateu on 06/04/2018.
 */
@Repository
public class ScheduleDao implements ICanLog{

    @Autowired
    private DataSource dataSource;
    @Autowired
    private JobSchedule jobSchedule;

    public Set<JobSchedule.ScheduleEntry> listScheduledJobs() {
        return jobSchedule.getScheduled();
    }

    public void scheduleJob(String jobName, String jobParams, String cronExpression) {
        jobSchedule.scheduleJob(jobName,jobParams,cronExpression);
    }
}
