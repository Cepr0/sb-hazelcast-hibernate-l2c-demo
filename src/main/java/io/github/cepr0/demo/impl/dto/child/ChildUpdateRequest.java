package io.github.cepr0.demo.impl.dto.child;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.cepr0.demo.base.dto.UpdateRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class ChildUpdateRequest extends UpdateRequest {

	private String name;

	@JsonProperty("parentIds")
	@Size(min = 1, max = 2)
	private Set<Integer> parents;
}
