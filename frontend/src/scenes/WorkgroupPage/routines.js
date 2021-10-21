import {createRoutine} from 'redux-saga-routines';

export const fetchWorkgroupRoutine = createRoutine('WORKGROUP:FETCH_INFO');
export const createBoardRoutine = createRoutine('WORKGROUP:CREATE_BOARD');
export const deleteBoardRoutine = createRoutine('WORKGROUP:DELETE_BOARD');
export const editBoardRoutine = createRoutine('WORKGROUP:EDIT_BOARD');

