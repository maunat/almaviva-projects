package it.almaviva.cassandra;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MeasureRepository extends CassandraRepository<Measure, UUID> {

}
