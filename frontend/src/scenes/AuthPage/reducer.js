import {signInRoutine, signUpRoutine} from "./routines";

const initialState = {
    loading: false,
    fistName: '',
    lastName: '',
    email: '',
    avatar: '',
}

const authData = (state = initialState, action) => {
    switch (action.type) {
        case signUpRoutine.TRIGGER:
        case signInRoutine.TRIGGER:
            return {
                ...state,
                loading: true,
            }
        case signUpRoutine.SUCCESS:
        case signInRoutine.SUCCESS:
            return {
                loading: false,
                ...action.payload,
            }
            
        case signUpRoutine.FAILURE:
        case signInRoutine.FAILURE:
            return {
                ...state,
                loading: false,
            }
        default: {
            return state;
        }
    }
}

export default authData;
