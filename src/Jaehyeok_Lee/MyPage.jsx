import React, { useState } from "react";
import { useNavigate, useParams } from "react-router-dom";

import { categoryOption } from './Option';
import './MyPage.css';

function MyPage(props) {
    const navigate = useNavigate();
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [region, setRegion] = useState("");
    const [dong, setDong] = useState("");
    const [category, setCategory] = useState("");
    const { postId } = useParams();

    const categoryOption = [
        {value: "drink", name: "커피-음료"},
        {value: "a", name: "A"},
        {value: "b", name: "B"},
        {value: "c", name: "C"},
        {value: "d", name: "D"},
    ];

    const findName = () => {
        fetch('${API}login', {
            method : "GET",
            headers : {
                "Content-Type" : "application/json",
                Authorization: localStorage.getItem('login-token')
            },
        })
        .then(response => {return response.json()})
        .then(response => setName(current => [...current, response.kakaoNickname]))
    }

    const findEmail = () => {
        fetch('/mypage/users/me', {
            method : "GET",
            headers : {"Content-Type" : "application/json"},
        })
        .then(response => {return response.json()})
        .then(response => {response.map((value)=>{
            setEmail(current => [...current, value.kakaoEmail]);
        })})
    }

    const handleChangeName = (event) => {
        setName(event.target.value);
    };

    const handleChangeEmail = (event) => {
        setEmail(event.target.value);
    };

    const handleChangePassword = (event) => {
        setPassword(event.target.value);
    };

    const handleChangeRegion = (event) => {
        setRegion(event.target.value);
    };

    const handleChangeDong = (event) => {
        setDong(event.target.value);
    };

    const handleChangeCategory = (event) => {
        setCategory(event.target.value);
    };

    const handleSubmit = (event) => {
        alert(`수정이 완료되었습니다.`);
        // 변경 데이터 저장하기
        event.preventDefault();
    };

    return (
        <form onSubmit={handleSubmit}>
            <div className="Wrapper">
                <div className="Container">
                    <div className="PostContainer">
                        <h3>기본 정보</h3>
                        <div className="Label">
                            이　　름　
                            <input style={{width: "180px", height: "30px"}} type="text" defaultValue={name} onChange={handleChangeName}/>
                        </div>
                        <div className="Label">
                            이 메 일　
                            <input style={{width: "180px", height: "30px"}} type="text" defaultValue={"이메일"} onChange={handleChangeEmail} />
                        </div>
                        <div className="Label">
                            비밀번호　
                            <input style={{width: "180px", height: "30px"}} type="password" defaultValue={"패스워드"} onChange={handleChangePassword} />
                        </div>
                        <p>　</p>
                        <h3>창업 정보</h3>
                        <div className="Label">
                            자 치 구　
                            <input style={{width: "180px", height: "30px"}} type="text" defaultValue={"광진구"} onChange={handleChangeRegion} />
                        </div>
                        <div className="Label">
                            행 정 동　
                            <input style={{width: "180px", height: "30px"}} type="text" defaultValue={"화양동"} onChange={handleChangeDong} />
                        </div>
                        <div className="Label">
                            희망업종　
                            <select options={categoryOption} style={{width: "180px", height: "30px"}} defaultValue={"커피-음료"}>
                                {categoryOption.map((option) => (
                                    <option value={option.value}>{option.name}</option>
                                ))}
                            </select>
                        </div>
                    </div>
                    <div className="PhotoContainer">
                        <div className="Photo"></div>
                        <div
                            className="Button"
                            type="submit"
                            title="사진 변경"
                            onClick={() => {navigate("/");}}
                        >
                            사진 변경
                        </div>
                    </div> 
                </div>
                <div
                    className="Button"
                    type="submit"
                    title="정보 수정"
                    onClick={() => {navigate("/");}}
                >
                    정보 수정
                </div>
            </div>
        </form>
    );
}

export default MyPage;