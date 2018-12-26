package io.github.cepr0.demo.impl.api;

import io.github.cepr0.demo.base.BaseController;
import io.github.cepr0.demo.impl.dto.child.ChildCreateRequest;
import io.github.cepr0.demo.impl.dto.child.ChildResponse;
import io.github.cepr0.demo.impl.dto.child.ChildUpdateRequest;
import io.github.cepr0.demo.impl.service.ChildService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("children")
public class ChildController extends BaseController<ChildCreateRequest, ChildUpdateRequest, ChildResponse> {

	public ChildController(ChildService service) {
		super(service);
	}

	@GetMapping("/{id}/parents")
	public ResponseEntity<?> getParents(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(((ChildService) service).getParents(id));
	}

	@GetMapping("/{id}/searchParents")
	public ResponseEntity<?> searchParents(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(((ChildService) service).searchParents(id));
	}
}
