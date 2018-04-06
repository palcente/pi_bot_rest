package com.labuda.matt.schedule;

import com.labuda.matt.iface.ICanLog;
import com.labuda.matt.utils.NotImplementedException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by mateu on 06/04/2018.
 */
@Component
public class JobSchedule implements ICanLog{

    protected synchronized void schedule(ScheduleEntry entry){
        throw new NotImplementedException();
    }

    protected synchronized void removeFromSchedule(ScheduleEntry entry){
        throw new NotImplementedException();
    }

    public Set<ScheduleEntry> getScheduled() {
        return scheduled;
    }

    private final Set<ScheduleEntry> scheduled = new HashSet<>();

    public void scheduleJob(String jobName, String jobParams, String cronExpression) {
        ScheduleEntry scheduleEntry = new ScheduleEntry();
        scheduleEntry.jobName = jobName;
        scheduleEntry.jobParams=jobParams;
        scheduleEntry.cronExpression=cronExpression;
        scheduled.add(scheduleEntry);
    }

    public class ScheduleEntry {

        public ScheduleEntry() {
        }

         @Override
         public String toString() {
             return "ScheduleEntry{" +
                     "jobName='" + jobName + '\'' +
                     ", jobParams='" + jobParams + '\'' +
                     ", cronExpression='" + cronExpression + '\'' +
                     '}';
         }

         public String getJobName() {
            return jobName;
        }

        public void setJobName(String jobName) {
            this.jobName = jobName;
        }

        public String getJobParams() {
            return jobParams;
        }

        public void setJobParams(String jobParams) {
            this.jobParams = jobParams;
        }

        public String getCronExpression() {
            return cronExpression;
        }

        public void setCronExpression(String cronExpression) {
            this.cronExpression = cronExpression;
        }

        private String jobName;
        private String jobParams;
        private String cronExpression;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ScheduleEntry that = (ScheduleEntry) o;

            if (jobName != null ? !jobName.equals(that.jobName) : that.jobName != null) return false;
            if (jobParams != null ? !jobParams.equals(that.jobParams) : that.jobParams != null) return false;
            return cronExpression != null ? cronExpression.equals(that.cronExpression) : that.cronExpression == null;
        }

        @Override
        public int hashCode() {
            int result = jobName != null ? jobName.hashCode() : 0;
            result = 31 * result + (jobParams != null ? jobParams.hashCode() : 0);
            result = 31 * result + (cronExpression != null ? cronExpression.hashCode() : 0);
            return result;
        }
    }


}
