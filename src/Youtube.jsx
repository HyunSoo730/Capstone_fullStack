import {React, useEffect, useState} from "react"
import axios from "axios";
import { locationData } from "./LocationDataItems";
import ReactApexChart from "react-apexcharts";
import { MapContainer, TileLayer, GeoJSON } from 'react-leaflet'
import geoData from './LocationData.json'
import { Drawer, Button } from 'rsuite';

import 'leaflet/dist/leaflet.css';
import "rsuite/dist/rsuite.css";

const style = {
  backgroundColor : 'white',
  border: '1px solid black',
}

const ApexChartOption = {
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
    categories: ["누적 수치"],
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

function Youtube(props){
  const [YoutubePlace, setYoutubePlace] = useState([]);

  const [isDrawerOpen, setDrawerOpen] = useState(false);
  const [DrawerTitle, setDrawerTitle] = useState("DRAWER_TITLE_ERROR");

  const CountYoutubePlace = () => {
    fetch("api/return", {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
    })  
    .then(response => {
      return response.json();
    })
    .then(response => {
      console.log(response)
      response.map((item)=>{
        setYoutubePlace(current => [...current, {name: item["dong"], data: [item["count"]]}]);
      });
    });
    console.log(YoutubePlace);
  }

  const polystyle = (feature) => {
    for (let i = 0; i < YoutubePlace.length; i++){
      if (feature.properties.EMD_NM.includes(YoutubePlace[i].name)){
        return {
          fillColor: 'rgba(' + 5 * YoutubePlace[i].data[0] + '0, 0, 0.5)',
          weight: 2,
          opacity: 1,
          color: 'white',  //Outline color
          fillOpacity: 0.7
        };
      }
    }
    return {
      fillColor: 'rgba(10, 0, 0, 0.5)',
      weight: 2,
      opacity: 1,
      color: 'white',  //Outline color
      fillOpacity: 0.7
    };
  }

  function whenClicked(e, feature) {
    setDrawerTitle(feature.properties.EMD_NM);
    setDrawerOpen(true);
  }

  const onEachFeature = (feature, layer) => {
    if(feature.properties){
      layer.bindPopup(feature.properties.EMD_NM);
    }
    layer.on({
      click: (e) => {whenClicked(e, feature)}
    });
  }

  useEffect(() => {
    CountYoutubePlace();
  }, [])

  if (YoutubePlace){
  return(
      <div style={style}>
        <MapContainer
          center={[37.541, 126.986]}
          zoom={12}
          scrollWheelZoom={true}
          style={{ width: "100%", height: "calc(100vh - 0rem)" }}>
          <TileLayer
            url="http://{s}.tile.osm.org/{z}/{x}/{y}.png"
            attribution='&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
          />
          <GeoJSON data={geoData} style={polystyle} onEachFeature={onEachFeature}/>
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
              <ReactApexChart options={ApexChartOption} series={YoutubePlace} type="bar" height={625}  />
            </div>
          </Drawer.Body>
        </Drawer>
      </div>
    );
  }
  else{
    <div>EMPTY</div>
  };
}
export default Youtube;