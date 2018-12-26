package io.github.cepr0.demo.impl.dto.child;

import io.github.cepr0.demo.base.dto.BaseResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChildResponse extends BaseResponse {
	private String name;
}
