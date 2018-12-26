package io.github.cepr0.demo.impl.repo;

import io.github.cepr0.demo.base.BaseRepo;
import io.github.cepr0.demo.impl.model.Child;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.QueryHint;
import java.util.List;

import static org.hibernate.annotations.QueryHints.CACHEABLE;

public interface ChildRepo extends BaseRepo<Child> {

	@QueryHints(value = @QueryHint(name = CACHEABLE, value = "true"))
	@Query("select c from Parent p join p.children c where p.id = ?1")
	List<Child> findChildrenByParentId(Integer id);
}
