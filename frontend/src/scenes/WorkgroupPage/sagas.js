import {all, call, put, takeEvery} from 'redux-saga/effects';
import {toastr} from 'react-redux-toastr';
import {fetchWorkgroupRoutine} from './routines';
import * as service from './service';

function* fetchWorkgroupInfo({payload}) {
    try {
        const response = yield call(() => service.fetchWorkgroupInfo(payload));
        yield put(fetchWorkgroupRoutine.success(response));
    } catch (error) {
        yield put(fetchWorkgroupRoutine.failure(error.message));
        toastr.error('Data error', 'Could not fetch board info');
    }
}

function* watchFetchWorkgroup() {
    yield takeEvery(fetchWorkgroupRoutine.TRIGGER, fetchWorkgroupInfo);
}

export default function* workgroupSagas() {
    yield all([
        watchFetchWorkgroup()
    ]);
}
