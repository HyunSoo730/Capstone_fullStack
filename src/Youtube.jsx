import {React, useEffect, useState} from "react"
import axios from "axios";
import { locationData } from "./LocationDataItems";

const style = {
  backgroundColor : 'white',
  border: '1px solid black',
}

function Youtube(props){
  const [YoutubeItem, setYoutubeItem] = useState([]);
  const [YoutubePlace, setYoutubePlace] = useState({});
  const API_KEY = process.env.REACT_APP_YOUTUBE_API_KEY;

  const CheckYoutubeItem = (items) => {
    for (let i = 0; i < items.length; i++){
      if (YoutubeItem.length == 0){
        setYoutubeItem([items[i]]);
        CountYoutubePlace(items[i]);
      }
      else{
        for (let j = 0; j < YoutubeItem.length; j++){
          if (items[i].snippet.title != YoutubeItem[j].snippet.title){
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
      if (items.snippet.title.includes(locationData[i])){
        if (YoutubePlace[locationData[i]] === undefined){
          setYoutubePlace({...YoutubePlace, [locationData[i]]: 1});
        }
        else {
          YoutubePlace[locationData[i]]++;
        }
        break;
      }
    }
  }

  useEffect(() => {
    axios
      .get(
        "https://www.googleapis.com/youtube/v3/videos?part=snippet&chart=mostPopular&maxResults=5&regionCode=KR&key=" + API_KEY
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
        console.log(res.data.items);
      })
      .catch(() => {console.log("Youtube API 핫플 Error")});
  }, [YoutubePlace]);

  return(
      <div style={style}>
        <div>
          Youtube HotPlace
            {Object.keys(YoutubePlace).map((item, index)=>{
              console.log(YoutubePlace)
              return(
                <li key={item}>
                    <a>
                      {item}
                    </a>
                </li>
              )
            })}
        </div>
      </div>
  );
}
export default Youtube;