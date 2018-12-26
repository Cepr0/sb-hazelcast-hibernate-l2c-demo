package io.github.cepr0.demo.base.mapper;

import io.github.cepr0.demo.base.BaseEntity;
import io.github.cepr0.demo.base.BaseRepo;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public interface ReferenceMapper<T extends BaseEntity> {

	BaseRepo<T> getRepo();

	default T toReference(@NonNull Integer id) {
		return getRepo().getOne(id);
	}

	default Integer toId(@NonNull T element) {
		return element.getId();
	}

	@Nullable
	default Set<T> toRefSet(@Nullable Collection<Integer> ids) {
		if (ids == null) return null;
		return ids.stream().map(this::toReference).collect(toSet());
	}

	default Set<Integer> toIdSet(@NonNull Collection<T> elements) {
		return elements.stream().map(this::toId).collect(toSet());
	}

	@Nullable
	default List<T> toRefList(@Nullable Collection<Integer> ids) {
		if (ids == null) return null;
		return ids.stream().map(this::toReference).collect(toList());
	}

	default List<Integer> toIdList(@NonNull Collection<T> elements) {
		return elements.stream().map(this::toId).collect(toList());
	}
}