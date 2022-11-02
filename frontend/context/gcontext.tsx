import React, { useState } from 'react';

interface IGlobalContextProps {
    current_query_id: number;
    setCurrentQueryId: (query_id: number) => void;
    flights: Array<any>;
    setFlights: (flights: Array<any>) => void;
}

export const GlobalContext = React.createContext<IGlobalContextProps>(null);

export const GlobalContextProvider = ({children}: any) => {
  const [current_query_id, setCurrentQueryId] = useState<number>(0);
  const [flights, setFlights] = useState<Array<any>>(new Array<any>());

  return (
    <GlobalContext.Provider value={{current_query_id, setCurrentQueryId, flights, setFlights}}>
      {children}
    </GlobalContext.Provider>
  );
};