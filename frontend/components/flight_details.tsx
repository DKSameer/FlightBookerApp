import React from 'react';
import { ReactElement, useState } from "react";
import Calendar from "../components/calendar";
import axios from "axios";
import { GlobalContext } from '../context/gcontext';
import Router from 'next/router';

export default function FlightDetails(): ReactElement {
    const { current_query_id, setCurrentQueryId, setFlights } = React.useContext(GlobalContext);

    const origins = new Array<string>("---", "Sao Paulo", "Sevilla", "Madrid", "Dublin", "Lisbon");
    const [selected_origin, setSelectedOrigin] = useState<string>(origins[0]);

    const [destinations, setDestinations] = useState<Array<string>>(new Array<string>("---"));
    const [selected_destination, setSelectedDestination] = useState<string>("");

    const flight_types = new Array<string>("One-way", "Round-trip");
    const [selected_flight_type, setSelectedFlightType] = useState<string>(flight_types[0]);

    let fd_date: Date = new Date(2022, 10, 6);

    function update_date(date: Date): void{
        fd_date = date;
        return;
    }

    function format_date(): string{
        return fd_date.getFullYear() + "-" + 
        (fd_date.getMonth() < 10 ? "0"+(fd_date.getMonth()+1) : (fd_date.getMonth()+1)) + "-" + 
        (fd_date.getDate() < 10 ? "0"+fd_date.getDate() : fd_date.getDate());
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
        const origin_id = convert_origin(origin);

        if(origin_id < 0){
            return;
        }

        await fetch(`http://localhost:8080/destination/getdest/${origin_id}`, {
            method: "GET",
        })
        .then((response) => {
            return response.json();
        })
        .then((data) => {
            console.log(data);
            setDestinations(data);
            setSelectedDestination(data[0]);
        })
        return;
    }

    function on_origin_change(event: React.ChangeEvent<HTMLSelectElement>): void{
        setSelectedOrigin(event.target.value);
        update_destinations(event.target.value);
        return;
    }

    function on_destination_change(event: React.ChangeEvent<HTMLSelectElement>): void{
        setSelectedDestination(event.target.value)
        return;
    }

    function on_flight_type_change(event: React.ChangeEvent<HTMLSelectElement>): void{
        setSelectedFlightType(event.target.value);
        return;
    }

    function format_flight_type(): string{
        if(selected_flight_type === "One-way"){
            return "oneway";
        }
        else if(selected_flight_type === "Round-trip"){
            return "roundtrip";
        }
        else return "";
    }

    function post_flight_details(): void{
        if(selected_origin === "" || selected_origin === "---"){
            alert("You must select an origin");
            return;
        }

        if(selected_destination === "" || selected_destination === "---"){
            alert("You must select a destination");
            return;
        }

        const flight_details = {date: format_date(), origin: selected_origin, destination: selected_destination};
        // For some reason Spring API was recieving null data from 'fetch()' [NOT SURE WHY!]
        // So gotta use axios..
        axios.post(`http://localhost:8080/destination/day/${format_flight_type()}`, flight_details).
        then((response) => {
            setCurrentQueryId(response.data.id);
            setFlights(response.data.dayFlights);
            console.log(response.data);
        })
        Router.push("/flights");
        return;
    }

    return (
    <div id="flight_details_wrapper" className="flex flex-col justify-center items-center">
        <div className="flex flex-wrap p-4 m-4">
            <div className="flex flex-col p-4">
                <div className="m-2 ml-0 text-lg">Origin</div>
                {/* 
                    This is could be a Dropdown component but data management is completely c-word.
                */}
                <div className="relative w-full lg:max-w-sm">
                    <select id="origin" onChange={on_origin_change} className="w-full p-2.5 text-gray-500 bg-white border rounded-md shadow-sm outline-none appearance-none hover:border-sky-400 hover:cursor-pointer focus:border-sky-300">
                        {origins.map(
                            (origin) => (
                                <option value={origin} key={origin} id={origin}>{origin}</option>
                            ))}
                    </select>
                </div>
            </div>
            <div className="flex flex-col p-4">
                <div className="m-2 ml-0 text-lg">Destionation</div>
                <div className="relative w-full lg:max-w-sm">
                    <select id="destination" onChange={on_destination_change}
                    className="form-select w-full p-2.5 text-gray-500 bg-white border rounded-md shadow-sm outline-none appearance-none hover:border-sky-400 hover:cursor-pointer focus:border-sky-300"
                    >
                        {destinations.map(
                            (destination) => (
                                <option value={destination} key={destination} id={destination}>{destination}</option>
                            ))}
                    </select>
                </div>
            </div>
            <div className="flex flex-col p-4">
                <div className="m-2 ml-0 text-lg">Date</div>
                <Calendar pass_date={update_date}/>
            </div>
            <div className="flex flex-col p-4">
                <div className="m-2 ml-0 text-lg">Flight type</div>
                <div className="relative w-full lg:max-w-sm">
                    <select onChange={on_flight_type_change} className="w-full p-2.5 text-gray-500 bg-white border rounded-md shadow-sm outline-none appearance-none hover:border-sky-400 hover:cursor-pointer focus:border-sky-300">
                        {flight_types.map(
                            (flight_type) => (
                                <option value={flight_type} key={flight_type}>{flight_type}</option>
                            ))}
                    </select>
                </div>
            </div>
        </div>
        <div id="search" className="flex flex-col p-4 justify-end" onClick={post_flight_details}>
            <button className="bg-white p-2 border border-sky-300 rounded hover:bg-sky-300">Search Flights</button>
        </div>
    </div>
    );
}
