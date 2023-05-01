import {React, useEffect, useState} from "react"
import axios from "axios";
import { locationData } from "./LocationDataItems";

const style = {
    backgroundColor : 'white',
    border: '1px solid black',
  }

function Crawling(props) {
    const [YoutubeItem, setYoutubeItem] = useState([]);
    const [NextToken, setNextToken] = useState(null);
    const API_KEY = process.env.REACT_APP_YOUTUBE_API_KEY;
  
    //api/find-entity/삼성동
    //api/return
    //api/save?dong=삼성동&name=승진이는_웃는게_예쁘다
    /** 
     * Youtube 데이터를 YoutubeItem 변수에 넣고 api/save?dong={동 이름}&name={영상 제목}을 통해 서버에 저장
     * @param {JSON} items Youtube 데이터
     * @returns response 결과
    **/
    const CheckYoutubeItem = (items) => {
        console.log(items);
        for (let i = 0; i < items.length; i++) {
            if (YoutubeItem.length === 0){
                setYoutubeItem([items[i]]);
                SaveYoutubePlace(items[i]);
            }
            else {
                for (let j = 0; j < YoutubeItem.length; j++) {
                    if (items[i].snippet.title !== YoutubeItem[j].snippet.title) {
                        setYoutubeItem([...YoutubeItem, items[i]]);
                        SaveYoutubePlace(items[i]);
                    }
                    else {
                        break;
                    }
                }
            }
        }
    }
    const SaveYoutubePlace = (items) => {
      for (let i = 0; i < locationData.length; i++){
        if (items.snippet.title.includes(locationData[i])) {
            fetch(`/api/save?dong=${locationData[i]}&name=${items.snippet.title}`)
            .then(response => {
                console.log("NEW_DATA"+response);
            })
            .catch(console.log("Youtube Data Save Error"));
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
          //"https://www.googleapis.com/youtube/v3/search?part=snippet&q=핫플&maxResults=5&regionCode=KR&key=" + API_KEY
        )
        .then((res) => {
            console.log(res.data.items);
          CheckYoutubeItem(res.data.items);
        })
        .catch(() => {console.log("Youtube API 핫플 Error")});
    }, []);

    return(
        <div style={style}>
            <h1>HWLLO</h1>
        </div>
    );
}
export default Crawling;