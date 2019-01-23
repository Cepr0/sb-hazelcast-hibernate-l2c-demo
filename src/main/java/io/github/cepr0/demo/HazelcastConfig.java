package io.github.cepr0.demo;

import com.hazelcast.config.Config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.hazelcast.repository.config.EnableHazelcastRepositories;

@Slf4j
@EnableHazelcastRepositories("io.github.cepr0.demo.hzc_data")
@Configuration
@EnableConfigurationProperties(HazelcastProperties.class)
public class HazelcastConfig {

	private final HazelcastProperties properties;

	public HazelcastConfig(HazelcastProperties properties) {
		this.properties = properties;
	}

	@Bean
	public Config config() {
		Config config = new Config();
		config.setInstanceName(properties.getInstance());
		config.getGroupConfig().setName(properties.getGroup());

		log.debug("[d] Hazelcast port: {}", properties.getPort());
		config.getNetworkConfig().setPort(properties.getPort());

		String managementCenterUrl = properties.getManagementCenterUrl();
		if (managementCenterUrl != null) {
			log.debug("[d] Hazelcast management center URL: {}", managementCenterUrl);
			config.getManagementCenterConfig()
					.setUrl(managementCenterUrl)
					.setEnabled(true);
		}
		return config;
	}
}
