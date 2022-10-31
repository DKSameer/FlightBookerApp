package flights.generator.Flights;


import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<FlightRequest, Long>{
}

