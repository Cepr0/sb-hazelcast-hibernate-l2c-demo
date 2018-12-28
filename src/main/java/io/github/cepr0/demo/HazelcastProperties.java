package io.github.cepr0.demo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("hzc")
public class HazelcastProperties {
	private Integer port = 5701;
	private String managementCenterUrl;
}
