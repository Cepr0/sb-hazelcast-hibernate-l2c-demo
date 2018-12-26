package io.github.cepr0.demo.impl.dto.child;

import io.github.cepr0.demo.base.dto.BaseResponse;
import io.github.cepr0.demo.impl.dto.parent.ParentDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class ChildResponse extends BaseResponse {
	private String name;
	private Set<ParentDto> parents;
}
