import {React, useEffect, useState} from "react"
import axios from "axios";
import { locationData } from "./LocationDataItems";
import ReactApexChart from "react-apexcharts";

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
  const [YoutubePlace, setYoutubePlace] = useState([]);
  const API_KEY = process.env.REACT_APP_YOUTUBE_API_KEY;

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
        "https://www.googleapis.com/youtube/v3/search?part=snippet&q=핫플&maxResults=30&regionCode=KR&key=" + API_KEY
      )
      .then((res) => {
        CheckYoutubeItem(res.data.items);
      })
      .catch(() => {console.log("Youtube API 핫플 Error")});
  }, [YoutubePlace]);

  return(
      <div style={style}>
        <div>
          <ReactApexChart options={ApexChartOption} series={YoutubePlace} type="bar" height={625} />
        </div>
      </div>
  );
}
export default Youtube;