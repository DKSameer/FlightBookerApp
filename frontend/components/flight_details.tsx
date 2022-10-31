import { ReactElement } from "react";

export default function Home(): ReactElement {
  return (
    <div id="flight_details_wrapper" className="flex flex-col">
        <div>Origin</div>
        <div>Destination</div>
        <div>Date</div>
    </div>
  );
}
