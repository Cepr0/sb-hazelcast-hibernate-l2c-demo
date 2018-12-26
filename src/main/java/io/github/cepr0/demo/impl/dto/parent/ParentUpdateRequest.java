package io.github.cepr0.demo.impl.dto.parent;

import io.github.cepr0.demo.base.dto.UpdateRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class ParentUpdateRequest extends UpdateRequest {
	@NotBlank private String name;
}
