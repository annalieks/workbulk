import {fetchBoardsRoutine, fetchWorkgroupsRoutine} from "./routines";

const initialState = {
    workgroupsLoading: true,
    boardsLoading: true,
    workgroups: [],
    boards: [],
}

const homeData = (state = initialState, action) => {
    switch (action.type) {
        case fetchBoardsRoutine.SUCCESS:
            return {
                ...state,
                boardsLoading: false,
                boards: action.payload
            };
        case fetchBoardsRoutine.FAILURE:
            return {
                ...state,
                boards: [],
                boardsLoading: false,
            }
        case fetchWorkgroupsRoutine.SUCCESS:
            return {
                ...state,
                workgroupsLoading: false,
                workgroups: action.payload
            };
        case fetchWorkgroupsRoutine.FAILURE:
            return {
                ...state,
                workgroups: [],
                workgroupsLoading: false,
            }
        default: {
            return state;
        }
    }
}

export default homeData;
