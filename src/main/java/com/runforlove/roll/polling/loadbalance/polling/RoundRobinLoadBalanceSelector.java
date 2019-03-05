package com.runforlove.roll.polling.loadbalance.polling;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class RoundRobinLoadBalanceSelector<N, C> extends AbstractLoadBalanceSelector<N, C> {
	
	private static AtomicInteger idx = new AtomicInteger(0);
	
	public RoundRobinLoadBalanceSelector(Supplier<List<N>> supplier, Predicate<N> predicate) {
		super(supplier, predicate);
	}
	
	@Override
	protected int getIndex(List<N> candidates) {
		return 0x7fffffff & idx.incrementAndGet();
	}
}
