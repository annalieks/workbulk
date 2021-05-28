import callWebApi from '../../helpers/web.helper';

export const fetchWorkgroupInfo = async (id) => {
    const result = await callWebApi({
        endpoint: `/workgroup/${id}`,
        type: 'GET'
    });

    return result.json();
};
