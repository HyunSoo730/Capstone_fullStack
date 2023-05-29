import axios from 'axios';
import {React, useEffect} from 'react';
import { Await, useLocation, useNavigate } from 'react-router-dom';
import useDidMountEffect from './UseDidMountEffect';
const Auth = () => {
    const navigate = useNavigate();
    const location = useLocation();
    const params = new URLSearchParams(location.search);
    const AUTH_CODE = params.get('code');

    useDidMountEffect(() => {
      async function fetch() {
        const response = await axios.get(`/auth/kakao/callback?code=${AUTH_CODE}`);
        localStorage.setItem('login-token', response);
        window.location.href = '/'
    };
    fetch();
    }, []);
    return (
        <div>
        </div>
    );
};
export default Auth;