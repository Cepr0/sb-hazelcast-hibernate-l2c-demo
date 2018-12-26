package io.github.cepr0.demo.impl.repo;

import io.github.cepr0.demo.base.BaseRepo;
import io.github.cepr0.demo.impl.dto.parent.ChildrenNumberProjection;
import io.github.cepr0.demo.impl.model.Parent;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.QueryHint;
import java.util.List;

import static org.hibernate.annotations.QueryHints.CACHEABLE;

public interface ParentRepo extends BaseRepo<Parent> {

	@QueryHints(value = @QueryHint(name = CACHEABLE, value = "true"))
	@Query("select p from Parent p join p.children c where c.id = ?1")
	List<Parent> findParentsByChildId(Integer id);

	@QueryHints(value = @QueryHint(name = CACHEABLE, value = "true"))
	@Query(value = "" +
			"select " +
			"p.* " +
			"from " +
			"parents p " +
			"join parents_children pc on p.id = pc.parent_id and pc.child_id = ?1" +
			"", nativeQuery = true)
	List<Parent> searchParentsByChildId(Integer id);

	@Query(value = "select count(*) as childrenNumber from parents_children where parent_id = ?1", nativeQuery = true)
	ChildrenNumberProjection getChildrenNumber(Integer id);
}
