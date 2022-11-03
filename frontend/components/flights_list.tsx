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


function SingleFlight(props: any): ReactElement{
    const layover = props.layover;
    const airline =  props.airline;
    if(layover == 0){
        return <p className="ml-8">Airline: <span className="font-semibold">{airline}</span></p>
    }
    return <></>;
}

function Scales(props: any): ReactElement {
    const layover = props.layover;
    const scales = props.scales_list;
    if(layover > 0){
        return (
            <div>
                <p className="p-1 font-semibold">Scales</p>
                <div>
                    {scales.map(
                        (scale: any) => (
                            <div className="border rounded border-sky-200 m-1" key={scale.flightNumber}>
                                <div className="flex pl-4">
                                    <p className="m-1">Origin: <span className="font-semibold">{scale.location}</span></p>
                                    <p className="m-1">-</p>
                                    <p className="m-1">Destination: <span className="font-semibold">{scale.destination}</span></p>
                                </div>
                                <p className="ml-8">Airline: <span className="font-semibold">{scale.airline}</span></p>
                            </div>
                        )
                    )}
                </div>
            </div>
        )
    }
    return <></>;
}

export default function FlightsList(): ReactElement {
    const { current_query_id, flights } = React.useContext(GlobalContext);

    return (
        <div className="flex flex-col justify-center items-center">
            <div className="font-semibold m-4 mb-1">Available Flights</div>
                <div className="flex justify-evenly flex-wrap overflow-y-scroll h-96 w-1/2 my-14 border rounded border-sky-300">
                    {flights.map(
                        (flight: any) => (
                            <Link href="/information" key={flight.id} className="flex bg-sky-300 hover:bg-sky-400 w-fit p-2 m-4 select-none text-gray-800 hover:cursor-pointer border rounded border-sky-300">
                                    <div>
                                        <p className="m-1">Flight: <span className="font-semibold">{flight.id}</span></p>
                                        <div className="border rounded border-sky-200 m-1">
                                            <div className="flex">
                                                <p className="m-1">Origin: <span className="font-semibold">{flight.origin}</span></p>
                                                <p className="m-1">-</p>
                                                <p className="m-1">Destination: <span className="font-semibold">{flight.destination}</span></p>
                                            </div>
                                            {<SingleFlight layover={flight.flightList.totalLayover} airline={flight.flightList.list[0].airline}></SingleFlight>}
                                        </div>
                                        <div>
                                            {<Scales layover={flight.flightList.totalLayover} scales_list={flight.flightList.list}></Scales>}
                                        </div>
                                        <p className="m-1">Departure: <span className="font-semibold">{flight.flightList.date}</span></p>
                                        <p className="m-1">Price: <span className="font-semibold">{flight.flightList.totalPrice}€</span></p>
                                    </div>
                            </Link>
                        ))}
                </div>
        </div>
    );
}
