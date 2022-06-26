package hello.springtx.propagation;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class MemberServiceTest {

	@Autowired
	MemberService memberService;
	@Autowired
	MemberRepository memberRepository;
	@Autowired
	LogRepository logRepository;

	@Test
	void outerTxOff_success() {
		// given
		String username = "outerTxOff_success";
		// when
		memberService.joinV1(username);
		// then
		assertThat(memberRepository.find(username)).isNotNull();
		assertThat(logRepository.find(username)).isNotNull();
	}

	@Test
	void singleTx() {
		// given
		String username = "outerTxOff_success";

		// when
		memberService.joinV1(username);

		// when
		assertThat(memberRepository.find(username)).isNotNull();
		assertThat(logRepository.find(username)).isNotNull();
	}
}
