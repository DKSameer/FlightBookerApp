import React from 'react';
import { ReactElement, useState } from "react";
import Calendar from "../components/calendar";
import Link from "next/link";
import axios from "axios";
import { GlobalContext } from '../context/gcontext';

export default function FlightDetails(): ReactElement {
    const { current_query_id, setCurrentQueryId, setFlights } = React.useContext(GlobalContext);

    const origins = new Array<string>("Sao Paulo", "Sevilla", "Madrid", "Dublin", "Lisbon");
    const [selected_origin, setSelectedOrigin] = useState<string>(origins[0]);

    const [destinations, setDestinations] = useState<Array<string>>(new Array<string>());
    const [selected_destination, setSelectedDestination] = useState<string>("");

    const flight_types = new Array<string>("One-way", "Round-trip");
    const [selected_flight_type, setSelectedFlightType] = useState<string>(flight_types[0]);

    const [date, setDate] = useState<Date>(new Date(2022, 10, 5));

    function update_date(date: Date): void{
        setDate(date);
        return;
    }

    function format_date(date: Date): string{
        return date.getFullYear() + "-" + 
        (date.getMonth() < 10 ? "0"+date.getMonth() : date.getMonth()) + "-" + 
        (date.getDay() < 10 ? "0"+date.getDay() : date.getDay());
    }

    // from string to num
    function convert_origin(origin: string): number{
        //this is not ideal..but time constraint is tough
        if(origin === "Sao Paulo"){
            return 0;
        }
        else if(origin === "Sevilla"){
            return 1;
        }
        else if(origin === "Madrid"){
            return 2;
        }
        else if(origin === "Dublin"){
            return 3;
        }
        else if(origin === "Lisbon"){
            return 4;
        }
        else{
            return -1;
        }
    }

    async function update_destinations(origin: string): Promise<void>{
        await fetch(`http://localhost:8080/destination/getdest/${convert_origin(origin)}`, {
            method: "GET",
        })
        .then((response) => {
            return response.json();
        })
        .then((data) => {
            setDestinations(data);
        })
        return;
    }

    function on_origin_change(event: React.ChangeEvent<HTMLSelectElement>): void{
        update_destinations(event.target.value);
        return;
    }

    function on_destination_change(event: React.ChangeEvent<HTMLSelectElement>): void{
        setSelectedDestination(event.target.value)
        return;
    }

    function on_flight_type_change(event: React.ChangeEvent<HTMLSelectElement>): void{
        update_destinations(event.target.value);
        return;
    }

    function post_flight_details(): void{
        //POST fetch: localhost:8080/destination body:{"date" : "2022-04-23", "origin" : "Sao Paulo","destination" : "Madrid"}
        const flight_details = {date: format_date(date), origin: selected_origin, destination: selected_destination};
        // For some reason Spring API was recieving null data from 'fetch()' [NOT SURE WHY!]
        // So gotta use axios..
        axios.post("http://localhost:8080/destination", flight_details).
        then((response) => {
            console.log(response.data);
            setCurrentQueryId(response.data.id);
            setFlights(response.data);
        })
        return;
    }

    return (
    <div id="flight_details_wrapper" className="flex flex-col justify-center items-center">
        <div className="flex p-4 m-4">
        <div className="flex flex-col p-4">
            <div>Origin</div>
            {/* 
                This is could be a Dropdown component but data management is completely c-word.
            */}
            <div className="relative w-full lg:max-w-sm">
                <select onChange={on_origin_change} className="w-full p-2.5 text-gray-500 bg-white border rounded-md shadow-sm outline-none appearance-none focus:border-indigo-600">
                    {origins.map(
                        (origin) => (
                            <option value={origin} key={origin}>{origin}</option>
                        ))}
                </select>
            </div>
        </div>
        <div className="flex flex-col p-4">
            <div>Destionation</div>
            <div className="relative w-full lg:max-w-sm">
                <select onChange={on_destination_change} className="w-full p-2.5 text-gray-500 bg-white border rounded-md shadow-sm outline-none appearance-none focus:border-indigo-600">
                    {destinations.map(
                        (destination) => (
                            <option value={destination} key={destination}>{destination}</option>
                        ))}
                </select>
            </div>
        </div>
        <div className="flex flex-col p-4">
            <div>Date</div>
            <Calendar date={update_date}/>
        </div>
        <div className="flex flex-col p-4">
            <div>Flight type</div>
            <div className="relative w-full lg:max-w-sm">
                <select onChange={on_flight_type_change} className="w-full p-2.5 text-gray-500 bg-white border rounded-md shadow-sm outline-none appearance-none focus:border-indigo-600">
                    {flight_types.map(
                        (flight_type) => (
                            <option value={flight_type} key={flight_type}>{flight_type}</option>
                        ))}
                </select>
            </div>
        </div>
        <div className="flex flex-col p-4">
            <Link href="/flights">
                <button className="bg-purple-300 p-2 border border-sky-300 rounded" onClick={post_flight_details}>Search</button>
            </Link>
        </div>
        </div>
    </div>
    );
}
