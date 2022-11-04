import { useRouter } from 'next/router';
import Layout from '../components/layout';

/* 
    Booking status page
*/
export default function Booking() {
    const booking_confirmation = useRouter().query.booking_confirmation;
    const booking_status_message = useRouter().query.booking_status;

    return (
        <Layout>
            <div className="flex flex-col justify-center items-center">
                <div className="font-semibold m-4">Booking {booking_confirmation === "true" ? "confirmed!" : "could not be processed."}</div>
                <div className="m-4">
                    <p>{booking_status_message}</p>
                </div>
            </div>
        </Layout>
    );
}
