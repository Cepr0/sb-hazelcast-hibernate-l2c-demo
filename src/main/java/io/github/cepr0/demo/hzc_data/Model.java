package io.github.cepr0.demo.hzc_data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.keyvalue.annotation.KeySpace;
import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@KeySpace("model")
public class Model implements Comparable<Model>, Serializable {
	@Id @Default private UUID id = UUID.randomUUID();
	@Default private Instant createdAt = Instant.now();
	private String name;
	private String email;

	@Override
	public int compareTo(@NonNull final Model m) {
		return this.getCreatedAt().compareTo(m.getCreatedAt());
	}
}
