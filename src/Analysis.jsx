import { React, useState, useEffect } from 'react';
import ReactApexChart from "react-apexcharts";
import { MapContainer, TileLayer, GeoJSON, useMapEvents } from 'react-leaflet'
import { Drawer, Button } from 'rsuite';
import geoData from './LocationData.json'
import geoDetailData from './LocationDetailData.json'
import {AnalysisData} from './AnalysisItems'

import 'leaflet/dist/leaflet.css';
import "rsuite/dist/rsuite.css";

function Analysis(props){
    const [CountMarketNum, setCountMarketNum] = useState([]);
    const [ResidentNum, setResidentNum] = useState([]);
    const [WorkNum, setWorkNum] = useState([]);
    const [WorkEarned, setWorkEarned] = useState([]);
    const [FacilityNum, setFacilityNum] = useState([]);

    const [isDrawerOpen, setDrawerOpen] = useState(false);
    const [DrawerTitle, setDrawerTitle] = useState("DRAWER_TITLE_ERROR");

    const [MyZoom, setMyZoom] = useState(12);

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
        },
        tooltip: {
          shared: false,
          intersect: true,
          x: {
            show: false
          }
        },
        legend: {
          horizontalAlign: "left",
          offsetX: 40
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
      }

    const MakeChartData = (targetValue, data_type) => {
      fetch("/api/local-commerce/change?dong=삼성1동", { 
          "Content-Type": "application/json",
      })
        .then(response => {
          console.log(response.json());
        });

        var year_data = AnalysisData.map((item)=>{
            return item[targetValue];
        });
        if (data_type === "market"){
            setCountMarketNum(current => [...current, {name: targetValue, data: year_data}]);
        }
        else if(data_type === "workearned"){
            setWorkEarned(current => [...current, {name: targetValue, data: year_data}])
        }
    }

    const MakeCurrentChartData = (targetValue, data_type) => {
        var CurrentData = AnalysisData[3][targetValue]
        if(data_type === "resident"){
            setResidentNum(current => [...current, {name: targetValue, data: [CurrentData]}]);
        }
        else if(data_type === "work"){
            setWorkNum(current => [...current, {name: targetValue, data: [CurrentData]}]);
        }
        else if(data_type === "facility"){
            setFacilityNum(current => [...current, {name: targetValue, data: [CurrentData]}]);
        }
    }

    function whenClicked(e, feature, mode) {
      if (mode === "normal"){
        setDrawerTitle(feature.properties.EMD_NM);
      }
      else {
        setDrawerTitle(feature.properties.TRDAR_NM);
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
      return <GeoJSON data={MyZoom < 15 ? geoData : geoDetailData} onEachFeature={MyZoom < 15 ? onEachFeature : onEachDetailFeature}/>;
    }

    useEffect(()=>{
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
    }, [DrawerTitle])

    return(
        <div>
            <MapContainer
              center={[37.541, 126.986]}
              zoom={12}
              scrollWheelZoom={true}
              zoomstart={()=>console.log("zoomend")}
              style={{ width: "100%", height: "calc(100vh - 0rem)" }}>
              <TileLayer
                  url="http://{s}.tile.osm.org/{z}/{x}/{y}.png"
                  attribution='&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
              />
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
                <div>2022년 개업 매장 추이는 {AnalysisData[3]['commerceMetrics']}입니다.</div>
                <ReactApexChart options={ApexChartLineOption} series={CountMarketNum} type="line" height={300}  />
                <div>{DrawerTitle}의 거주 구성원</div>
                <ReactApexChart options={ApexChartBarOption} series={ResidentNum} type="bar" height={300}  />
                <div>{DrawerTitle}의 직장 구성원</div>
                <ReactApexChart options={ApexChartBarOption} series={WorkNum} type="bar" height={300}  />
                <div>2022년 직장 인구 소득 추이</div>
                <ReactApexChart options={ApexChartLineOption} series={WorkEarned} type="line" height={300}  />
                <div>{DrawerTitle}의 집객시설 개수</div>
                <ReactApexChart options={ApexChartBarOption} series={FacilityNum} type="bar" height={300}  />
            </div>
          </Drawer.Body>
        </Drawer>
        </div>
    );
}
export default Analysis;