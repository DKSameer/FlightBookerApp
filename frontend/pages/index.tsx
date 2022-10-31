import Head from 'next/head';
import Image from 'next/image';
import Layout from '../components/layout';
import FlightDetails from '../components/flight_details'

export default function Home() {
  return (
    <Layout>
      <FlightDetails></FlightDetails>
    </Layout>
  );
}
