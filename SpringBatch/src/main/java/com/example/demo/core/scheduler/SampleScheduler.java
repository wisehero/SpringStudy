package com.example.demo.core.scheduler;

import java.util.Collections;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SampleScheduler {

	@Autowired
	private Job fileReadWriteJob;

	@Autowired
	private JobLauncher launcher;

	@Scheduled(cron = "0 */1 * * * *")
	public void helloWorldJobRun() throws
			JobInstanceAlreadyCompleteException,
			JobExecutionAlreadyRunningException,
			JobParametersInvalidException,
			JobRestartException {

		JobParameters jobParameters = new JobParameters(
				Collections.singletonMap("requestTime", new JobParameter(System.currentTimeMillis()))
		);
		launcher.run(fileReadWriteJob, jobParameters);
	}
}
