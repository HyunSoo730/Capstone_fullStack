export const MenuItems = [
    {
        title: 'Home',
        url: '',
        cName: 'nav-links'
    },
    {
        title: 'About',
        url: 'about',
        cName: 'nav-links'
    },
    {
        title: 'Sign in',
        url: `https://kauth.kakao.com/oauth/authorize?client_id=${process.env.REACT_APP_REST_API_KEY}&redirect_uri=${process.env.REACT_APP_REDIRECT_URI}&response_type=code`,
        cName: 'nav-links'
    },
    {
        title: 'Youtube',
        url: 'youtube',
        cName: 'nav-links'
    }
]