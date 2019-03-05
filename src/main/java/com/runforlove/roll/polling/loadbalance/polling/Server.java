package com.runforlove.roll.polling.loadbalance.polling;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(of = {"ip", "port"})
@AllArgsConstructor
public class Server {
	
	public Server(String ip, String port){
		this.ip = ip;
		this.port = port;
		this.metaInfo = new MetaInfo(true);
	}
	
	/**
	 * 服务器IP
	 */
	private String ip;
	
	/**
	 * 服务器端口号
	 */
	private String port;
	
	private MetaInfo metaInfo = new MetaInfo();
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static final class MetaInfo{
		
		/**
		 * 当前节点的健康检查是否ok
		 */
		private boolean up;
	}
}
