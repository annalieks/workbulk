import {createColumnRoutine, fetchBoardRoutine} from "./routines";

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
        case createColumnRoutine.SUCCESS: {
            return {
                ...state,
                columns: [...state.columns, action.payload]
            }
        } 
        default: {
            return state;
        }
    }
}

export default boardData;
