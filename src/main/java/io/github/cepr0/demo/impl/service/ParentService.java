package io.github.cepr0.demo.impl.service;

import io.github.cepr0.demo.base.service.AbstractBaseService;
import io.github.cepr0.demo.impl.dto.child.ChildDto;
import io.github.cepr0.demo.impl.dto.parent.*;
import io.github.cepr0.demo.impl.mapper.ChildMapper;
import io.github.cepr0.demo.impl.mapper.ParentMapper;
import io.github.cepr0.demo.impl.model.Parent;
import io.github.cepr0.demo.impl.repo.ChildRepo;
import io.github.cepr0.demo.impl.repo.ParentRepo;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@Transactional(readOnly = true)
public class ParentService extends AbstractBaseService<Parent, ParentCreateRequest, ParentUpdateRequest, ParentResponse> {

	private final ChildRepo childRepo;
	private final ChildMapper childMapper;

	public ParentService(ParentRepo repo, ParentMapper mapper, ChildRepo childRepo, ChildMapper childMapper) {
		super(repo, mapper);
		this.childRepo = childRepo;
		this.childMapper = childMapper;
	}

	public List<ChildDto> getChildren(Integer id) {
		return childRepo.findChildrenByParentId(id).stream().map(childMapper::toChildDto).collect(toList());
	}

	@CacheEvict(value = "childrenNumber", key = "#id")
	@Transactional
	@Override
	public Optional<ParentResponse> update(Integer id, ParentUpdateRequest request) {
		return super.update(id, request);
	}

	@Cacheable("childrenNumber")
	public ChildrenNumberDto getChildrenNumber(Integer id) {
		ChildrenNumberProjection projection = ((ParentRepo) repo).getChildrenNumber(id);
		return ((ParentMapper) mapper).toChildrenNumberDto(projection);
	}
}
