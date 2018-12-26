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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

import static org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "name", callSuper = true)
@Entity
@Table(name = "parents")
@DynamicInsert
@DynamicUpdate
@Cache(usage = READ_WRITE)
public class Parent extends BaseEntity {

	@Column(columnDefinition = "text")
	private String name;

	@Cache(usage = READ_WRITE)
	@ManyToMany(mappedBy = "parents")
	@BatchSize(size = 20)
	private Set<Child> children;

	public Parent(String name) {
		this.name = name;
	}
}
