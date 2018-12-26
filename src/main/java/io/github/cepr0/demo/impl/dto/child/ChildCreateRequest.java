package io.github.cepr0.demo.impl.dto.child;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.cepr0.demo.base.dto.CreateRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class ChildCreateRequest extends CreateRequest {

	@NotBlank
	private String name;

	@JsonProperty("parentIds")
	@NotNull
	@Size(min = 1, max = 2)
	private Set<Integer> parents;
}
