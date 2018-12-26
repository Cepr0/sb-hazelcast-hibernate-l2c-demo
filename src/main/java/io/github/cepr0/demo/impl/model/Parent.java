package io.github.cepr0.demo.impl.model;

import io.github.cepr0.demo.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "parents")
@Cache(usage = READ_WRITE)
public class Parent extends BaseEntity {

	@Column(columnDefinition = "text")
	private String name;

	@Cache(usage = READ_WRITE)
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	@JoinTable(
			name = "parents_children",
			joinColumns = @JoinColumn(name = "parent_id"),
			inverseJoinColumns = @JoinColumn(name = "child_id")
	)
	@BatchSize(size = 20)
	private Set<Child> children;

	public Parent(String name, Collection<Child> children) {
		this.name = name;
		this.children = new HashSet<>(children);
	}
}
