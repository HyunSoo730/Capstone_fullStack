import {React, useState} from "react"
import axios from "axios";
import { Button } from "react-bootstrap";
import { locationData } from "./LocationDataItems";
import useDidMountEffect from "./UseDidMountEffect";
import "./YoutubeVideoStyle.css";
//서울 핫플 끝
//서울 카페 끝
//서울 놀거리 끝
//서울 맛집 끝
//서울 여행 끝
//서울 인싸
const style = {
    backgroundColor : 'white',
    border: '1px solid black',
}
function Crawling(props) {
  const [NextPageToken, setNextPageToken] = useState(null);
  const API_KEY = process.env.REACT_APP_YOUTUBE_API_KEY;
  
  //api/find-entity/삼성동
  //api/return
  //api/save?dong=삼성동&name=승진이는_웃는게_예쁘다
  /** 
   * Youtube 데이터를 YoutubeItem 변수에 넣고 api/save?dong={동 이름}&name={영상 제목}을 통해 서버에 저장
   * @param {JSON} items Youtube 데이터
   * @returns response 결과
  **/
  const CheckYoutubeItem = (result) => {
    const items = result.items;
    for (let i = 0; i < items.length; i++) {
      for(let j = 0; j < locationData.length; j++) {
        if (items[i].snippet.title.includes(locationData[j].slice(0,2))) {
          axios
          .get(`https://www.googleapis.com/youtube/v3/videos?part=snippet,statistics&id=${items[i].id.videoId}&maxResults=1&key=`+API_KEY)
          .then((res) => {
            try{
              const viewCount = res.data.items[0].statistics.viewCount;
              const likeCount = res.data.items[0].statistics.likeCount;
              fetch("/api/youtube/save", {
                method: "POST",
                headers: {
                "Content-Type": "application/json",
                },
                body: JSON.stringify({
                  dong: locationData[j],
                  name: items[i].snippet.title,
                  views: viewCount,
                  videoLink: items[i].id.videoId,
                  likes: likeCount,
                  date: items[i].snippet.publishTime,
                  thumbnail: items[i].snippet.thumbnails.default.url
                }),
              })
              .then(response => {
                  console.log("Youtube_DATA ADDED\n" + response.json());
              })
              .catch(console.log("YoutubeData Save Error"));
            }
            catch{
              fetch("/api/youtube/save", {
                method: "POST",
                headers: {
                "Content-Type": "application/json",
                },
                body: JSON.stringify({
                  dong: locationData[j],
                  name: items[i].snippet.title,
                  views: 1,
                  videoLink: items[i].id.videoId,
                  likes: 1,
                  date: items[i].snippet.publishTime,
                  thumbnail: items[i].snippet.thumbnails.default.url
                }),
              })
              .then(response => {
                  console.log("Youtube_DATA ADDED\n" + response.json());
              })
              .catch(console.log("YoutubeData Save Error"));
            }

          });
          break;
        }
      }
    }
    setNextPageToken(result.nextPageToken);
  }

  const whenClicked = (e) => {
    axios
      .get(
        `https://www.googleapis.com/youtube/v3/search?part=snippet&publishedAfter=2022-01-01T00:00:00Z&q=서울 인싸&pageToken=${NextPageToken}&maxResults=1000&regionCode=KR&key=` + API_KEY
      )
      .then((res) => {
        CheckYoutubeItem(res.data);
      })
      .catch(() => {console.log("Youtube API Name Error")});
  }
  
  useDidMountEffect(() => {
    axios
      .get(
        "https://www.googleapis.com/youtube/v3/search?part=snippet&publishedAfter=2022-01-01T00:00:00Z&q=서울 인싸&maxResults=100&regionCode=KR&key=" + API_KEY
        )
      .then((res) => {
        CheckYoutubeItem(res.data);
      })
      .catch(() => {console.log("Youtube API Name Error")});
  }, []);

  return(
    <div style={style}>
      <Button onClick={whenClicked}>다음 페이지</Button>
    </div>
  );
}
export default Crawling;