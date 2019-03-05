package com.runforlove.roll.polling.loadbalance.polling;

import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

public final class LoadBalanceSelectors {
	
	private LoadBalanceSelectors() {
	
	}
	
	public static <N, C> LoadBalanceSelector<N, C> of(Supplier<List<N>> supplier, Predicate<N> predicate,
	                                                  LoadBalanceStrategyEnums loadBalanceStrategy) {
		
		if (loadBalanceStrategy == LoadBalanceStrategyEnums.RANDOM) {
			return new RandomLoadBalanceSelector<N,C>(supplier, predicate);
		}
		return new RoundRobinLoadBalanceSelector<N,C>(supplier, predicate);
	}
}
