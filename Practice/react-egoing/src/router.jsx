import { createBrowserRouter } from "react-router-dom";
import App from "./App";

const Ex1 = () => (
  <iframe
    src="/js-sc/ex1.html"
    style={{ width: "100vw", height: "100vh", border: "none" }}
  ></iframe>
);

const router = createBrowserRouter([
  {
    path: "/",
    element: <App />,
  },
  {
    path: "/ex1",
    element: <Ex1 />,
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
