package io.github.cepr0.demo.impl.dto.parent;

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
public class ParentUpdateRequest extends UpdateRequest {

	private String name;

	@JsonProperty("childIds")
	@Size(min = 1, max = 2)
	private Set<Integer> children;

}
