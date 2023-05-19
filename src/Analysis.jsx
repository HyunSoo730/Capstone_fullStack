import { React, useState, useEffect } from 'react';
import ReactApexChart from "react-apexcharts";
import { MapContainer, TileLayer, GeoJSON, useMapEvents} from 'react-leaflet';
import { Drawer, Button } from 'rsuite';
import geoData from './LocationData.json';
import geoDetailData from './LocationDetailData.json';
import {GU_locationData} from './LocationDataGUItems';
import {locationData} from './LocationDataItems';
import Sidebar from './Jaehyeok_Lee/Sidebar';

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

  const findDong = (val) => {
    for(let i = 0; i < locationData.length; i++) {
      if (locationData[i].includes(val.slice(0,2))) 
        return locationData[i];
    }
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
            serviceName: '커피-음료'
          }),
        })  
        .then(response => { 
          return response.json();
        });
      }
      else {
        analysis_data = fetch(`/api/single_commerce/industry?commercialCode=${DrawerDetailCode}&serviceName=커피-음료`, {
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
        var year_data = response.map((item)=>{
          return item[targetValue];
        });
        setCountMarketNum(current => [...current, {name: targetValue, data: year_data.slice(0, 4)}]);
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
      var year_data = response.map((item)=>{
        return item[targetValue];
      });
      setWorkEarned(current => [...current, {name: targetValue, data: year_data.slice(0, 4)}]);
      }); 
    }
    else if(data_type === "fee"){
      let analysis_data = null;
      analysis_data = MakeAnalysisDetailData(`/api/local-commerce/total/rentalfee/${GU_locationData[DrawerGU.slice(0, 5)]}/${findDong(DrawerTitle)}`);
      analysis_data.then(response => {
        var year_data = response.map((item)=>{
          return item[targetValue];
        });
      setRentalFee(current => [...current, {name: targetValue, data: year_data.slice(0, 4)}]);
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
        setResidentNum(current => [...current, {name: targetValue, data: [current_data]}]);
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
        setWorkNum(current => [...current, {name: targetValue, data: [current_data]}]);
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
        setFacilityNum(current => [...current, {name: targetValue, data: [current_data]}]);
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
        setTimeFloatingPop(current => [...current, {name: targetValue, data: [current_data]}]);
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
      setSexFloatingPop(current => [...current, {name: targetValue, data: [current_data]}]);
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
      setAgeFloatingPop(current => [...current, {name: targetValue, data: [current_data]}]);
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
      setWeekFloatingPop(current => [...current, {name: targetValue, data: [current_data]}]);
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
    if (mode === "normal"){
      setDrawerTitle(feature.properties.EMD_NM);
      setDrawerGU(feature.properties.EMD_CD);
    }
    else {
      setDrawerTitle(feature.properties.TRDAR_NM);
      setDrawerDetailCode(feature.properties.TRDAR_NO);
    }
    setDrawerOpen(true);
  }
  
  const onEachFeature = (feature, layer) => {
    if(feature.properties){
      layer.bindPopup(feature.properties.EMD_NM);
    }
    layer.on({
      click: (e) => {whenClicked(e, feature, "normal")}
    });
  }

  const onEachDetailFeature = (feature, layer) => {
    if(feature.properties){
      layer.bindPopup(feature.properties.TRDAR_NM);
    }
    layer.on({
      click: (e) => {whenClicked(e, feature, "detail")}
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
  }, [DrawerTitle])

  return(
    <div >
      <div
        calssName='sidebar'
        style={{
          position: 'absolute',
          top: 0,
          left: 0,
          zIndex: 10000
        }}>
        <Sidebar/>
      </div>
      <MapContainer
        center={[37.541, 126.986]}
        zoom={12}
        scrollWheelZoom={true}
        style={{ width: "100%", height: "calc(100vh - 0rem)", position: "absolute", top: 0, left: 0}}>
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
            <Button onClick={() => setDrawerOpen(false)} appearance="primary">Confirm</Button>
          </Drawer.Actions>
        </Drawer.Header>
        <Drawer.Body>
          <div>
          {MarketFuture && MyZoom > 15 && MarketFuture.map((item)=>{return (
            <div>2022년 {item[3]["dong"]}의 개업 매장 추이는 {item[3]["commerceMetrics"]}입니다.</div>
          )})}
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