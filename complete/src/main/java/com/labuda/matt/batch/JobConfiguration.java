package com.labuda.matt.batch;

import com.labuda.matt.iface.ICanLog;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;

/**
 * Created by mateu on 07/04/2018.
 */

public class JobConfiguration implements ICanLog {

    private String jobName;
    private final Collection<JobConfigurationEntry> entries;

    class JobConfigurationEntry {
        private String key;
        private String value;
        private String paramType;
        private boolean identifying;

        public JobConfigurationEntry(String key, String value, String paramType, boolean identifying) {
            this.key = key;
            this.value = value;
            this.paramType = paramType;
            this.identifying = identifying;
        }
    }

    public JobConfiguration(String jobName, Collection<JobConfigurationEntry> entries) {
        this.jobName = jobName;
        this.entries = entries;
    }

    public JobParameters toJobParameters() {
        _log().debug("Starting building jobParameters...");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        JobParametersBuilder jpb = new JobParametersBuilder();

        for (JobConfigurationEntry e : entries) {
            switch (e.paramType.toUpperCase()) {
                case "STRING":
                    jpb.addString(e.key, e.value, e.identifying);
                    _log().debug("Added '{}' jobParameter: {} of value: {} ", e.paramType, e.key, e.value);
                    break;
                case "DATE": {
                    try {
                        jpb.addDate(e.key, sdf.parse(e.value), e.identifying);
                        _log().debug("Added '{}' jobParameter: {} of value: {} ", e.paramType, e.key, e.value);
                    } catch (ParseException e1) {
                        _log().error("Could not parse date - skipping job parameter {}", e.value);
                        e1.printStackTrace();
                    }
                    break;
                }
                case "LONG":
                    jpb.addLong(e.key, Long.parseLong(e.value), e.identifying);
                    _log().debug("Added '{}' jobParameter: {} of value: {} ", e.paramType, e.key, e.value);
                    break;
                case "DOUBLE":
                    jpb.addDouble(e.key, Double.parseDouble(e.value), e.identifying);
                    _log().debug("Added '{}' jobParameter: {} of value: {} ", e.paramType, e.key, e.value);
                    break;
                default:
                    _log().error("Invalid jobParameter type: '{}' key: {} value: {}", e.paramType, e.key, e.value);
            }
        }
        _log().info("Returning job parameters - count = {}", jpb.toJobParameters().getParameters().size());
        return jpb.toJobParameters();
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Collection<JobConfigurationEntry> getEntries() {
        return entries;
    }
}

