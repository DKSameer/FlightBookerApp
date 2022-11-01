import Head from 'next/head';
import Image from 'next/image';
import Layout from '../components/layout';
import FlightDetails from '../components/flight_details'
import FlightsList from '../components/flights_list';

export default function Flights() {
    return (
        <Layout>
            <FlightsList></FlightsList>
        </Layout>
    );
}
