package io.github.cepr0.demo.impl.model;

import io.github.cepr0.demo.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import static org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "children")
@Cache(usage = READ_WRITE)
public class Child extends BaseEntity {

	@Column(columnDefinition = "text")
	private String name;

	public Child(String name) {
		this.name = name;
	}
}
