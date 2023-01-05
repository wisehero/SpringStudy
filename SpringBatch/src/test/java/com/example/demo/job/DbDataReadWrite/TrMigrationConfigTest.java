package com.example.demo.job.DbDataReadWrite;

import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.SpringBatchTestConfig;
import com.example.demo.core.domain.accounts.AccountsRepository;
import com.example.demo.core.domain.orders.Orders;
import com.example.demo.core.domain.orders.OrdersRepository;

@ActiveProfiles("test")
@SpringBatchTest
@SpringBootTest(classes = {SpringBatchTestConfig.class, TrMigrationConfig.class})
class TrMigrationConfigTest {

	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;

	@Autowired
	private OrdersRepository ordersRepository;

	@Autowired
	private AccountsRepository accountsRepository;

	@AfterEach
	public void cleanUpEach() {
		ordersRepository.deleteAll();
		accountsRepository.deleteAll();
	}

	@Test
	public void success_noDate() throws Exception {
		JobExecution jobExecution = jobLauncherTestUtils.launchJob();

		Assertions.assertEquals(jobExecution.getExitStatus(), ExitStatus.COMPLETED);
		Assertions.assertEquals(0, accountsRepository.count());
	}

	@Test
	public void success_existData() throws Exception {
		Orders orders1 = new Orders(null, "kakao gift", 1500, new Date());
		Orders orders2 = new Orders(null, "kakao gift", 1500, new Date());

		ordersRepository.save(orders1);
		ordersRepository.save(orders2);

		JobExecution jobExecution = jobLauncherTestUtils.launchJob();

		Assertions.assertEquals(jobExecution.getExitStatus(), ExitStatus.COMPLETED);
		Assertions.assertEquals(2, accountsRepository.count());
	}
}
