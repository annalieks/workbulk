import {all, call, put, takeEvery} from 'redux-saga/effects';
import {toastr} from 'react-redux-toastr';
import {createBoardRoutine, createWorkgroupRoutine, fetchBoardsRoutine, fetchWorkgroupsRoutine} from './routines';
import * as service from './service';

function* fetchWorkgroups() {
    try {
        const response = yield call(service.fetchWorkgroups);
        yield put(fetchWorkgroupsRoutine.success(response));
    } catch (error) {
        yield put(fetchWorkgroupsRoutine.failure(error.message));
        toastr.error('Data error', 'Could not get the workgroups list');
    }
}

function* fetchBoards() {
    try {
        const response = yield call(service.fetchBoards);
        yield put(fetchBoardsRoutine.success(response));
    } catch (error) {
        yield put(fetchBoardsRoutine.failure(error.message));
        toastr.error('Data error', 'Could not get the boards list');
    }
}

function* createWorkgroup({payload}) {
    try {
        yield call(() => service.createWorkgroup(payload.name, payload.description));
        yield put(fetchWorkgroupsRoutine.trigger());
    } catch (error) {
        yield put(createWorkgroupRoutine.failure(error.message));
        toastr.error('Data error', 'Could not create workgroup');
    }
}

function* createBoard({payload}) {
    try {
        yield call(() => service.createBoard(payload.name, payload.description));
        yield put(fetchBoardsRoutine.trigger());
    } catch (error) {
        yield put(createBoardRoutine.failure(error.message));
        toastr.error('Data error', 'Could not create board');
    }
}

function* watchFetchWorkgroups() {
    yield takeEvery(fetchWorkgroupsRoutine.TRIGGER, fetchWorkgroups);
}

function* watchFetchBoards() {
    yield takeEvery(fetchBoardsRoutine.TRIGGER, fetchBoards);
}

function* watchCreateWorkgroup() {
    yield takeEvery(createWorkgroupRoutine.TRIGGER, createWorkgroup);
}

function* watchCreateBoard() {
    yield takeEvery(createBoardRoutine.TRIGGER, createBoard);
}

export default function* homeSagas() {
    yield all([
        watchFetchWorkgroups(),
        watchFetchBoards(),
        watchCreateWorkgroup(),
        watchCreateBoard()
    ]);
}
