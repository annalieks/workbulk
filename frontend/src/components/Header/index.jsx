import React from 'react';
import styles from './styles.module.sass';
import logo from '../../assets/logo.png';
import {Link, useLocation} from 'react-router-dom';
import {TOKEN_NAME} from '../../commons/constants';
import {history} from '../../helpers/history.helper';
import * as tokenHelper from '../../helpers/token.helper';

const excludeControls = ['/signin', '/signup']

const Header = () => {

    const location = useLocation();
    const authenticated = localStorage.getItem(TOKEN_NAME);
    const renderControls = () => !excludeControls.includes(location.pathname) && !authenticated;

    const signOut = () => {
        tokenHelper.clearToken();
        history.push('/');
    }

    return (
        <div className={styles.header_container}>
            <Link to="/">
                <img className={styles.logo} src={logo} alt="Workbulk logo"/>
            </Link>
            <div className={styles.controls_container}>
                {renderControls() && <Link to="/signin">
                    <div className={`${styles.item} ${styles.sign_in}`}>Sign in</div>
                </Link>}
                {renderControls() && <Link to="/signup">
                    <div className={`${styles.item} ${styles.sign_up}`}>Sign up</div>
                </Link>}
                {authenticated && <button
                    type={'button'}
                    className={`${styles.item} ${styles.sign_out}`}
                    onClick={() => signOut()}
                >
                    Sign out
                </button>}
            </div>
        </div>
    );
}

export default Header;
