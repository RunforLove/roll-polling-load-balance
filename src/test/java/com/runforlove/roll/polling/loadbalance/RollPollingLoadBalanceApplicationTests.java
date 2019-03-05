package com.runforlove.roll.polling.loadbalance;

import com.runforlove.roll.polling.loadbalance.polling.LoadBalanceSelector;
import com.runforlove.roll.polling.loadbalance.polling.LoadBalanceSelectors;
import com.runforlove.roll.polling.loadbalance.polling.LoadBalanceStrategyEnums;
import com.runforlove.roll.polling.loadbalance.polling.Server;

import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import static java.util.Collections.sort;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RollPollingLoadBalanceApplicationTests {
	
	private static List<Server> servers = Lists.newArrayList();
	private final static Predicate<Server> IS_UP = server -> server.getMetaInfo().isUp();
	
	@Before
	public void before() {
		Server server_0 = new Server("192.168.0.108", "8080");
		Server server_1 = new Server("192.168.0.20", "8080");
		Server server_2 = new Server("192.168.0.3", "8080");
		Server server_3 = new Server("192.168.0.14", "8080");
		Server server_4 = new Server("192.168.0.5", "8080");
		servers = Arrays.asList(server_0, server_1, server_2, server_3, server_4);
		
		Comparator<Server> comparator = Comparator.comparing(Server::getIp);
		sort(servers, comparator);
	}
	
	@Test
	public void testRoundRobin() {
		LoadBalanceSelector<Server, String>
				loadBalanceSelector =
				LoadBalanceSelectors.of(() -> servers, IS_UP, LoadBalanceStrategyEnums.RoundRobin);
		for (int i = 0; i < 20; i++) {
			System.out.println(loadBalanceSelector.select(""));
		}
	}
}
