package com.runforlove.roll.polling.loadbalance.polling;


import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class RandomLoadBalanceSelector<N, C> extends AbstractLoadBalanceSelector<N, C> {
	
	public RandomLoadBalanceSelector(Supplier<List<N>> supplier, Predicate<N> predicate) {
		super(supplier, predicate);
	}
	
	@Override
	protected int getIndex(List<N> candidates) {
		return (int) (ThreadLocalRandom.current().nextDouble() * candidates.size());
	}
}
