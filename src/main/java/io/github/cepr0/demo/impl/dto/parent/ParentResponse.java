package io.github.cepr0.demo.impl.dto.parent;

import io.github.cepr0.demo.base.dto.BaseResponse;
import io.github.cepr0.demo.impl.dto.child.ChildResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class ParentResponse extends BaseResponse {
	private String name;
	private Set<ChildResponse> children;
}
