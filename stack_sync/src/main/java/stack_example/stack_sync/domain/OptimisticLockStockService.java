package stack_example.stack_sync.domain;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// 별도의 락을 잡지 않으므로 성능상 이점이 있다.
// 업데이트가 실패했을때를 대비한 로직을 개발자가 직접 작성
@Service
public class OptimisticLockStockService {
	private StockRepository stockRepository;

	public OptimisticLockStockService(StockRepository stockRepository) {
		this.stockRepository = stockRepository;
	}

	@Transactional
	public void decrease(Long id, Long quantity) {
		Stock stock = stockRepository.findByIdWithOptimisticLock(id);
		stock.decrease(quantity);

		stockRepository.saveAndFlush(stock);
	}
}
