import React from 'react';
import Link from "next/link";
import { ReactElement, useState } from "react";
import { GlobalContext } from '../context/gcontext';

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
                            <div className="border rounded border-sky-200 m-1 hover:bg-sky-300" key={scale.flightNumber}>
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
    const { current_query_id, flights, setFlights } = React.useContext(GlobalContext);

    const [airline_filter, setAirlineFilter] = useState<string>("");
    const [luggage_filter, setLuggageFilter] = useState<string>("");
    const [scales_filter, setScalesFilter] = useState<string>("");

    async function fetch_flights_filter(): Promise<void>{
        const url = `http://localhost:8080/destination/${current_query_id}/filter?${airline_filter !== "" ? "airline="+airline_filter : ""}${luggage_filter !== "" ? "&luggage="+luggage_filter : ""}${scales_filter !== "" ? "&scales="+scales_filter : ""}`;

        console.log("URL: " + url);

        await fetch(url, {
            method: "GET",
        })
        .then((response) => {
            return response.json();
        })
        .then((data) => {
            console.log(data);
            setFlights(data);
        })
        return;
    }

    function on_airline_filter_change(event: React.ChangeEvent<HTMLSelectElement>): void{
        setAirlineFilter(event.target.value);
        return;
    }

    function on_luggage_filter_change(event: React.ChangeEvent<HTMLSelectElement>): void{
        setLuggageFilter(event.target.value);
        return;
    }

    function on_scales_filter_change(event: React.ChangeEvent<HTMLSelectElement>): void{
        setLuggageFilter(event.target.value);
        return;
    }

    return (
        <div className="flex flex-col flex-wrap justify-center items-center px-10">
            <div className="font-semibold m-4 mb-1">Available Flights</div>
                <div className="flex flex-col mt-8">
                    <div id="filter_wrapper" className="flex flex-row w-full justify-center">
                        <div className="m-4 p-1">
                            <p>Airline</p>
                            <select onChange={on_airline_filter_change} className="w-32 p-1 border rounded">
                                <option value="" key="">None</option>
                                <option value="Emirates" key="Emirates">Emirates</option>
                                <option value="Qatar Airways" key="Qatar Airways">Qatar Airways</option>
                                <option value="Singapore Airlines" key="Singapore Airlines">Singapore Airlines</option>
                                <option value="Japan Airlines" key="Japan Airlines">Japan Airlines</option>
                                <option value="Turkish Airlines" key="Turkish Airlines">Turkish Airlines</option>
                                <option value="Air France" key="Air France">Air France</option>
                                <option value="Korean Air" key="Korean Air">Korean Air</option>
                                <option value="Swiss International Air" key="Swiss International Air">Swiss International Air</option>
                                <option value="British Airways" key="British Airways">British Airways</option>
                                <option value="Lufthansa" key="Lufthansa">Lufthansa</option>
                            </select>
                        </div>
                        <div className="m-4 p-1">
                            <p>Luggage</p>
                            <select onChange={on_luggage_filter_change} className="w-32 p-1 border rounded">
                                <option value="" key="">None</option>
                                <option value="true" key="true">Yes</option>
                                <option value="false" key="false">No</option>
                            </select>
                        </div>
                        <div className="m-4 p-1">
                            <p>Scales</p>
                            <select onChange={on_luggage_filter_change} className="w-32 p-1 border rounded">
                                <option value="" key="">None</option>
                                <option value="0" key="0">0</option>
                                <option value="1" key="1">1</option>
                                <option value="2" key="2">2</option>
                            </select>
                        </div>
                        <div className="flex justify-center items-center w-fit h-fit p-2 border rounded hover:bg-sky-400 hover:cursor-pointer mx-4 mb-1 font-semibold mt-10 bg-sky-300">
                            <button onClick={fetch_flights_filter}>Filter Flights</button>
                        </div>
                    </div> 
                </div>
                <div className="flex justify-evenly flex-wrap overflow-y-scroll h-96 w-full my-14 border rounded border-sky-300">
                    {flights.map(
                        (flight: any) => (
                            <Link href={{pathname:"/information", query:{price:flight.flightList.totalPrice}}} key={flight.id} className="flex justify-center items-center bg-sky-300 hover:bg-sky-400 w-2/3 p-2 m-4 select-none text-gray-800 hover:cursor-pointer border rounded border-sky-300">
                                <div>
                                    <p className="m-1">Flight: <span className="font-semibold">{flight.id}</span></p>
                                    <div className="border rounded border-sky-200 m-1 hover:bg-sky-300">
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
                                    <p className="m-1">Luggage: <span className="font-semibold">{flight.flightList.allowLuggage ? "Yes" : "No"}</span></p>
                                    <p className="m-1">Price: <span className="font-semibold">{flight.flightList.totalPrice}€</span></p>
                                </div>
                            </Link>
                        ))}
                </div>
        </div>
    );
}
