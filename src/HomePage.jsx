import { React, useState } from 'react';
import { MenuItems } from "./HomePageMenuItems";
import HomeStyle from './HomePage.css';
import HomeNavStyle from "./HomePageNavbar.css"

import main_logo from './logo.png'

const HomePage = () => {
    const [clicked, setClicked] = useState(false);

    const handleClick = () => {
        setClicked(!clicked);
    }

    return (
        <div>
            <nav className='Navbar'>
              <h1 className="navbar-logo"><img src={main_logo} onClick={() => window.location.href='/'}/></h1>
          <div className='menu-icon' onClick={handleClick}>
              <i className={clicked ? 'fas fa-times' : 'fas fa-bars'}></i>
          </div>
          <ul className="nav-menu-active">
              {MenuItems.map((item, index)=>{
                if (localStorage.getItem('login-token') && item.url === `https://kauth.kakao.com/oauth/authorize?client_id=${process.env.REACT_APP_REST_API_KEY}&redirect_uri=http://localhost:3000/auth/kakao/callback&response_type=code`){
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
            <div id={HomeStyle.wrap}>
                <body id={HomeStyle.body}>
                    <footer id={HomeStyle.footer}>
                        <span>개발자 : Izony | WHO | WANT | ME </span><br/>
                        <span>Github : https://github.com/HyunSoo730/Capstone_fullStack</span><br/>
                        <span>version : 0.1 </span>
                    </footer>
                </body>
            </div>
        </div>
    )
};
export default HomePage;