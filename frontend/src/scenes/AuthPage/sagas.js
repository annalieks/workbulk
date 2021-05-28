import { all, call, put, takeEvery } from 'redux-saga/effects';
import { toastr } from 'react-redux-toastr';
import {signInRoutine, signUpRoutine} from './routines';
import {history} from '../../helpers/history.helper';
import * as service from './service';
import * as token from  '../../helpers/token.helper';

function* signIn({ payload }) {
    try {
        const response = yield call(() => service.signIn(payload));
        token.setToken(response.token);
        yield put(signInRoutine.success(response.user));
        history.push('/');
    } catch (error) {
        token.clearToken();
        yield put(signInRoutine.failure(error.message));
        toastr.error('Login error', 'Please, check your credentials');
    }
}

function* signUp({ payload }) {
    try {
        const response = yield call(() => service.signUp(payload));
        token.setToken(response.token);
        yield put(signUpRoutine.success(response.user));
        history.push('/');
    } catch (error) {
        token.clearToken();
        yield put(signUpRoutine.failure(error.message));
        toastr.error('Register error', 'Could not register new user');
    }
}

function* watchSignIn() {
    yield takeEvery(signInRoutine.TRIGGER, signIn);
}

function* watchSignUp() {
    yield takeEvery(signUpRoutine.TRIGGER, signUp);
}

export default function* authSagas() {
    yield all([
        watchSignIn(),
        watchSignUp(),
    ]);
}
