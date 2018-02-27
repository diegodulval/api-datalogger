package br.com.habeis.api.repositories;

import br.com.habeis.api.domain.Feed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@Repository
public interface FeedRepository extends JpaRepository<Feed, Integer>, JpaSpecificationExecutor {

}
