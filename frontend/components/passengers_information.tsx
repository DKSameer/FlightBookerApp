import Link from "next/link";
import { ReactElement, useState } from "react";
import Passenger from "./passenger";
import { PassengerClass, Age } from "./passenger";
import Router from 'next/router';
import axios from "axios";
import { useRouter } from 'next/router';

let passengers: Array<PassengerClass> = new Array<PassengerClass>();

export default function PassengersInformation(): ReactElement {
    const [number_of_passengers_array, setNumberOfPassengersArray] = useState<Array<number>>(new Array<number>());

    const flight_price = useRouter().query.price;

    function on_number_of_passengers_change(event: React.ChangeEvent<HTMLInputElement>): void{
        let x = new Array<number>();
        let n: number = parseInt(event.target.value);
        for(let i=0; i<n; i++){
            x.push(i+1);
        }
        setNumberOfPassengersArray(x);
        return;
    }


    function containsPassenger(name: string): boolean {
        for(let i:number=0; i<passengers.length; i++){
            if(passengers[i].name === name){
                return true;
            }
        }
        return false;
    }

    function add_passenger(name: string, surname: string, nationality: string,
        identification: string, age: Age, bags: boolean): void{
            if(!containsPassenger(name)){
                passengers.push(new PassengerClass(name, surname, nationality, identification, age, bags));
            }
            console.log(passengers);
            return;
    }

    function passengers_info_request_object(): any{
        let babies = 0;
        let kids = 0;
        let luggages = 0;
        for(let i: number = 0; i<passengers.length; i++){
            if(passengers[i].bags){
                luggages++;
            }
            if(passengers[i].age === Age.LessThan2){
                babies++;
            }
            if(passengers[i].age === Age.Between2And9){
                kids++;
            }
        }
        return {totalPassengers: passengers.length, luggages: luggages, kids: kids, babies: babies, basePrice: flight_price};
    }

    function book_flight(): void{
        const passengers_information = passengers_info_request_object();
        console.log(passengers_information);
        axios.post(`http://localhost:8082/price`, passengers_information).
        then((response) => {
            console.log(response.data);
        })
        Router.push("/booking");
        return;
    }

    return (
        <div className="flex flex-col justify-center items-center">
            <div className="font-semibold m-4">Passengers Information</div>
            <div className="flex flex-col overflow-y-scroll h-96 my-10 mx-28 p-8 border rounded border-sky-300">
                <div className="p-4">
                    <Passenger p_data={add_passenger}></Passenger>
                </div>
                <div className="p-4">
                    <input
                        type="text"
                        className="block w-3/4 px-4 py-2 text-purple-300 bg-white border_lean border rounded border-sky-300"
                        placeholder="Number of extra passengers..."
                        onChange={on_number_of_passengers_change}
                    />
                </div>
                <div>
                    {number_of_passengers_array.map(
                    (p) => (
                        <div className="p-4" key={p}>
                            <Passenger p_data={add_passenger}></Passenger>
                        </div>
                    ))}
                </div>
            </div>
            <div className="flex flex-col p-4 justify-end">
                <button className="bg-sky-300 p-2 border border-sky-400 rounded" onClick={book_flight}>Book</button>
            </div>
        </div>
    );
}
