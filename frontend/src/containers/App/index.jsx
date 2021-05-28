import React from 'react';
import Header from '../../components/Header';
import {connect} from 'react-redux';
import {Route, Router, Switch} from 'react-router-dom';
import HomePage from '../../scenes/HomePage';
import LandingPage from '../../scenes/LandingPage';
import {history} from '../../helpers/history.helper';
import 'react-redux-toastr/lib/css/react-redux-toastr.min.css';
import WorkgroupPage from '../../scenes/WorkgroupPage';
import BoardPage from '../../scenes/BoardPage';
import AuthPage from '../../scenes/AuthPage';
import {TOKEN_NAME} from "../../commons/constants";

const App = ({loading}) => (
    <Router history={history}>
        <Header/>
        <Switch>
            <Route exact path="/" component={() => !loading && localStorage.getItem(TOKEN_NAME) ? <HomePage/> : <LandingPage/>}/>
            <Route exact path="/home" component={HomePage}/>
            <Route exact path="/wg/:id" component={WorkgroupPage}/>
            <Route exact path="/board/:id" component={BoardPage}/>
            <Route exact path="/signup" component={() => <AuthPage isSignUp={true}/>}/>
            <Route exact path="/signin" component={() => <AuthPage isSignUp={false}/>}/>
        </Switch>
    </Router>
);

const mapStateToProps = (state) => ({
    loading: state.auth.loading,
});

export default connect(mapStateToProps)(App);
