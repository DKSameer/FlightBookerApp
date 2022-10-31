import { ReactElement } from "react";
import Dropdown from "./dropdown"
import Calendar from "../components/calendar";

export default function FlightDetails(): ReactElement {
  return (
    <div id="flight_details_wrapper" className="flex flex-col justify-center items-center">
      <div className="flex p-4 m-4">
        <div className="flex flex-col p-4 bg-red-200">
          <div>Origin</div>
          <Dropdown options="Origin"></Dropdown>
        </div>
        <div className="flex flex-col p-4 bg-red-200">
          <div>Destionation</div>
          <Dropdown options="Destionation"></Dropdown>
        </div>
        <div className="flex flex-col p-4 bg-red-200">
          <div>Date</div>
          <Calendar/>
        </div>
      </div>
    </div>
  );
}
