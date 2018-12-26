package io.github.cepr0.demo;

import com.hazelcast.config.Config;
import com.hazelcast.config.ManagementCenterConfig;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static java.lang.String.format;

@Slf4j
@Configuration
@EnableConfigurationProperties(HazelcastConfig.HazelcastProperties.class)
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
		config.getGroupConfig().setName("dev");
		log.info("[i] Hazelcast port: {}", properties.getPort());
		config.getNetworkConfig()
				.setPort(properties.getPort())
//				.setPublicAddress("localhost:5701")
				.getJoin()
				.getMulticastConfig()
				.setEnabled(true)
		;

		config.setManagementCenterConfig(new ManagementCenterConfig()
				.setUrl("http://localhost:38080/hazelcast-mancenter")
				.setEnabled(true)
		);

//		SerializerConfig sc = new SerializerConfig().setTypeClass(Employee.class).setClass(EmployeeSerializer.class);
//		config.getSerializationConfig().addSerializerConfig(sc);
		return config;
	}

	@Getter
	@Setter
	@ConfigurationProperties("hazelcast")
	public static class HazelcastProperties {
		private Integer port = 5701;
	}
}
