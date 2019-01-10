package io.github.cepr0.demo.impl.service;

import io.github.cepr0.demo.base.service.AbstractBaseService;
import io.github.cepr0.demo.impl.dto.child.ChildDto;
import io.github.cepr0.demo.impl.dto.parent.*;
import io.github.cepr0.demo.impl.mapper.ChildMapper;
import io.github.cepr0.demo.impl.mapper.ParentMapper;
import io.github.cepr0.demo.impl.model.Parent;
import io.github.cepr0.demo.impl.repo.ChildRepo;
import io.github.cepr0.demo.impl.repo.ParentRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Slf4j
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

	@Transactional
	@Override
	public Optional<ParentResponse> update(Integer parentId, ParentUpdateRequest request) {
		return super.update(parentId, request);
	}

	@CacheEvict(value = "Parent.childrenNumber", key = "#parentId")
	@Override
	public void delete(Integer parentId) {
		super.delete(parentId);
	}

	public List<ChildDto> getChildren(Integer parentId) {
		return childRepo.findChildrenByParentId(parentId)
				.stream()
				.map(childMapper::toChildDto)
				.collect(toList());
	}

	@Cacheable("Parent.childrenNumber")
	public ChildrenNumberDto getChildrenNumber(Integer parentId) {
		ChildrenNumberProjection projection = ((ParentRepo) repo).getChildrenNumber(parentId);
		return ((ParentMapper) mapper).toChildrenNumberDto(projection);
	}
}
