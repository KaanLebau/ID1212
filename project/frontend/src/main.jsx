import React from 'react'
import ReactDOM from 'react-dom/client'
import Login from './components/views/Login'
import Home from './components/views/Home'
import DisplayName from './components/views/DisplayName'

import {
  createBrowserRouter,
  RouterProvider,
} from "react-router-dom";

const router = createBrowserRouter([
  {
    path: "/",
    element: <Login />,
  },
  {
    path: "/home",
    element: <Home />,
  },
  {
    path: "/home/:courseId",
    element: <Home />,
  },
  {
    path: "/home/:courseId/:topicId",
    element: <Home />,
  },
  {
    path: "/home/:courseId/:topicId/:postId",
    element: <Home />,
  },
  {
    path: "/displayname",
    element: <DisplayName />,
  }
]);

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>,
)
