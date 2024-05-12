import { createBrowserRouter } from "react-router-dom";
import { TopicProvider } from './context/TopicContext';
import Header from './components/Header';
import Nav from './components/Nav';
import ContentArea from './components/ContentArea';

const Ex1 = () => (
  <iframe
    src="/js-sc/ex1.html"
    style={{ width: "100vw", height: "100vh", border: "none" }}
  ></iframe>
);

const router = createBrowserRouter([
  {
    path: "/",
    element: (
      <TopicProvider>
        <div>

          <ContentArea />
        </div>
      </TopicProvider>
    ),
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
