import React from 'react';
import styles from './styles.module.sass';

// eslint-disable-next-line no-unused-vars
const TaskCard = ({id, text}) => {
    // current card as state
    return (
        <div className={styles.card_container}>
            <p>{text}</p>
        </div>
    );
}

export default TaskCard;
