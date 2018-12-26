package io.github.cepr0.demo.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.springframework.data.domain.Persistable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.io.Serializable;
import java.util.Objects;

import static javax.persistence.GenerationType.SEQUENCE;
import static org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE;

@Getter
@Setter
@NoArgsConstructor
@ToString(of = "id")
@MappedSuperclass
@Cache(usage = READ_WRITE)
public abstract class BaseEntity implements Serializable, Persistable<Integer> {

	@Id
	@GeneratedValue(strategy = SEQUENCE)
	private Integer id;

	@Version
	private Integer version;

	@Override
	public boolean isNew() {
		return getId() == null;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof BaseEntity)) return false;
		BaseEntity that = (BaseEntity) o;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}
}
