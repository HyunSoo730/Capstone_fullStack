import React, { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import Select from 'react-select';
import Avatar from 'react-avatar';
import Styled from 'styled-components';
import useDidMountEffect from "../UseDidMountEffect";
import main_logo from '../logo.png'
import { MenuItems } from "../HomePageMenuItems";

import './MyPage.css';

const StyledAvatar = Styled(Avatar)`
    box-shadow: 2px 2px 5px lightgray;
    height: 105px;
    width: 105px;
    border-radius: 100%;
    object-fit: cover;
`;

const StyledSelect = Styled(Select)`
    text-align: start;
    padding-left: 20px;
    padding-right: 10px;
    border: 1px solid lightgray;
    border-radius: 0;
    width: 230px;
    height: 48px;
    display: flex;
    outline-color: #03CF5D;
`;

function MyPage(props) {
    const [startUpInfoList, setStartUpInfoList] = useState([]);
    const [profileImg, setProfileImg] = useState("");
    const [nickname, setNickname] = useState("");
    const [email, setEmail] = useState("");
    const [id, setId] = useState([]);
    const [borough, setBorough] = useState([]);
    const [dong, setDong] = useState([]);
    const [choiced, setChoiced] = useState([]);
    const [clicked, setClicked] = useState(false);
    const [countList, setCountList] = useState([]);
    const [saved, setSaved] = useState([]);

    const handleClick = () => {setClicked(!clicked);}

    var categoryOption = [
        { value: '한식음식점', label: '한식음식점' },
        { value: '중식음식점', label: '중식음식점' },
        { value: '일식음식점', label: '일식음식점' },
        { value: '양식음식점', label: '양식음식점' },
        { value: '제과점', label: '제과점' },
        { value: '패스트푸드점', label: '패스트푸드점' },
        { value: '치킨전문점', label: '치킨전문점' },
        { value: '분식전문점', label: '분식전문점' },
        { value: '호프-간이주점', label: '호프-간이주점' },
        { value: '커피-음료', label: '커피-음료' },
    ];

    const getBasicUserInfo = () => {
        fetch('/mypage/users/me', {
            method : "POST",
            headers : {
                "Content-Type" : "application/json",
                Authorization : localStorage.getItem('login-token'),
            },
            body : JSON.stringify({})
        })
        .then(response => {return response.json()})
        .then(response => {
            setProfileImg(response.kakaoProfileImg);
            setNickname(response.kakaoNickname);
            setEmail(response.kakaoEmail);
        })
    }

    const getStartUpUserInfo = () => {
        fetch('/mypage/users/savedata', {
            method : "POST",
            headers : {
                "Content-Type" : "application/json",
                Authorization : localStorage.getItem('login-token'),
            },
            body : JSON.stringify({})
        })
        .then(response => {return response.json()})
        .then(response => {
            if(Array.isArray(response)){
                response.map((value)=>{
                    setStartUpInfoList(current => [...current, value]);
                })
            }
        })
    }

    const sendUpdatedUserInfo = () => {
        fetch('/mypage/users/me', {
            method : "POST",
            headers : {
                "Content-Type" : "application/json",
                Authorization : localStorage.getItem('login-token'),
            },
            body : JSON.stringify({
                kakaoProfileImg: profileImg,
                kakaoNickname: nickname,
            })
        })
    }

    const addStartUpInfo = () => {
        fetch('/mypage/users/save', {
            method : "POST",
            headers : {
                "Content-Type" : "application/json",
                Authorization : localStorage.getItem('login-token'),
            },
            body : JSON.stringify({
                borough: borough,
                dong: dong,
                serviceName: choiced,
            })
        })
    }

    const deleteStartUpInfo = (idx) => {
        fetch(`/mypage/users/${id[idx]}`, {
            method : "DELETE",
            headers : {
                "Content-Type" : "application/json",
                Authorization : localStorage.getItem('login-token'),
            },
        })
    }

    const handleChangeProfileImg = () => {
        /*
        const fileReader = new FileReader();
        fileReader.onload = function() {
            setProfileImg({result: fileReader.result})
        }
        fileReader.readAsDataURL(file);
        */
    };

    const handleChangeNickname = (event) => {
        setNickname(event.target.value);
    };

    const handleChangeBorough = (event) => {
        setBorough(current => [...current, event.target.value]);
    };

    const handleChangeDong = (event) => {        
        setDong(current => [...current, event.target.value]);
    };

    const checkStartUpInfo = (event) => { 
        addStartUpInfo();
        console.log("몇 번째 버튼1?", event.target);
        console.log("몇 번째 버튼2?", event.target.value);
        saved[event.target.value] = true;
        setSaved(saved);
    }

    const onAddDiv = () => {
        let countArr = [...countList];
        let counter = -1;
        if (countArr.length != 0) {
            counter = countArr.slice(-1)[0];
        }
        counter += 1;
        saved[counter] = false;
        setSaved(saved);
        countArr.push(counter);
        setCountList(countArr); 
    }

    const onDelDiv = (event) => {
        let countArr = [...countList];
        saved.pop(event.target.value);
        setSaved(saved);
        countArr.pop(event.target.value);
        setCountList(countArr);
        deleteStartUpInfo(event.target.value);
    }

    const handleSubmit = (event) => {
        sendUpdatedUserInfo();
        alert(`수정이 완료되었습니다.`);
        event.preventDefault();
    };

    useDidMountEffect(() => {
        setId([]);
        setBorough([]);
        setDong([]);
        setChoiced([]);
        getBasicUserInfo();
        getStartUpUserInfo();
        id.map((index)=>{setCountList(current => [...current, index]);})
        console.log("오우야1번째" + countList)
        console.log("오우야2번째" + dong.length)
        console.log("오우야3번째" + choiced.length)
    }, [])

    return (
        <div className="Page">
            <nav className='Navbar' style={{position: "static"}}>
                <h1 className="navbar-logo"><img src={main_logo} /></h1>
                <div className='menu-icon' onClick={handleClick}>
                    <i className={clicked ? 'fas fa-times' : 'fas fa-bars'}></i>
                </div>
                <ul className={clicked ? 'nav-menu active' : 'nav-menu'}>
                    {MenuItems.map((item, index)=>{
                        return (
                            <li key={index}>
                                <a className={item.cName} href={item.url}>{item.title}</a>
                            </li>
                        )
                    })}
                </ul>
            </nav>
            <div className="InPage">
                <div className="SideWrapper">
                    <div className="SideTag">마이 페이지</div>
                </div>
                <div className="Wrapper">
                    <div className="Container">
                        <div className="PostContainer">
                            <div className="BasicInfo">
                                <div className="PhotoContainer">
                                    <h3>기본 정보</h3>
                                    <StyledAvatar src={profileImg}/>
                                    <div className="PhotoButton" title="사진 변경" onClick={handleChangeProfileImg}>
                                    </div>
                                </div>
                                <div className="InBasicInfo">
                                    <div className="Label">
                                        <div className="Tag">이름</div>
                                        <input className="WriteBasic" type="text" defaultValue={nickname} onChange={handleChangeNickname}/>
                                    </div>
                                    <div style={{height: "10px"}}/>
                                    <div className="Label">
                                        <div className="Tag">이메일</div>
                                        <div className="WriteBasic" type="text" style={{backgroundColor: "white", color: "gray"}}>{email}</div>
                                    </div>
                                </div>
                            </div>

                            <div className="StartUpInfo">
                                <h3 style={{marginLeft: "130px", marginBottom: "25px"}}>창업 정보</h3>
                                <span style={{marginLeft: "130px", position: "absolute", top: "495px", color: "lightgray", fontSize: "12px"}}>* 최대 5개까지 등록할 수 있습니다.</span>
                                {countList && countList.map((item, index) => {
                                    return(
                                    <div className="InStartUpInfo">
                                        <input className="WriteStartUp" type="text" placeholder="자치구" defaultValue={borough[index]} onChange={handleChangeBorough} />
                                        <input className="WriteStartUp" type="text" placeholder="행정동" defaultValue={dong[index]} onChange={handleChangeDong} />
                                        <Select
                                            placeholder={"희망 업종"}
                                            options={categoryOption}
                                            defaultValue={{value: choiced[index], label: choiced[index]}}
                                            onChange={(value) => setChoiced(current => [...current, value["value"]])}
                                            styles={{
                                                control: provided => ({...provided, width: '230px', height: '48px', textAlign: 'start', paddingLeft: '10px'}),
                                                menu: provided => ({...provided, width: '230px', textAlign: 'start'})
                                            }}
                                            theme={(theme) => ({
                                                ...theme,
                                                borderRadius: 0,
                                                colors: {
                                                  ...theme.colors,
                                                  primary25: '#D3FFDC',
                                                  primary: '#03CF5D',
                                                  primary50: '#D3FFDC',
                                                },
                                            })}
                                        />
                                        {/*<select style={{borderRight: "1px solid lightgray"}} className="WriteStartUp" placeholder="희망 업종" onChange={(value) => setChoiced(value["value"])} defaultValue={{value: choiced, label: choiced}}>
                                            {categoryOption.map((item) => (
                                                <option className="OptionStyle" value={item.value}>{item.value}</option>
                                            ))}
                                            </select>*/}
                                        {
                                            saved[index] == false?
                                            <div className="CheckButton" onClick={checkStartUpInfo} value={index}></div>:
                                            <div className="DeleteButton" onClick={onDelDiv} value={index}></div>
                                        }
                                    </div>
                                )})}
                                {
                                    countList.length < 5?
                                    <div className="PlusButton" title="저장" onClick={onAddDiv}>
                                        창업 정보 추가 +
                                    </div>
                                    : null
                                }
                            </div>

                            <div className="CompleteButton" type="submit" title="수정 완료" onClick={handleSubmit}>
                                수정 완료
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default MyPage;