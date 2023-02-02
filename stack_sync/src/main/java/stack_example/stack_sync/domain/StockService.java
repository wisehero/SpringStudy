package stack_example.stack_sync.domain;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StockService {

	private StockRepository stockRepository;

	public StockService(StockRepository stockRepository) {
		this.stockRepository = stockRepository;
	}

	@Transactional
	public void decrease(Long id, Long quantity) {
		// get stock
		// 재고 감소
		// 저장
		Stock stock = stockRepository.findById(id).orElseThrow();

		stock.decrease(quantity);

		stockRepository.saveAndFlush(stock);
	}
}
