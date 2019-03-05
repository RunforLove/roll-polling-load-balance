package com.runforlove.roll.polling.loadbalance.polling;

public interface LoadBalanceSelector<N, C> {
	
	/**
	 * 根据指定条件选择一台机器提供服务
	 *
	 * @param c 选择条件
	 * @return 选择结果
	 */
	N select(C c);
}
