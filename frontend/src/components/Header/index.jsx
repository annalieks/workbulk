import React from 'react';
import styles from './styles.module.sass';
import logo from '../../assets/logo.png';
import {Link} from 'react-router-dom';

const Header = () => (
    <div className={styles.header_container}>
        <Link to="/">
            <img className={styles.logo} src={logo} alt="Workbulk logo"/>
        </Link>
        <div className={styles.controls_container}>
            <Link to="/signin">
                <div className={`${styles.item} ${styles.sign_in}`}>Sign in</div>
            </Link>
            <Link to="/signup">
                <div className={`${styles.item} ${styles.sign_up}`}>Sign up</div>
            </Link>
        </div>
    </div>
);

export default Header;
