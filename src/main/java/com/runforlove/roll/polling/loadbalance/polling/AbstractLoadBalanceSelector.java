package com.runforlove.roll.polling.loadbalance.polling;

import java.util.List;
import java.util.function.Supplier;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractLoadBalanceSelector<N,C> implements LoadBalanceSelector<N,C>{
	
	private final Supplier<List<N>> supplier;

}
