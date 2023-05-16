import axios from 'axios';
import {React, useEffect} from 'react';

const Auth = () => {

    useEffect(()=> {
        let params = new URL(document.location.toString()).searchParams;
        let code = params.get("code"); // 인가코드 받는 부분
        console.log(code);

      }, [])

    return (
        <div>
        </div>
    );
};
export default Auth;