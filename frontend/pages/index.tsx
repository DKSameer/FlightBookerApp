import React from 'react';
import Layout from '../components/layout';
import { GlobalContext } from '../context/gcontext';
import Router from 'next/router';

/* 
    Login page
*/
export default function Login() {
    const { logged_username, setLoggedUsername, logged_password, setLoggedPassword } = React.useContext(GlobalContext);

    function on_username_change(event: React.ChangeEvent<HTMLInputElement>): void{
        setLoggedUsername(event.target.value);
        return;
    }

    function on_password_change(event: React.ChangeEvent<HTMLInputElement>): void{
        setLoggedPassword(event.target.value);
        return;
    }

    function execute_login(): void{
        if(logged_username === ""){
            alert("Username is a mandatory field");
            return;
        }
        if(logged_password === ""){
            alert("Password is a mandatory field");
            return;
        }
        /* 
            Validate login data..
        */
       /* 
            If valid input and checsums are fine go to home page.
       */
        Router.push("/home");
        /* 
            Additionally if login data matches admin it could render
            route to a different page.
        */
       return;
    }

    return (
        <Layout>
            <div className="flex flex-col justify-center items-center">
                <div>
                    <p className="font-semibold text-xl">Login</p>
                </div>
                <div className="w-fit m-4 mb-0">
                    <p>Username</p>
                    <input
                        type="text"
                        className="block w-6/7 px-4 py-2 border rounded-md"
                        onChange={on_username_change}
                    />
                </div>
                <div className="w-fit m-4 mb-0">
                    <p>Password</p>
                    <input
                        type="password"
                        className="block w-6/7 px-4 py-2 border rounded-md"
                        onChange={on_password_change}
                    />
                </div>
                <div className="flex flex-row justify-center items-center">
                    <div className="flex justify-center items-center w-fit h-fit p-2 border rounded hover:bg-sky-400 hover:cursor-pointer mx-4 mb-1 font-semibold mt-10 bg-sky-300">
                        <button onClick={execute_login}>Login</button>
                    </div>
                    <div className="flex justify-center items-center w-fit h-fit p-2 border rounded mx-4 mb-1 font-semibold mt-10 bg-gray-300">
                        <button>Signup</button>
                    </div>
                </div>
            </div>
        </Layout>
    );
}
