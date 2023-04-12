import './App.css';
import SignUp from './SignUp'

function App() {
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