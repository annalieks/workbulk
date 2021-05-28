import {fetchBoardRoutine} from "./routines";

const initialState = {
    loading: true,
    name: '',
    description: '',
    columns: []
}

const boardData = (state = initialState, action) => {
    switch (action.type) {
        case fetchBoardRoutine.TRIGGER: {
            return {
                ...state,
                loading: true,
            }
        }
        case fetchBoardRoutine.SUCCESS: {
            return {
                ...state,
                ...action.payload,
                loading: false
            }
        }
        default: {
            return state;
        }
    }
}

export default boardData;
