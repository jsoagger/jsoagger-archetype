import React from 'react';

// common pages
const GenericLoginPage = React.lazy(() => import('pages/GenericPages/GenericLoginPage'));
const LostPassPage = React.lazy(() => import('pages/CommonPages/LostPassPage'));
const PeopleRegisterPage = React.lazy(() => import('pages/CommonPages/PeopleRegisterPage/PeopleRegisterPage.js'));
const backendRootContext = process.env.REACT_APP_CONTEXT_ROOT;

//clients pages
const DemoPeopleDetailsPage = React.lazy(() => import('pages/GenericPages/PeopleDetailsPage/PeopleDetailsPage.js'));
const DemoHomeDemoDesktopPage = React.lazy(() => import('pages/GenericPages/GenericHomePage/GenericDesktopHomePage.js'));
const DemoHomeDemoMobilePage = React.lazy(() => import('pages/GenericPages/GenericHomePage/GenericMobileHomePage.js'));
const DemoGenericHomePage = React.lazy(() => import('pages/GenericPages/GenericHomePage/GenericHomePage.js'));
const DemoAdminHomePage = React.lazy(() => import('pages/GenericPages/GenericManagementPage/GenericManagementPage.js'));


//routes  
const demoRoutes = [
	{ path: `/${backendRootContext}`, exact: true, name: 'Demo', component: DemoGenericHomePage },
	{ path: `/${backendRootContext}/desktop`, exact: true, name: 'Demo desktop', component: DemoHomeDemoDesktopPage },
	{ path: `/${backendRootContext}/mobile`, exact: true, name: 'Demo mobile', component: DemoHomeDemoMobilePage },
	{ path: `/${backendRootContext}/profile/:accountId`, exact: true, name: 'User profile', component: DemoPeopleDetailsPage },
	{ path: `/${backendRootContext}/admin`, exact: true, name: 'Admin', component: DemoAdminHomePage },
	
	{ path: `/${backendRootContext}/lostPass`, exact: true, name: 'Lost pass', component: LostPassPage },
	{ path: `/${backendRootContext}/userProfile/:accountId`, exact: true, name: 'Profile', component: DemoPeopleDetailsPage},
	
	{ path: `/login`, exact: true, name: 'Authentication', component: GenericLoginPage},
	{ path: `/register/:source`, exact: true, name: 'Register', component: PeopleRegisterPage },
];

export default demoRoutes;




