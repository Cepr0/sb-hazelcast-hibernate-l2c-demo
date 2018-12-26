package io.github.cepr0.demo.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.lang.NonNull;

import javax.persistence.QueryHint;
import java.util.List;
import java.util.Optional;

import static org.hibernate.annotations.QueryHints.CACHEABLE;

@NoRepositoryBean
public interface BaseRepo<T extends BaseEntity> extends JpaRepository<T, Integer> {

	@QueryHints(value = @QueryHint(name = CACHEABLE, value = "true"))
	@Override
	@NonNull
	Optional<T> findById(@NonNull Integer id);

	@QueryHints(value = @QueryHint(name = CACHEABLE, value = "true"))
	@Override
	void deleteById(@NonNull Integer id);

	@QueryHints(value = @QueryHint(name = CACHEABLE, value = "true"))
	@Override
	@NonNull
	Page<T> findAll(@NonNull Pageable pageable);

	@QueryHints(value = @QueryHint(name = CACHEABLE, value = "true"))
	@Override
	@NonNull
	List<T> findAll();
}
