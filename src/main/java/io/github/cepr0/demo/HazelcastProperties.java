package io.github.cepr0.demo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Validated
@ConfigurationProperties("hzc")
public class HazelcastProperties {
	@NotBlank private String instance;
	@NotBlank private String group;
	private Integer port = 5701;
	private String managementCenterUrl;
}
