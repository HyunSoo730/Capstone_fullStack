import './App.css';
import { Route, Routes, BrowserRouter, Link } from 'react-router-dom';
import HomePage from './HomePage';
import Auth from './Auth'
import Youtube from './Youtube';
import Analysis from './Analysis';
import Crawling from './Crawling';
import MyPage from './Jaehyeok_Lee/MyPage';

function App() {

  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route exact path="/" element={<HomePage />}/>
          <Route path="/auth/kakao/callback" element={<Auth />}/>
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