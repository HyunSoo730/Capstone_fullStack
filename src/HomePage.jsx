import { React, useState } from 'react';
import { MenuItems } from "./HomePageMenuItems";
import HomeStyle from './HomePage.css';
import HomeNavStyle from "./HomePageNavbar.css"

import main_logo from './logo.png'
import { Button } from 'react-bootstrap';

const HomePage = () => {
    const [clicked, setClicked] = useState(false);

    const handleClick = () => {
        setClicked(!clicked);
    }

    return (
        <div className='back'>
            <nav className='Navbar'>
              <h1 className="navbar-logo"><img src={main_logo} onClick={() => window.location.href='/'}/></h1>
          <div className='menu-icon' onClick={handleClick}>
              <i className={clicked ? 'fas fa-times' : 'fas fa-bars'}></i>
          </div>
          <ul className="nav-menu-active">
              {MenuItems.map((item, index)=>{
                if (localStorage.getItem('login-token') && item.url === `https://kauth.kakao.com/oauth/authorize?client_id=${process.env.REACT_APP_REST_API_KEY}&redirect_uri=http://3.39.41.194:3000/auth/kakao/callback&response_type=code`){
                  return;
                }
                if(!localStorage.getItem('login-token') && item.url === "mypage"){
                  return;
                }
                if(window.location.pathname === '/' + item.url){
                  return (
                    <li class="nav-links-active" key={index}>
                        <a className="nav-component-active" href={item.url}>
                            {item.title}
                        </a>
                    </li>
                  )
                }
                else{
                  return (
                    <li key={index}>
                        <a className={item.cName} href={item.url}>
                            {item.title}
                        </a>
                    </li>
                  )
                }
              })}
          </ul>
        </nav>
        <div>
          <h1 className='title'>당신의 도전에 <br/> 함께 하겠습니다.</h1>
          <h5 className='subtitle'>최신 상권 트랜드 솔루션, YouTrend</h5>
        </div>
          <div style={{position: "absolute", right: "5%"}}>
            <Button onClick={()=>window.location.href="/youtube"} className='trendbutton' style={{ width: "350px", height: "407px", backgroundColor: "rgba(200, 200, 200, 0.8)", fontFamily:'mbcfont', right: "10px"}} variant="light">
              <h4 style={{position: "absolute", top: "10px", left: "10px"}}>유튜브 트렌드</h4>
              <img style={{display: "block", width: "80%", margin: "auto"}} src={ require('./trendbutton.png') } />
              <img style={{position: "absolute", width: "30%", bottom: "0", right: "0"}} src={ require('./arrowbutton.png') } />
            </Button>
            <Button onClick={()=>window.location.href="/analysis"} className='analysisbutton' style={{ width: "350px", height: "407px", backgroundColor: "rgba(200, 200, 200, 0.8)", fontFamily:'mbcfont'}} variant="light">
            <h4 style={{position: "absolute", top: "10px", left: "10px"}}>상권 분석</h4>
              <img style={{display: "block", width: "80%", margin: "auto"}} src={ require('./analysisbutton.png') } />
              <img style={{position: "absolute", width: "30%", bottom: "0", right: "0"}} src={ require('./arrowbutton.png') } />
            </Button>
          </div>
        </div>
    )
};
export default HomePage;