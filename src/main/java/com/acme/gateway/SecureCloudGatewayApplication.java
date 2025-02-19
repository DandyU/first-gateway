package com.acme.gateway;

import com.acme.gateway.config.GatewayLoadBalancerConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
@EnableConfigurationProperties(GatewayLoadBalancerConfig.class)
public class SecureCloudGatewayApplication {

	private final BuildProperties buildProperties;
	private final GatewayLoadBalancerConfig gatewayLoadBalancerConfig;

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(SecureCloudGatewayApplication.class);
		app.addListeners(new ApplicationPidFileWriter()); // application.yml에 정의된(spring.pid.file) 파일에 pid를 저장
		app.run(args);
	}

	@Bean
	public ApplicationRunner applicationRunner() {
		gatewayLoadBalancerConfig.getRoutes().entrySet().forEach(entry -> {log.info("Route Map: {}:{}", entry.getKey(), entry.getValue());});

		return args ->
				log.info(" :: " + buildProperties.getArtifact() + " ::   (v" + buildProperties.getVersion() + ")");
	}

}
