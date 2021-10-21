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

export const editWorkgroup = async (workgroupId, name, description) => {
    const result = await callWebApi({
        endpoint: `/workgroup/${workgroupId}`,
        type: 'PUT',
        request: {
            name,
            description
        }
    })

    return result.json();
}

export const deleteWorkgroup = async (workgroupId) => {
    await callWebApi({
        endpoint: `/workgroup/${workgroupId}`,
        type: 'DELETE'
    });
}

export const editBoard = async (boardId, name, description) => {
    const result = await callWebApi({
        endpoint: `/board/${boardId}`,
        type: 'PUT',
        request: {
            name,
            description
        }
    })

    return result.json();
}

export const deleteBoard = async (boardId) => {
    await callWebApi({
        endpoint: `/board/${boardId}`,
        type: 'DELETE'
    });
}