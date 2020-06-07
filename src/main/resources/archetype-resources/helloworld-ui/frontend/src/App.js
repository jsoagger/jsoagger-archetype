import React, { Component } from 'react';
import { HashRouter, Route, Switch, Redirect } from 'react-router-dom';
import { createBrowserHistory } from 'history'
import { loginService } from '_services/login.services.js';
import {commons} from '_helpers/commons';

import 'react-toastify/dist/ReactToastify.css';
import './App.scss';

// Core Containers
const AdminLayout = React.lazy(() => import('./containers/Core/AdminLayout/AdminLayout'));
const AnonLayout = React.lazy(() => import('./containers/Core/AnonLayout/AnonLayout'));
const GenericHomeLayout = React.lazy(() => import('./containers/Core/GenericHomeLayout/GenericHomeLayout'));
const LoginLayout = React.lazy(() => import('./containers/Core/LoginLayout'));

// history
export const browserHistory = createBrowserHistory();
const backendUrl = 'home';
const loading = () => <div className="animated fadeIn pt-3 text-center">Loading...</div>;

/**
 * This is the first component displayed in the application.
 * 
 * Following pages are public (not auth required): login, register, lostpass,404,500.
 * 
 * Other pages are private
 */
class App extends Component {

	constructor(props) {
        super(props);
        this.state = {
            isAuthenticating: true,
        }
        
        this.handleLoginSuccess = this.handleLoginSuccess.bind(this);
        this.handleResponseError = this.handleResponseError.bind(this);
    }        
	componentDidMount(){
  		if(commons.jsoagger_core_isUser() || commons.jsoagger_core_isAnonUser()){
  			this.setState({
  				isAuthenticating: false,
  			});
  		}
  		else {
  			this.setState({
  				isAuthenticating: true,
  			});
  			
  			localStorage.clear();
	        loginService.loginAsAnon()
	        .then(response => {
	            if(response.status !== 200){
	            	this.handleResponseError(response)
	            }
	            else {
	            	var json = response.json();
	                return json;
	            }
	        })
	        .then(json => {
	        	this.handleLoginSuccess(json);
	        })
	        .catch(error => {
	            this.handleResponseError(error);
	        });
  		}
  	}
  	
  	handleLoginSuccess(json){
    	commons.jsoagger_core_loginSuccess(json);
    	this.setState({
  			isAuthenticating: false
  		})	   	
  	}
  	
  	handleResponseError(error){
  		commons.jsoagger_core_loginError(error);
  		window.location.href = '/#/500';  		
  	}

  render() {
  	if(this.state.isAuthenticating === true){
  		return loading();
  	}

    return (
      <HashRouter history={browserHistory}>
          <React.Suspense fallback={loading()}>
            <Switch>
            	<Route exact path="/" render={props => (
                         <Redirect to={{ pathname: '/home', state: { from: props.location } }} />
                    )}
            	/>
            	<Route exact path="/login" name="Login" render={props => <LoginLayout {...props}/>} />
            	<Route path={'/admin'} exact name="Admin" component={AdminLayout} />
            	<Route path={'/lostPass'} name="Lost password" component={LoginLayout} />
            	<Route path={'/home'} name="Accueil" component={GenericHomeLayout} />            	
            	<Route path={'/' + backendUrl + '/desktop'} exact name="Demo desktop" component={GenericHomeLayout} />
            	<Route path={'/' + backendUrl + '/mobile'} exact name="Demo mobile" component={GenericHomeLayout} />
            	<Route path={'/' + backendUrl + '/profile/:accountId'} name="Demo profile" component={GenericHomeLayout} />
            	<Route path='/400' name="Not found" component={AnonLayout} />
            	<Route path='/500' name="Error" component={AnonLayout} />
            	<Route path='*' name="Unkown" component={AnonLayout} />
            </Switch>
          </React.Suspense>
      </HashRouter>
    );
  }
}

export default App;

