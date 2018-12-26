package io.github.cepr0.demo.impl.mapper;

import io.github.cepr0.demo.base.mapper.BaseMapper;
import io.github.cepr0.demo.base.mapper.ReferenceMapper;
import io.github.cepr0.demo.impl.dto.parent.*;
import io.github.cepr0.demo.impl.model.Parent;
import io.github.cepr0.demo.impl.repo.ParentRepo;
import lombok.Getter;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", nullValueCheckStrategy = ALWAYS, unmappedTargetPolicy = IGNORE, uses = ChildMapper.class)
public abstract class ParentMapper implements BaseMapper<Parent, ParentCreateRequest, ParentUpdateRequest, ParentResponse>, ReferenceMapper<Parent> {
	@Autowired @Getter private ParentRepo repo;

	public abstract ChildrenNumberDto toChildrenNumberDto(ChildrenNumberProjection projection);
}
