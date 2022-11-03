import '../styles/globals.css'
import type { AppProps } from 'next/app'
import { GlobalContextProvider } from '../context/gcontext';

export default function App({ Component, pageProps }: AppProps) {
    return( 
        <GlobalContextProvider>
            <title>FlightBooker</title>
            <Component {...pageProps} />
        </GlobalContextProvider>
    );
}
