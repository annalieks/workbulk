import {createRoutine} from 'redux-saga-routines';

export const fetchWorkgroupsRoutine = createRoutine('HOME:FETCH_WORKGROUPS');
export const fetchBoardsRoutine = createRoutine('HOME:FETCH_BOARDS');
export const createWorkgroupRoutine = createRoutine('HOME:CREATE_WORKGROUP');
export const createBoardRoutine = createRoutine('HOME:CREATE_BOARD');
