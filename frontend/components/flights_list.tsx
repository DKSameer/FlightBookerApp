import React from 'react';
import Link from "next/link";
import { ReactElement, useState } from "react";
import { GlobalContext } from '../context/gcontext';

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
    const { current_query_id, flights } = React.useContext(GlobalContext);
    console.log("flights LIST qid: "+current_query_id);
    console.log(flights);

    return (
        <div className="flex flex-col justify-center items-center">
            <div className="font-semibold m-4">Flight Options</div>
            {flights.map(
                (flight) => (
                    <Link href="/information" key={flight.id}>
                        <div className="flex bg-blue-500 w-fit m-2 hover:cursor-pointer">
                            <div>
                                <p className="m-1">{flight.id}</p>
                                <p className="m-1">{flight.origin}</p>
                                <p className="m-1">{flight.destination}</p>
                                <p className="m-1">{flight.flightList.totalPrice}€</p>
                            </div>
                            <div>
                                
                            </div>
                        </div>
                    </Link>
                ))}
        </div>
    );
}
