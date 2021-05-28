import {combineReducers} from "redux";
import {reducer as toastr} from 'react-redux-toastr';
import auth from './scenes/AuthPage/reducer';
import home from './scenes/HomePage/reducer';
import board from './scenes/BoardPage/reducer';
import workgroup from './scenes/WorkgroupPage/reducer';

export default combineReducers({
    toastr,
    auth,
    home,
    board,
    workgroup
});
