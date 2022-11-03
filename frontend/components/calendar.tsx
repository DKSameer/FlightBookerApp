import { ReactElement, useState } from "react";
import Calendar from 'react-calendar';
import 'react-calendar/dist/Calendar.css';

export default function CalendarC(props: {pass_date: (date: Date) => void}): ReactElement {
    function pass_date(event: any): void{
        props.pass_date(event);
        return;
    }

    return (
        <div className='calendar-container'>
            <Calendar onChange={pass_date} defaultValue={new Date(2022, 10, 4)} defaultView="month" className="text-sky-400"/>
        </div>
    );
  }
