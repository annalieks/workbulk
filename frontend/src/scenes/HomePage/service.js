import callWebApi from '../../helpers/web.helper';

export const fetchWorkgroups = async () => {
    const result = await callWebApi({
        endpoint: '/workgroup/all',
        type: 'GET'
    });

    return result.json();
};

export const fetchBoards = async () => {
    const result = await callWebApi({
        endpoint: '/board/all',
        type: 'GET'
    });

    return result.json();
};

export const createWorkgroup = async (name, description) => {
    const result = await callWebApi({
        endpoint: '/workgroup/create',
        type: 'POST',
        request: {
            name,
            description
        }
    });

    return result.json();
}

export const createBoard = async (name, description) => {
    const result = await callWebApi({
        endpoint: '/board/create',
        type: 'POST',
        request: {
            name,
            description
        }
    });

    return result.json();
}
