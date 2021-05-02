import React from 'react';
import ReduxToastr from 'react-redux-toastr';
import Header from "../../components/Header";
import {Provider} from 'react-redux';
import {Route, Router, Switch} from 'react-router-dom';
import HomePage from '../../scenes/HomePage';
import LandingPage from '../../scenes/LandingPage';
import {store} from '../../store';
import {history} from '../../helpers/history.helper';
import 'react-redux-toastr/lib/css/react-redux-toastr.min.css';
import WorkgroupPage from "../../scenes/WorkgroupPage";
import AuthPage from "../../scenes/AuthPage";

const App = () => (
    <Provider store={store}>
        <ReduxToastr
            timeOut={2000}
            newestOnTop={false}
            preventDuplicates
            position="top-left"
            transitionIn="fadeIn"
            transitionOut="fadeOut"
            progressBar
            closeOnToastrClick
        />
        <Router history={history}>
            <Header/>
            <Switch>
                <Route exact path="/" component={LandingPage}/>
                <Route exact path="/home" component={HomePage}/>
                <Route exact path="/wg/:id" component={WorkgroupPage}/>
                <Route exact path="/signup" component={() => <AuthPage isSignup={true}/>}/>
                <Route exact path="/signin" component={() => <AuthPage isSignup={false}/>}/>
            </Switch>
        </Router>
    </Provider>
);

export default App;
