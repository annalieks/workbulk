import React from 'react';
import styles from './styles.module.sass';
import {Form, Input} from 'semantic-ui-react';

const AuthPage = ({ isSignup }) => (
    <div className={styles.auth_container}>
        <Form className={styles.form_container}>
            <div className={styles.title}>{isSignup ? 'Sign Up' : 'Sign In'}</div>
            {isSignup && <Input placeholder={'First Name'}/>}
            {isSignup && <Input placeholder={'Last Name'}/>}
            <Input placeholder={'Email'}/>
            <Form.Input placeholder='Password' type='password' />
            <button type={'submit'} className={styles.submit_btn}>Submit</button>
            <p className={styles.recommendation}>
                {isSignup ? 'Already have an account? ' : 'Don\'t have an account yet? '}
                <a href={isSignup ? '/signin' : '/signup'}>
                    {isSignup ? 'Sign in' : 'Sign up'}
                </a>
            </p>
        </Form>
    </div>
);

export default AuthPage;
