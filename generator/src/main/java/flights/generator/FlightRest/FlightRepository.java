package flights.generator.FlightRest;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<FlightRequest, Long>{
}

