package stack_example.stack_sync.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import jakarta.persistence.LockModeType;

public interface StockRepository extends JpaRepository<Stock, Long> {
	@Lock(value = LockModeType.PESSIMISTIC_WRITE)
	@Query("SELECT s FROM Stock s WHERE s.id = :id")
	Stock findByIdWithPessimisticLock(Long id);
}
