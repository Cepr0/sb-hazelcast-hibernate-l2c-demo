package io.github.cepr0.demo.impl.api;

import io.github.cepr0.demo.base.BaseController;
import io.github.cepr0.demo.impl.dto.parent.ParentCreateRequest;
import io.github.cepr0.demo.impl.dto.parent.ParentResponse;
import io.github.cepr0.demo.impl.dto.parent.ParentUpdateRequest;
import io.github.cepr0.demo.impl.service.ParentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("parents")
public class ParentController extends BaseController<ParentCreateRequest, ParentUpdateRequest, ParentResponse> {

	public ParentController(ParentService service) {
		super(service);
	}

	@GetMapping("/{id}/children")
	public ResponseEntity<?> getChildren(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(((ParentService) service).getChildren(id));
	}

	@GetMapping("/{id}/childrenNumber")
	public ResponseEntity<?> getChildrenNumber(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(((ParentService) service).getChildrenNumber(id));
	}
}
