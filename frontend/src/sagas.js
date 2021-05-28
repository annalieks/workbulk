import {all} from 'redux-saga/effects';
import authSagas from './scenes/AuthPage/sagas';
import homeSagas from './scenes/HomePage/sagas';
import boardSagas from './scenes/BoardPage/sagas';
import workgroupSagas from './scenes/WorkgroupPage/sagas';

export default function* rootSaga() {
    yield all([
        authSagas(),
        homeSagas(),
        boardSagas(),
        workgroupSagas()
    ]);
}
