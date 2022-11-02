import Link from "next/link";
import { ReactElement, useState } from "react";
import Passenger from "./passenger";
import { PassengerClass, Age } from "./passenger";

export default function PassengersInformation(): ReactElement {
    const [number_of_passengers_array, setNumberOfPassengersArray] = useState<Array<number>>(new Array<number>());

    function on_number_of_passengers_change(event: React.ChangeEvent<HTMLInputElement>): void{
        let x = new Array<number>();
        let n: number = parseInt(event.target.value);
        for(let i=0; i<n; i++){
            x.push(i+1);
        }
        setNumberOfPassengersArray(x);
        console.log(number_of_passengers_array);
        return;
    }

    let passengers: Array<PassengerClass> = new Array<PassengerClass>();

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

    return (
        <div className="flex flex-col justify-center items-center">
            <div className="font-semibold m-4">Passengers Information</div>
            <div className="flex flex-col bg-pink-200 p-6 w-2/3">
                <Passenger p_data={add_passenger}></Passenger>
            </div>
            <div>
                <input
                        type="text"
                        className="block w-6/7 px-4 py-2 text-purple-300 bg-white border_lean"
                        placeholder="Any other passengers?"
                        onChange={on_number_of_passengers_change}
                    />
            </div>
            {number_of_passengers_array.map(
                (p) => (
                    <Passenger key={p} p_data={add_passenger}></Passenger>
                ))}
        </div>
    );
}
