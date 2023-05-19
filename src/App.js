import './App.css';
import { Route, Routes, BrowserRouter } from 'react-router-dom';
import HomePage from './HomePage';
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