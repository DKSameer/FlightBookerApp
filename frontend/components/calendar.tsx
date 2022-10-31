import { ReactElement, useState } from "react";
import Calendar from 'react-calendar';
import 'react-calendar/dist/Calendar.css';

export default function CalendarC(): ReactElement {
    const [date, setDate] = useState(new Date(2022, 10, 1));
  
    function print_dates(){
        console.log(date);
    }

    return (
        <div className='calendar-container'>
            <Calendar onChange={setDate} defaultValue={date}/>
            <button onClick={print_dates}>*print date in console*</button>
        </div>
    );
  }
