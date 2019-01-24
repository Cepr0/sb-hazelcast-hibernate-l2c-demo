package io.github.cepr0.demo.hzc_data;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static java.util.Optional.ofNullable;

@RestController
@RequestMapping("models")
public class ModelController {

	private final ModelRepo modelRepo;

	public ModelController(final ModelRepo modelRepo) {
		this.modelRepo = modelRepo;
	}

	@PostMapping
	public Model create(@RequestBody @Valid final Model model) {
		return modelRepo.save(Model.builder()
				.name(model.getName())
				.email(model.getEmail())
				.build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable final UUID id) {
		modelRepo.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@PatchMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable final UUID id, @RequestBody @Valid final Model model) {
		return ResponseEntity.of(modelRepo.findById(id).map(m -> {
			ofNullable(model.getName()).ifPresent(m::setName);
			ofNullable(model.getEmail()).ifPresent(m::setEmail);
			return modelRepo.save(m);
		}));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> get(@PathVariable final UUID id) {
		return ResponseEntity.of(modelRepo.findById(id));
	}

	@GetMapping()
	public List<Model> getAll(
			@RequestParam(value = "name", required = false, defaultValue = "") String name,
			@RequestParam(value = "email", required = false, defaultValue = "") String email,
			Sort sort
	) {
		return modelRepo.findAllByNameContainingAndEmailContaining(name, email);
	}
}
