import React from 'react';
import ReactDOM from 'react-dom';
import { Provider } from 'react-redux';
import { combineReducers, createStore } from 'redux';
import App from './App';
import * as serviceWorker from './serviceWorker';

// reducers
import {userAuthenticationReducer} from '_reducers/authenticationReducer.js';
import {currentContainerReducer} from '_reducers/currentContainerReducer.js';
import {peopleDetailsReducer} from '_reducers/peopleDetailsReducer.js';
import {searchMembersReducer} from '_reducers/searchMembersReducer.js';
import {searchBusinessRulesReducer} from '_reducers/searchBusinessRulesReducer.js';
import {enumerationsReducer} from '_reducers/enumerationsReducer.js';
import {headerSearchReducer} from '_reducers/headerSearchReducer.js';
import {applicationReducer} from '_reducers/applicationReducer.js';
import {imagesCachesReducer} from '_reducers/imagesCachesReducer.js';
/*--------------------------------------------------------------------------------------------
* REDUX STORE
*--------------------------------------------------------------------------------------------*/
/**
 * All reducers combined.
 * Be aware: key of reducers are crucial (Ex: 'currentUser', 'currentContainers'), it can no be renamed because
 * mapStateToProps access data inside store through this key.
 * 
 */
const allReducers = combineReducers({
  currentUser: userAuthenticationReducer,
  currentContainers: currentContainerReducer,
  peopleDetails: peopleDetailsReducer,
  searchMembers: searchMembersReducer,
  searchBusinessRules: searchBusinessRulesReducer,
  enumerations: enumerationsReducer,
  headerSearchComp: headerSearchReducer,
  applicationConfig: applicationReducer,
  imagesCaches: imagesCachesReducer,
});

export const store = createStore(allReducers);

/*--------------------------------------------------------------------------------------------
* ReactDOM RENDER APP
*--------------------------------------------------------------------------------------------*/
// make the store available to all container components in the application without passing it explicitly. 
// We only need to use it once when rendering the root component, like bellow.
ReactDOM.render(
    <Provider store={store}>
        <App />
    </Provider>, document.getElementById('root'));

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: http://bit.ly/CRA-PWA
serviceWorker.unregister();


