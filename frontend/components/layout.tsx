import { ReactElement } from "react";

export default function Layout({children}: any): ReactElement {
  return (
      <div id="app_wrapper" className="flex flex-col justify-center items-center bg-sky-200 h-full border rounded border-sky-300 mx-20 my-10">
          <div id="logo_wrapper" className="flex justify-center items-center w-full">
              <div className="m-8 mb-16">
                  <h1 className=" font-semibold text-2xl">Flight Booker</h1>
              </div>
          </div>
          <main>{children}</main>
      </div>
  );
}
