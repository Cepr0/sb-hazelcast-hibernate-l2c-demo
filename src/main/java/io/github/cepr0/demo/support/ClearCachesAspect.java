package io.github.cepr0.demo.support;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.cache.CacheManager;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Slf4j
@Aspect
@Component
public class ClearCachesAspect {

	private final CacheManager cacheManager;

	public ClearCachesAspect(@NonNull final CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	@Around("@annotation(clearCaches)")
	public Object clearCaches(@NonNull final ProceedingJoinPoint pjp, @NonNull final ClearCaches clearCaches) throws Throwable {
		Object result = pjp.proceed(pjp.getArgs());
		for (String cachePattern : clearCaches.value()) {
			cacheManager.getCacheNames()
					.stream()
					.filter(cacheName -> cacheName.contains(cachePattern))
					.forEach(cacheName -> ofNullable(cacheManager.getCache(cacheName)).ifPresent(cache -> {
						cache.clear();
						log.debug("[d] Cache '{}' evicted", cacheName);
					}));
		}
		return result;
	}
}
