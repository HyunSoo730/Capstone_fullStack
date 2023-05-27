import { useEffect, useState } from "react";
import React from 'react';
import Select from 'react-select';

import './Sidebar.css';

function Sidebar() {
    const [salesRankUp, setSalesRankUp] = useState([]);
    const [salesUp3, setSalesUp3] = useState([]);
    const [salesUp4, setSalesUp4] = useState([]);
    const [salesIncreased, setSalesIncreased] = useState([]);
    const [salesRankDown, setSalesRankDown] = useState([]);
    const [salesDown3, setSalesDown3] = useState([]);
    const [salesDown4, setSalesDown4] = useState([]);
    const [salesDecreased, setSalesDecreased] = useState([]);

    const [floatingPopRankUp, setFloatingPopRankUp] = useState([]);
    const [floatingPopUp21, setFloatingPopUp21] = useState([]);
    const [floatingPopUp22, setFloatingPopUp22] = useState([]);
    const [floatingPopIncreased, setFloatingPopIncreased] = useState([]);
    const [floatingPopRankDown, setFloatingPopRankDown] = useState([]);
    const [floatingPopDown21, setFloatingPopDown21] = useState([]);
    const [floatingPopDown22, setFloatingPopDown22] = useState([]);
    const [floatingPopDecreased, setFloatingPopDecreased] = useState([]);

    const [rentalFeeRankUp, setRentalFeeRankUp] = useState([]);
    const [rentalFeeUp21, setRentalFeeUp21] = useState([]);
    const [rentalFeeUp22, setRentalFeeUp22] = useState([]);
    const [rentalFeeIncreased, setRentalFeeIncreased] = useState([]);
    const [rentalFeeRankDown, setRentalFeeRankDown] = useState([]);
    const [rentalFeeDown21, setRentalFeeDown21] = useState([]);
    const [rentalFeeDown22, setRentalFeeDown22] = useState([]);
    const [rentalFeeDecreased, setRentalFeeDecreased] = useState([]);

    const [recommendIndexRank, setRecommendIndexRank] = useState([]);
    const [nonRecommendIndexRank, setNonRecommendIndexRank] = useState([]);

    const [choiced, setChoiced] = useState("한식음식점");

    const data = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

    const findSalesRankUp = () => {
        fetch(`/api/local-commerce/${choiced}/top_rank`, {
            method : "GET",
            headers : {"Content-Type" : "application/json"},
        })
        .then(response => {return response.json()})
        .then(response => {
            if(Array.isArray(response)){
                response.map((value)=>{
                    setSalesRankUp(current => [...current, value.dong]);
                    setSalesUp3(current => [...current, value.quarterThreeTotal]);
                    setSalesUp4(current => [...current, value.quarterFourTotal]);
                    setSalesIncreased(current => [...current, value.growthRateFigures]);
                })
            }
        })
    }

    const findSalesRankDown = () => {
        fetch(`/api/local-commerce/${choiced}/low_rank`, {
            method : "GET",
            headers : {"Content-Type" : "application/json"},
        })
        .then(response => {return response.json()})
        .then(response => {
            if(Array.isArray(response)){
                response.map((value)=>{
                    setSalesRankDown(current => [...current, value.dong]);
                    setSalesDown3(current => [...current, value.quarterThreeTotal]);
                    setSalesDown4(current => [...current, value.quarterFourTotal]);
                    setSalesDecreased(current => [...current, value.growthRateFigures]);
                })
            }
        })
    }

    const findFloatingPopRankUp = () => {
        fetch('/api/rank/floating', {
            method : "GET",
            headers : {"Content-Type" : "application/json"},
        })
        .then(response => {return response.json()})
        .then(response => {
            if(Array.isArray(response)){
                response.map((value)=>{
                    setFloatingPopRankUp(current => [...current, value.areaName]);
                    setFloatingPopUp21(current => [...current, value.floating2021]);
                    setFloatingPopUp22(current => [...current, value.floating2022]);
                    setFloatingPopIncreased(current => [...current, value.riseRate]);
                })
            }
        })
    }

    const findFloatingPopRankDown = () => {
        fetch('/api/rank/floating/lower', {
            method : "GET",
            headers : {"Content-Type" : "application/json"},
        })
        .then(response => {return response.json()})
        .then(response => {
            if(Array.isArray(response)){
                response.map((value)=>{
                    setFloatingPopRankDown(current => [...current, value.areaName]);
                    setFloatingPopDown21(current => [...current, value.floating2021]);
                    setFloatingPopDown22(current => [...current, value.floating2022]);
                    setFloatingPopDecreased(current => [...current, value.riseRate]);
                })
            }
        })
    }

    const findRentalFeeRankUp = () => {
        fetch('/api/rank/rentalfee', {
            method : "GET",
            headers : {"Content-Type" : "application/json"},
        })
        .then(response => {return response.json()})
        .then(response => {
            if(Array.isArray(response)){
                response.map((value)=>{
                    setRentalFeeRankUp(current => [...current, value.areaName]);
                    setRentalFeeUp21(current => [...current, value.rentalFee21]);
                    setRentalFeeUp22(current => [...current, value.rentalFee22]);
                    setRentalFeeIncreased(current => [...current, value.riseRate]);
                })
            }
        })
    }

    const findRentalFeeRankDown = () => {
        fetch('/api/rank/rentalfee/lower', {
            method : "GET",
            headers : {"Content-Type" : "application/json"},
        })
        .then(response => {return response.json()})
        .then(response => {
            if(Array.isArray(response)){
                response.map((value)=>{
                    setRentalFeeRankDown(current => [...current, value.areaName]);
                    setRentalFeeDown21(current => [...current, value.rentalFee21]);
                    setRentalFeeDown22(current => [...current, value.rentalFee22]);
                    setRentalFeeDecreased(current => [...current, value.riseRate]);
                })
            }
        })
    }

    const findRecommendIndexRank = () => {
        fetch('/api/rank/floating-rentalfee', {
            method : "GET",
            headers : {"Content-Type" : "application/json"},
        })
        .then(response => {return response.json()})
        .then(response => {
            if(Array.isArray(response)){
                response.map((value)=>{
                    setRecommendIndexRank(current => [...current, value.areaName]);
                })
            }
        })
    }
    
    const findNonRecommendIndexRank = () => {
        fetch('/api/rank/floating-rentalfee/lower', {
            method : "GET",
            headers : {"Content-Type" : "application/json"},
        })
        .then(response => {return response.json()})
        .then(response => {
            if(Array.isArray(response)){
                response.map((value)=>{
                    setNonRecommendIndexRank(current => [...current, value.areaName]);
                })
            }
        })
    }

    useEffect(() => {
        setSalesRankUp([]);
        setSalesUp3([]);
        setSalesUp4([]);
        setSalesIncreased([]);
        setSalesRankDown([]);
        setSalesDown3([]);
        setSalesDown4([]);
        setSalesDecreased([]);
    
        setFloatingPopRankUp([]);
        setFloatingPopUp21([]);
        setFloatingPopUp22([]);
        setFloatingPopIncreased([]);
        setFloatingPopRankDown([]);
        setFloatingPopDown21([]);
        setFloatingPopDown22([]);
        setFloatingPopDecreased([]);
    
        setRentalFeeRankUp([]);
        setRentalFeeUp21([]);
        setRentalFeeUp22([]);
        setRentalFeeIncreased([]);
        setRentalFeeRankDown([]);
        setRentalFeeDown21([]);
        setRentalFeeDown22([]);
        setRentalFeeDecreased([]);
    
        setRecommendIndexRank([]);
        setNonRecommendIndexRank([]);
        
        findSalesRankUp();
        findSalesRankDown();

        findFloatingPopRankUp();
        findFloatingPopRankDown();

        findRentalFeeRankUp();
        findRentalFeeRankDown();

        findRecommendIndexRank();
        findNonRecommendIndexRank();
    },[choiced])

    const [btnActivate, setBtnActivate] = useState("sales");
    const toggleActivate = (e) => {
        setBtnActivate((prev) => {return e.target.value;});
    };

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

    return (
        <div className = "sidebar">

            <div className = "side_filter">
                <button value = "sales" className = {"filter_button" + (btnActivate == "sales" ? " activated" : "")} onClick={toggleActivate}>매출</button>
                <button value = "floating" className = {"filter_button" + (btnActivate == "floating" ? " activated" : "")} onClick={toggleActivate}>유동인구</button>
                <button value = "rental" className = {"filter_button" + (btnActivate == "rental" ? " activated" : "")} onClick={toggleActivate}>임대료</button>
                <button value = "recommend" className = {"filter_button" + (btnActivate == "recommend" ? " activated" : "")} onClick={toggleActivate}>추천 지역</button>
            </div>

            {
                btnActivate === "sales" ?
                <div style={{boxShadow: "2px 2px 3px gray", width: "400px", borderRadius: "5px"}}>
                    <Select options={categoryOption} defaultValue={{value: choiced, label: choiced}} onChange={(value) => setChoiced(value["value"])}/>
                </div>
                : null
            }

            <div className = "side_rank">
                {
                    btnActivate === "sales" ?
                    <div>
                        <h5>{choiced} 매출 증가량 랭킹 TOP10</h5>
                        <p style={{height: "10px"}}/>
                        <div>순위　 　　지역　　　3분기　 　4분기　 　증가량</div>
                        <p style={{height: "2px"}}/>
                        <p style={{width: "350px", backgroundColor: "lightgray", height: "1px"}}/>
                        <p style={{height: "2px"}}/>
                        <div className="side_inside">
                            <div className="side_index">{data.map((idx) => {return(<div style={{textAlign: "start"}}>{idx}</div>)})}</div>
                            <div className="side_index">{data.map((idx) => {return(<div style={{textAlign: "end"}}>{salesRankUp[idx - 1]}</div>)})}</div>
                            <div className="side_index">{data.map((idx) => {return(<div style={{textAlign: "end"}}>{Math.round(salesUp3[idx - 1] / 10000000) / 10}억</div>)})}</div>
                            <div className="side_index">{data.map((idx) => {return(<div style={{textAlign: "end"}}>{Math.round(salesUp4[idx - 1] / 10000000) / 10}억</div>)})}</div>
                            <div className="side_index">{data.map((idx) => {return(<div style={{textAlign: "end", color: "tomato"}}>{Math.round(salesIncreased[idx - 1] * 10) / 10}%↑</div>)})}</div>
                        </div>
                        <p style={{height: "5px"}}/>
                    </div>
                    : null
                }
                {
                    btnActivate === "floating" ?
                    <div>
                        <h5>유동 인구 증가량 랭킹 TOP10</h5>
                        <p style={{height: "10px"}}/>
                        <div>순위　　지역　 　2021년 　 2022년　　증가량</div>
                        <p style={{height: "2px"}}/>
                        <p style={{width: "350px", backgroundColor: "lightgray", height: "1px"}}/>
                        <p style={{height: "2px"}}/>
                        <div className="side_inside">
                            <div className="side_index">{data.map((idx) => {return(<div style={{textAlign: "start"}}>{idx}</div>)})}</div>
                            <div className="side_index">{data.map((idx) => {return(<div style={{textAlign: "end"}}>{floatingPopRankUp[idx - 1]}</div>)})}</div>
                            <div className="side_index">{data.map((idx) => {return(<div style={{textAlign: "end"}}>{Math.round(floatingPopUp21[idx - 1] / 1000) / 10}만</div>)})}</div>
                            <div className="side_index">{data.map((idx) => {return(<div style={{textAlign: "end"}}>{Math.round(floatingPopUp22[idx - 1] / 1000) / 10}만</div>)})}</div>
                            <div className="side_index">{data.map((idx) => {return(<div style={{textAlign: "end", color: "tomato"}}>{Math.round(floatingPopIncreased[idx - 1] * 10) / 10}%↑</div>)})}</div>
                        </div>
                        <p style={{height: "5px"}}/>
                    </div>
                    : null
                }
                {
                    btnActivate === "rental" ?
                    <div>
                        <h5>임대료 증가량 랭킹 TOP10</h5>
                        <p style={{height: "10px"}}/>
                        <div>순위　　지역　 　2021년 　 2022년　　증가량</div>
                        <p style={{height: "2px"}}/>
                        <p style={{width: "350px", backgroundColor: "lightgray", height: "1px"}}/>
                        <p style={{height: "2px"}}/>
                        <div className="side_inside">
                            <div className="side_index">{data.map((idx) => {return(<div style={{textAlign: "start"}}>{idx}</div>)})}</div>
                            <div className="side_index">{data.map((idx) => {return(<div style={{textAlign: "end"}}>{rentalFeeRankUp[idx - 1]}</div>)})}</div>
                            <div className="side_index">{data.map((idx) => {return(<div style={{textAlign: "end"}}>{Math.round(rentalFeeUp21[idx - 1] / 1000) / 10}만</div>)})}</div>
                            <div className="side_index">{data.map((idx) => {return(<div style={{textAlign: "end"}}>{Math.round(rentalFeeUp22[idx - 1] / 1000) / 10}만</div>)})}</div>
                            <div className="side_index">{data.map((idx) => {return(<div style={{textAlign: "end", color: "tomato"}}>{Math.round(rentalFeeIncreased[idx - 1] * 10) / 10}%↑</div>)})}</div>
                        </div>
                        <p style={{height: "5px"}}/>
                    </div>
                    : null
                }
                {
                    btnActivate === "recommend" ?
                    <div>
                        <h5>창업하기<span style={{color: "royalblue"}}> 좋은</span> 지역 TOP10</h5>
                        <p style={{height: "7px"}}/>
                        <p style={{width: "250px", backgroundColor: "lightgray", height: "1px"}}/>
                        <div className="side_inside">
                            <div className="side_index">
                                <p style={{height: "15px"}}/>
                                {data.map((idx) => {return(<div style={{textAlign: "start"}}>{idx}</div>)})}
                            </div>
                            <div className="side_index">
                                <p style={{height: "15px"}}/>
                                {data.map((idx) => {return(<div style={{textAlign: "end"}}>{recommendIndexRank[idx - 1]}</div>)})}
                            </div>
                        </div>
                        <p style={{height: "5px"}}/>
                    </div>
                    : null
                }
            </div>

            <div className = "side_rank">
                {
                    btnActivate === "sales" ?
                    <div>
                        <h5>{choiced} 매출 감소량 랭킹 TOP10</h5>
                        <p style={{height: "10px"}}/>
                        <div>순위　 　　지역　　3분기　 　4분기　 　감소량</div>
                        <p style={{height: "2px"}}/>
                        <p style={{width: "350px", backgroundColor: "lightgray", height: "1px"}}/>
                        <p style={{height: "2px"}}/>
                        <div className="side_inside">
                            <div className="side_index">{data.map((idx) => {return(<div style={{textAlign: "start"}}>{idx}</div>)})}</div>
                            <div className="side_index">{data.map((idx) => {return(<div style={{textAlign: "end"}}>{salesRankDown[idx - 1]}</div>)})}</div>
                            <div className="side_index">{data.map((idx) => {return(<div style={{textAlign: "end"}}>{Math.round(salesDown3[idx - 1] / 10000000) / 10}억</div>)})}</div>
                            <div className="side_index">{data.map((idx) => {return(<div style={{textAlign: "end"}}>{Math.round(salesDown4[idx - 1] / 10000000) / 10}억</div>)})}</div>
                            <div className="side_index">{data.map((idx) => {return(<div style={{textAlign: "end", color: "royalblue"}}>{-Math.round(salesDecreased[idx - 1] * 10) / 10}%↓</div>)})}</div>
                        </div>
                        <p style={{height: "5px"}}/>
                    </div>
                    : null
                }
                {
                    btnActivate === "floating" ?
                    <div>
                        <h5>유동 인구 감소량 랭킹 TOP10</h5>
                        <p style={{height: "10px"}}/>
                        <div>순위　　지역　 　2021년 　 2022년　　감소량</div>
                        <p style={{height: "2px"}}/>
                        <p style={{width: "350px", backgroundColor: "lightgray", height: "1px"}}/>
                        <p style={{height: "2px"}}/>
                        <div className="side_inside">
                            <div className="side_index">{data.map((idx) => {return(<div style={{textAlign: "start"}}>{idx}</div>)})}</div>
                            <div className="side_index">{data.map((idx) => {return(<div style={{textAlign: "end"}}>{floatingPopRankDown[idx - 1]}</div>)})}</div>
                            <div className="side_index">{data.map((idx) => {return(<div style={{textAlign: "end"}}>{Math.round(floatingPopDown21[idx - 1] / 1000) / 10}만</div>)})}</div>
                            <div className="side_index">{data.map((idx) => {return(<div style={{textAlign: "end"}}>{Math.round(floatingPopDown22[idx - 1] / 1000) / 10}만</div>)})}</div>
                            <div className="side_index">{data.map((idx) => {return(<div style={{textAlign: "end", color: "royalblue"}}>{-Math.round(floatingPopDecreased[idx - 1] * 10) / 10}%↓</div>)})}</div>
                        </div>
                        <p style={{height: "5px"}}/>
                    </div>
                    : null
                }
                {
                    btnActivate === "rental" ?
                    <div>
                        <h5>임대료 감소량 랭킹 TOP10</h5>
                        <p style={{height: "10px"}}/>
                        <div>순위　　지역　 　2021년 　 2022년　　감소량</div>
                        <p style={{height: "2px"}}/>
                        <p style={{width: "350px", backgroundColor: "lightgray", height: "1px"}}/>
                        <p style={{height: "2px"}}/>
                        <div className="side_inside">
                            <div className="side_index">{data.map((idx) => {return(<div style={{textAlign: "start"}}>{idx}</div>)})}</div>
                            <div className="side_index">{data.map((idx) => {return(<div style={{textAlign: "end"}}>{rentalFeeRankDown[idx - 1]}</div>)})}</div>
                            <div className="side_index">{data.map((idx) => {return(<div style={{textAlign: "end"}}>{Math.round(rentalFeeDown21[idx - 1] / 1000) / 10}만</div>)})}</div>
                            <div className="side_index">{data.map((idx) => {return(<div style={{textAlign: "end"}}>{Math.round(rentalFeeDown22[idx - 1] / 1000) / 10}만</div>)})}</div>
                            <div className="side_index">{data.map((idx) => {return(<div style={{textAlign: "end", color: "royalblue"}}>{-Math.round(rentalFeeDecreased[idx - 1] * 10) / 10}%↓</div>)})}</div>
                        </div>
                        <p style={{height: "5px"}}/>
                    </div>
                    : null
                }
                {
                    btnActivate === "recommend" ?
                    <div>
                        <h5>창업하기<span style={{color: "tomato"}}> 아쉬운</span> 지역 TOP10</h5>
                        <p style={{height: "7px"}}/>
                        <p style={{width: "250px", backgroundColor: "lightgray", height: "1px"}}/>
                        <div className="side_inside">
                            <div className="side_index">
                                <p style={{height: "15px"}}/>
                                {data.map((idx) => {return(<div style={{textAlign: "start"}}>{idx}.</div>)})}
                            </div>
                            <div className="side_index">
                                <p style={{height: "15px"}}/>
                                {data.map((idx) => {return(<div style={{textAlign: "end"}}>{nonRecommendIndexRank[idx - 1]}</div>)})}
                            </div>
                        </div>
                        <p style={{height: "5px"}}/>
                    </div>
                    : null
                }
            </div>

        </div>
    )
}

export default Sidebar;