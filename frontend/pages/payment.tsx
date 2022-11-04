import React, { useState } from 'react';
import Layout from '../components/layout';
import Router from 'next/router';
import axios from "axios";

/* 
    Payment page
*/
export default function Payment() {
    const [name, setName] = useState<string>("");
    const [card_number, setCardNumber] = useState<string>("");
    const [expire_date_year, setExpireDateYear] = useState<string>("");
    const [expire_date_month, setExpireDateMonth] = useState<string>("");

    function on_name_change(event: React.ChangeEvent<HTMLInputElement>): void{
        setName(event.target.value);
        return;
    }

    function on_card_number_change(event: React.ChangeEvent<HTMLInputElement>): void{
        setCardNumber(event.target.value);
        return;
    }

    function on_expire_date_year_change(event: React.ChangeEvent<HTMLInputElement>): void{
        setExpireDateYear(event.target.value);
        return;
    }

    function on_expire_date_month_change(event: React.ChangeEvent<HTMLInputElement>): void{
        setExpireDateMonth(event.target.value);
        return;
    }

    function execute_payment(): void{
        if(name === ""){
            alert("Name is a mandatory field");
            return;
        }
        if(card_number === ""){
            alert("Card Number is a mandatory field");
            return;
        }
        axios.post(`http://localhost:8081/book`, {name: name, cardNumber: card_number, expiryDate:expire_date_month+"/"+expire_date_year}).
        then((response) => {
            Router.push({pathname:"/booking", query:{booking_confirmation: response.data.bookingConfirmed, booking_status: response.data.message}});
        }).
        catch((error) => {
            alert(error.response.data.message);
        })
       return;
    }

    return (
        <Layout>
            <div className="flex flex-col justify-center items-center">
                <div>
                    <p className="font-semibold text-xl">Payment</p>
                </div>
                <div className="w-fit m-4 mb-0">
                    <p>Name</p>
                    <input
                        type="text"
                        className="block w-6/7 px-4 py-2 border rounded-md"
                        onChange={on_name_change}
                    />
                </div>
                <div className="w-fit m-4 mb-0">
                    <p>Card Number</p>
                    <input
                        type="text"
                        className="block w-6/7 px-4 py-2 border rounded-md"
                        onChange={on_card_number_change}
                    />
                </div>
                <div className="w-1/2 m-4 mb-0">
                    <p>Expiration Date</p>
                    <div className="flex">
                        <div>
                            <p>Month</p>
                            <input
                                type="text"
                                className="block w-1/2 px-4 py-2 border rounded-md"
                                onChange={on_expire_date_month_change}
                            />
                        </div>
                        <div>
                            <p>Year</p>
                            <input
                                type="text"
                                className="block w-1/2 px-4 py-2 border rounded-md"
                                onChange={on_expire_date_year_change}
                            />
                        </div>
                    </div>
                </div>
                <div className="flex flex-row justify-center items-center">
                    <div className="flex justify-center items-center w-fit h-fit p-2 border rounded hover:bg-sky-400 hover:cursor-pointer mx-4 mb-1 font-semibold mt-10 bg-sky-300">
                        <button onClick={execute_payment}>Submit</button>
                    </div>
                </div>
            </div>
        </Layout>
    );
}
