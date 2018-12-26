package io.github.cepr0.demo.base;

import io.github.cepr0.demo.base.dto.BaseResponse;
import io.github.cepr0.demo.base.dto.CreateRequest;
import io.github.cepr0.demo.base.dto.UpdateRequest;
import io.github.cepr0.demo.base.service.BaseService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public abstract class BaseController<C extends CreateRequest, U extends UpdateRequest, R extends BaseResponse> {

	protected final BaseService<C, U, R> service;

	public BaseController(BaseService<C, U, R> service) {
		this.service = service;
	}

	@PostMapping
	protected ResponseEntity<?> create(@Valid @RequestBody C request) {
		return ResponseEntity.ok(service.create(request));
	}

	@PatchMapping("/{id}")
	protected ResponseEntity<?> update(@PathVariable("id") Integer id, @Valid @RequestBody U request) {
		return ResponseEntity.of(service.update(id, request));
	}

	@DeleteMapping("/{id}")
	protected ResponseEntity<?> delete(@PathVariable("id") Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	protected ResponseEntity<?> getOne(@PathVariable("id") Integer id) {
		return ResponseEntity.of(service.getOne(id));
	}

	protected ResponseEntity<?> getAll(Pageable pageable) {
		return ResponseEntity.ok(service.getAll(pageable));
	}

	protected ResponseEntity<?> getAll() {
		return ResponseEntity.ok(service.getAll());
	}
}
