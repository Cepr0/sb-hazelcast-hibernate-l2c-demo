package io.github.cepr0.demo.impl.mapper;

import io.github.cepr0.demo.base.mapper.BaseMapper;
import io.github.cepr0.demo.base.mapper.ReferenceMapper;
import io.github.cepr0.demo.impl.dto.child.ChildCreateRequest;
import io.github.cepr0.demo.impl.dto.child.ChildDto;
import io.github.cepr0.demo.impl.dto.child.ChildResponse;
import io.github.cepr0.demo.impl.dto.child.ChildUpdateRequest;
import io.github.cepr0.demo.impl.model.Child;
import io.github.cepr0.demo.impl.repo.ChildRepo;
import lombok.Getter;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", nullValueCheckStrategy = ALWAYS, unmappedTargetPolicy = IGNORE, uses = ParentMapper.class)
public abstract class ChildMapper implements BaseMapper<Child, ChildCreateRequest, ChildUpdateRequest, ChildResponse>, ReferenceMapper<Child> {
	@Autowired @Getter private ChildRepo repo;

	public abstract ChildDto toChildDto(Child child);
}
