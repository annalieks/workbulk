import * as queryString from 'query-string';
import {TOKEN_NAME} from '../commons/constants';

export const serverAddress = 'https://workbulk-back.eastus.azurecontainer.io:443';
// export const serverAddress = 'http://localhost:7070';

function getFetchUrl(args) {
    return serverAddress + args.endpoint + (args.query ? `?${queryString.stringify(args.query)}` : '');
}

function getFetchArgs(args) {
    const headers = {};
    if (!args.attachment) {
        headers['Content-Type'] = 'application/json';
        headers.Accept = 'application/json';
    }
    const token = localStorage.getItem(TOKEN_NAME);
    if (token && !args.skipAuthorization) {
        headers.Authorization = `Bearer ${token}`;
    }
    let body;
    if (args.attachment) {
        if (args.type === 'GET') {
            throw new Error('GET request does not support attachments.');
        }
        // headers['Content-Type'] = 'multipart/form-data';
        const formData = new FormData();
        formData.append('image', args.attachment);
        body = formData;
    } else if (args.request) {
        if (args.type === 'GET') {
            throw new Error('GET request does not support request body.');
        }
        body = JSON.stringify(args.request);
    }
    return {
        method: args.type,
        headers,
        signal: args.ct,
        ...(args.request === 'GET' ? {} : {body})
    };
}

export async function throwIfResponseFailed(res) {
    if (!res.ok) {
        let parsedException = 'Something went wrong with request!';
        try {
            parsedException = await res.json();
        } catch (err) {
            //
        }
        throw parsedException;
    }
}

export default async function callWebApi(args) {
    const res = await fetch(
        getFetchUrl(args),
        getFetchArgs(args)
    );
    await throwIfResponseFailed(res);
    return res;
}
