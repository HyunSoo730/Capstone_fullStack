import axios from 'axios';
import {React, useEffect} from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import useDidMountEffect from './UseDidMountEffect';


const Auth = () => {
    const navigate = useNavigate();
    const location = useLocation();
    const params = new URLSearchParams(location.search);
    const AUTH_CODE = params.get('code');

    useDidMountEffect(() => {
      fetch(`/auth/kakao/callback?code=${AUTH_CODE}`, {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
        },
      })
      .then(res=> {return res.text()})
      .then(res=> localStorage.setItem('login-token', res))
      }, []);
    return (
        <div>
        </div>
    );
};
export default Auth;