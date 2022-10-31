import { ReactElement } from "react";

export default function Layout({children}: any): ReactElement {
  return (
    <div id="app_wrapper" className="flex flex-col">
        <div id="logo_wrapper" className="flex justify-center items-center w-full">
            <div className="p-8">
                <h1>Flight Booker</h1>
            </div>
        </div>
        <main>{children}</main>
    </div>
  );
}
