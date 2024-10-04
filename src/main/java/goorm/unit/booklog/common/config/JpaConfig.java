package goorm.unit.booklog.common.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;

@EntityScan(basePackages = "goorm.unit.booklog")
@EnableJpaRepositories(basePackages = "goorm.unit.booklog")
public class JpaConfig {

	private final EntityManager entityManager;

	public JpaConfig(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Bean
	public JPAQueryFactory jpaQueryFactory() {
		return new JPAQueryFactory(entityManager);
	}
}

