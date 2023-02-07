package stack_example.stack_sync.facade;

import org.springframework.stereotype.Component;

import stack_example.stack_sync.repository.RedisLockRepository;
import stack_example.stack_sync.service.StockService;

@Component
public class LettuceLockStockFacade {

	private RedisLockRepository redisLockRepository;

	private StockService stockService;

	public LettuceLockStockFacade(RedisLockRepository redisLockRepository, StockService stockService) {
		this.redisLockRepository = redisLockRepository;
		this.stockService = stockService;
	}

	public void decrease(Long key, Long quantity) throws InterruptedException {
		while (!redisLockRepository.lock(key)) {
			Thread.sleep(100);
		}

		try {
			stockService.decrease(key, quantity);
		} finally {
			redisLockRepository.unlock(key);
		}
	}
}
