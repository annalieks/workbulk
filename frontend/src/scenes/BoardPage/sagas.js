import {all, call, put, takeEvery} from 'redux-saga/effects';
import {toastr} from 'react-redux-toastr';
import {addCardRoutine, fetchBoardRoutine} from './routines';
import * as service from './service';

function* fetchBoardInfo({payload}) {
    try {
        const response = yield call(() => service.fetchBoardInfo(payload));
        yield put(fetchBoardRoutine.success(response));
    } catch (error) {
        yield put(fetchBoardRoutine.failure(error.message));
        toastr.error('Data error', 'Could not fetch board info');
    }
}

function* createCard({payload}) {
    try {
        yield call(() => service.addCard(payload.id, payload.text));
        yield put(fetchBoardRoutine.trigger(payload.boardId));
    } catch (error) {
        yield put(fetchBoardRoutine.failure(error.message));
        toastr.error('Card error', 'Could not add new card');
    }
}

function* watchFetchBoard() {
    yield takeEvery(fetchBoardRoutine.TRIGGER, fetchBoardInfo);
}

function* watchAddCard() {
    yield takeEvery(addCardRoutine.TRIGGER, createCard);
}

export default function* boardSagas() {
    yield all([
        watchFetchBoard(),
        watchAddCard(),
    ]);
}
