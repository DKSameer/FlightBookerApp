import Link from "next/link";
import { ReactElement, useState } from "react";

class Flight {
    flight_id: number;
    origin: string;
    destination: string;
    departure_date: Date;
    layover: Array<String>;
    luggage: boolean;
    price: number;

    constructor(
        flight_id: number, origin: string, destination: string, 
        departure_date: Date, layover: Array<string>, luggage: boolean,
        price: number){
            
        this.flight_id = flight_id;
        this.origin = origin;
        this.destination = destination;
        this.departure_date = departure_date;
        this.layover = layover;
        this.luggage = luggage;
        this.price = price;
    }
}

export default function FlightsList(): ReactElement {
    /* 
        TODO: fetch flight options
    */
    const [flights, setFlights] = useState<Array<Flight>>(new Array<Flight>(
        new Flight(1, "porto", "lisbon", new Date(2022, 10, 7, 3, 30, 0, 0), new Array<string>("100 montaditos"), false, 300),
        new Flight(2, "zimbabwe", "kenia", new Date(2022, 10, 7, 3, 30, 0, 0), new Array<string>("100 montaditos"), false, 300),
        new Flight(3, "china", "mars", new Date(2022, 10, 7, 3, 30, 0, 0), new Array<string>("100 montaditos"), false, 300),
        new Flight(4, "ok", "xd", new Date(2022, 10, 7, 3, 30, 0, 0), new Array<string>("100 montaditos"), false, 700)
    ));
    
    return (
        <div className="flex flex-col justify-center items-center">
            <div className="font-semibold m-4">Flight Options</div>
            {flights.map(
                (flight) => (
                    <Link href="/information" key={flight.flight_id}>
                        <div className="flex bg-blue-500 w-fit m-2 hover:cursor-pointer">
                            <p className="m-1">{flight.flight_id}</p>
                            <p className="m-1">{flight.origin}</p>
                            <p className="m-1">{flight.destination}</p>
                            <p className="m-1">{flight.price}€</p>
                        </div>
                    </Link>
                ))}
        </div>
    );
}
