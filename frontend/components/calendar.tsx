import { ReactElement, useState } from "react";
import Calendar from 'react-calendar';
import 'react-calendar/dist/Calendar.css';

export default function CalendarC(props: {date: (date: Date) => void}): ReactElement {
    const [date, setDate] = useState(new Date(2022, 10, 4));
  
    function print_dates(){
        console.log(date);
    }

    function pass_date(event: any): void{
        props.date(event);
        return;
    }

    return (
        <div className='calendar-container'>
            <Calendar onChange={pass_date} defaultValue={date}/>
            <button onClick={print_dates}>*print date in console*</button>
        </div>
    );
  }
