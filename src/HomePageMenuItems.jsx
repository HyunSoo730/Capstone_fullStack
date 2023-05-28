export const MenuItems = [
    {
        title: 'Youtube',
        url: 'youtube',
        cName: 'nav-links'
    },
    {
        title: 'Analysis',
        url: 'analysis',
        cName: 'nav-links'
    },
    {
        title: 'Sign in',
        url: `https://kauth.kakao.com/oauth/authorize?client_id=${process.env.REACT_APP_REST_API_KEY}&redirect_uri=http://localhost:3000/auth/kakao/callback&response_type=code`,
        cName: 'nav-links'
    },
    {
        title: 'My page',
        url: 'mypage',
        cName: 'nav-links'
    }
]