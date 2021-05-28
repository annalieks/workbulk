import callWebApi from '../../helpers/web.helper';

export const signIn = async (credentials) => {
    const result = await callWebApi({
        endpoint: '/auth/signin',
        type: 'POST',
        request: {
            email: credentials.email,
            password: credentials.password
        }
    });

    return result.json();
};

export const signUp = async (data) => {
    const result = await callWebApi({
        endpoint: '/auth/signup',
        type: 'POST',
        request: {
            email: data.email,
            password: data.password,
            firstName: data.firstName,
            lastName: data.lastName
        }
    });

    return result.json();
};
