import React from 'react';

// common pages
const GenericLoginPage = React.lazy(() => import('pages/GenericPages/GenericLoginPage'));
const LostPassPage = React.lazy(() => import('pages/CommonPages/LostPassPage'));
const PeopleRegisterPage = React.lazy(() => import('pages/CommonPages/PeopleRegisterPage/PeopleRegisterPage.js'));


//clients pages
const DemoPeopleDetailsPage = React.lazy(() => import('pages/GenericPages/PeopleDetailsPage/PeopleDetailsPage.js'));
const DemoHomeDemoDesktopPage = React.lazy(() => import('pages/GenericPages/GenericHomePage/GenericDesktopHomePage.js'));
const DemoHomeDemoMobilePage = React.lazy(() => import('pages/GenericPages/GenericHomePage/GenericMobileHomePage.js'));
const DemoGenericHomePage = React.lazy(() => import('pages/GenericPages/GenericHomePage/GenericHomePage.js'));
const DemoAdminHomePage = React.lazy(() => import('pages/GenericPages/GenericManagementPage/GenericManagementPage.js'));


//routes  
const demoRoutes = [
	{ path: '/helloworld', exact: true, name: 'Demo', component: DemoGenericHomePage },
	{ path: '/helloworld/desktop', exact: true, name: 'Demo desktop', component: DemoHomeDemoDesktopPage },
	{ path: '/helloworld/mobile', exact: true, name: 'Demo mobile', component: DemoHomeDemoMobilePage },
	{ path: '/helloworld/profile/:accountId', exact: true, name: 'User profile', component: DemoPeopleDetailsPage },
	{ path: '/helloworld/admin', exact: true, name: 'Admin', component: DemoAdminHomePage },
	
	{ path: '/helloworld/lostPass', exact: true, name: 'Lost pass', component: LostPassPage },
	{ path: '/helloworld/userProfile/:accountId', exact: true, name: 'Profile', component: DemoPeopleDetailsPage},
	
	{ path: '/login', exact: true, name: 'Authentication', component: GenericLoginPage},
	{ path: '/register/:source', exact: true, name: 'Register', component: PeopleRegisterPage },
];

export default demoRoutes;




