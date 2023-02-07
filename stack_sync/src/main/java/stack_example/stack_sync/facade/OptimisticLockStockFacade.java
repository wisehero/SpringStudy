package stack_example.stack_sync.facade;

import org.springframework.stereotype.Service;

import stack_example.stack_sync.service.OptimisticLockStockService;

@Service
public class OptimisticLockStockFacade {

	private final OptimisticLockStockService optimisticLockStockService;

	public OptimisticLockStockFacade(OptimisticLockStockService optimisticLockStockService) {
		this.optimisticLockStockService = optimisticLockStockService;
	}

	public void decrease(Long id, Long quantity) throws InterruptedException {
		while (true) {
			try {
				optimisticLockStockService.decrease(id, quantity);
				break;
			} catch (Exception e) {
				Thread.sleep(50);
			}
		}
	}
}
