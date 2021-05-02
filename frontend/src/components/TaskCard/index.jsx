import React from 'react';
import styles from './styles.module.sass';

const TaskCard = ({id, name}) => {
    // current card as state
    return (
        <div className={styles.card_container}>
            <p>{name}</p>
        </div>
    );
}

export default TaskCard;
