import {createRoutine} from 'redux-saga-routines';

export const fetchBoardRoutine = createRoutine('BOARD:FETCH_INFO');

export const addCardRoutine = createRoutine('CARD:ADD');

export const createColumnRoutine = createRoutine('COLUMN:CREATE');
