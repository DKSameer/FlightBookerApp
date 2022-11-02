import { ReactElement, useEffect, useState } from "react";

export default function Dropdown(props: {mode: string}): ReactElement {
    const [selected_option, setSelectedOption] = useState<string>("");
    const [options, setOptions] = useState<Array<string>>(fetch_options(props.mode));

    function on_option_change(event: React.ChangeEvent<HTMLSelectElement>): void{
        setSelectedOption(event.target.value);
        console.log(event.target.value);
        return;
    }

    function fetch_options(mode: string): Array<string>{
        /* 
            TODO: When mode is destination, an extra paramater 'origin' 
                must be passed in order to load all possible destinations
                linked to that origin location.
                 fetch : localhost:8080/destination/origin_id
        */
        let new_options = new Array<string>();
        if(mode === "type"){
            new_options.push("One-way");
            new_options.push("Round-trip");
        }
        else if(mode === "origin"){
            new_options.push("Sao Paulo"); //id = 0
            new_options.push("Sevilla"); //id = 1
            new_options.push("Madrid"); //id = 2
            new_options.push("Dublin"); //id = 3
            new_options.push("Lisbon"); //id = 4
        }
        else if(mode === "destination"){
            new_options.push("Sao Paulo");
            new_options.push("Sevilla");
            new_options.push("Madrid");
            new_options.push("Dublin");
            new_options.push("Lisbon");
            new_options.push("Rome");
        }
        else if(mode === "passenger_age"){
            new_options.push("< 2 years");
            new_options.push("between 2 and 9 years");
            new_options.push("> 9 years");
        }
        else if(mode === "passenger_bags"){
            new_options.push("yes");
            new_options.push("no");
        }
        else{
            console.error("Unknown dropdown mode '" + mode + "'.");
        }
        return new_options;
    }

    return (
        <div className="relative w-full lg:max-w-sm">
            <select onChange={on_option_change} className="w-full p-2.5 text-gray-500 bg-white border rounded-md shadow-sm outline-none appearance-none focus:border-indigo-600">
                {options.map(
                    (option) => (
                        <option value={option} key={option}>{option}</option>
                    ))}
            </select>
        </div>
    );
}
