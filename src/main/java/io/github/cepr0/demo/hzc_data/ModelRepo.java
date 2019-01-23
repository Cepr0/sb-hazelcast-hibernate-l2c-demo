package io.github.cepr0.demo.hzc_data;

import org.springframework.data.hazelcast.repository.HazelcastRepository;

import java.util.UUID;

public interface ModelRepo extends HazelcastRepository<Model, UUID> {
}
