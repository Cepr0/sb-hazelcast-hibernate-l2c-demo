package io.github.cepr0.demo;

import io.github.cepr0.demo.impl.repo.ParentRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.event.EventListener;

import java.util.concurrent.ThreadLocalRandom;

@EnableCaching
@SpringBootApplication
public class Application {

	@Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
	private Integer batchSize;

	private final ParentRepo parentRepo;

	public Application(ParentRepo parentRepo) {
		this.parentRepo = parentRepo;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@EventListener
	public void demoData(ApplicationReadyEvent e) {
		if (parentRepo.count() > 0) return;

		ThreadLocalRandom r = ThreadLocalRandom.current();

//		List<Parent> parents = new ArrayList<>(batchSize);
//		for (int i = 1; i <= 1_000; i++) {
//			List<Child> children = new ArrayList<>();
//
//			int childrenNumber = r.nextInt(2,6);
//			for (int j = 1; j <= childrenNumber; j++) {
//				children.add(new Child("child_" + i + "_" + j));
//			}
//
//			parents.add(new Parent("parent_m_" + i, children));
//			parents.add(new Parent("parent_f_" + i, children));
//
//			if (parents.size() >= batchSize) {
//				parentRepo.saveAll(parents);
//				parents.clear();
//			}
//		}
	}
}

