import { all, call, put, takeEvery } from 'redux-saga/effects';
import { toastr } from 'react-redux-toastr';
import {
    createBoardRoutine,
    createWorkgroupRoutine,
    deleteBoardRoutine,
    editBoardRoutine,
    fetchBoardsRoutine,
    fetchWorkgroupsRoutine,
    deleteWorkgroupRoutine,
    editWorkgroupRoutine
} from './routines';
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

function* createWorkgroup({ payload }) {
    try {
        yield call(() => service.createWorkgroup(payload.name, payload.description));
        yield put(fetchWorkgroupsRoutine.trigger());
    } catch (error) {
        yield put(createWorkgroupRoutine.failure(error.message));
        toastr.error('Data error', 'Could not create workgroup');
    }
}

function* createBoard({ payload }) {
    try {
        yield call(() => service.createBoard(payload.name, payload.description));
        yield put(fetchBoardsRoutine.trigger());
    } catch (error) {
        yield put(createBoardRoutine.failure(error.message));
        toastr.error('Data error', 'Could not create board');
    }
}

function* deleteWorkgroup({ payload }) {
    try {
        yield call(() => service.deleteWorkgroup(payload.id));
        yield put(fetchWorkgroupsRoutine.trigger());
    } catch (error) {
        yield put(deleteWorkgroupRoutine.failure(error.message));
        toastr.error('Data error', 'Could not delete workgroup');
    }
}

function* editWorkgroup({ payload }) {
    try {
        yield call(() => service.editWorkgroup(payload.id, payload.name, payload.description));
        yield put(fetchWorkgroupsRoutine.trigger());
    } catch (error) {
        yield put(editWorkgroupRoutine.failure(error.message));
        toastr.error('Data error', 'Could not edit workgroup');
    }
}

function* deleteBoard({ payload }) {
    try {
        yield call(() => service.deleteBoard(payload.id));
        yield put(fetchBoardsRoutine.trigger());
    } catch (error) {
        yield put(deleteBoardRoutine.failure(error.message));
        toastr.error('Data error', 'Could not delete board');
    }
}

function* editBoard({ payload }) {
    try {
        yield call(() => service.editBoard(payload.id, payload.name, payload.description));
        yield put(fetchBoardsRoutine.trigger());
    } catch (error) {
        yield put(editBoardRoutine.failure(error.message));
        toastr.error('Data error', 'Could not edit board');
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

function* watchDeleteWorkgroup() {
    yield takeEvery(deleteWorkgroupRoutine.TRIGGER, deleteWorkgroup);
}

function* watchEditWorkgroup() {
    yield takeEvery(editWorkgroupRoutine.TRIGGER, editWorkgroup);
}

function* watchDeleteBoard() {
    yield takeEvery(deleteBoardRoutine.TRIGGER, deleteBoard);
}

function* watchEditBoard() {
    yield takeEvery(editBoardRoutine.TRIGGER, editBoard);
}

export default function* homeSagas() {
    yield all([
        watchFetchWorkgroups(),
        watchFetchBoards(),
        watchCreateWorkgroup(),
        watchCreateBoard(),
        watchDeleteWorkgroup(),
        watchEditWorkgroup(),
        watchDeleteBoard(),
        watchEditBoard()
    ]);
}
