import React from 'react';

// common pages
const Page404 = React.lazy(() => import('pages/CommonPages/Page404'));
const Page500 = React.lazy(() => import('pages/CommonPages/Page500'));

const commonRoutes = [
	{ path: '/404', exact: true, name: 'Not found', component: Page404},
	{ path: '/500', exact: true, name: 'Internal server error', component: Page500},
];

export default commonRoutes;




