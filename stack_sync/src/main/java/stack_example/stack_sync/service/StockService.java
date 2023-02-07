package stack_example.stack_sync.service;

import org.springframework.stereotype.Service;

import stack_example.stack_sync.domain.Stock;
import stack_example.stack_sync.repository.StockRepository;

@Service
public class StockService {

	private StockRepository stockRepository;

	public StockService(StockRepository stockRepository) {
		this.stockRepository = stockRepository;
	}

	public synchronized void decrease(Long id, Long quantity) {
		// get stock
		// 재고 감소
		// 저장
		Stock stock = stockRepository.findById(id).orElseThrow();

		stock.decrease(quantity);

		stockRepository.saveAndFlush(stock);
	}
}
