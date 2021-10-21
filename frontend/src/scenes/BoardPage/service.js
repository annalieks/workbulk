import callWebApi from '../../helpers/web.helper';

export const fetchBoardInfo = async (id) => {
    const result = await callWebApi({
        endpoint: `/board/${id}`,
        type: 'GET'
    });

    return result.json();
};

export const addCard = async (columnId, text) => {
    const result = await callWebApi({
        endpoint: `/card/create?column-id=${columnId}`,
        type: 'POST',
        request: {
            text
        }
    });

    return result.json();
};

export const createColumn = async (boardId, name) => {
    const result = await callWebApi({
        endpoint: `/column/create?board-id=${boardId}`,
        type: 'POST',
        request: {
            name
        }
    });

    return result.json();
}