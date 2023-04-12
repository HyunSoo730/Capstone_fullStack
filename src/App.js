<<<<<<< HEAD
import './App.css';
import SignUp from './SignUp'

function App() {
=======
import logo from "./logo.svg";
import "./App.css";
import Auth from "./Auth";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
function App() {
  const REST_API_KEY = "8d2b99868ab67054454a535a7db9fc4f";
  const REDIRECT_URI = "http://localhost:3000/oauth/kakao/callback";
  const KAKAO_AUTH_URL = `https://kauth.kakao.com/oauth/authorize?client_id=${REST_API_KEY}&redirect_uri=${REDIRECT_URI}&response_type=code`;
>>>>>>> 3a17bfcc5ab3a5106472f1ea49c88b3cd04bb7d2
  return (
    <Router>
    <div className="App">
      <Routes>
        <Route exact path="/" element={<h1><a href={KAKAO_AUTH_URL}>Kakao Login</a></h1>}/>
        <Route path="/oauth/kakao/callback" element={<Auth />}/>
      </Routes>
    </div>
    </Router>
  );
}
export default App;