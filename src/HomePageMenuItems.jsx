export const MenuItems = [
    {
        title: '유튜브 트렌드',
        url: 'youtube',
        cName: 'nav-links'
    },
    {
        title: '상권 분석',
        url: 'analysis',
        cName: 'nav-links'
    },
    {
        title: '로그인',
        url: `https://kauth.kakao.com/oauth/authorize?client_id=${process.env.REACT_APP_REST_API_KEY}&redirect_uri=http://3.39.41.194:3000/auth/kakao/callback&response_type=code`,
        cName: 'nav-links'
    },
    {
        title: '내 정보',
        url: 'mypage',
        cName: 'nav-links'
    }
]