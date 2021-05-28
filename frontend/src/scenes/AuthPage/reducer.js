import {signInRoutine, signUpRoutine} from "./routines";

const initialState = {
    loading: false,
    fistName: '',
    lastName: '',
    email: ''
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
        default: {
            return state;
        }
    }
}

export default authData;
