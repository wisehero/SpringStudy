package hello.springtx.propagation;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MemberRepository {

	private final EntityManager em;

	// @Transactional
	public void save(Member member) {
		log.info("membre저장");
		em.persist(member);
	}

	public Member find(String username) {
		return em.createQuery("select m from Member m where m.username = :username", Member.class)
			.setParameter("username", username)
			.getResultList().stream().findAny().orElseThrow(RuntimeException::new);
	}
}
