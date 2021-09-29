import { all, call, put, takeEvery } from 'redux-saga/effects';
import { toastr } from 'react-redux-toastr';
import { addCardRoutine, createColumnRoutine, fetchBoardRoutine } from './routines';
import * as service from './service';

function* fetchBoardInfo({ payload }) {
    try {
        const response = yield call(() => service.fetchBoardInfo(payload));
        yield put(fetchBoardRoutine.success(response));
    } catch (error) {
        yield put(fetchBoardRoutine.failure(error.message));
        toastr.error('Data error', 'Could not fetch board info');
    }
}

function* createCard({ payload }) {
    try {
        const result = yield call(() => service.addCard(payload.id, payload.text));
        console.log(result)
        yield put(fetchBoardRoutine.trigger(payload.boardId));
    } catch (error) {
        yield put(fetchBoardRoutine.failure(error.message));
        toastr.error('Card error', 'Could not add new card');
    }
}

function* createColumn({ payload }) {
    try {
        const result = yield call(() => service.createColumn(payload.id, payload.name));
        yield put(createColumnRoutine.success(result));
    } catch (error) {
        yield put(createColumnRoutine.failure(error.message));
        toastr.error('Column error', 'Could not create new column');
    }
}

function* watchFetchBoard() {
    yield takeEvery(fetchBoardRoutine.TRIGGER, fetchBoardInfo);
}

function* watchAddCard() {
    yield takeEvery(addCardRoutine.TRIGGER, createCard);
}

function* watchCreateColumn() {
    yield takeEvery(createColumnRoutine.TRIGGER, createColumn);
}

export default function* boardSagas() {
    yield all([
        watchFetchBoard(),
        watchAddCard(),
        watchCreateColumn()
    ]);
}
