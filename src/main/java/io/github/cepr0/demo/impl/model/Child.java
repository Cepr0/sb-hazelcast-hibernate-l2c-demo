package io.github.cepr0.demo.impl.model;

import io.github.cepr0.demo.base.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "name", callSuper = true)
@Entity
@Table(name = "children")
@DynamicInsert
@DynamicUpdate
@Cache(usage = READ_WRITE)
public class Child extends BaseEntity {

	@Column(columnDefinition = "text")
	private String name;

	@Cache(usage = READ_WRITE)
	@ManyToMany
	@JoinTable(
			name = "parents_children",
			joinColumns = @JoinColumn(name = "child_id"),
			inverseJoinColumns = @JoinColumn(name = "parent_id")
	)
	@BatchSize(size = 20)
	private Set<Parent> parents;

	public Child(String name, Collection<Parent> parents) {
		this.name = name;
		this.parents = new HashSet<>(parents);
	}
}
