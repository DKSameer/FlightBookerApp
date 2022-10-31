import { ReactElement, useState } from "react";

export default function Dropdown(props: {options: string}): ReactElement {
    const [option, setOption] = useState("");

    function on_option_change(event: React.ChangeEvent<HTMLInputElement>){
        console.log(event.target.value);
    }

    return (
        <div className="relative w-full lg:max-w-sm">
            <select onChange={on_option_change} className="w-full p-2.5 text-gray-500 bg-white border rounded-md shadow-sm outline-none appearance-none focus:border-indigo-600">
                <option>{props.options} Options</option>
                {/* 
                    TODO: 
                        - Fetch options
                        - Fill selector with options:

                            {options.map((option) => (
                                <option value={option.value}>{option.label}</option>
                            ))} 
                */}
            </select>
        </div>
    );
}
