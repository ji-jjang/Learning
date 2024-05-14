import { createBrowserRouter } from "react-router-dom";
import Users from "./Users";

const router = createBrowserRouter([
  {
    path: "/",
    element: <Users />,
  },
  {
    path: "/login",
    element: <div>login</div>,
  },
  {
    path: "/register",
    element: <div>register</div>,
  },
]);

export default router;
