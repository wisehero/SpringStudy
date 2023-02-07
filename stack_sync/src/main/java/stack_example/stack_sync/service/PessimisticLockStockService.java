package stack_example.stack_sync.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import stack_example.stack_sync.domain.Stock;
import stack_example.stack_sync.repository.StockRepository;

// 비관적락은 충돌이 빈번하게 일어난다면 낙관적락보다 좋다.
// 데이터 정합성 측면에서는 효과적이다.
// 별도의 락을 잡기 때문에 성능 감소가 있을 수 있다.
@Service
public class PessimisticLockStockService {
	private StockRepository stockRepository;

	public PessimisticLockStockService(StockRepository stockRepository) {
		this.stockRepository = stockRepository;
	}

	@Transactional
	public void decrease(Long id, Long quantity) {
		Stock stock = stockRepository.findByIdWithPessimisticLock(id);
		stock.decrease(quantity);
		stockRepository.saveAndFlush(stock);
	}
}
