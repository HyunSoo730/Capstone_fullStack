import { React, useState, useEffect } from 'react';
import ReactApexChart from "react-apexcharts";
import { MapContainer, TileLayer, GeoJSON, useMapEvents, Marker, Popup} from 'react-leaflet';
import { Drawer, Button } from 'rsuite';
import geoData from './LocationData.json';
import geoDetailData from './LocationDetailData.json';
import {GU_locationData} from './LocationDataGUItems';
import {locationData} from './LocationDataItems';
import Sidebar from './Jaehyeok_Lee/Sidebar';
import Select from 'react-select';

import { MenuItems } from "./HomePageMenuItems";

import 'leaflet/dist/leaflet.css';
import "rsuite/dist/rsuite.css";

function Analysis(props){

  const [CountMarketNum, setCountMarketNum] = useState([]);
  const [ResidentNum, setResidentNum] = useState([]);
  const [WorkNum, setWorkNum] = useState([]);
  const [WorkEarned, setWorkEarned] = useState([]);
  const [FacilityNum, setFacilityNum] = useState([]);
  const [SexFloatingPop, setSexFloatingPop] = useState([]);
  const [AgeFloatingPop, setAgeFloatingPop] = useState([]);
  const [TimeFloatingPop, setTimeFloatingPop] = useState([]);
  const [WeekFloatingPop, setWeekFloatingPop] = useState([]);
  const [RentalFee, setRentalFee] = useState([]);
  const [AvgPeriod, setAvgPeriod] = useState();
  const [MarketFuture, setMarketFuture] = useState([]);

  const [isDrawerOpen, setDrawerOpen] = useState(false);
  const [DrawerTitle, setDrawerTitle] = useState("DRAWER_TITLE_ERR");
  const [DrawerDetailCode, setDrawerDetailCode] = useState("DRAWER_CODE_ERR");
  const [DrawerGU, setDrawerGU] = useState("DRAWER_GU_ERR");

  const [MyZoom, setMyZoom] = useState(12);
  const [MyLayer, setMyLayer] = useState();

  const [MarketSelection, setMarketSelection] = useState("한식음식점");

  const findDong = (val) => {
    for(let i = 0; i < locationData.length; i++) {
      if (locationData[i].includes(val.slice(0,2))){
        return locationData[i];
      }
    }
    return "성수동";
  }

  var SelectOption = [
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

  var changeName = {
    "numOfFranchiseStores" : "프랜차이즈 매장 수",
    "numOfStores" : "일반 매장 수",
    "totalNumOfStores" : "총 매장 수",
    "numOfMenResidents" : "남자 인구",
    "totalNumOfResidents": "총 인구",
    "numOfWomenResidents" : "여자 인구",
    "numOfAge10Residents": "10대 인구",
    "numOfAge20Residents": "20대 인구",
    "numOfAge30Residents": "30대 인구",
    "numOfAge40Residents": "40대 인구",
    "numOfAge50Residents": "50대 인구",
    "numOfAge60Residents": "60대 인구",
    "totalNumOfWorkplace": "총 직장 인구",
    "numOfMenWorkplace": "남자 직장 인구",
    "numOfWomenWorkplace": "여자 직장 인구",
    "numOf10AgeWorkplace": "10대 직장 인구",
    "numOf20AgeWorkplace": "20대 직장 인구",
    "numOf30AgeWorkplace": "30대 직장 인구",
    "numOf40AgeWorkplace": "40대 직장 인구",
    "numOf50AgeWorkplace": "50대 직장 인구",
    "numOf60AgeWorkplace": "60대 직장 인구",
    "numOfFacility": "총 집객시설 수",
    "numOfGovernmentOffice": "관공서 수",
    "numOfBank": "은행 수",
    "numOfPharmacy": "약국 수",
    "numOfHospital": "병원 수",
    "numOfGeneralHospital": "일반 병원 수",
    "numOfKindergarten": "유치원 수",
    "numOfElementarySchool": "초등학교 수",
    "numOfMiddleSchool": "중학교 수",
    "numOfHighSchool": "고등학교 수",
    "numOfUniversity": "대학교 수",
    "numOfDepartmentStore": "백화점 수",
    "numOfSupermarket": "마트 수",
    "numOfTheater": "영화관 수",
    "numOfAccommodation": "숙박시설 수",
    "numOfRailStation": "기차역 수",
    "numOfAirport": "공항 수",
    "numOfBusTerminal": "버스 터미널 수",
    "numOfSubway": "지하철역 수",
    "numOfBusStop": "버스 정류장 수",
    "male_flpop": "남자 유동인구",
    "female_flpop": "여자 유동인구",
    "age_10_flpop": "10대 유동인구",
    "age_20_flpop": "20대 유동인구",
    "age_30_flpop": "30대 유동인구",
    "age_40_flpop": "40대 유동인구",
    "age_50_flpop": "50대 유동인구",
    "age_60_flpop": "60대 유동인구",
    "time_1_flpop": "00시~06시 유동인구",
    "time_2_flpop": "06시~11시 유동인구",
    "time_3_flpop": "11시~14시 유동인구",
    "time_4_flpop": "14시~17시 유동인구",
    "time_5_flpop": "17시~21시 유동인구",
    "time_6_flpop": "21시~24시 유동인구",
    "mon_flpop": "월요일 유동인구",
    "tue_flpop": "화요일 유동인구",
    "wed_flpop": "수요일 유동인구",
    "thu_flpop": "목요일 유동인구",
    "fri_flpop": "금요일 유동인구",
    "sat_flpop": "토요일 유동인구",
    "sun_flpop": "요일 유동인구",
    "rentalfee_total": "총 임대료 평균",
    "averageMonthlyIncome": "평균 한달 임금",
    "totalAmountSpent": "총 소비",
  };

  const [clicked, setClicked] = useState(false);

    const handleClick = () => {
        setClicked(!clicked);
    }

  var ApexChartLineOption = {
    chart: {
      height: 300,
      type: "line",
      stacked: false,
      toolbar: {
        show: false
      }
    },
    dataLabels: {
        enabled: true,
        formatter: function (val) {
          return val;
        },
        offsetY: -20,
        style: {
          fontSize: '12px',
          colors: ["#304758"]
        }
      },
    stroke: {
      width: [4, 4]
    },
    plotOptions: {
      bar: {
        columnWidth: "20%"
      }
    },
    xaxis: {
      categories: ["2022년 1분기", "2022년 2분기", "2022년 3분기", "2022년 4분기"]
    },
    yaxis: {
        axisBorder: {
          show: false
      }
    }
  };

 
  const ApexChartBarOption = {
    chart: {
      height: 350,
      type: 'bar',
      toolbar: {
        show: false
      }
    },
    plotOptions: {
      bar: {
        borderRadius: 10,
        dataLabels: {
          position: 'top', // top, center, bottom
        },
      }
    },
    dataLabels: {
      enabled: true,
      formatter: function (val) {
        return val;
      },
      offsetY: -20,
      style: {
        fontSize: '12px',
        colors: ["#304758"]
      }
    },
    xaxis: {
      categories: ["2022년 4분기"],
      position: 'top',
      axisBorder: {
        show: false
      },
      axisTicks: {
        show: false
      },
      crosshairs: {
        fill: {
          type: 'gradient',
          gradient: {
            colorFrom: '#D8E3F0',
            colorTo: '#BED1E6',
            stops: [0, 100],
            opacityFrom: 0.4,
            opacityTo: 0.5,
          }
        }
      },
      tooltip: {
        enabled: true,
      }
    },
    yaxis: {
      axisBorder: {
        show: false
      },
      axisTicks: {
        show: false,
      },
      labels: {
        show: false,
        formatter: function (val) {
          return val;
        }
      }
    }
  };
  /** 
   * BACK-END POST REQUEST
   * BODY : borough, dong
   * @param String URL 정보
   * @returns JSON Array
  **/
  const MakeAnalysisData = (url) => {
    let analysis_data = fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        borough: GU_locationData[DrawerGU.slice(0, 5)],
        dong: findDong(DrawerTitle),
      }),
    })  
    .then(response => { 
      return response.json();
    });
    return analysis_data;
  }
  /** 
   * BACK-END GET REQUEST
   * QUERY : commercialCode
   * @param String URL 정보
   * @returns JSON Array
  **/
  const MakeAnalysisDetailData = (url) => {
    let analysis_data = fetch(url, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
    })  
    .then(response => { 
      return response.json();
    });
    return analysis_data;
  }
  //음료 바꿀 것
  const MakeChartData = (targetValue, data_type) => {
    if (data_type === "market") {
      let analysis_data = null;
      if (MyZoom < 15) {
        analysis_data = fetch(`/api/local-commerce/integrated_dong/industry`, {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            borough: GU_locationData[DrawerGU.slice(0, 5)],
            dong: DrawerTitle,
            serviceName: MarketSelection
          }),
        })  
        .then(response => { 
          return response.json();
        });
      }
      else {
        analysis_data = fetch(`/api/single_commerce/industry?commercialCode=${DrawerDetailCode}&serviceName=${MarketSelection}`, {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
          },
        })  
        .then(response => { 
          return response.json();
        });
      }
      analysis_data.then(response => {
        if(Array.isArray(response)){
        var year_data = response.map((item)=>{
          return item[targetValue];
        });
        setCountMarketNum(current => [...current, {name: changeName[targetValue], data: year_data.slice(0, 4)}]);
      }
      }); 
    }
    else if(data_type === "workearned") {
      let analysis_data = null;
      if (MyZoom < 15){
        analysis_data = MakeAnalysisData("/api/local-commerce/integrated_dong/income_consumption");
      }
      else{
        analysis_data = MakeAnalysisDetailData(`/api/single_commerce/income_consumption?commercialCode=${DrawerDetailCode}`);
      }
      analysis_data.then(response => {
        if(Array.isArray(response)){
        var year_data = response.map((item)=>{
          return item[targetValue];
        });
        setWorkEarned(current => [...current, {name: changeName[targetValue], data: year_data.slice(0, 4)}]);
      }
      }); 
    }
    else if(data_type === "fee"){
      let analysis_data = null;
      analysis_data = MakeAnalysisDetailData(`/api/local-commerce/total/rentalfee/${GU_locationData[DrawerGU.slice(0, 5)]}/${findDong(DrawerTitle)}`);
      analysis_data.then(response => {
        if(Array.isArray(response)){
        var year_data = response.map((item)=>{
          return item[targetValue];
        });
      setRentalFee(current => [...current, {name: changeName[targetValue], data: year_data.slice(0, 4)}]);
        }
      });
    }
  }

  const MakeCurrentChartData = (targetValue, data_type) => {
    let analysis_data = null
    if(data_type === "resident"){
      if (MyZoom < 15) {
        analysis_data = MakeAnalysisData("/api/local-commerce/integrated_dong/resident");
      }
      else {
        analysis_data = MakeAnalysisDetailData(`/api/single_commerce/resident?commercialCode=${DrawerDetailCode}`);
      }
      analysis_data.then(response => {
        var current_data = response[3][targetValue];
        setResidentNum(current => [...current, {name: changeName[targetValue], data: [current_data]}]);
      });
    }
    else if(data_type === "work"){
      if (MyZoom < 15){
        analysis_data = MakeAnalysisData("/api/local-commerce/integrated_dong/workplace");
      }
      else {
        analysis_data = MakeAnalysisDetailData(`/api/single_commerce/workplace?commercialCode=${DrawerDetailCode}`);
      }
      analysis_data.then(response => {
        var current_data = response[3][targetValue];
        setWorkNum(current => [...current, {name: changeName[targetValue], data: [current_data]}]);
      });
    }
    else if(data_type === "facility") {
      if (MyZoom < 15) {
        analysis_data = MakeAnalysisData("/api/local-commerce/integrated_dong/facility");
      }
      else {
        analysis_data = MakeAnalysisDetailData(`/api/single_commerce/facility?commercialCode=${DrawerDetailCode}`);
      }
      analysis_data.then(response => {
        var current_data = response[3][targetValue];
        setFacilityNum(current => [...current, {name: changeName[targetValue], data: [current_data]}]);
      });
    }
    else if(data_type === "timepopulation"){
      let analysis_data = null;
      if (MyZoom < 15){
        analysis_data = MakeAnalysisDetailData(`/api/local-commerce/total/floating/${GU_locationData[DrawerGU.slice(0, 5)]}/${findDong(DrawerTitle)}`);
      }
      else{
        analysis_data = MakeAnalysisDetailData(`/api/local-commerce/total/floating/${GU_locationData[DrawerGU.slice(0, 5)]}/${findDong(DrawerTitle)}`);
      }
      analysis_data.then(response => {
        var current_data = response[3][targetValue];
        setTimeFloatingPop(current => [...current, {name: changeName[targetValue], data: [current_data]}]);
      });
    }
    else if(data_type === "sexpopulation"){
      let analysis_data = null;
      if (MyZoom < 15){
        analysis_data = MakeAnalysisDetailData(`/api/local-commerce/total/floating/${GU_locationData[DrawerGU.slice(0, 5)]}/${findDong(DrawerTitle)}`);
      }
      else{
        analysis_data = MakeAnalysisDetailData(`/api/local-commerce/total/floating/${GU_locationData[DrawerGU.slice(0, 5)]}/${findDong(DrawerTitle)}`);
      }
      analysis_data.then(response => {
      var current_data = response[3][targetValue];
      setSexFloatingPop(current => [...current, {name: changeName[targetValue], data: [current_data]}]);
      });
    }
    else if(data_type === "agepopulation"){
      let analysis_data = null;
      if (MyZoom < 15){
        analysis_data = MakeAnalysisDetailData(`/api/local-commerce/total/floating/${GU_locationData[DrawerGU.slice(0, 5)]}/${findDong(DrawerTitle)}`);
      }
      else{
        analysis_data = MakeAnalysisDetailData(`/api/local-commerce/total/floating/${GU_locationData[DrawerGU.slice(0, 5)]}/${findDong(DrawerTitle)}`);
      }
      analysis_data.then(response => {
      var current_data = response[3][targetValue];
      setAgeFloatingPop(current => [...current, {name: changeName[targetValue], data: [current_data]}]);
      });
    }
    else if (data_type === "weekpopulation"){
      let analysis_data = null;
      if (MyZoom < 15){
        analysis_data = MakeAnalysisDetailData(`/api/local-commerce/total/floating/${GU_locationData[DrawerGU.slice(0, 5)]}/${findDong(DrawerTitle)}`);
      }
      else{
        analysis_data = MakeAnalysisDetailData(`/api/local-commerce/total/floating/${GU_locationData[DrawerGU.slice(0, 5)]}/${findDong(DrawerTitle)}`);
      }
      analysis_data.then(response => {
      var current_data = response[3][targetValue];
      setWeekFloatingPop(current => [...current, {name: changeName[targetValue], data: [current_data]}]);
      });
    }
    else if (data_type === "avgperiod"){
      analysis_data = MakeAnalysisDetailData(`/api/local-commerce/total/operation/${GU_locationData[DrawerGU.slice(0, 5)]}/${findDong(DrawerTitle)}`);
      analysis_data.then(response => {
        setAvgPeriod(response[targetValue]);
      });
    }
    else if (data_type === "marketfuture"){
      analysis_data = MakeAnalysisData(`/api/local-commerce/integrated_dong/change`);
      analysis_data.then(response => {
          setMarketFuture(Object.values(response));
      });
    }
  }

  function whenClicked(e, feature, mode) {
    setDrawerOpen(true);
    if (mode === "normal"){
      setDrawerTitle(feature.properties.EMD_NM);
      setDrawerGU(feature.properties.EMD_CD);
    }
    else {
      setDrawerTitle(feature.properties.TRDAR_NM);
      setDrawerDetailCode(feature.properties.TRDAR_NO);
    }
  }
  
  const onEachFeature = (feature, layer) => {
    layer.on('click', function (e) {
      setMyLayer(e);
      whenClicked(e, feature, "normal");
      layer.setStyle({ fillColor: 'rgba(1,1,1,0)' });
      layer.bindPopup(feature.properties.EMD_NM).openPopup();
    });
    layer.on('mouseover', function (e) {
      layer.setStyle({ fillColor: 'rgba(1,1,1,0)' });
      layer.bindPopup(feature.properties.EMD_NM).openPopup();
    });
    layer.on('mouseout', function (e) {
      layer.setStyle({ fillColor: 'blue' });
    });
  }

  const onEachDetailFeature = (feature, layer) => {
    layer.on('click', function (e) {
      setMyLayer(e);
      layer.setStyle({ fillColor: 'rgba(1,1,1,0)' });
      layer.bindPopup(feature.properties.TRDAR_NM).openPopup();
      whenClicked(e, feature, "detail");
    });
    layer.on('mouseover', function (e) {
      layer.setStyle({ fillColor: 'rgba(1,1,1,0)' });
      layer.bindPopup(feature.properties.TRDAR_NM).openPopup();
    });
    layer.on('mouseout', function (e) {
      layer.setStyle({ fillColor: 'red' });
    });
  }
  const RenderingGeoJSON = () => {
    const MyMap  = useMapEvents({
      zoomend() {
        setMyZoom(MyMap.getZoom())
      }
    })
    return <GeoJSON data={MyZoom < 15 ? geoData : geoDetailData} onEachFeature={MyZoom < 15 ? onEachFeature : onEachDetailFeature} color={MyZoom < 15 ? "BLUE" : "RED"}/>;
  }

  useEffect(()=>{
    if (isDrawerOpen === true) {
      setMarketFuture();
      MakeCurrentChartData("commerceMetrics", "marketfuture")
      setCountMarketNum([]);
      MakeChartData("numOfStores", "market");
      MakeChartData("totalNumOfStores", "market");
      MakeChartData("numOfFranchiseStores", "market");
      setResidentNum([]);
      MakeCurrentChartData("totalNumOfResidents", "resident");
      MakeCurrentChartData("numOfMenResidents", "resident");
      MakeCurrentChartData("numOfWomenResidents", "resident");
      MakeCurrentChartData("numOfAge10Residents", "resident");
      MakeCurrentChartData("numOfAge20Residents", "resident");
      MakeCurrentChartData("numOfAge30Residents", "resident");
      MakeCurrentChartData("numOfAge40Residents", "resident");
      MakeCurrentChartData("numOfAge50Residents", "resident");
      MakeCurrentChartData("numOfAge60Residents", "resident");
      setWorkNum([]);
      MakeCurrentChartData("totalNumOfWorkplace", "work");
      MakeCurrentChartData("numOfMenWorkplace", "work");
      MakeCurrentChartData("numOfWomenWorkplace", "work");
      MakeCurrentChartData("numOf10AgeWorkplace", "work");
      MakeCurrentChartData("numOf20AgeWorkplace", "work");
      MakeCurrentChartData("numOf30AgeWorkplace", "work");
      MakeCurrentChartData("numOf40AgeWorkplace", "work");
      MakeCurrentChartData("numOf50AgeWorkplace", "work");
      MakeCurrentChartData("numOf60AgeWorkplace", "work");
      setWorkEarned([]);
      MakeChartData("averageMonthlyIncome", "workearned");
      MakeChartData("totalAmountSpent", "workearned");
      setFacilityNum([]);
      MakeCurrentChartData("numOfFacility", "facility");
      MakeCurrentChartData("numOfGovernmentOffice", "facility");
      MakeCurrentChartData("numOfBank", "facility");
      MakeCurrentChartData("numOfGeneralHospital", "facility");
      MakeCurrentChartData("numOfHospital", "facility");
      MakeCurrentChartData("numOfPharmacy", "facility");
      MakeCurrentChartData("numOfKindergarten", "facility");
      MakeCurrentChartData("numOfElementarySchool", "facility");
      MakeCurrentChartData("numOfMiddleSchool", "facility");
      MakeCurrentChartData("numOfHighSchool", "facility");
      MakeCurrentChartData("numOfUniversity", "facility");
      MakeCurrentChartData("numOfDepartmentStore", "facility");
      MakeCurrentChartData("numOfSupermarket", "facility");
      MakeCurrentChartData("numOfTheater", "facility");
      MakeCurrentChartData("numOfAccommodation", "facility");
      MakeCurrentChartData("numOfAirport", "facility");
      MakeCurrentChartData("numOfRailStation", "facility");
      MakeCurrentChartData("numOfBusTerminal", "facility");
      MakeCurrentChartData("numOfSubway", "facility");
      MakeCurrentChartData("numOfBusStop", "facility");
      if(MyZoom < 15) {
        setSexFloatingPop([]);
        MakeCurrentChartData("male_flpop", "sexpopulation");
        MakeCurrentChartData("female_flpop", "sexpopulation");
        setAgeFloatingPop([]);
        MakeCurrentChartData("age_10_flpop", "agepopulation");
        MakeCurrentChartData("age_20_flpop", "agepopulation");
        MakeCurrentChartData("age_30_flpop", "agepopulation");
        MakeCurrentChartData("age_40_flpop", "agepopulation");
        MakeCurrentChartData("age_50_flpop", "agepopulation");
        MakeCurrentChartData("age_60_flpop", "agepopulation");
        setTimeFloatingPop([]);
        MakeCurrentChartData("time_1_flpop", "timepopulation");
        MakeCurrentChartData("time_2_flpop", "timepopulation");
        MakeCurrentChartData("time_3_flpop", "timepopulation");
        MakeCurrentChartData("time_4_flpop", "timepopulation");
        MakeCurrentChartData("time_5_flpop", "timepopulation");
        MakeCurrentChartData("time_6_flpop", "timepopulation");
        setWeekFloatingPop([]);
        MakeCurrentChartData("mon_flpop", "weekpopulation");
        MakeCurrentChartData("tue_flpop", "weekpopulation");
        MakeCurrentChartData("wed_flpop", "weekpopulation");
        MakeCurrentChartData("thu_flpop", "weekpopulation");
        MakeCurrentChartData("fri_flpop", "weekpopulation");
        MakeCurrentChartData("sat_flpop", "weekpopulation");
        MakeCurrentChartData("sun_flpop", "weekpopulation");
        setRentalFee([]);
        MakeChartData("rentalfee_total", "fee");
        setAvgPeriod();
        MakeCurrentChartData("avg_period", "avgperiod");
      }
    }
  }, [DrawerTitle, MarketSelection])

  return(
    <div>
              <nav className='Navbar' style={{position: "static"}}>
          <div className='menu-icon' onClick={handleClick}>
              <i className={clicked ? 'fas fa-times' : 'fas fa-bars'}></i>
          </div>
          <ul className={clicked ? 'nav-menu active' : 'nav-menu'}>
              {MenuItems.map((item, index)=>{
                return (
                  <li key={index}>
                      <a className={item.cName} href={item.url}>
                          {item.title}
                      </a>
                  </li>
                )
              })}
          </ul>
        </nav>
      <div
        className='sidebar'
        style={{
          position: 'absolute',
          top: 10,
          left: 50,
          zIndex: 10000
        }}>
        <Sidebar/>
      </div>
      <MapContainer
        center={[37.541, 126.986]}
        zoom={12}
        scrollWheelZoom={true}
        style={{ width: "100%", height: "calc(100vh - 80px)", position: "static"}}>
        <TileLayer
            zIndex={0}
            url="http://{s}.tile.osm.org/{z}/{x}/{y}.png"
            attribution='&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'/>
        <RenderingGeoJSON/>
      </MapContainer>

      <Drawer placement='right' open={isDrawerOpen} onClose={() => setDrawerOpen(false)}>
        <Drawer.Header>
          <Drawer.Title>{DrawerTitle}</Drawer.Title>
          <Drawer.Actions>
            <Button onClick={() => setDrawerOpen(false)}>Cancel</Button>
            <Button style={{backgroundColor: "green"}} onClick={() => setDrawerOpen(false)} appearance="primary">Confirm</Button>
          </Drawer.Actions>
        </Drawer.Header>
        <Drawer.Body>
          <div>
            <Select options={SelectOption} defaultValue={SelectOption[0]} onChange={(value) => setMarketSelection(value["value"])}></Select>
            <br></br>
          {MarketFuture && MarketFuture[Object.keys(MarketFuture)[0]] && MyZoom < 15 && 
            <div>2022년 {DrawerTitle}의 개업 매장 추이는 <b style={{color: "green"}}>{MarketFuture[Object.keys(MarketFuture)[0]][3]["commerceMetrics"]}</b>입니다.</div>
          }
              <ReactApexChart options={ApexChartLineOption} series={CountMarketNum} type="line" height={300}  />
              <div>{DrawerTitle}의 거주 구성원</div>
              <ReactApexChart options={ApexChartBarOption} series={ResidentNum} type="bar" height={300}  />
              <div>{DrawerTitle}의 직장 구성원</div>
              <ReactApexChart options={ApexChartBarOption} series={WorkNum} type="bar" height={300}  />
              <div>2022년 상권 내 소득 및 지출</div>
              <ReactApexChart options={ApexChartLineOption} series={WorkEarned} type="line" height={300}  />
              <div>{DrawerTitle}의 집객시설 개수</div>
              <ReactApexChart options={ApexChartBarOption} series={FacilityNum} type="bar" height={300}  />
              {MyZoom < 15 ? <>
                <div>{DrawerTitle}의 성별 유동 인구</div>
                <ReactApexChart options={ApexChartBarOption} series={SexFloatingPop} type="bar" height={300}  />
                <div>{DrawerTitle}의 연령층별 유동 인구</div>
                <ReactApexChart options={ApexChartBarOption} series={AgeFloatingPop} type="bar" height={300}  />
                <div>{DrawerTitle}의 시간대별 유동 인구</div>
                <ReactApexChart options={ApexChartBarOption} series={TimeFloatingPop} type="bar" height={300}  />
                <div>{DrawerTitle}의 요일별 유동 인구</div>
                <ReactApexChart options={ApexChartBarOption} series={WeekFloatingPop} type="bar" height={300}  />
                <div>{DrawerTitle}의 분기별 임대료</div>
                <ReactApexChart options={ApexChartLineOption} series={RentalFee} type="line" height={300}  />
                <div>
                  {DrawerTitle}의 평균 영업시간은 {AvgPeriod}시간 입니다.
                </div></> : null
              }
          </div>
        </Drawer.Body>
      </Drawer>
    </div>
  );
}
export default Analysis;