package io.github.cepr0.demo.base.mapper;

import io.github.cepr0.demo.base.BaseEntity;
import io.github.cepr0.demo.base.BaseRepo;
import org.springframework.lang.NonNull;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public interface ReferenceMapper<T extends BaseEntity> {

	BaseRepo<T> getRepo();

	default T toReference(Integer id) {
		return getRepo().getOne(id);
	}

	default Integer toId(T element) {
		return element.getId();
	}

	default Set<T> toRefSet(@NonNull Collection<Integer> ids) {
		return ids.stream().map(this::toReference).collect(toSet());
	}

	default Set<Integer> toIdSet(@NonNull Collection<T> elements) {
		return elements.stream().map(this::toId).collect(toSet());
	}

	default List<T> toRefList(@NonNull Collection<Integer> ids) {
		return ids.stream().map(this::toReference).collect(toList());
	}

	default List<Integer> toIdList(@NonNull Collection<T> elements) {
		return elements.stream().map(this::toId).collect(toList());
	}
}