import { ReactElement, useState } from "react";

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
    const bags_list = new Array<string>("No", "Yes");

    let passenger = new PassengerClass(name, surname, nationality, identification, age, bags);

    function on_name_change(event: React.ChangeEvent<HTMLInputElement>): void{
        setName(event.target.value);
        return;
    }

    function on_surname_change(event: React.ChangeEvent<HTMLInputElement>): void{
        setSurame(event.target.value);
        return;
    }

    function on_nationality_change(event: React.ChangeEvent<HTMLInputElement>): void{
        setNationality(event.target.value);
        return;
    }

    function on_identification_change(event: React.ChangeEvent<HTMLInputElement>): void{
        setIdentification(event.target.value);
        return;
    }

    function on_age_change(event: React.ChangeEvent<HTMLSelectElement>): void{
        setAge(event.target.value === "0" ? Age.LessThan2 : (event.target.value === "1" ? Age.Between2And9 : Age.BiggerThan9));
        return;
    }

    function on_bags_change(event: React.ChangeEvent<HTMLSelectElement>): void{
        setBags(event.target.value == "Yes" ? true : false);
        return;
    }

    function pass_data(): void{
        props.p_data(name, surname, nationality, identification, age, bags);
    }
    
    return (
        <div className="flex flex-col bg-sky-300 pb-4 border rounded border-sky-400">
            <div className="flex">
                <div className="w-fit m-4 mb-0">
                    <p>Name</p>
                    <input
                        type="text"
                        className="block w-6/7 px-4 py-2 border rounded-md"
                        onChange={on_name_change}
                    />
                </div>
                <div className="w-fit m-4 mb-0">
                    <p>Surname</p>
                    <input
                        type="text"
                        className="block w-6/7 px-4 py-2 border rounded-md"
                        onChange={on_surname_change}
                    />
                </div>
            </div>
            <div className="w-fit m-4 mb-0">
                <p>Nationality</p>
                <input
                    type="text"
                    className="block w-6/7 px-4 py-2 border rounded-md"
                    onChange={on_nationality_change}
                />
            </div>
            <div className="w-fit m-4">
                <p>Identification (NIF or Passport)</p>
                <input
                    type="text"
                    className="block w-6/7 px-4 py-2 border rounded-md"
                    onChange={on_identification_change}
                />
            </div>
            <div className="m-4 mb-0">
                <p>Age</p>
                <div className="relative w-full lg:max-w-sm">
                    <select onChange={on_age_change} className="hover:cursor-pointer w-full p-2.5 text-gray-500 bg-white border rounded-md shadow-sm outline-none appearance-none focus:border-indigo-600">
                        {age_list.map(
                            (age) => (
                                <option value={age} key={age}>{age === 0 ? "< 2 years" : (age === 1 ? "Between 2 and 9 years (inclusive)" : "> 9 years")}</option>
                            ))}
                    </select>
                </div>
            </div>
            <div className="m-4 mb-0">
                <p>Bags</p>
                <div className="relative w-full lg:max-w-sm">
                    <select onChange={on_bags_change} className="hover:cursor-pointer w-full p-2.5 text-gray-500 bg-white border rounded-md shadow-sm outline-none appearance-none focus:border-indigo-600">
                        {bags_list.map(
                            (bags) => (
                                <option value={bags} key={bags}>{bags}</option>
                            ))}
                    </select>
                </div>
            </div>
            <div className="m-4 mb-0 p-1 bg-white border rounded border-sky-400 hover:bg-green-200 hover:cursor-pointer w-fit">
                <button onClick={pass_data}>Save</button>
            </div>
        </div>
    );
}