import callWebApi from '../../helpers/web.helper';

export const fetchWorkgroupInfo = async (id) => {
    const result = await callWebApi({
        endpoint: `/workgroup/${id}`,
        type: 'GET'
    });

    return result.json();
};

export const createBoard = async (workgroupId, name, description) => {
    const result = await callWebApi({
        endpoint: `/workgroup/${workgroupId}/board`,
        type: 'POST',
        request: {
            name,
            description
        }
    })

    return result.json();
}

export const editBoard = async (workgroupId, id, name, description) => {
    const result = await callWebApi({
        endpoint: `/workgroup/${workgroupId}/board/${id}`,
        type: 'PUT',
        request: {
            name,
            description
        }
    })

    return result.json();
}

export const deleteBoard = async (workgroupId, id) => {
    await callWebApi({
        endpoint: `/workgroup/${workgroupId}/board/${id}`,
        type: 'DELETE'
    })
}

