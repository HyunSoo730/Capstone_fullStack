import './App.css';
import { Route, Routes, BrowserRouter } from 'react-router-dom';
import HomePage from './HomePage';
import Auth from './Auth'
import Youtube from './Youtube';
import Analysis from './Analysis';
import Crawling from './Crawling';
import MyPage from './Jaehyeok_Lee/MyPage';

function App() {
  const KAKAO_AUTH_URL = `https://kauth.kakao.com/oauth/authorize?client_id=${process.env.REACT_APP_REST_API_KEY}&redirect_uri=http://localhost:3000/oauth/kakao/callback&response_type=code`;

  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route exact path="/" element={<HomePage />}/>
          <Route exact path="/login" element={<h1><a href={KAKAO_AUTH_URL}>Kakao Login</a></h1>}/>
          <Route path="/oauth/kakao/callback" element={<Auth />}/>
          <Route path="/youtube" element={<Youtube />}/>
          <Route path="/analysis" element={<Analysis />}/>
          <Route path="/crawling" element={<Crawling/>}/>
          <Route path="/mypage" element={<MyPage/>}/>
        </Routes>
      </BrowserRouter>
    </div>
  );
}
export default App;