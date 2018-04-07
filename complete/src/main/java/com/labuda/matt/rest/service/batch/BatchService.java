package com.labuda.matt.rest.service.batch;

import com.labuda.matt.batch.JobConfiguration;
import com.labuda.matt.iface.ICanLog;
import com.labuda.matt.utils.NotImplementedException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.stereotype.Service;

import javax.batch.runtime.JobExecution;

/**
 * Created by mateu on 07/04/2018.
 */
@Service
public class BatchService implements ICanLog {

    public JobExecution runJob(Job job, JobParameters jobParameters){
        throw new NotImplementedException();
    }

    public JobConfiguration getJobParameters(String jobName){
        throw new NotImplementedException();
    }

    public Job buildJob(String jobName, JobParameters jobParameters) {
        throw new NotImplementedException();
    }


}
