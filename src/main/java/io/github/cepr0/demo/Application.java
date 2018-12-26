package io.github.cepr0.demo;

import io.github.cepr0.demo.impl.model.Child;
import io.github.cepr0.demo.impl.model.Parent;
import io.github.cepr0.demo.impl.repo.ChildRepo;
import io.github.cepr0.demo.impl.repo.ParentRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@EnableAsync
@EnableCaching
@SpringBootApplication
public class Application {

	@Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
	private Integer batchSize;

	private final ParentRepo parentRepo;
	private final ChildRepo childRepo;
	private final ApplicationEventPublisher publisher;

	public Application(ParentRepo parentRepo, ChildRepo childRepo, ApplicationEventPublisher publisher) {
		this.parentRepo = parentRepo;
		this.childRepo = childRepo;
		this.publisher = publisher;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@EventListener
	public void demoData(ApplicationReadyEvent e) {
		if (parentRepo.count() > 0) return;
		for (int i = 0; i < 10_000 / batchSize; i++) {
			publisher.publishEvent(new BatchSaveEvent(i));
		}
	}

	@Async
	@EventListener
	@Transactional
	public void batchSave(BatchSaveEvent e) {
		int iteration = e.getIteration();
		ThreadLocalRandom r = ThreadLocalRandom.current();
		List<Parent> parentAccumulator = new ArrayList<>();
		List<Child> childAccumulator = new ArrayList<>();

		for (int i = 1; i <= batchSize; i++) {
			int familyNumber = i + iteration * batchSize;
			List<Parent> parents = List.of(
					new Parent("mother_" + familyNumber),
					new Parent("father_" + familyNumber)
			);
			List<Child> children = new ArrayList<>();
			int childrenNumber = r.nextInt(2,6);
			for (int j = 1; j <= childrenNumber; j++) {
				children.add(new Child("child_" + familyNumber + "_" + j, parents));
			}
			parentAccumulator.addAll(parents);
			childAccumulator.addAll(children);
		}
		parentRepo.saveAll(parentAccumulator);
		childRepo.saveAll(childAccumulator);
	}

	@lombok.Value
	private static class BatchSaveEvent {
		private int iteration;
	}
}

