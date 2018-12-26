package io.github.cepr0.demo.impl.dto.child;

import io.github.cepr0.demo.base.dto.CreateRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChildCreateRequest extends CreateRequest {
	private String name;
}
