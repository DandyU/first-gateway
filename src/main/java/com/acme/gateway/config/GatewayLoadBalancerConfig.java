package com.acme.gateway.config;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "loadbalancer")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GatewayLoadBalancerConfig {

  private Map<String, String> routes;
  private Map<String, ServiceInfo> services;

}
