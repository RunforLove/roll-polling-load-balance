package com.runforlove.roll.polling.loadbalance.polling;

import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractLoadBalanceSelector<N, C> implements LoadBalanceSelector<N, C> {
	
	private final Supplier<List<N>> supplier;
	private final Predicate<N> predicate;
	
	@Override
	public N select(C c) {
		List<N> candidates = supplier.get();
		if (CollectionUtils.isEmpty(candidates)) {
			return null;
		} else if (candidates.size() == 1) {
			return predicate.test(candidates.get(0)) ? candidates.get(0) : null;
		}
		
		N candidate = null;
		int idx = getIndex(candidates);
		for (int i = 0; i < candidates.size(); i++) {
			N n = candidates.get((i + idx) % candidates.size());
			if (predicate.test(n)) {
				candidate = n;
			}
		}
		return candidate;
	}
	
	abstract protected int getIndex(List<N> candidates);
}
