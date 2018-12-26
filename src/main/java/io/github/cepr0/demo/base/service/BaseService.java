package io.github.cepr0.demo.base.service;

import io.github.cepr0.demo.base.dto.BaseResponse;
import io.github.cepr0.demo.base.dto.CreateRequest;
import io.github.cepr0.demo.base.dto.UpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BaseService <C extends CreateRequest, U extends UpdateRequest, R extends BaseResponse> {
	R create(C request);
	Optional<R> update(Integer id, U request);
	void delete(Integer id);

	Optional<R> getOne(Integer id);
	List<R> getAll();
	Page<R> getAll(Pageable pageable);
}
