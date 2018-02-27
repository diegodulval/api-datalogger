package br.com.habeis.api.repositories;

import br.com.habeis.api.domain.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Integer>, JpaSpecificationExecutor {

}
