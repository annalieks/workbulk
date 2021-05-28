import {fetchWorkgroupRoutine} from './routines';


const initialState = {
    loading: true,
    id: '',
    name: '',
    description: '',
    boards: []
}

const workgroupData = (state = initialState, action) => {
    switch (action.type) {
        case fetchWorkgroupRoutine.TRIGGER: {
            return {
                ...state,
                loading: true,
            }
        }
        case fetchWorkgroupRoutine.SUCCESS: {
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

export default workgroupData;
