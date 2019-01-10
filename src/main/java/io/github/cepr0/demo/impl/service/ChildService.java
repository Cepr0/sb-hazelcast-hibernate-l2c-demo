package io.github.cepr0.demo.impl.service;

import io.github.cepr0.demo.base.service.AbstractBaseService;
import io.github.cepr0.demo.impl.dto.child.ChildCreateRequest;
import io.github.cepr0.demo.impl.dto.child.ChildResponse;
import io.github.cepr0.demo.impl.dto.child.ChildUpdateRequest;
import io.github.cepr0.demo.impl.dto.parent.ParentDto;
import io.github.cepr0.demo.impl.mapper.ChildMapper;
import io.github.cepr0.demo.impl.mapper.ParentMapper;
import io.github.cepr0.demo.impl.model.Child;
import io.github.cepr0.demo.impl.repo.ChildRepo;
import io.github.cepr0.demo.impl.repo.ParentRepo;
import io.github.cepr0.demo.support.ClearCaches;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Transactional(readOnly = true)
@Service
public class ChildService extends AbstractBaseService<Child, ChildCreateRequest, ChildUpdateRequest, ChildResponse> {

	private final ParentRepo parentRepo;
	private final ParentMapper parentMapper;

	public ChildService(ChildRepo repo, ChildMapper mapper, ParentRepo parentRepo, ParentMapper parentMapper) {
		super(repo, mapper);
		this.parentRepo = parentRepo;
		this.parentMapper = parentMapper;
	}

	@ClearCaches("Parent.children")
	@Transactional
	@Override
	public ChildResponse create(ChildCreateRequest request) {
		return super.create(request);
	}

	@ClearCaches("Parent.children")
	@Transactional
	@Override
	public void delete(Integer id) {
		super.delete(id);
	}

	public List<ParentDto> getParents(Integer id) {
		return parentRepo.findParentsByChildId(id).stream().map(parentMapper::toParentDto).collect(Collectors.toList());
	}

	public List<ParentDto> searchParents(Integer id) {
		return parentRepo.searchParentsByChildId(id).stream().map(parentMapper::toParentDto).collect(Collectors.toList());
	}
}
