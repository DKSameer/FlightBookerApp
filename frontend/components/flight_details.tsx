import { ReactElement } from "react";
import Dropdown from "./dropdown"
import Calendar from "../components/calendar";
import Link from "next/link";

export default function FlightDetails(): ReactElement {
  return (
    <div id="flight_details_wrapper" className="flex flex-col justify-center items-center">
      <div className="flex p-4 m-4">
        <div className="flex flex-col p-4">
          <div>Origin</div>
          <Dropdown mode="origin"></Dropdown>
        </div>
        <div className="flex flex-col p-4">
          <div>Destionation</div>
          <Dropdown mode="destination"></Dropdown>
        </div>
        <div className="flex flex-col p-4">
          <div>Date</div>
          <Calendar/>
        </div>
        <div className="flex flex-col p-4">
          <div>Flight type</div>
          <Dropdown mode="type"></Dropdown>
        </div>
        <div className="flex flex-col p-4">
          <Link href="/flights">
            <button className="bg-purple-300 p-2 border border-sky-300 rounded" >Search</button>
          </Link>
        </div>
      </div>
    </div>
  );
}
