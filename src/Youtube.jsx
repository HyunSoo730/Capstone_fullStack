import {React, useEffect, useState} from "react"
import axios from "axios";
import { locationData } from "./LocationDataItems";
import ReactApexChart from "react-apexcharts";
import { MapContainer, TileLayer, GeoJSON, Marker, Popup } from 'react-leaflet'
import geoData from './LocationData.json'
import { Drawer, Button, Placeholder } from 'rsuite';

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

  const [YoutubeItem, setYoutubeItem] = useState([]);
  const [YoutubePlace, setYoutubePlace] = useState([{name: "성수동", data: [1]}, {name: "삼성동", data: [3]}, {name: "연남동", data: [10]}]);
  const API_KEY = process.env.REACT_APP_YOUTUBE_API_KEY;

  const [isDrawerOpen, setDrawerOpen] = useState(false);
  const [DrawerTitle, setDrawerTitle] = useState("DRAWER_TITLE_ERROR");

  const CheckYoutubeItem = (items) => {
    for (let i = 0; i < items.length; i++){
      if (YoutubeItem.length === 0){
        setYoutubeItem([items[i]]);
        CountYoutubePlace(items[i]);
      }
      else{
        for (let j = 0; j < YoutubeItem.length; j++){
          if (items[i].snippet.title !== YoutubeItem[j].snippet.title){
            setYoutubeItem([...YoutubeItem, items[i]]);
            CountYoutubePlace(items[i]);
          }
          else{
            break;
          }
        }
      }
    }
  }

  const CountYoutubePlace = (items) => {
    for (let i = 0; i < locationData.length; i++){
      if (items.snippet.title.includes(locationData[i])) {
        var place_name_data = YoutubePlace.map((item) => {
          return item.name;
        })
        var target_index = place_name_data.indexOf(locationData[i]);
        if (target_index === -1){
          setYoutubePlace([...YoutubePlace, {name: locationData[i], data: [1]}]);
        }
        else {
          YoutubePlace[target_index].data[0]++;
        }
        console.log(YoutubePlace)
        break;
      }
    }
  }

  const polystyle = (feature) => {
    for (let i = 0; i < YoutubePlace.length; i++){
      if (feature.properties.EMD_NM.includes(YoutubePlace[i].name)){
        //item.feature.properties = {...item.feature.properties, color: rgba(0.1 * YoutubePlace[i].data[0], 0, 0, 0.5)};
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
    axios
      .get(
        //"https://www.googleapis.com/youtube/v3/videos?part=snippet&chart=mostPopular&maxResults=5&regionCode=KR&key=" + API_KEY
      )
      .then((res) => {
        CheckYoutubeItem(res.data.items);
      })
      .catch(() => {console.log("Youtube API mostPopular Error")});
    axios
      .get(
        //"https://www.googleapis.com/youtube/v3/search?part=snippet&q=핫플&maxResults=1&regionCode=KR&key=" + API_KEY
      )
      .then((res) => {
        CheckYoutubeItem(res.data.items);
      })
      .catch(() => {console.log("Youtube API 핫플 Error")});
  }, [YoutubePlace]);

  return(
      <div style={style}>
        <MapContainer
          center={[37.541, 126.986]}
          zoom={13}
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
export default Youtube;