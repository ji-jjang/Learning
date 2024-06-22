import React from "react";
import "./index.css";
import App from "./App";
import Login from "./Login"
import { BrowserRouter as Router, Route, Routes } from "react-router-dom"
import Box from "@mui/material/Box"
import Typography from "@mui/material/Typography";

function Copyright() {
    return (
      <Typography variant="body2" color="textSecondary" align="center">
        {"Copyright © "}
        fsoftwareengineer, {new Date().getFullYear()}
        {"."}
      </Typography>
    );
  }
  
  class AppRouter extends React.Component {
    render() {
        return (
            <div>
                <Router>
                    <Routes>
                        <Route path="/login" element={<Login />} />
                        <Route path="/" element={<App />} />
                    </Routes>
                    <Box mt={5}>
                        <Copyright />
                    </Box>
                </Router>
            </div>
        );
    }
}

export default AppRouter;