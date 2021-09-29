import { all, call, put, takeEvery } from 'redux-saga/effects';
import { toastr } from 'react-redux-toastr';
import { createBoardRoutine, fetchWorkgroupRoutine, deleteBoardRoutine, editBoardRoutine } from './routines';
import * as service from './service';

function* fetchWorkgroupInfo({ payload }) {
    try {
        const response = yield call(() => service.fetchWorkgroupInfo(payload));
        yield put(fetchWorkgroupRoutine.success(response));
    } catch (error) {
        yield put(fetchWorkgroupRoutine.failure(error.message));
        toastr.error('Data error', 'Could not fetch board info');
    }
}

function* createBoard({ payload }) {
    try {
        const response = yield call(() => service.createBoard(payload.id,
            payload.name, payload.description));
        yield put(createBoardRoutine.success(response));
    } catch (error) {
        yield put(createBoardRoutine.failure(error.message));
        toastr.error('Data error', 'Could not fetch board info');
    }
}

function* deleteBoard({ payload }) {
    try {
        console.log('Deleting board')
        yield call(() => service.deleteBoard(payload.workgroupId,
            payload.id));
        yield put(fetchWorkgroupRoutine.trigger(payload.workgroupId));
    } catch (error) {
        yield put(deleteBoardRoutine.failure(error.message));
        toastr.error('Data error', 'Could not delete board');
    }
}

function* editBoard({ payload }) {
    try {
        yield call(() => service.editBoard(payload.workgroupId, payload.id,
            payload.name, payload.description));
        yield put(fetchWorkgroupRoutine.trigger(payload.workgroupId));
    } catch (error) {
        yield put(editBoardRoutine.failure(error.message));
        toastr.error('Data error', 'Could not edit board');
    }
}

function* watchFetchWorkgroup() {
    yield takeEvery(fetchWorkgroupRoutine.TRIGGER, fetchWorkgroupInfo);
}

function* watchCreateBoard() {
    yield takeEvery(createBoardRoutine.TRIGGER, createBoard);
}

function* watchDeleteBoard() {
    yield takeEvery(deleteBoardRoutine.TRIGGER, deleteBoard);
}

function* watchEditBoard() {
    yield takeEvery(editBoardRoutine.TRIGGER, editBoard);
}

export default function* workgroupSagas() {
    yield all([
        watchFetchWorkgroup(),
        watchCreateBoard(),
        watchDeleteBoard(),
        watchEditBoard()
    ]);
}
