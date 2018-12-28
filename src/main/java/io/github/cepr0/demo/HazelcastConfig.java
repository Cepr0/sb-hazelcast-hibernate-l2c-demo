package io.github.cepr0.demo;

import com.hazelcast.config.Config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static java.lang.String.format;

@Slf4j
@Configuration
@EnableConfigurationProperties(HazelcastProperties.class)
public class HazelcastConfig {

	@Value("${spring.application.name}")
	private String appName;

	@Value("${server.port}")
	private String serverPort;

	private final HazelcastProperties properties;

	public HazelcastConfig(HazelcastProperties properties) {
		this.properties = properties;
	}

	@Bean
	public Config config() {
		Config config = new Config();
		config.setInstanceName(format("%s[%s]", appName, properties.getPort()));
		config.getGroupConfig().setName(appName);

		log.info("[i] Hazelcast port: {}", properties.getPort());
		config.getNetworkConfig().setPort(properties.getPort());

		String managementCenterUrl = properties.getManagementCenterUrl();
		if (managementCenterUrl != null) {
			log.info("[i] Hazelcast management center URL: {}", managementCenterUrl);
			config.getManagementCenterConfig()
					.setUrl(managementCenterUrl)
					.setEnabled(true);
		}
		return config;
	}
}
