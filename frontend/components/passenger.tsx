import { ReactElement, useState } from "react";
import Dropdown from "./dropdown";

/* 
    This should be birthdate..(discussable instructions)
*/
export enum Age{
    LessThan2,
    Between2And9,
    BiggerThan9
}

export class PassengerClass {
    name: string;
    surname: string;
    nationality: string;
    identification: string;
    age: Age;
    bags: boolean;

    constructor(
        name: string, surname: string, nationality: string,
        identification: string, age: Age, bags: boolean){
        
        this.name = name;
        this.surname = surname;
        this.nationality = nationality;
        this.identification = identification;
        this.age = age;
        this.bags = bags;
    }
}

export default function PassengerC(props: {p_data: any}): ReactElement {
    const [name, setName] = useState<string>("");
    const [surname, setSurame] = useState<string>("");
    const [nationality, setNationality] = useState<string>("");
    const [identification, setIdentification] = useState<string>("");
    const [age, setAge] = useState<Age>(Age.BiggerThan9);
    const [bags, setBags] = useState<boolean>(false);

    const age_list = new Array<Age>(Age.LessThan2, Age.Between2And9, Age.BiggerThan9);
    const bags_list = new Array<string>("Yes", "No");

    let passenger = new PassengerClass(name, surname, nationality, identification, age, bags);

    function on_name_change(event: React.ChangeEvent<HTMLInputElement>): void{
        setName(event.target.value);
        return;
    }

    function on_surname_change(event: React.ChangeEvent<HTMLSelectElement>): void{
        setSurame(event.target.value);
        return;
    }

    function on_nationality_change(event: React.ChangeEvent<HTMLSelectElement>): void{
        setNationality(event.target.value);
        return;
    }

    function on_identification_change(event: React.ChangeEvent<HTMLSelectElement>): void{
        setIdentification(event.target.value);
        return;
    }

    function on_age_change(event: React.ChangeEvent<HTMLSelectElement>): void{
        setIdentification(event.target.value);
        return;
    }

    function on_bags_change(event: React.ChangeEvent<HTMLSelectElement>): void{
        setIdentification(event.target.value);
        return;
    }

    function pass_data(): void{
        props.p_data(name, surname, nationality, identification, age, bags);
    }
    
    return (
        <div className="flex flex-col bg-green-400 pb-4">
            <div className="flex">
                <div className="w-fit m-4 mb-0">
                    <p>Name</p>
                    <input
                        type="text"
                        className="block w-6/7 px-4 py-2 text-purple-300 bg-white border_lean"
                        placeholder="Your name..."
                        onChange={on_name_change}
                    />
                </div>
                <div className="w-fit m-4 mb-0">
                    <p>Surame</p>
                    <input
                        type="text"
                        className="block w-6/7 px-4 py-2 text-purple-300 bg-white border_lean"
                        placeholder="Your surname..."
                    />
                </div>
            </div>
            <div className="w-fit m-4 mb-0">
                <p>Nationality</p>
                <input
                    type="text"
                    className="block w-6/7 px-4 py-2 text-purple-300 bg-white border_lean"
                    placeholder="Your nationality..."
                />
            </div>
            <div className="w-fit m-4">
                <p>Identification (NIF or Passport)</p>
                <input
                    type="text"
                    className="block w-6/7 px-4 py-2 text-purple-300 bg-white border_lean"
                    placeholder="Your identification number..."
                />
            </div>
            <div className=" m-4 mb-0">
                <p>Age</p>
                <div className="relative w-full lg:max-w-sm">
                    <select onChange={on_age_change} className="w-full p-2.5 text-gray-500 bg-white border rounded-md shadow-sm outline-none appearance-none focus:border-indigo-600">
                        {age_list.map(
                            (age) => (
                                <option value={age} key={age}>{age}</option>
                            ))}
                    </select>
                </div>
            </div>
            <div className=" m-4 mb-0">
                <p>Bags</p>
                <div className="relative w-full lg:max-w-sm">
                    <select onChange={on_bags_change} className="w-full p-2.5 text-gray-500 bg-white border rounded-md shadow-sm outline-none appearance-none focus:border-indigo-600">
                        {bags_list.map(
                            (bags) => (
                                <option value={bags} key={bags}>{bags}</option>
                            ))}
                    </select>
                </div>
            </div>
            <div className="bg-yellow-200 w-fit">
                <button onClick={pass_data}>Save</button>
            </div>
        </div>
    );
}