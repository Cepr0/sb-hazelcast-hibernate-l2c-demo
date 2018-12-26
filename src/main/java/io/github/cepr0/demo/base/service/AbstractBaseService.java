package io.github.cepr0.demo.base.service;

import io.github.cepr0.demo.base.BaseEntity;
import io.github.cepr0.demo.base.BaseRepo;
import io.github.cepr0.demo.base.dto.BaseResponse;
import io.github.cepr0.demo.base.dto.CreateRequest;
import io.github.cepr0.demo.base.dto.UpdateRequest;
import io.github.cepr0.demo.base.mapper.BaseMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Transactional(readOnly = true)
public abstract class AbstractBaseService<
		T extends BaseEntity,
		C extends CreateRequest,
		U extends UpdateRequest,
		R extends BaseResponse
		> implements BaseService<C, U, R> {

	protected final BaseRepo<T> repo;
	protected final BaseMapper<T, C, U, R> mapper;

	public AbstractBaseService(BaseRepo<T> repo, BaseMapper<T, C, U, R> mapper) {
		this.repo = repo;
		this.mapper = mapper;
	}

	@Transactional
	@Override
	public R create(C request) {
		T entity = mapper.fromCreateRequest(request);
		return mapper.toResponse(repo.save(entity));
	}

	@Transactional
	@Override
	public Optional<R> update(Integer id, U request) {
		return repo.findById(id)
				.map(entity -> {
					T updated = mapper.fromUpdateRequest(entity, request);
					repo.flush();
					return mapper.toResponse(updated);
				});
	}

	@Transactional
	@Override
	public void delete(Integer id) {
		repo.deleteById(id);
	}

	@Override
	public Page<R> getAll(Pageable pageable) {
		return repo.findAll(pageable).map(mapper::toResponse);
	}

	@Override
	public List<R> getAll() {
		return repo.findAll().stream().map(mapper::toResponse).collect(toList());
	}

	@Override
	public Optional<R> getOne(Integer id) {
		return repo.findById(id).map(mapper::toResponse);
	}
}
