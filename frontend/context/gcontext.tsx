import React, { useState } from 'react';

interface IGlobalContextProps {
    logged_username: string;
    setLoggedUsername: (logged_username: string) => void; 
    logged_password: string;
    setLoggedPassword: (logged_password: string) => void; 
    current_query_id: number;
    setCurrentQueryId: (query_id: number) => void;
    flights: Array<any>;
    setFlights: (flights: Array<any>) => void;
}

export const GlobalContext = React.createContext<IGlobalContextProps>({} as IGlobalContextProps);

export const GlobalContextProvider = ({children}: any) => {
    const [current_query_id, setCurrentQueryId] = useState<number>(0);
    const [flights, setFlights] = useState<Array<any>>(new Array<any>());
    const [logged_username, setLoggedUsername] = useState<string>("");
    const [logged_password, setLoggedPassword] = useState<string>("");

    return (
    <GlobalContext.Provider value={
        {current_query_id, setCurrentQueryId, flights, setFlights, 
        logged_username, setLoggedUsername, 
        logged_password, setLoggedPassword}
    }>
        {children}
    </GlobalContext.Provider>
    );
};