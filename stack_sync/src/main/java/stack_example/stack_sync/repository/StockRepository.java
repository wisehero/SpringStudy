package stack_example.stack_sync.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import jakarta.persistence.LockModeType;
import stack_example.stack_sync.domain.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {
	@Lock(value = LockModeType.PESSIMISTIC_WRITE)
	@Query("SELECT s FROM Stock s WHERE s.id = :id")
	Stock findByIdWithPessimisticLock(Long id);

	@Lock(value = LockModeType.OPTIMISTIC)
	@Query("SELECT s FROM Stock s WHERE s.id = :id")
	Stock findByIdWithOptimisticLock(Long id);
}
