package io.github.cepr0.demo.base.mapper;

import io.github.cepr0.demo.base.BaseEntity;
import io.github.cepr0.demo.base.dto.BaseResponse;
import io.github.cepr0.demo.base.dto.CreateRequest;
import io.github.cepr0.demo.base.dto.UpdateRequest;
import org.mapstruct.MappingTarget;

public interface BaseMapper<T extends BaseEntity, C extends CreateRequest, U extends UpdateRequest, R extends BaseResponse> {
	T fromCreateRequest(C request);
	T fromUpdateRequest(@MappingTarget T element, U request);
	R toResponse(T element);
}
