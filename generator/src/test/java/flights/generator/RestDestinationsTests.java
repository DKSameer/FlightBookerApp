package flights.generator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import flights.generator.FlightRest.RestDestinations;

@SpringBootTest
class RestDestinationsTests {

    enum Locations {
        SAO_PAULO(0),
        SEVILLA(1),
        MADRID(2),
        DUBLIN(3),
        LISBON(4);

        public int value;

        Locations(int value) {
            this.value = value;
        }
    }

    @Test
    void testRandomDestinations() {
        RestDestinations dest = new RestDestinations();
        //System.out.println(dest.initializeDestinations(Locations.MADRID.value).toString());
        assertTrue(dest.initializeDestinations(Locations.MADRID.value).size() >= 1);
    }

}
