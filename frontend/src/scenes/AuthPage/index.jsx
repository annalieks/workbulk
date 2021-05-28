import React, {useState} from 'react';
import styles from './styles.module.sass';
import {Form, Input} from 'semantic-ui-react';
import {signInRoutine, signUpRoutine} from './routines';
import {connect} from 'react-redux';
import ScreenLoader from "../../components/ScreenLoader";

const AuthPage = ({isSignUp, signIn, signUp, loading}) => {

    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
        if (isSignUp) {
            signUp({firstName, lastName, email, password});
        } else {
            signIn({email, password});
        }
    }

    return loading
        ? <ScreenLoader />
        : (
        <div className={styles.auth_container}>
            <Form className={styles.form_container}>
                <div className={styles.title}>{isSignUp ? 'Sign Up' : 'Sign In'}</div>
                {isSignUp &&
                <Input
                    placeholder={'First Name'}
                    onChange={(e) => setFirstName(e.target.value)}
                />}
                {isSignUp &&
                <Input
                    placeholder={'Last Name'}
                    onChange={(e) => setLastName(e.target.value)}
                />}
                <Input
                    placeholder={'Email'}
                    onChange={(e) => setEmail(e.target.value)}
                />
                <Form.Input
                    placeholder='Password'
                    type='password'
                    onChange={(e) => setPassword(e.target.value)}
                />
                <button
                    type={'submit'}
                    className={styles.submit_btn}
                    onClick={(e) => handleSubmit(e)}
                >
                    Submit
                </button>
                <p className={styles.recommendation}>
                    {isSignUp ? 'Already have an account? ' : 'Don\'t have an account yet? '}
                    <a href={isSignUp ? '/signin' : '/signup'}>
                        {isSignUp ? 'Sign in' : 'Sign up'}
                    </a>
                </p>
            </Form>
        </div>
    );
}

const mapStateToProps = (state) => ({
    loading: state.auth.loading,
});

const mapDispatchToProps = {
    signIn: signInRoutine,
    signUp: signUpRoutine
}

export default connect(mapStateToProps, mapDispatchToProps)(AuthPage);
