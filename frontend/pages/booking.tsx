import Layout from '../components/layout';
import PassengersInformation from '../components/passengers_information';
/* 
    Passengers information page
*/
export default function Information() {
    return (
        <Layout>
            <div className="flex flex-col justify-center items-center">
                <div className="font-semibold m-4">Booking Information</div>
                <div className="m-4">
                    <p>{Math.random() < .7 ? "Successfully booked!" : "Could not book the chosen flight :("}</p>
                </div>
            </div>
        </Layout>
    );
}
