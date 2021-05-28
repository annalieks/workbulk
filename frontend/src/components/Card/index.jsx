import React from 'react';
import styles from './styles.module.sass';
import {Link} from "react-router-dom";

const Card = ({id, name, type}) => {
    return (
        <Link to={`/${type}/${id}`}>
            <div className={styles.card_container}>
                <p>{name}</p>
            </div>
        </Link>
    );
}

export default Card;
